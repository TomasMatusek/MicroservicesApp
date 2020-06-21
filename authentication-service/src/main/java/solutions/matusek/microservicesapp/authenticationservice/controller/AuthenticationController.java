package solutions.matusek.microservicesapp.authenticationpasswordservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @ResponseBody
    @PostMapping("/authenticate-user")
    public ResponseEntity<JWTTokenResponse>
}

