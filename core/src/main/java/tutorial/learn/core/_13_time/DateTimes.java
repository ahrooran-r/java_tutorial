package tutorial.learn.core._13_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimes {
    public static void main(String[] args) {

        LocalDate ld = LocalDate.of(2010, 2, 23);

        // give in terms of dates and times
        LocalDateTime ldt = LocalDateTime.of(
                ld,
                LocalTime.of(2, 7)
        );

        // or give in terms of year, month, day, hour, min etc.
        LocalDateTime ldt2 = LocalDateTime.of(
                2013,
                5,
                17,
                4,
                35,
                44,
                400
        );

        // OR add to existing local date
        LocalDateTime ldt3 = ld.atTime(2, 7);
        // both ldt and ldt3 are same
    }
}
