package dev.myrold.lotty.security;

import java.util.List;

import dev.myrold.lotty.service.RoleService;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.config.AuthenticationModeConfiguration;
import io.micronaut.security.oauth2.configuration.OpenIdAdditionalClaimsConfiguration;
import io.micronaut.security.oauth2.endpoint.token.response.DefaultOpenIdAuthenticationMapper;
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdClaims;
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdTokenResponse;
import jakarta.inject.Singleton;

/**
 * We only needed to override {@link DefaultOpenIdAuthenticationMapper#getRoles(String, OpenIdTokenResponse, OpenIdClaims)},
 * so we extend, override and replace the default implementation. This way all OpenId users will get the correct roles.
 */
@Singleton
@Replaces(DefaultOpenIdAuthenticationMapper.class)
public class GlobalOpenIdAuthenticationMapper extends DefaultOpenIdAuthenticationMapper {

    private final RoleService roleService;

    public GlobalOpenIdAuthenticationMapper(
        OpenIdAdditionalClaimsConfiguration openIdAdditionalClaimsConfiguration,
        AuthenticationModeConfiguration authenticationModeConfiguration,
        RoleService roleService
    ) {
        super(openIdAdditionalClaimsConfiguration, authenticationModeConfiguration);
        this.roleService = roleService;
    }

    @Override
    protected List<String> getRoles(String providerName, OpenIdTokenResponse tokenResponse, OpenIdClaims openIdClaims) {
        return roleService.resolveRoles(openIdClaims.getEmail());
    }
}