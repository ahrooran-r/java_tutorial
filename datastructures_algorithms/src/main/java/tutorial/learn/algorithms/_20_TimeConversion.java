package tutorial.learn.algorithms;

/**
 * https://www.hackerrank.com/challenges/time-conversion/problem?isFullScreen=true
 */
public class _20_TimeConversion {
    public static void main(String[] args) {

        System.out.println(timeConversion("09:01:00PM"));
        System.out.println(timeConversion("12:01:00AM"));
        System.out.println(timeConversion("12:01:00PM"));

    }

    public static String timeConversion(String s) {
        var amPM = s.substring(s.length() - 2);
        var hour = s.substring(0, 2);

        int hh = Integer.parseInt(hour);
        String militaryHour = switch (amPM.toLowerCase()) {
            case "am" -> {
                if (12 == hh) yield "00";
                else yield hour;
            }
            case "pm" -> {
                if (12 == hh) hh = 0;
                int HH = 12 + hh;
                yield String.valueOf(HH);
            }
            default -> throw new IllegalStateException("Unexpected value: " + amPM.toLowerCase());
        };

        var militaryS = s.replace(hour, militaryHour).replace(amPM, "");
        return militaryS;
    }
}
