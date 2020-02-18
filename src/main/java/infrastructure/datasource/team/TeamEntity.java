package infrastructure.datasource.team;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import infrastructure.datasource.BaseEntity;
import infrastructure.datasource.member.MemberEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class TeamEntity extends BaseEntity {
	@Column
	private String name;
	@Column
	private String category;
	@OneToMany
	@JoinColumn(name = "team_id")
	private List<MemberEntity> members;
}