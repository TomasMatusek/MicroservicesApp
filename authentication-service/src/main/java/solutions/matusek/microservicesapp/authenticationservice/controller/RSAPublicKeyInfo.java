package solutions.matusek.microservicesapp.authenticationservice.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class RSAPublicKeyInfo {
    private final String algorithm;
    private final String key;
}