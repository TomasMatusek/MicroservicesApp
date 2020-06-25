package solutions.matusek.mycroservicesapp.useridentityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.matusek.mycroservicesapp.useridentityservice.domain.User;
import solutions.matusek.mycroservicesapp.useridentityservice.service.IUserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserRestController {

    private final IUserService userService;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public UserRestController(IUserService userService, DiscoveryClient discoveryClient) {
        this.userService = userService;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/users")
    public @ResponseBody ResponseEntity<UsersListResponse> getUserByMail(
        @RequestParam(name = "search-by", defaultValue = "") String searchBy,
        @RequestParam(name = "value", defaultValue = "") String value) {

        List<User> result = new ArrayList<>();
        switch (searchBy.toLowerCase()) {
            case "id":
                result = userService.findAllById(value); break;
            case "email":
                result = userService.findAllByEmail(value); break;
        }
        return ResponseEntity.ok(new UsersListResponse(result));
    }
}