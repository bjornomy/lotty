package dev.myrold.controller;

import org.reactivestreams.Publisher;

import java.util.List;

import dev.myrold.security.RoleConstants;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.oauth2.configuration.OauthClientConfigurationProperties;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Introspected
@RequiredArgsConstructor
@Controller("/api/config")
@ExecuteOn(TaskExecutors.IO)
@Secured(RoleConstants.ADMIN)
public class ConfigController {

    private final List<OauthClientConfigurationProperties> oauth2Clients;


    @Get("/oauth-providers")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Publisher<String> getProviders() {
        return Flux.fromStream(
            // Micronaut returns [name,othername] for some reason. That is not valid json...
            // add enclosing `"` manually
            oauth2Clients.stream().map(clientConfig -> String.format("\"%s\"", clientConfig.getName()))
        );
    }
}
