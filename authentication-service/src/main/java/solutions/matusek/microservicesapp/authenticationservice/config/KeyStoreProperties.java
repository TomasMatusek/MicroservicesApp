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
@ConfigurationProperties("solutions.matusek.key-store")
public class KeyStoreProperties {

    private String filePath;
    private String password;
    private String type;

    @Override
    public String toString() {
        return "KeyStoreConfig{" +
                "filePath='" + filePath + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}