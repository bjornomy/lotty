package dev.myrold.lotty.api;

public record UserInfo(
    String name,
    String email,
    String identifier,
    String pictureUrl,
    String provider
) {
}