package solutions.matusek.mycroservicesapp.useridentityservice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class User {
    private final String id;
    private final String email;
    private final Date created;
    private final boolean enabled;
}