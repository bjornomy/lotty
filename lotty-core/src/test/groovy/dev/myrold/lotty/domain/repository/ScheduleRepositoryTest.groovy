package dev.myrold.lotty.domain.repository

import dev.myrold.lotty.TestData
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification
import spock.lang.Subject

@MicronautTest
class ScheduleRepositoryTest extends Specification {

    @Subject
    @Inject
    ScheduleRepository scheduleRepository

    @Inject
    BaseRepository baseRepository

    def 'should create schedule'() {
        expect:
            !baseRepository.persistAndFlush(TestData.scheduleOne).isNew()
    }

    def 'should find most used schedules'() {
        given:
            def schedules = baseRepository.persistAll(TestData.schedules)
            def lottery = TestData.lotteries[0]
            lottery.schedule = schedules[0]
            def owner = baseRepository.persist(lottery.ownedBy)
            lottery = baseRepository.persist(lottery)
        when:
            def mostUsed = scheduleRepository.findMostUsed(1)
        then:
            !mostUsed.isEmpty()
            mostUsed[0].id == schedules[0].id
        cleanup:
            baseRepository.removeAll([lottery, owner])
            baseRepository.removeAll(schedules)
    }
}
