package solutions.matusek.microservicesapp.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.matusek.microservicesapp.authenticationservice.service.JWTToken;
import solutions.matusek.microservicesapp.authenticationservice.service.JWTTokenService;

import javax.ws.rs.core.Response;

@RestController
public class AuthenticationController {

    private final JWTTokenService jwtTokenService;

    @Autowired
    public AuthenticationController(JWTTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @ResponseBody
    @PostMapping("authenticate/username-password")
    public ResponseEntity<JWTTokenResponse> usernamePasswordAuthentication(
            @RequestParam(name = "password", defaultValue = "") String password,
            @RequestParam(name = "username", defaultValue = "") String username) {
        JWTToken jwtToken = jwtTokenService.authenticate(username, password);
        return ResponseEntity.ok(new JWTTokenResponse(jwtToken.getToken()));
    }

    @ResponseBody
    @GetMapping("/jwt/public-key")
    public ResponseEntity<JWTPublicKeyResponse> getPublicKey() {
        // multiple keys can be specified so if we want to replace old one we add new so it is valid for services that did not reloaded key
        // so JWT is validate by one, if not valid then it can try old new
        // once all services reload its keys we can remove old one
        String[] keys = { "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIAcBB4zmTsYq2F8ZqbNfF+oIFQdCWyZYLnCKIRX49oMa tomas.matusek@hotmail.co.uk" };
        return ResponseEntity.ok(new JWTPublicKeyResponse(keys));
    }
}