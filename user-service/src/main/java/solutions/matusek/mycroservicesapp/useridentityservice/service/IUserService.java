package solutions.matusek.mycroservicesapp.useridentityservice.service;

import solutions.matusek.mycroservicesapp.useridentityservice.domain.User;

import java.util.List;

public interface IUserService {
    List<User> findAllByEmail(String email);
    List<User> findAllById(String id);
}