package dev.myrold.lotty.security;

import dev.myrold.lotty.service.UserInfoService;
import io.micronaut.cache.annotation.CacheInvalidate;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.event.LoginSuccessfulEvent;
import io.micronaut.security.event.LogoutEvent;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

// TODO: delete

//@Singleton
@RequiredArgsConstructor
public class SecurityEventHandler {

    private final UserInfoService userInfoService;

    @EventListener
    public void onSuccessfulLogin(LoginSuccessfulEvent event) {
        Authentication auth = (Authentication) event.getSource();

        // only used to add the user to our cache
        Mono.from(userInfoService.getUserInfo(auth))
            .subscribe();
    }

    @EventListener
    @CacheInvalidate(cacheNames = UserInfoService.USER_INFO_CACHE, keyGenerator = EventKeyGenerator.class)
    public void onLogout(LogoutEvent event) {
        // no-op - used invalidate cache on existing user
    }

}
