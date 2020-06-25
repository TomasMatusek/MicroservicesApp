package solutions.matusek.mycroservicesapp.useridentityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDatabaseRepository extends JpaRepository<UserRow, String> {
    List<UserRow> findAllByEmail(String email);
}