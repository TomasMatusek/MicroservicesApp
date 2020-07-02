package solutions.matusek.microservicesapp.authenticationservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class JWTTokenResponse {
    private final String token;
}
