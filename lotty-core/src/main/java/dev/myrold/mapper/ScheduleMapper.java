package dev.myrold.mapper;

import dev.myrold.api.Schedule;
import dev.myrold.domain.ScheduleEntity;
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
            entity.getTarget()
        );
    }
}
