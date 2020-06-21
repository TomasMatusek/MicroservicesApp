package solutions.matusek.mycroservicesapp.useridentityservice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
    private final String userId;
    private final String email;
}