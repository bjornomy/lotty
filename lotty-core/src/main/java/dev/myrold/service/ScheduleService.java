package dev.myrold.service;

import com.github.f4b6a3.tsid.Tsid;

import java.util.Optional;

import dev.myrold.api.Schedule;
import dev.myrold.api.create.CreateSchedule;
import dev.myrold.domain.ScheduleEntity;
import dev.myrold.domain.repository.BaseRepository;
import dev.myrold.domain.repository.ScheduleRepository;
import dev.myrold.mapper.ScheduleMapper;
import dev.myrold.util.TsidUtil;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class ScheduleService {

    private final BaseRepository baseRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;


    public Optional<Schedule> createSchedule(CreateSchedule createSchedule) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(TsidUtil.next());
        scheduleEntity.setFrequency(createSchedule.frequency());
        scheduleEntity.setTarget(createSchedule.target());

        ScheduleEntity persisted = baseRepository.persist(scheduleEntity);
        return Optional.ofNullable(scheduleMapper.mapToApi(persisted));
    }

    public Optional<Schedule> findOne(Tsid id) {
        return scheduleRepository.findOne(id)
            .map(scheduleMapper::mapToApi);
    }

}
