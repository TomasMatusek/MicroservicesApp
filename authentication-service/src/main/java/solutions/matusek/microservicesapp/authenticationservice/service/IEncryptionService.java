package solutions.matusek.microservicesapp.authenticationservice.service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IEncryptionService {
    String encryptData(String data);
    String decryptData(String encryptedData);
}