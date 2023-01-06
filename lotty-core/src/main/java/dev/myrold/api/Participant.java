package dev.myrold.api;

public record Participant(
    String id,
    String name,
    long wins,
    long participations
) {

    public Participant (String id) {
        this(id, null, 0, 0);
    }
}
