package dev.myrold.api;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Price(
    PriceType type,
    String description
) {
}
