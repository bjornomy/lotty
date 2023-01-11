package dev.myrold.lotty.service;

import org.reactivestreams.Publisher;

import java.util.Map;

import dev.myrold.lotty.api.UserInfo;
import dev.myrold.lotty.client.gitlab.GithubApiClient;
import dev.myrold.lotty.client.google.GoogleApiClient;
import io.micronaut.cache.annotation.CachePut;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.oauth2.endpoint.token.response.OauthAuthenticationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

// TODO: delete

@Slf4j
//@Singleton
@RequiredArgsConstructor
public class UserInfoService {

    public static final String USER_INFO_CACHE = "user-info";

    public static final String GITHUB_PROVIDER = "github";
    public static final String GOOGLE_PROVIDER = "google";

    private final GoogleApiClient googleApiClient;
    private final GithubApiClient githubApiClient;

    @CachePut(cacheNames = USER_INFO_CACHE, async = true)
    public Publisher<UserInfo> getUserInfo(Authentication authentication) {
        Map<String, Object> oAuthProperties = authentication.getAttributes();

        String provider = (String) oAuthProperties.get(OauthAuthenticationMapper.PROVIDER_KEY);
        String token = (String) oAuthProperties.get(OauthAuthenticationMapper.ACCESS_TOKEN_KEY);

        switch (provider) {
            case GITHUB_PROVIDER -> {
                log.info("Getting UserInfo from [{}]", GITHUB_PROVIDER);
                return Mono.from(githubApiClient.getUser(String.format("Bearer %s", token)))
                    .map(gu -> new UserInfo(
                        gu.getName() != null ? gu.getName() : gu.getLogin(),
                        gu.getEmail(),
                        Long.toString(gu.getId()),
                        gu.getAvatarUrl(),
                        GITHUB_PROVIDER
                    ));
            }
            case GOOGLE_PROVIDER -> {
                log.info("Getting UserInfo from [{}]", GOOGLE_PROVIDER);
                return Mono.from(googleApiClient.getUserInfo(token))
                    .map(gu -> new UserInfo(
                        gu.getName(),
                        gu.getEmail(),
                        gu.getSub(),
                        gu.getPicture(),
                        GOOGLE_PROVIDER
                    ));
            }
            default -> {
                throw new IllegalArgumentException(String.format("Provider [%s] is not supported", provider));
            }
        }
    }
}
