package dev.myrold.util;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import dev.myrold.api.Participant;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Singleton;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class JWTExtractorUtil {

    @Property(name = "micronaut.security.enabled", defaultValue = "true")
    private boolean securityEnabled;


    public Optional<Participant> getParticipantFromToken(Optional<String> token) {
        if (!securityEnabled || token.isEmpty()) {
            log.debug("No token present");
            return Optional.empty();
        }

        try {
            String jwtStr = token.get().split("Bearer ", 2)[1];
            JWT jwt = JWTParser.parse(jwtStr);
            String sub = jwt.getJWTClaimsSet().getStringClaim("sub");

            if (sub != null && !sub.isEmpty()) {
                return Optional.of(new Participant(sub));
            } else {
                return Optional.empty();
            }

        } catch (ParseException e) {
            throw new RuntimeException(String.format("Failed to parse authorization token: %s got exception: %s", token, e));
        }
    }
}
