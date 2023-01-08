package dev.myrold.security;

import io.micronaut.cache.interceptor.CacheKeyGenerator;
import io.micronaut.core.annotation.AnnotationMetadata;
import io.micronaut.security.event.LogoutEvent;
import lombok.NoArgsConstructor;

/**
 * the key in our cache is {@link io.micronaut.security.authentication.Authentication}, which is the source
 * of {@link io.micronaut.security.event.LogoutEvent}. This lets the cache implementor get the destructed
 */
@NoArgsConstructor
public class EventKeyGenerator implements CacheKeyGenerator {

    @Override public Object generateKey(AnnotationMetadata annotationMetadata, Object... params) {
        return ((LogoutEvent) params[0]).getSource();
    }
}