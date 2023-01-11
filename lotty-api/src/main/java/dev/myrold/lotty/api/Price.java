package dev.myrold.lotty.api;

public record Price(
    String id,
    String name,
    String description,
    String winner
) implements TsidAware {
}
