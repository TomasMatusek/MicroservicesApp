package solutions.matusek.microservicesapp.authenticationservice.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JWTToken {
    private final String token;
}
