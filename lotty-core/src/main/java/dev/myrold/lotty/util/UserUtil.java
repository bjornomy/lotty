package dev.myrold.lotty.util;

import java.security.Principal;
import java.util.Map;

import dev.myrold.lotty.api.UserInfo;
import io.micronaut.http.context.ServerRequestContext;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.oauth2.endpoint.token.response.OauthAuthenticationMapper;
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdClaims;

public final class UserUtil {

    /**
     * Ripped from {@link io.micronaut.security.utils.DefaultSecurityService},
     * found through:
     * <a href="https://micronaut-projects.github.io/micronaut-security/latest/guide/#securityService">Micronaut Security Docs</a>
     *
     * @return Name of the logged-in user
     */
    public static String getUserIdentityFromRequestContext() {
        return ServerRequestContext.currentRequest()
            .flatMap(request -> request.getUserPrincipal(Principal.class))
            .map(Principal::getName)
            .orElse("UNKNOWN");
    }


    public static UserInfo getUserInfo(Authentication authentication) {
        Map<String, Object> attributes = authentication.getAttributes();

        return new UserInfo(
            (String) attributes.get(OpenIdClaims.CLAIMS_NAME),
            (String) attributes.get(OpenIdClaims.CLAIMS_EMAIL),
            authentication.getName(),
            (String) attributes.get(OpenIdClaims.CLAIMS_PICTURE),
            (String) attributes.get(OauthAuthenticationMapper.PROVIDER_KEY)
        );
    }

}
