package dev.myrold.service;

import com.github.f4b6a3.tsid.Tsid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dev.myrold.api.Lottery;
import dev.myrold.api.Schedule;
import dev.myrold.api.create.CreateLottery;
import dev.myrold.domain.LotteryEntity;
import dev.myrold.domain.ParticipantEntity;
import dev.myrold.domain.ScheduleEntity;
import dev.myrold.domain.repository.BaseRepository;
import dev.myrold.domain.repository.LotteryRepository;
import dev.myrold.mapper.LotteryMapper;
import io.micronaut.security.authentication.Authentication;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class LotteryService {

    private final BaseRepository baseRepository;
    private final LotteryRepository lotteryRepository;
    private final LotteryMapper lotteryMapper;

    private final ParticipantService participantService;

    public Optional<Lottery> createLottery(Authentication authentication, CreateLottery createRequest) {
        ParticipantEntity participant = participantService.procureParticipant(authentication);

        LotteryEntity entity = new LotteryEntity();
        entity.setName(createRequest.name());
        entity.setParticipationFee(createRequest.participationFee());
        entity.setOwnedBy(participant);

        entity.setSchedule(procureSchedule(createRequest.schedule()));

        LotteryEntity persisted = baseRepository.persist(entity);

        return Optional.ofNullable(lotteryMapper.mapToApi(persisted));
    }

    public Stream<Lottery> findLast() {
        return lotteryRepository.findLast()
            .map(lotteryMapper::mapToApi);
    }

    public Stream<Lottery> findLastForParticipant(Authentication authentication) {
        ParticipantEntity participant = participantService.procureParticipant(authentication);

        return lotteryRepository.findLast(participant)
            .map(lotteryMapper::mapToApi);
    }

    public Optional<Lottery> findOne(Tsid id) {
        return lotteryRepository.findOne(id)
            .map(lotteryMapper::mapToApi);
    }

    public void deleteLottery(String id) {
        baseRepository.detach(LotteryEntity.class, Tsid.from(id));
    }

    public boolean exists(String id) {
        return baseRepository.exists(LotteryEntity.class, Tsid.from(id));
    }

    public boolean isOwnedBy(Authentication authentication, String id) {
        ParticipantEntity participant = participantService.procureParticipant(authentication);

        return exists(id) && lotteryRepository.findOne(Tsid.from(id))
            .map(l -> l.getOwnedBy().idEquals(participant))
            .orElse(Boolean.FALSE);
    }

    private ScheduleEntity procureSchedule(Schedule schedule) {
        if (schedule.id() != null) {
            return baseRepository.getReference(ScheduleEntity.class, schedule.idAsTsid());
        } else {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setFrequency(schedule.frequency());
            scheduleEntity.setTarget(scheduleEntity.getTarget());

            return scheduleEntity;
        }
    }

}
