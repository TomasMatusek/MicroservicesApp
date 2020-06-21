package solutions.matusek.microservicesapp.authenticationservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Service
public class JWTTokenService {

    private Algorithm algorithm;
    private final int KEY_SIZE = 2048;
    private final String ALGORITHM = "RSA";

    @PostConstruct
    private void init() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            algorithm = Algorithm.RSA512((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    
    public JWTToken authenticate(String username, String password) {

        // store secret encrypted in DB using certificate
        // JWT hash must no be none
        // public key must not be accepterepom
        // push generated public ket to all services, each service should have queue new key at top, older at bottom, size of 5 items max

        String jwtToken = createJWT();

        return new JWTToken(jwtToken);
    }

    public String createJWT() {
        return JWT.create()
                .withClaim("email", "tomas.matusek@hotmail.co.uk")
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600))
                .withIssuer("solutions.matusek")
                .sign(getAlgorithm());
    }

    public DecodedJWT decodeJWT(String token) {
        return JWT.decode(token);
    }

    public boolean isJWTValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("solutions.matusek")
                    .build();
            return verifier.verify(token) != null;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}