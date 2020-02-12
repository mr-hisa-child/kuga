package webapp.kuga.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import webapp.kuga.util.StringUtil;

@Getter
@Setter
@Entity
@Table
public class Member extends BaseEntity {

	@Column
	private String name;
	@Column
	private Integer no;
	@Column(name = "team_id")
	private String teamId;

	public void setNo(String no) {
		if (!StringUtil.isEmpty(no)) {
			this.no = Integer.parseInt(no);
		}
	}
}