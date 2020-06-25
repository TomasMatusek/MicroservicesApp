package solutions.matusek.mycroservicesapp.useridentityservice.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserRow {

    @Id
    private String id;

    @Column
    private String email;

    @Column
    private Date created;

    @Column
    private boolean enabled;
}