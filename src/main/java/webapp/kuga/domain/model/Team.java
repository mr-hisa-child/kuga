package webapp.kuga.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team extends BaseEntity {
    private String name;
    private String category;
}