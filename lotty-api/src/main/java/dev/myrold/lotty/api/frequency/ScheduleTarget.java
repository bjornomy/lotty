package dev.myrold.lotty.api.frequency;

import dev.myrold.lotty.api.ScheduleFrequency;

public interface ScheduleTarget {

    String targetDescription();

    default String description() {
        return String.format(frequency().getFormat(), targetDescription());
    }

    ScheduleFrequency frequency();

    static String describe(ScheduleFrequency frequency, String target) {

        ScheduleTarget found = switch (frequency) {
            case Weekly, BiWeekly, EveryThreeWeeks -> WeekTarget.of(target);
            case Monthly -> MonthTarget.of(target);
        };

        return found.description();
    }

}
