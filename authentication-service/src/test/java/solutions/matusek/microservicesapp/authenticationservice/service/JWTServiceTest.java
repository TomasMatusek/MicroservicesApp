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
@PrepareForTest({JWTService.class})
class JWTServiceTest {

    private JWTService JWTService;

    @Before
    public void setUp() throws Exception {
        JWTConfig jwtConfig = new JWTConfig();
        KeyPair keyPair = KeyPairGenerator.getInstance(jwtConfig.getAlgorithm()).genKeyPair();
        this.JWTService = new JWTService(keyPair, jwtConfig, null);
    }

    public JWTServiceTest() {
    }

    @Test
    public void when_jwtTokenGenerate_then_tokenIsNotValid() {
        String jwtToken = JWTService.createJWT();
        Assertions.assertThat(JWTService.isJWTValid(jwtToken)).isTrue();
    }

    @Test
    public void when_nonValidTokenProvided_then_tokenIsInvalid() {
        Assertions.assertThat(JWTService.isJWTValid("{}")).isFalse();
        Assertions.assertThat(JWTService.isJWTValid("someText")).isFalse();
        Assertions.assertThat(JWTService.isJWTValid(null)).isFalse();
    }

    // TODO https://github.com/powermock/powermock/wiki/Mockito
}