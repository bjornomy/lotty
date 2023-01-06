package dev.myrold.api;

import io.micronaut.core.annotation.Introspected;

@Introspected
public enum ScheduleFrequency {
    Monthly,
    EveryThreeWeeks,
    BiWeekly,
    Weekly
}
