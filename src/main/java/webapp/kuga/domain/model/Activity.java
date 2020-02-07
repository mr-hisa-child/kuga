package webapp.kuga.domain.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import webapp.kuga.util.DateUtil;

@Getter
@Setter
@ToString
public class Activity extends BaseEntity {
    private String teamId;
    private String title;
    private int year;
    private int month;
    private int day;

    public void setDate(String date) {
        LocalDate d = DateUtil.parse(date, "yyyy-MM-dd");
        this.year = d.getYear();
        this.month = d.getMonthValue();
        this.day = d.getDayOfMonth();
    }

    public String getDate() {
        return DateUtil.format(LocalDate.of(this.year, this.month, this.day), "yyyy-MM-dd");
    }
}