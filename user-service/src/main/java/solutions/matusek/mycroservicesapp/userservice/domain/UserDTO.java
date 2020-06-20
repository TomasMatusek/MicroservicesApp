package solutions.matusek.mycroservicesapp.userservice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
    private final String userId;
    private final String email;
}