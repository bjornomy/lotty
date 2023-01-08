package dev.myrold.mapper;

import dev.myrold.api.Participant;
import dev.myrold.domain.ParticipantEntity;
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
