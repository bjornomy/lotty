package dev.myrold.lotty.api.frequency;

import java.util.Map;

import dev.myrold.lotty.api.ScheduleFrequency;

public record MonthTarget(int dayOfMonth) implements ScheduleTarget {

    private static final Map<Integer, String> specialDayMappings = Map.ofEntries(
        Map.entry(1, "first"), Map.entry(2, "second"), Map.entry(3, "third")
    );

    @Override
    public String targetDescription() {
        return dayOfMonth < 4 ? specialDayMappings.get(dayOfMonth) : Integer.toString(dayOfMonth);
    }

    @Override
    public ScheduleFrequency frequency() {
        return ScheduleFrequency.Monthly;
    }

    public static MonthTarget of(String dayOfMonth) {
        return new MonthTarget(Integer.parseInt(dayOfMonth));
    }

}
