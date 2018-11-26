package com.emerzonic.SpringApp.util;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class GenerateDateString {

    public String getDateString(String dateString, Timestamp createdOn) {
        if (dateString == null) {
            dateString = DateTimeFormatter.ofPattern("E, MMM. dd yyyy").format(createdOn.toLocalDateTime());
        }
        return dateString;
    }
}
