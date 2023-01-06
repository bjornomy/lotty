package dev.myrold.service;

import com.github.f4b6a3.tsid.Tsid;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.myrold.api.Lottery;
import dev.myrold.api.Schedule;
import dev.myrold.api.create.CreateLottery;
import dev.myrold.domain.LotteryEntity;
import dev.myrold.domain.ParticipantEntity;
import dev.myrold.domain.ScheduleEntity;
import dev.myrold.domain.repository.BaseRepository;
import dev.myrold.domain.repository.LotteryRepository;
import dev.myrold.mapper.LotteryMapper;
import dev.myrold.util.TsidUtil;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class LotteryService {

    private final BaseRepository baseRepository;
    private final LotteryRepository lotteryRepository;
    private final LotteryMapper lotteryMapper;

    private final ParticipantService participantService;

    public Optional<Lottery> createLottery(Principal principal, CreateLottery createRequest) {
        ParticipantEntity participant = participantService.procureParticipant(principal);

        LotteryEntity entity = new LotteryEntity();
        entity.setId(TsidUtil.next());
        entity.setName(createRequest.name());
        entity.setParticipationFee(createRequest.participationFee());
        entity.setCreatedBy(participant);

        entity.setSchedule(procureSchedule(createRequest.schedule()));

        LotteryEntity persisted = baseRepository.persist(entity);

        return Optional.ofNullable(lotteryMapper.mapToApi(persisted));
    }

    public List<Lottery> findLast() {
        return lotteryRepository.findLast().stream()
            .map(lotteryMapper::mapToApi)
            .collect(Collectors.toList());
    }

    public Optional<Lottery> findOne(Tsid id) {
        return lotteryRepository.findOne(id)
            .map(lotteryMapper::mapToApi);
    }

    public void deleteLottery(String id) {
        baseRepository.detach(LotteryEntity.class, TsidUtil.stringToLong(id));
    }

    public boolean exists(String id) {
        return baseRepository.exists(LotteryEntity.class, TsidUtil.stringToLong(id));
    }

    public boolean isOwnedBy(Principal principal, String id) {
        ParticipantEntity participant = participantService.procureParticipant(principal);

        return exists(id) && lotteryRepository.findOne(Tsid.from(id))
            .map(l -> l.getCreatedBy().idEquals(participant))
            .orElse(Boolean.FALSE);
    }

    private ScheduleEntity procureSchedule(Schedule schedule) {
        if (schedule.id() != null) {
            Long scheduleId = TsidUtil.stringToLong(schedule.id());
            return baseRepository.getReference(ScheduleEntity.class, scheduleId);
        } else {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setId(TsidUtil.next());
            scheduleEntity.setFrequency(schedule.frequency());
            scheduleEntity.setTarget(scheduleEntity.getTarget());

            return scheduleEntity;
        }
    }

}
