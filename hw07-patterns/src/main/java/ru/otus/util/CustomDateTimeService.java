package ru.otus.util;

import java.time.LocalDateTime;

public class CustomDateTimeService {
    LocalDateTime dateTime;

    public CustomDateTimeService() {
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEven() {
        return dateTime.getSecond() % 2 == 0;
    }
}
