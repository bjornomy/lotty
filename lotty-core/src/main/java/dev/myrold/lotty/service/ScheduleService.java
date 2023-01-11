package dev.myrold.lotty.service;

import com.github.f4b6a3.tsid.Tsid;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import dev.myrold.lotty.api.Schedule;
import dev.myrold.lotty.api.create.CreateSchedule;
import dev.myrold.lotty.domain.ScheduleEntity;
import dev.myrold.lotty.domain.repository.BaseRepository;
import dev.myrold.lotty.domain.repository.ScheduleRepository;
import dev.myrold.lotty.mapper.ScheduleMapper;
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
        scheduleEntity.setFrequency(createSchedule.scheduleFrequency());
        scheduleEntity.setTarget(createSchedule.target());

        ScheduleEntity persisted = baseRepository.persist(scheduleEntity);
        return Optional.ofNullable(scheduleMapper.mapToApi(persisted));
    }

    public Optional<Schedule> findOne(Tsid id) {
        return scheduleRepository.findOne(id)
            .map(scheduleMapper::mapToApi);
    }

    public Set<Schedule> findMostUsed() {
        return scheduleRepository.findMostUsed().stream()
            .map(scheduleMapper::mapToApi)
            .collect(Collectors.toSet());
    }

}
