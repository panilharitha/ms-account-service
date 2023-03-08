package igd.anz.sample.assessment.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HttpSanitizer {

    public String sanitizer(String input) {
        String clean = StringEscapeUtils.escapeHtml4(input);
        if (!Objects.equals(input, clean)) {
            throw new IllegalArgumentException(input);
        }

        return clean;
    }
}
