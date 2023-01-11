package dev.myrold.lotty.api.create;

import dev.myrold.lotty.api.ScheduleFrequency;

public record CreateSchedule(
    ScheduleFrequency scheduleFrequency,
    String target
) {
}
