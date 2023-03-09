package igd.anz.assessment.unit.util;

import igd.anz.assessment.util.CommonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CommonUtilsTest {

    @Test
    void dateToStringwithFormat_SuccessResponse_ValidDate() {
        String response = CommonUtils.dateToStringwithFormat(new Date(TimeUnit.SECONDS.toMillis(1220227200L)));

        assertEquals("01/09/2008", response);
    }

    @Test
    void dateToStringwithFormat_EmptyResponse_Null() {
        String response = CommonUtils.dateToStringwithFormat(null);

        assertEquals("", response);
    }

    @Test
    void dateToStringwithmonthNameFormat_SuccessResponse_ValidDate() {
        String response = CommonUtils.dateToStringwithmonthNameFormat(new Date(TimeUnit.SECONDS.toMillis(1220227200L)));

        assertEquals("Sep.,01,2008", response);
    }

    @Test
    void dateToStringwithmonthNameFormat_EmptyResponse_Null() {
        String response = CommonUtils.dateToStringwithmonthNameFormat(null);

        assertEquals("", response);
    }

    @Test
    void maskGivenAccountNumber_Success_Ninedigits() {
        String response = CommonUtils.maskGivenAccountNumber("123456789");

        assertEquals("XXXXX6789", response);
    }

    @Test
    void maskGivenAccountNumber_Success_Fourdigits() {
        String response = CommonUtils.maskGivenAccountNumber("1234");

        assertEquals("1234", response);
    }

    @Test
    void maskGivenAccountNumber_Success_Null() {
        String response = CommonUtils.maskGivenAccountNumber(null);

        assertEquals("", response);
    }
}
