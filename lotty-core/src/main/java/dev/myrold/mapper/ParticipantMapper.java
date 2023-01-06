package dev.myrold.mapper;

import dev.myrold.api.Participant;
import dev.myrold.domain.ParticipantEntity;
import dev.myrold.util.TsidUtil;
import jakarta.inject.Singleton;

@Singleton
public class ParticipantMapper implements Mapper<Participant, ParticipantEntity> {

    public Participant mapToApi(ParticipantEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Participant(
            TsidUtil.longToString(entity.getId()),
            entity.getName(),
            0,
            0
        );
    }
}
