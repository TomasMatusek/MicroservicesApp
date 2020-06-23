package solutions.matusek.microservicesapp.authenticationservice.config;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Configuration
public class JWTKeyPairConfig {

    private final JWTConfig jwtConfig;

    @Autowired
    public JWTKeyPairConfig(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public KeyPair keyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(jwtConfig.getAlgorithm());
            keyPairGenerator.initialize(jwtConfig.getKeySize());
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new BeanInitializationException(String.format("Failed to generate key pair using %s algorithm with key size of %s bits", jwtConfig.getAlgorithm(), jwtConfig.getKeySize()));
        }
    }
}