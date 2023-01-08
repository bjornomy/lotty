package dev.myrold.api;

public record UserInfo(
    String name,
    String email,
    String identifier,
    String pictureUrl,
    String provider
) {
}
