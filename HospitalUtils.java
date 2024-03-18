package hospital;

import java.util.Calendar;

public class HospitalUtils {
    public static void printDateTime() {
        Calendar calendar = Calendar.getInstance();
        System.out.print("Date: " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.YEAR));
        System.out.println("\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
    }
}
