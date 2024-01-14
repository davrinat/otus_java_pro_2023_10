package ru.otus.util;

import java.time.LocalDateTime;

public class CustomDateTimeService implements ICustomDateTimeService {
    private final IDateTimeProvider dateTimeProvider;

    public CustomDateTimeService(IDateTimeProvider dateTime) {
        this.dateTimeProvider = dateTime;
    }

    public LocalDateTime getDateTimeProvider() {
        return dateTimeProvider.getDate();
    }

    @Override
    public boolean isEven() {
        return getDateTimeProvider().getSecond() % 2 == 0;
    }

    @Override
    public boolean isNotEven() {
        return getDateTimeProvider().getSecond() % 2 != 0;
    }
}
