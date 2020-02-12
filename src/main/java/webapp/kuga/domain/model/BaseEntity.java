package webapp.kuga.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GenericGenerator(name = "UuidGenerator", strategy = "webapp.kuga.domain.model.UuidGenerator")
	@GeneratedValue(generator = "UuidGenerator")
	private String id;
//    private Date createDt;
//    private Date updateDt;
}