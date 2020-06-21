package solutions.matusek.mycroservicesapp.useridentityservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import solutions.matusek.mycroservicesapp.useridentityservice.domain.UserDTO;

@Getter
@AllArgsConstructor
public class UserDetailResponse {
    private final UserDTO user;
}