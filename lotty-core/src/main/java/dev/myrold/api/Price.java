package dev.myrold.api;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Price(
    String id,
    String name,
    String description,
    String winner
) implements TsidAware {
}
