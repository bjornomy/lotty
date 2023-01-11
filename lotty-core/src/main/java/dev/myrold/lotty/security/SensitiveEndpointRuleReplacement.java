package dev.myrold.lotty.security;

import org.reactivestreams.Publisher;

import java.util.Collections;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.inject.ExecutableMethod;
import io.micronaut.management.endpoint.EndpointSensitivityProcessor;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRuleResult;
import io.micronaut.security.rules.SensitiveEndpointRule;
import io.micronaut.security.token.RolesFinder;
import jakarta.inject.Singleton;
import lombok.NonNull;
import reactor.core.publisher.Mono;

@Singleton
@Replaces(SensitiveEndpointRule.class)
public class SensitiveEndpointRuleReplacement extends SensitiveEndpointRule {

    private final RolesFinder rolesFinder;

    public SensitiveEndpointRuleReplacement(EndpointSensitivityProcessor endpointSensitivityProcessor, RolesFinder rolesFinder) {
        super(endpointSensitivityProcessor);
        this.rolesFinder = rolesFinder;
    }

    @Override
    @NonNull
    protected Publisher<SecurityRuleResult> checkSensitiveAuthenticated(@NonNull HttpRequest<?> request,
                                                                        @NonNull Authentication authentication,
                                                                        @NonNull ExecutableMethod<?, ?> method) {
        return Mono.fromCallable(() -> {
            if (rolesFinder.hasAnyRequiredRoles(Collections.singletonList(RoleConstants.ADMIN), authentication.getRoles())) {
                return SecurityRuleResult.ALLOWED;
            } else {
                return SecurityRuleResult.REJECTED;
            }
        });
    }
}