package com.example.timeformattest.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ZonedDateTimeFormatter implements Formatter<ZonedDateTime> {
    @Override
    public ZonedDateTime parse(String text, Locale locale) throws ParseException {
        return ZonedDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public String print(ZonedDateTime object, Locale locale) {
        return ((ZonedDateTime)object).toString();
    }
}
