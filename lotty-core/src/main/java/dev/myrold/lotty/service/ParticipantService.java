package dev.myrold.lotty.service;

import dev.myrold.lotty.api.UserInfo;
import dev.myrold.lotty.domain.ParticipantEntity;
import dev.myrold.lotty.domain.repository.BaseRepository;
import dev.myrold.lotty.domain.repository.ParticipantRepository;
import dev.myrold.lotty.mapper.ParticipantMapper;
import dev.myrold.lotty.util.UserUtil;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.config.SecurityConfigurationProperties;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class ParticipantService {

    private final SecurityConfigurationProperties securityProperties;

    private final BaseRepository baseRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    /**
     * Will try to find an existing Participant for the given user.
     * If it doesn't exist, create it, and then return it.
     *
     * If security is disabled, return default user
     */
    public ParticipantEntity procureParticipant(Authentication authentication) {

        if (authentication == null) {
            if (securityProperties.isEnabled()) {
                throw new IllegalArgumentException("No user in context, even though security is enabled!");
            } else {
                return participantRepository.getDefaultParticipant();
            }
        }

        UserInfo user = UserUtil.getUserInfo(authentication);

        return participantRepository.findByIdentity(user.identifier(), user.provider())
            .orElseGet(() -> {
                ParticipantEntity entity = new ParticipantEntity();
                entity.setName(user.name());
                entity.setEmail(user.email());
                entity.setOpenIdIdentity(user.identifier());
                entity.setProvider(user.provider());
                entity.setPictureUrl(user.pictureUrl());

                return baseRepository.persist(entity);
            });
    }
}