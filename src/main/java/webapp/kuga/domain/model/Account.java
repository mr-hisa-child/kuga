package webapp.kuga.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Account extends BaseEntity {
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@ManyToMany
	@JoinTable(name = "account_team", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
	private List<Team> teams;
}
