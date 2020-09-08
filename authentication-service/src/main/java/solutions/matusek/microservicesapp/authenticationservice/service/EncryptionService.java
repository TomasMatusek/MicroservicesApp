package solutions.matusek.microservicesapp.authenticationservice.service;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solutions.matusek.microservicesapp.authenticationservice.config.CertificateProperties;
import solutions.matusek.microservicesapp.authenticationservice.config.KeyStoreProperties;
import solutions.matusek.microservicesapp.authenticationservice.service.exception.DataDecryptionException;
import solutions.matusek.microservicesapp.authenticationservice.service.exception.DataEncryptionException;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Initialization of KeyStore to get keys used to encrypt / decrypt sensitive data from database.
 * Reading and writing of keys from database using keystore pair.
 */
@Service
public class EncryptionService implements IEncryptionService {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private final KeyStoreProperties keyStoreProperties;
    private final CertificateProperties certificateProperties;

    private static final String CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    @Autowired
    public EncryptionService(KeyStoreProperties keyStoreProperties, CertificateProperties certificateProperties) {
        this.keyStoreProperties = keyStoreProperties;
        this.certificateProperties = certificateProperties;
    }

    @PostConstruct
    void init() {
        try {
            // Init access to key store where key pair for encryption is stored
            File keyStoreFile = new File(keyStoreProperties.getFilePath());
            KeyStore keyStore = KeyStore.getInstance(keyStoreProperties.getType());
            keyStore.load(new FileInputStream(keyStoreFile), keyStoreProperties.getPassword().toCharArray());
            // Get actual keys for encryption / decryption
            publicKey = keyStore.getCertificate(certificateProperties.getAlias()).getPublicKey();
            privateKey = (PrivateKey) keyStore.getKey(certificateProperties.getAlias(), certificateProperties.getPassword().toCharArray());
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException e) {
            throw new BeanInitializationException(String.format("Failed to initialize key store. %s %s", keyStoreProperties, certificateProperties), e);
        }
    }

    /**
     * Encrypt given data.
     * Maximum data encrypted is (2048 bits RSA key / 8 bit) => 256 bytes - 11 bytes for padding = 245 bytes
     * @throws DataEncryptionException When encryption failed.
     */
    @Override
    public byte[] encrypt(byte[] data) {
        if (data == null)
            return new byte[0];

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            cipher.init(Cipher.PUBLIC_KEY, getPublicKey());
            return cipher.doFinal(data);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new DataEncryptionException(String.format("Failed to encrypt data. Cipher: %s. Mode: %s. Certificate: %s.", CIPHER_TRANSFORMATION, Cipher.ENCRYPT_MODE, certificateProperties), e);
        }
    }

    /**
     * Decrypt given string.
     * @param encryptedData Data encrypted by encrypt method.
     * @throws DataDecryptionException When decryption failed.
     * @return Decrypted plan text data in form of string.
     */
    @Override
    public byte[] decrypt(byte[] encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            cipher.init(Cipher.PRIVATE_KEY, getPrivateKey());
            return cipher.doFinal(encryptedData);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new DataEncryptionException(String.format("Failed to decrypt data. Cipher: %s. Mode: %s. Certificate: %s.", CIPHER_TRANSFORMATION, Cipher.DECRYPT_MODE, certificateProperties), e);
        }
    }

    /**
     * Uses binary-to-text encoding schemes that represent binary data in an ASCII string format.
     * @param data Raw data.
     * @return Base64 encoded data in form of string.
     */
    public static String toBase64String(byte[] data) {
        return Base64.toBase64String(data);
    }

    /**
     * Uses binary-to-text decoding schemes that represent binary data in an ASCII string format.
     * @param base64EncodedString String that is Base64 encoded.
     * @return Raw data.
     */
    public static byte[] fromBase64String(String base64EncodedString) {
        return Base64.decode(base64EncodedString);
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    private PrivateKey getPrivateKey() {
        return privateKey;
    }

    private KeyStoreProperties getKeyStoreConfig() {
        return keyStoreProperties;
    }

    private CertificateProperties getCertificateConfig() {
        return certificateProperties;
    }
}