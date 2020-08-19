package solutions.matusek.microservicesapp.authenticationservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import solutions.matusek.microservicesapp.authenticationservice.config.JWTConfig;
import solutions.matusek.microservicesapp.authenticationservice.controller.UsersListResponse;
import solutions.matusek.microservicesapp.authenticationservice.domain.JWTToken;
import solutions.matusek.microservicesapp.authenticationservice.service.exception.AuthenticationException;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JWTService {

    /*

    private final JWTConfig jwtConfig;
    private final Algorithm algorithm;
    private final RestTemplate restTemplate;
    private final EncryptionService;



    read key from database if does not exists crete new

    @PostConstruct
    public KeyPair keyPair() {
        try {
            KeyPair keyPair = new KeyPair(new PublicKey() {
                @Override
                public String getAlgorithm() {
                    return null;
                }

                @Override
                public String getFormat() {
                    return null;
                }

                @Override
                public byte[] getEncoded() {
                    return new byte[0];
                }
            }, new PrivateKey() {
                @Override
                public String getAlgorithm() {
                    return null;
                }

                @Override
                public String getFormat() {
                    return null;
                }

                @Override
                public byte[] getEncoded() {
                    return new byte[0];
                }
            });

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(jwtConfig.getAlgorithm());
            keyPairGenerator.initialize(jwtConfig.getKeySize());
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new BeanInitializationException(String.format("Failed to generate key pair using %s algorithm with key size of %s bits", jwtConfig.getAlgorithm(), jwtConfig.getKeySize()));
        }
    }


    @Autowired
    public JWTService(EncryptionService encryptionService, JWTConfig jwtConfig, RestTemplate restTemplate) {
        this.jwtConfig = jwtConfig;
        this.algorithm = Algorithm.RSA512((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.restTemplate = restTemplate;
    }

    public JWTToken authenticateUsingPassword(String username, String password) {

        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            throw new AuthenticationException(AuthenticationException.Error.INVALID_CREDENTIALS);
        }

        ResponseEntity<UsersListResponse> response = restTemplate.getForEntity("http://USER-IDENTITY-SERVICE/users?search-by=email&value=" + username, UsersListResponse.class);

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

    private long getExpiresAtInMilliseconds() {
        return System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(jwtConfig.getExpirationInSeconds());
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public RSAPublicKey getRSAPublicKey() {
        return publicKey;
    }

    public JWTConfig getJwtConfig () {
        return jwtConfig;
    }

     */
}