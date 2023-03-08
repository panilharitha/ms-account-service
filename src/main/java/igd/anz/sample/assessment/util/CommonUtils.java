package igd.anz.sample.assessment.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    public static String dateToStringwithFormat(Date date) {
        return dateFormatter.format(date);
    }

    public static String dateToStringwithmonthNameFormat(Date date) {
        dateFormatter = new SimpleDateFormat("MMM,dd,yyyy");
        return dateFormatter.format(date);
    }
}
