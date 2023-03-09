package igd.anz.assessment.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CommonUtils {
    private static SimpleDateFormat dateFormatter;
    public static String dateToStringwithFormat(Date date) {
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateConvator(date);
    }

    public static String dateToStringwithmonthNameFormat(Date date) {
        dateFormatter = new SimpleDateFormat("MMM,dd,yyyy");
        return dateConvator(date);
    }

    private static String dateConvator(Date date) {
        if(date != null) {
            return dateFormatter.format(date);
        }
        log.warn("message:\"Given date is null\"");
        return "";
    }

    public static String maskGivenAccountNumber(String accountNumber) {
        if(accountNumber != null) {
            return StringUtils.overlay(accountNumber, StringUtils.repeat("X", accountNumber.length() - 4), 0, accountNumber.length() - 4);
        }
        log.warn("message:\"Given accountNumber is null\"");
        return "";
    }
}
