package solutions.matusek.microservicesapp.authenticationservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solutions.matusek.microservicesapp.authenticationservice.domain.User;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersListResponse {
    private List<User> users;
}