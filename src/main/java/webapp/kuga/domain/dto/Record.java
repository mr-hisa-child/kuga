package webapp.kuga.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Record {
	private String memberName;
	private int attend;
	private int goal;
	private int assist;
	private int win;
}
