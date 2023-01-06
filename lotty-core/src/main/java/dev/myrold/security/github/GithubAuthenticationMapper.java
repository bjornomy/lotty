package dev.myrold.security.github;

import org.reactivestreams.Publisher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import dev.myrold.security.RoleConstants;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.oauth2.endpoint.authorization.state.State;
import io.micronaut.security.oauth2.endpoint.token.response.OauthAuthenticationMapper;
import io.micronaut.security.oauth2.endpoint.token.response.TokenResponse;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Named("github")
@Singleton
class GithubAuthenticationMapper implements OauthAuthenticationMapper {

    private final GithubApiClient apiClient;

    GithubAuthenticationMapper(GithubApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Publisher<AuthenticationResponse> createAuthenticationResponse(TokenResponse tokenResponse, @Nullable State state) {
        return Flux.from(apiClient.getUser("Bearer " + tokenResponse.getAccessToken()))
            .map(user -> {
                String username = user.getName() != null ? user.getName() : user.getEmail();
                List<String> roles;

                if (Objects.equals(username, "bom@myrold.dev")) {
                    roles = Arrays.asList(RoleConstants.ADMIN, RoleConstants.USER);
                } else {
                    roles = Collections.singletonList(RoleConstants.USER);
                }

                return AuthenticationResponse.success(username, roles);
            });
    }
}