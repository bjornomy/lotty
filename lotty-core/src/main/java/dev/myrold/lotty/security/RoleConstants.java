package dev.myrold.lotty.security;

import io.micronaut.security.rules.SecurityRule;

public interface RoleConstants extends SecurityRule {
    String ADMIN = "ROLE_ADMIN";
    String USER = "ROLE_USER";
}
