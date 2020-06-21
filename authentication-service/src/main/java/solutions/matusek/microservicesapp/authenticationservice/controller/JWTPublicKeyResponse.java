package solutions.matusek.microservicesapp.authenticationservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JWTPublicKeyResponse {
    private final String[] publicKeys;
}
