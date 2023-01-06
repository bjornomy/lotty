package dev.myrold.api;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Participant(
    String id,
    String name,
    long wins,
    long participations
) {

    public Participant(String id) {
        this(id, null, 0, 0);
    }
}
