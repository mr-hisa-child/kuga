package webapp.kuga.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account extends BaseEntity {
    private String name;
    private String email;
    private String password;
}
