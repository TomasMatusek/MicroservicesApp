package solutions.matusek.mycroservicesapp.useridentityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserIdentityServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserIdentityServiceApplication.class, args);
	}
}