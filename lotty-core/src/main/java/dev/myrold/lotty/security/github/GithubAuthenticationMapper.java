package dev.myrold.lotty.security.github;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.Map;

import dev.myrold.lotty.client.gitlab.GithubApiClient;
import dev.myrold.lotty.service.RoleService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.oauth2.configuration.OauthConfigurationProperties.OpenIdConfigurationProperties.AdditionalClaimsConfigurationProperties;
import io.micronaut.security.oauth2.endpoint.authorization.state.State;
import io.micronaut.security.oauth2.endpoint.token.response.OauthAuthenticationMapper;
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdClaims;
import io.micronaut.security.oauth2.endpoint.token.response.TokenResponse;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static dev.myrold.lotty.service.UserInfoService.GITHUB_PROVIDER;

@Slf4j
@Named(GITHUB_PROVIDER)
@Singleton
@RequiredArgsConstructor
class GithubAuthenticationMapper implements OauthAuthenticationMapper {

    private final AdditionalClaimsConfigurationProperties claimsProperties;
    private final GithubApiClient apiClient;
    private final RoleService roleService;

    @Override
    public Publisher<AuthenticationResponse> createAuthenticationResponse(TokenResponse tokenResponse, @Nullable State state) {
        return Flux.from(apiClient.getUser("Bearer " + tokenResponse.getAccessToken()))
            .map(user -> {
                // we want to emulate an OpenId mapped Authentication
                Map<String, Object> attributes = new HashMap<>();
                attributes.put(OpenIdClaims.CLAIMS_NAME, user.getName() != null ? user.getName() : user.getLogin());
                attributes.put(OpenIdClaims.CLAIMS_EMAIL, user.getEmail());
                attributes.put(OpenIdClaims.CLAIMS_PICTURE, user.getAvatarUrl());
                attributes.put(OauthAuthenticationMapper.PROVIDER_KEY, GITHUB_PROVIDER);

                if (claimsProperties.isAccessToken()) {
                    attributes.put(OauthAuthenticationMapper.ACCESS_TOKEN_KEY, tokenResponse.getAccessToken());
                }

                if (claimsProperties.isRefreshToken()) {
                    attributes.put(OauthAuthenticationMapper.REFRESH_TOKEN_KEY, tokenResponse.getRefreshToken());
                }

                // For OpenId Auth we use ´sub´ as the user, as such we'll use GitHubs ´id´ as a replacement
                return AuthenticationResponse.success(
                    Long.toString(user.getId()), roleService.resolveRoles(user.getEmail()), attributes
                );
            });
    }
}