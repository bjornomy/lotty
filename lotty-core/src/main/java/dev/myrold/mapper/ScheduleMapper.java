package dev.myrold.mapper;

import dev.myrold.api.Schedule;
import dev.myrold.domain.ScheduleEntity;
import dev.myrold.util.TsidUtil;
import jakarta.inject.Singleton;

@Singleton
public class ScheduleMapper implements Mapper<Schedule, ScheduleEntity> {

    @Override
    public Schedule mapToApi(ScheduleEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Schedule(
            TsidUtil.longToString(entity.getId()),
            entity.getFrequency(),
            entity.getTarget()
        );
    }
}
