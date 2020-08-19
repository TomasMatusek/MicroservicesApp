package solutions.matusek.microservicesapp.authenticationservice.service;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solutions.matusek.microservicesapp.authenticationservice.config.CertificateConfig;
import solutions.matusek.microservicesapp.authenticationservice.config.KeyStoreConfig;
import solutions.matusek.microservicesapp.authenticationservice.service.exception.DataDecryptionException;
import solutions.matusek.microservicesapp.authenticationservice.service.exception.DataEncryptionException;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;

/**
 * Initialization of KeyStore to get keys used to encrypt / decrypt sensitive data from database.
 * Reading and writing of keys from database using keystore pair.
 */
@Service
public class EncryptionService implements IEncryptionService {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private final KeyStoreConfig keyStoreConfig;
    private final CertificateConfig certificateConfig;

    private static final String CIPHER_TRANSFORMATION = "AES/ECB/PKCS5Padding";

    @Autowired
    public EncryptionService(KeyStoreConfig keyStoreConfig, CertificateConfig certificateConfig) {
        this.keyStoreConfig = keyStoreConfig;
        this.certificateConfig = certificateConfig;
    }

    @PostConstruct
    public void init() {
        try {
            // Init access to key store where key pair for encryption is stored
            File keyStoreFile = new File(keyStoreConfig.getFilePath());
            KeyStore keyStore = KeyStore.getInstance(keyStoreConfig.getType());
            keyStore.load(new FileInputStream(keyStoreFile), keyStoreConfig.getPassword().toCharArray());
            // Get actual keys
            publicKey = keyStore.getCertificate(certificateConfig.getAlias()).getPublicKey();
            privateKey = (PrivateKey) keyStore.getKey(certificateConfig.getAlias(), certificateConfig.getPassword().toCharArray());
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException e) {
            throw new BeanInitializationException(String.format("Failed to initialize key store. %s %s", keyStoreConfig, certificateConfig), e);
        }
    }

    /**
     * Encrypt given string.
     * @param data String to be encrypted.
     * @throws DataEncryptionException When encryption failed.
     * @return Encrypted data in form of string.
     */
    @Override
    public String encryptData(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(getPublicKey().getEncoded(), certificateConfig.getType());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Arrays.toString(cipher.doFinal(data.getBytes()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new DataEncryptionException(String.format("Failed to encrypt data. Cipher: %s. Mode: %s. Certificate: %s.", CIPHER_TRANSFORMATION, Cipher.ENCRYPT_MODE, certificateConfig), e);
        }
    }

    /**
     * Decrypt given string.
     * @param encryptedData Data encrypted by encrypt method.
     * @throws DataDecryptionException When decryption failed.
     * @return Decrypted plan text data
     * in form of string.
     */
    @Override
    public String decryptData(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(getPrivateKey().getEncoded(), certificateConfig.getType());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return Arrays.toString(cipher.doFinal(encryptedData.getBytes()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new DataEncryptionException(String.format("Failed to decrypt data. Cipher: %s. Mode: %s. Certificate: %s.", CIPHER_TRANSFORMATION, Cipher.DECRYPT_MODE, certificateConfig), e);
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    private PrivateKey getPrivateKey() {
        return privateKey;
    }
}