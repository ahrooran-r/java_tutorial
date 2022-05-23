package _9_modules.time;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Dates {
    public static void main(String[] args) {

        // Immutable
        LocalDate ld = LocalDate.of(2021, 1, 13);
        LocalDate ld2 = LocalDate.of(2021, Month.JANUARY, 13);

        // easier addition -> a new ld is returned cause these are immutable
        ld = ld.plusDays(25);

        // Format dates in different ways -> 1. Create a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy");

        // 2.1 do formatting by passing formatter into date object
        String formatted_date_1 = ld.format(formatter);
        System.out.println(formatted_date_1);

        // 2.2 OR do formatting by passing date object into formatter
        String formatted_date_2 = formatter.format(ld);

    }
}
