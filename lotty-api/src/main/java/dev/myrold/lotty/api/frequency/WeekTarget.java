package dev.myrold.lotty.api.frequency;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import dev.myrold.lotty.api.ScheduleFrequency;

public record WeekTarget(DayOfWeek dayOfWeek) implements ScheduleTarget {

    @Override
    public String targetDescription() {
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    @Override
    public ScheduleFrequency frequency() {
        return ScheduleFrequency.Weekly;
    }

    public static WeekTarget of(String dayOfWeek) {
        return new WeekTarget(DayOfWeek.valueOf(dayOfWeek.toLowerCase()));
    }

}
