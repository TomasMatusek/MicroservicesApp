package solutions.matusek.mycroservicesapp.useridentityservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import solutions.matusek.mycroservicesapp.useridentityservice.domain.User;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class UsersListResponse {

    private final List<User> users;

    public UsersListResponse(User user) {
        this.users = Collections.singletonList(user);
    }
}