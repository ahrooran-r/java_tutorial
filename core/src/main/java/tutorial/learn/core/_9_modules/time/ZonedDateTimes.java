package tutorial.learn.core._9_modules.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimes {
    public static void main(String[] args) {

        // available zones
        for (String zone : ZoneId.getAvailableZoneIds()) {
            System.out.println(zone);
        }

        ZonedDateTime zdt = ZonedDateTime.of(
                LocalDateTime.MIN,
                ZoneId.of("Asia/Tokyo")
        );
    }
}
