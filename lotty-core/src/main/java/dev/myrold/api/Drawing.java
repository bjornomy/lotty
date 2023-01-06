package dev.myrold.api;

import java.time.Instant;

public record Drawing(
    String winner,
    Price price,
    Instant drawnAt
) {
}
