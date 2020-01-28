package webapp.kuga.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Activity extends BaseEntity {
    private String teamId;
    private String title;
    private String year;
    private String month;
    private String day;

    public void setDate(String date) {

    }
}