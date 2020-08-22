package solutions.matusek.microservicesapp.authenticationservice.service;

public interface IEncryptionService {
    byte[] encrypt(byte[] data);
    byte[] decrypt(byte[] encryptedData);
}