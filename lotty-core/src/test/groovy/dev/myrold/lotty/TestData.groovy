package dev.myrold.lotty

import ScheduleFrequency
import dev.myrold.lotty.domain.DrawingEntity
import dev.myrold.lotty.domain.LotteryEntity
import dev.myrold.lotty.domain.ParticipantEntity
import dev.myrold.lotty.domain.PriceEntity
import dev.myrold.lotty.domain.ScheduleEntity

import java.time.DayOfWeek
import java.time.Instant

final class TestData {

    static final scheduleOne = new ScheduleEntity(
        frequency: ScheduleFrequency.Weekly,
        target: DayOfWeek.MONDAY.name(),
    )

    static final scheduleTwo = new ScheduleEntity(
        frequency: ScheduleFrequency.Monthly,
        target: '01',
    )

    static final schedules = [scheduleOne, scheduleTwo]

    static final participantOne = new ParticipantEntity(
        email: 'test-one@example.com',
        name: 'Test One',
        openIdIdentity: '1',
        provider: 'test'
    )

    static final participantTwo = new ParticipantEntity(
        email: 'test-two@example.com',
        name: 'Test Two',
        openIdIdentity: '2',
        provider: 'test'
    )

    static final participants = [
        participantOne, participantTwo
    ]

    static final drawing = new DrawingEntity(drawnAt: Instant.now())

    static final priceOne = new PriceEntity(
        name: 'First Price',
        description: 'Fine Wine',
        winner: participantOne
    )

    static final lotteryWithDrawingAndPrice = new LotteryEntity(
        name: '1',
        participationFee: 1.0g,
        schedule: scheduleOne,
        endingAt: Instant.now(),
        ownedBy: participantTwo
    )

    static final lotteries = [
        new LotteryEntity(
            name: '1',
            participationFee: 1.0g,
            schedule: scheduleOne,
            endingAt: Instant.now(),
            ownedBy: participantOne
        ),
        new LotteryEntity(
            name: '1',
            participationFee: 1.0g,
            schedule: scheduleOne,
            endingAt: Instant.now(),
            ownedBy: participantOne
        ),
        new LotteryEntity(
            name: '1',
            participationFee: 1.0g,
            schedule: scheduleOne,
            endingAt: Instant.now(),
            ownedBy: participantOne
        ),
        new LotteryEntity(
            name: '1',
            participationFee: 1.0g,
            schedule: scheduleOne,
            endingAt: Instant.now(),
            ownedBy: participantTwo
        )
    ]

}
