package dev.myrold.api;

import java.time.Instant;
import java.util.List;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Drawing(
    String id,
    List<Price> prices,
    Instant drawnAt
) implements TsidAware {
}
