package solutions.matusek.microservicesapp.authenticationservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("solutions.matusek.certificate")
public class CertificateProperties {

    private String alias;
    private String password;
    private String algorithm;
    private int keySize;

    @Override
    public String toString() {
        return "SecretKeyProperties{" +
                "alias='" + alias + '\'' +
                ", algorithm='" + algorithm + '\'' +
                ", keySize=" + keySize +
                '}';
    }
}