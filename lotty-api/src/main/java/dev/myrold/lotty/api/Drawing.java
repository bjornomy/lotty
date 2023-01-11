package dev.myrold.lotty.api;

import java.time.Instant;
import java.util.Set;

public record Drawing(
    String id,
    Set<Price> prices,
    Instant drawnAt
) implements TsidAware {
}
