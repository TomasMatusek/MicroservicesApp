package solutions.matusek.microservicesapp.authenticationservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solutions.matusek.microservicesapp.authenticationservice.config.JWTConfig;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class AuthorizationService {

    private final JWTConfig jwtConfig;
    private final Algorithm algorithm;

    @Autowired
    public AuthorizationService(KeyPair keyPair, JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.algorithm = Algorithm.RSA512((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
    }

    public JWTToken authenticateUsingPassword(String username, String password) {
        return null;
    }

    public JWTToken generateTokenUsingRefreshToken(String refreshToken) {
        return null;
    }

    public void invalidateExistingJWTToken(String jwtToken) {

    }

    public String createJWT() {
        return JWT.create()
                .withClaim("email", "tomas.matusek@hotmail.co.uk")
                .withExpiresAt(new Date(getExpiresAtInMilliseconds()))
                .withIssuer(jwtConfig.getIssuer())
                .sign(getAlgorithm());
    }

    public DecodedJWT decodeJWT(String token) {
        return JWT.decode(token);
    }

    // JWT hash must no be none
    // public key must not be accepted
    public boolean isJWTValid(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }

        if (getAlgorithm() == null) {
            throw new IllegalArgumentException("FUCK");
        }

        try {
            JWTVerifier verifier = JWT.require(getAlgorithm())
                    .withIssuer(jwtConfig.getIssuer())
                    .build();
            return verifier.verify(token) != null;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    private Algorithm getAlgorithm() {
        return algorithm;
    }

    private long getExpiresAtInMilliseconds() {
        return System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(jwtConfig.getExpirationInSeconds());
    }
}