package dev.myrold.api;

import java.time.Instant;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Drawing(
    String id,
    String winner,
    Price price,
    Instant drawnAt
) {
}
