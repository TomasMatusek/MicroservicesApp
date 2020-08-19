package solutions.matusek.microservicesapp.authenticationservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// @TODO validate values, like password must be non null, type must be enum of alowed values

@Setter
@Getter
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("solutions.matusek.certificate")
public class CertificateConfig {
    private String alias;
    private String password;
    private String type;

    @Override
    public String toString() {
        return "CertificateConfig{" +
                "alias='" + alias + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}