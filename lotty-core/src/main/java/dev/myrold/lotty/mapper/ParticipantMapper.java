package dev.myrold.lotty.mapper;

import dev.myrold.lotty.api.Participant;
import dev.myrold.lotty.domain.ParticipantEntity;
import jakarta.inject.Singleton;

@Singleton
public class ParticipantMapper implements Mapper<Participant, ParticipantEntity> {

    public Participant mapToApi(ParticipantEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Participant(
            entity.getIdAsString(),
            entity.getName(),
            0,
            0
        );
    }
}
