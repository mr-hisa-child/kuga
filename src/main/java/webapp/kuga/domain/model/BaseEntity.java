package webapp.kuga.domain.model;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity {
    private String id;
    private Date createDt;
    private Date updateDt;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
    }
}