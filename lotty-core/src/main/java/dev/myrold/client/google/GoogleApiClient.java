package dev.myrold.client.google;

import org.reactivestreams.Publisher;

import dev.myrold.api.UserInfo;
import dev.myrold.service.UserInfoService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.security.oauth2.client.OpenIdProviderMetadata;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class GoogleApiClient {

    @Named(UserInfoService.GOOGLE_PROVIDER)
    private final OpenIdProviderMetadata openIdProviderMetadata;

    private final HttpClient httpClient;

    public Publisher<GoogleUser> getUserInfo(String token) {

        HttpRequest<Object> userInfoRequest = HttpRequest.GET(openIdProviderMetadata.getUserinfoEndpoint())
            .bearerAuth(token);

        return Mono.from(httpClient.exchange(userInfoRequest, GoogleUser.class))
            .mapNotNull(HttpResponse::body);
    }
}
