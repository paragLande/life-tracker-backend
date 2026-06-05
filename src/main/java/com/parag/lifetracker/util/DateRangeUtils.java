package com.parag.lifetracker.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public final class DateRangeUtils {
    private DateRangeUtils() {
    }

    public static LocalDate startOfCurrentWeek(LocalDate date) {
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    public static LocalDate startOfCurrentMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }
}
