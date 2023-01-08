package dev.myrold.api;

import java.util.List;
import java.util.Set;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Lottery(
    String id,
    String name,
    Set<Participant> participants,
    List<Drawing> drawings
) implements TsidAware {
}
