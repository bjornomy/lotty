package dev.myrold.lotty.api;

public record Participant(
    String id,
    String name,
    long wins,
    long participations
) implements TsidAware {

    public Participant(String id) {
        this(id, null, 0, 0);
    }
}
