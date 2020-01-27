package webapp.kuga.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
  private Long id;
  private String name;
  private String email;
  private String password;
}
