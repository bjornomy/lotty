package dev.myrold.lotty.domain.repository


import dev.myrold.lotty.JpaTestUtil
import dev.myrold.lotty.TestData
import groovy.sql.Sql
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import javax.sql.DataSource

@MicronautTest
class LotteryRepositoryTest extends Specification {

    @Subject
    @Inject
    LotteryRepository lotteryRepository

    @Inject
    BaseRepository baseRepository

    @Inject @Shared DataSource ds

    Sql sql

    def setup() {
        sql = new Sql(ds)
    }

    def 'create lottery'() {
        given:
            def schedule = baseRepository.persist(TestData.scheduleOne)
            def participant = baseRepository.persist(TestData.participantOne)
        when:
            def persisted = baseRepository.persistAndFlush(TestData.lotteries[0])
        then:
            lotteryRepository.findOne(persisted.id)
        cleanup:
            JpaTestUtil.removeAll(baseRepository, schedule, participant, persisted)
    }

    def 'should fetch relationships'() {
        given:
            // sequence is important!
            def schedule = baseRepository.persist(TestData.scheduleOne)
            def participants = baseRepository.persistAll(TestData.participants)
            def lottery = baseRepository.persist(TestData.lotteryWithDrawingAndPrice)
        and:
            lottery.addParticipants(participants.toSet())
            lottery.addDrawing(TestData.drawing)
            lottery = baseRepository.persist(lottery)
            lottery.getDrawings()[0].addPrice(TestData.priceOne)
            lottery = baseRepository.persist(lottery)
        when:
            def optFound = lotteryRepository.findOne(lottery.id)
        then:
            optFound.isPresent()
            def found = optFound.get()

            found.participants.size() == 2
            found.drawings.size() == 1

            found.drawings[0].prices.size() == 1
            found.drawings[0].prices[0].getName() == TestData.priceOne.name
        cleanup:
            JpaTestUtil.removeAll(baseRepository, schedule)
            JpaTestUtil.removeAll(baseRepository, participants)
            JpaTestUtil.removeAll(baseRepository, lottery)
    }

    def 'find last, empty'() {
        expect:
            lotteryRepository.findLast().size() == 0
    }

    def 'find last'() {
        given:
            def persisted = JpaTestUtil.persistAll(baseRepository)
        expect:
            lotteryRepository.findLast().isEmpty() == false
        cleanup:
            JpaTestUtil.removeAll(baseRepository, persisted)
    }

    def 'find last with limit'() {
        given:
            def persisted = JpaTestUtil.persistAll(baseRepository)
        expect:
            lotteryRepository.findLast(2).size() == 2
        cleanup:
            JpaTestUtil.removeAll(baseRepository, persisted)
    }

    def 'find last owned by participant'() {
        given:
            def persisted = JpaTestUtil.persistAll(baseRepository)
        expect:
            lotteryRepository.findLast(TestData.participantOne).size() == 3
            lotteryRepository.findLast(TestData.participantTwo).size() == 1
        cleanup:
            JpaTestUtil.removeAll(baseRepository, persisted)
    }

    def 'find last owned by participant with limit'() {
        given:
            def persisted = JpaTestUtil.persistAll(baseRepository)
        expect:
            lotteryRepository.findLast(TestData.participantOne, 2).size() == 2
        cleanup:
            JpaTestUtil.removeAll(baseRepository, persisted)
    }

}
