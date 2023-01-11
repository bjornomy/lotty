package dev.myrold.lotty.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import dev.myrold.lotty.security.RoleConstants;
import io.micronaut.context.annotation.Property;
import io.micronaut.security.authentication.Authentication;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class RoleService {

    @Property(name = "lotty.admin-users")
    private List<String> adminUsers = new ArrayList<>();

    public List<String> resolveRoles(String email) {
        if (adminUsers.stream().anyMatch(a -> Objects.equals(a, email))) {
            return Arrays.asList(RoleConstants.ADMIN, RoleConstants.USER);
        } else {
            return Collections.singletonList(RoleConstants.USER);
        }
    }

    public boolean isAdmin(Authentication authentication) {
        return authentication.getRoles().contains(RoleConstants.ADMIN);
    }
}
