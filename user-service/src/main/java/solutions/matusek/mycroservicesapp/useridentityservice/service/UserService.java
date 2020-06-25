package solutions.matusek.mycroservicesapp.useridentityservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solutions.matusek.mycroservicesapp.useridentityservice.domain.User;
import solutions.matusek.mycroservicesapp.useridentityservice.repository.IUserDatabaseRepository;
import solutions.matusek.mycroservicesapp.useridentityservice.repository.UserRow;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserDatabaseRepository databaseRepository;

    @Autowired
    public UserService(IUserDatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    @Override
    public List<User> findAllByEmail(String email) {
        List<UserRow> userRows = databaseRepository.findAllByEmail(email);
        return userRows.stream()
                .map(row -> new User(row.getId(), row.getEmail(), row.getCreated(), row.isEnabled()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAllById(String id) {
        List<UserRow> userRows = databaseRepository.findAllById(Collections.singletonList(id));
        return userRows.stream()
                .map(row -> new User(row.getId(), row.getEmail(), row.getCreated(), row.isEnabled()))
                .collect(Collectors.toList());
    }
}