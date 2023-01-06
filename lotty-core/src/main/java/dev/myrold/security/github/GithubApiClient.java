package dev.myrold.security.github;

import org.reactivestreams.Publisher;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

@Header(name = "User-Agent", value = "Micronaut")
@Client("github-api")
public interface GithubApiClient {

    @Get("/user")
    Publisher<GithubUser> getUser(@Header("Authorization") String authorization);
}