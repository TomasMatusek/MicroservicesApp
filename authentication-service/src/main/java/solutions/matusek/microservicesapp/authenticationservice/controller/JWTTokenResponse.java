package solutions.matusek.microservicesapp.authenticationservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JWTTokenResponse {
    private final String token;
}
