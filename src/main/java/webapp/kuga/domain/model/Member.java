package webapp.kuga.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member extends BaseEntity {

    private String name;
    private String no;
    private String teamId;
}