package dev.myrold.lotty


import dev.myrold.lotty.domain.LotteryEntity
import dev.myrold.lotty.domain.ParticipantEntity
import dev.myrold.lotty.domain.base.BaseEntity
import dev.myrold.lotty.domain.LotteryParticipant
import dev.myrold.lotty.domain.ScheduleEntity
import dev.myrold.lotty.domain.repository.BaseRepository

final class JpaTestUtil {

    static final List<BaseEntity> persistAll(BaseRepository baseRepository) {
        List<BaseEntity> persisted = []
        persisted << baseRepository.persist(TestData.scheduleOne)
        persisted.addAll(baseRepository.persistAll(TestData.participants))
        persisted.addAll(baseRepository.persistAll(TestData.lotteries))

        persisted
    }

    static final <I, E extends BaseEntity<I>> void removeAll(BaseRepository baseRepository, E... persisted) {
        removeAll(baseRepository, Arrays.asList(persisted))
    }

    static final <I, E extends BaseEntity<I>, C extends Class<E>> void removeAll(BaseRepository baseRepository, List<E> persisted) {
        if (persisted == null) {
            return
        }

        Map<C, List<E>> entitiesByType = persisted.groupBy { it.class } as Map<C, List<E>>

        baseRepository.removeAll(entitiesByType.get(LotteryEntity))
        baseRepository.removeAll(entitiesByType.get(ScheduleEntity))
        baseRepository.removeAll(entitiesByType.get(ParticipantEntity))

        TestData.scheduleOne.id = null
        TestData.participants.each { it.id = null }
        TestData.lotteries.each { it.id = null }
    }

    static final List<LotteryParticipant> addParticipantsToLottery(
        BaseRepository baseRepository, LotteryEntity lottery, ParticipantEntity... participants
    ) {
        addParticipantsToLottery(baseRepository, lottery, participants as Set<ParticipantEntity>)
    }


    static final List<LotteryParticipant> addParticipantsToLottery(
        BaseRepository baseRepository, LotteryEntity lottery, Set<ParticipantEntity> participants
    ) {
        // the actual lottery is not updated here, and we would need to force hibernate
        // to re-fetch the lottery entity for this to take effect
        baseRepository.persistAll(participants.collect {
            new LotteryParticipant(lottery: lottery, participant: it)
        })
    }

}
