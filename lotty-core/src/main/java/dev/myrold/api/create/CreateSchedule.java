package dev.myrold.api.create;

import dev.myrold.api.ScheduleFrequency;
import io.micronaut.core.annotation.Introspected;

@Introspected
public record CreateSchedule(
    ScheduleFrequency frequency,
    String target
) {
}
