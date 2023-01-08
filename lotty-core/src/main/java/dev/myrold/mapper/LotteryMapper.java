package dev.myrold.mapper;

import com.github.f4b6a3.tsid.Tsid;

import dev.myrold.api.Lottery;
import dev.myrold.domain.LotteryEntity;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class LotteryMapper implements Mapper<Lottery, LotteryEntity> {

    private final ParticipantMapper participantMapper;
    private final DrawingMapper drawingMapper;

    public Lottery mapToApi(LotteryEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Lottery(
            entity.getIdAsString(),
            entity.getName(),
            participantMapper.mapToApi(entity.getParticipants()),
            drawingMapper.mapToApi(entity.getDrawings())
        );
    }

}
