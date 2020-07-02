package solutions.matusek.microservicesapp.authenticationservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("solutions.matusek.keyStore")
public class KeyStoreConfig {
    private String filePath;
    private String password;
    private String type;
}