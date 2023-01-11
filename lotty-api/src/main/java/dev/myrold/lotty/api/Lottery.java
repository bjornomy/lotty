package dev.myrold.lotty.api;

import java.util.Set;

public record Lottery(
    String id,
    String name,
    Set<Participant> participants,
    Set<Drawing> drawings
) implements TsidAware {
}
