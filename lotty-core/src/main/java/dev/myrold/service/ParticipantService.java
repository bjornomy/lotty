package dev.myrold.service;

import java.security.Principal;

import dev.myrold.domain.ParticipantEntity;
import dev.myrold.domain.repository.BaseRepository;
import dev.myrold.domain.repository.ParticipantRepository;
import dev.myrold.mapper.ParticipantMapper;
import dev.myrold.util.TsidUtil;
import io.micronaut.context.annotation.Property;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class ParticipantService {

    @Property(name = "micronaut.security.enabled")
    private final boolean securityEnabled;

    private final BaseRepository baseRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    public ParticipantEntity procureParticipant(Principal principal) {

        if (principal == null) {
            if (securityEnabled) {
                throw new IllegalArgumentException("No user in context, even though security is enabled!");
            } else {
                return participantRepository.getDefaultParticipant();
            }
        }

        return participantRepository.findByIdentity(principal.getName())
            .orElseGet(() -> {
                ParticipantEntity entity = new ParticipantEntity();
                entity.setId(TsidUtil.next());
                entity.setIdentity(principal.getName());

                return baseRepository.persist(entity);
            });
    }
}
