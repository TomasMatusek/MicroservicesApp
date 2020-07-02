package solutions.matusek.microservicesapp.authenticationservice.service.exception;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {

    private final Error error;

    public AuthenticationException(Error error) {
        this.error = error;
    }

    public enum Error {
        INVALID_CREDENTIALS,
        USER_NOT_FOUND,
        USER_DISABLED;
    }
}
