package solutions.matusek.microservicesapp.authenticationservice.service.exception;

public class DataDecryptionException extends RuntimeException {
    public DataDecryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
