package solutions.matusek.microservicesapp.authenticationservice.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {
    private String issuer = "unknown";
    private String algorithm = "RSA";
    private int keySize = 2048;
    private int expirationInSeconds = 3600;
}
