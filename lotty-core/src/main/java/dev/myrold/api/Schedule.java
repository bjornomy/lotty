package dev.myrold.api;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Schedule(
    String id,
    ScheduleFrequency frequency,
    String target
) implements TsidAware {
}
