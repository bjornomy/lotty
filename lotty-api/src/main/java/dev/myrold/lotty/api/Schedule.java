package dev.myrold.lotty.api;

public record Schedule(
    String id,
    ScheduleFrequency scheduleFrequency,
    String target,
    String description
) implements TsidAware {
}
