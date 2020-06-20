package solutions.matusek.mycroservicesapp.userservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import solutions.matusek.mycroservicesapp.userservice.domain.UserDTO;

@Getter
@AllArgsConstructor
public class UserDetailResponse {
    private final UserDTO user;
}