package solutions.matusek.microservicesapp.authenticationservice.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import solutions.matusek.microservicesapp.authenticationservice.config.JWTConfig;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@SpringBootTest
@RunWith(PowerMockRunner.class)
@PrepareForTest({AuthorizationService.class})
class AuthorizationServiceTest {
//
//    private AuthorizationService authorizationService;
//
//    @Before
//    public void setUp() throws Exception {
//        JWTConfig jwtConfig = new JWTConfig();
//        KeyPair keyPair = KeyPairGenerator.getInstance(jwtConfig.getAlgorithm()).genKeyPair();
//        this.authorizationService = new AuthorizationService(keyPair, jwtConfig);
//    }
//
//    public AuthorizationServiceTest() {
//    }
//
//    @Test
//    public void when_jwtTokenGenerate_then_tokenIsNotValid() {
//        String jwtToken = authorizationService.createJWT();
//        Assertions.assertThat(authorizationService.isJWTValid(jwtToken)).isTrue();
//    }
//
//    @Test
//    public void when_nonValidTokenProvided_then_tokenIsInvalid() {
//        Assertions.assertThat(authorizationService.isJWTValid("{}")).isFalse();
//        Assertions.assertThat(authorizationService.isJWTValid("someText")).isFalse();
//        Assertions.assertThat(authorizationService.isJWTValid(null)).isFalse();
//    }
//
//    // TODO https://github.com/powermock/powermock/wiki/Mockito
}