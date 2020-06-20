package solutions.matusek.mycroservicesapp.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import solutions.matusek.mycroservicesapp.userservice.domain.UserDTO;

import java.util.Collections;
import java.util.List;

@RestController
public class UserRestController {

    private static final List<UserDTO> users = Collections.singletonList(
        new UserDTO("d6ce1a47-0d97-4e0a-939f-1da55869829b", "fake.user@server.com")
    );

    @GetMapping("/users/{userId}")
    public @ResponseBody ResponseEntity<UserDetailResponse> getUserDetail(@PathVariable String userId) {
        return users.stream()
                .filter(u -> userId != null && userId.equals(u.getUserId()))
                .findFirst()
                .map(userDTO -> ResponseEntity.ok(new UserDetailResponse(userDTO)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}