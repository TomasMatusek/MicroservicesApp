package solutions.matusek.microservicesapp.authenticationservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import solutions.matusek.microservicesapp.authenticationservice.config.CertificateConfig;
import solutions.matusek.microservicesapp.authenticationservice.config.KeyStoreConfig;

@MockBean(EncryptionService.class)
@MockBean(CertificateConfig.class)
@MockBean(KeyStoreConfig.class)
@SpringBootTest
public class EncryptionServiceTest {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private CertificateConfig certificateConfig;

    @Autowired
    private KeyStoreConfig keyStoreConfig;

    @Test
    public void When_Then() {
        Assertions.assertNotNull(certificateConfig.getAlias());
    }

    @Test
    public void When_encryptionServiceInitialized_Then_publicKeyIsNoEmpty() {
        Assertions.assertNotNull(encryptionService.getPublicKey());
    }

    @Test
    public void When_encryptionServiceInitialized_Then_privateKeyIsNoEmpty() {
        Assertions.assertNotNull(encryptionService.getPublicKey());
        Assertions.assertNotNull(ReflectionTestUtils.invokeMethod(encryptionService, "getPrivateKey"));
    }
}