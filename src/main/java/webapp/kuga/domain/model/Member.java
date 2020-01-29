package webapp.kuga.domain.model;

import lombok.Getter;
import lombok.Setter;
import webapp.kuga.util.StringUtil;

@Getter
@Setter
public class Member extends BaseEntity {

    private String name;
    private Integer no;
    private String teamId;

    public void setNo(String no) {
        if (!StringUtil.isEmpty(no)) {
            this.no = Integer.parseInt(no);
        }
    }
}