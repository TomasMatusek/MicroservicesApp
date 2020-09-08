package solutions.matusek.microservicesapp.authenticationservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import solutions.matusek.microservicesapp.authenticationservice.config.CertificateProperties;
import solutions.matusek.microservicesapp.authenticationservice.config.KeyStoreProperties;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class EncryptionServiceTest {

    @Autowired
    private EncryptionService encryptionService;

    @Test
    public void When_ServiceInitialized_Then_CertificateConfigurationFromYmlMatch() {
        CertificateProperties config = ReflectionTestUtils.invokeMethod(encryptionService, "getCertificateConfig");
        Assertions.assertNotNull(config);
        Assertions.assertEquals("matusek.solutions", config.getAlias());
        Assertions.assertEquals("aaa111", config.getPassword());
        Assertions.assertEquals("RSA", config.getAlgorithm());
        Assertions.assertEquals(2048, config.getKeySize());
    }

    @Test
    public void When_ServiceInitialized_Then_KeyStoreConfigurationFromYmlMatch() {
        KeyStoreProperties config = ReflectionTestUtils.invokeMethod(encryptionService, "getKeyStoreConfig");
        Assertions.assertNotNull(config);
        Assertions.assertEquals(config.getFilePath(), "/Users/matusek/Workplace/MatusekSolutions/MicroservicesApp/authentication-service/src/test/resources/test-keystore");
        Assertions.assertEquals(config.getPassword(), "aaa111");
        Assertions.assertEquals(config.getType(), "PKCS12");
    }

    @Test
    public void When_EncryptionParameterMethodIsNull_Then_EmptyStringExpected() {
        byte[] encrypted = encryptionService.encrypt(null);
        Assertions.assertArrayEquals(new byte[0], encrypted);
    }

    @Test
    public void When_StringIsEncryptedAndDecryptedAgain_Then_StringsMustMatch() {
        final String text = "Test123!";
        byte[] encrypted = encryptionService.encrypt(text.getBytes());
        byte[] decrypted = encryptionService.decrypt(encrypted);
        Assertions.assertEquals(text, new String(decrypted));
    }

    @Test
    public void When_SameEncodedValueAreBase64Encoded_Then_ExpectedOutputIsSame() {
        byte[] data = { 18, 43, 61, 25, 127, 43, 12, 41, 52};
        String attempt1 = EncryptionService.toBase64String(data);
        String attempt2 = EncryptionService.toBase64String(data);
        Assertions.assertEquals(attempt1, attempt2);
    }

    @Test
    public void When_OriginalValueIsEncodedAndThenDecoded_Then_DataShouldMatchOriginal() {
        byte[] data = { 18, 43, 61, 25, 127, 43, 12, 41, 52 };
        String base64EncodedString = EncryptionService.toBase64String(data);
        byte[] data2 = EncryptionService.fromBase64String(base64EncodedString);
        Assertions.assertArrayEquals(data, data2);
    }
}