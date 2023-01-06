package dev.myrold.api;

import java.util.List;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Lottery(
    String id,
    String name,
    List<Participant> participants,
    List<Drawing> drawings
) {
}
