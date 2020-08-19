package solutions.matusek.microservicesapp.authenticationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ConfigBeans {

    private final static String CERTIFICATE_CONFIG_ALIAS = "matusek.solutions";
    private final static String CERTIFICATE_CONFIG_PASSWORD = "aaa111";
    private final static String CERTIFICATE_CONFIG_TYPE = "RSA";

    private final static String KEY_STORE_CONFIG_FILE_PATH = "/Users/matusek/Workplace/MatusekSolutions/MicroservicesApp/authentication-service/src/test/resources/test-keystore";
    private final static String KEY_STORE_CONFIG_PASSWORD = "Soonr123";
    private final static String KEY_STORE_CONFIG_TYPE = "JKS";

    @Bean
    @Primary
    public CertificateConfig certificateConfig() {
        return new CertificateConfig(CERTIFICATE_CONFIG_ALIAS, CERTIFICATE_CONFIG_PASSWORD, CERTIFICATE_CONFIG_TYPE);
    }

    @Bean
    @Primary
    public KeyStoreConfig keyStoreConfig() {
        return new KeyStoreConfig(KEY_STORE_CONFIG_FILE_PATH, KEY_STORE_CONFIG_PASSWORD, KEY_STORE_CONFIG_TYPE);
    }
}