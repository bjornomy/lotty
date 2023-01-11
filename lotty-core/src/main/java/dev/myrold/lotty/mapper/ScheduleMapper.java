package dev.myrold.lotty.mapper;

import dev.myrold.lotty.api.Schedule;
import dev.myrold.lotty.api.frequency.ScheduleTarget;
import dev.myrold.lotty.domain.ScheduleEntity;
import jakarta.inject.Singleton;

@Singleton
public class ScheduleMapper implements Mapper<Schedule, ScheduleEntity> {

    @Override
    public Schedule mapToApi(ScheduleEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Schedule(
            entity.getIdAsString(),
            entity.getFrequency(),
            entity.getTarget(),
            ScheduleTarget.describe(entity.getFrequency(), entity.getTarget())
        );
    }
}
