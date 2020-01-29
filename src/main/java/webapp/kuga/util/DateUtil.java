package webapp.kuga.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate parse(String str, String format) {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(format));
    }

    public static String format(LocalDate date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}