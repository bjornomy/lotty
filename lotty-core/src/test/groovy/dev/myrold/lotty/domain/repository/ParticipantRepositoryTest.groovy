package dev.myrold.lotty.domain.repository

import dev.myrold.lotty.TestData
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification
import spock.lang.Subject

@MicronautTest
class ParticipantRepositoryTest extends Specification {

    @Inject
    @Subject
    ParticipantRepository participantRepository

    @Inject BaseRepository baseRepository



    def 'default Tsid'() {
        expect:
            ParticipantRepository.DEFAULT_PARTICIPANT.toLong() == 0
    }

    def 'should find default participant'() {
        expect:
            participantRepository.getDefaultParticipant()
    }

    def 'should create participant'() {
        expect:
            !baseRepository.persistAndFlush(TestData.participantOne).isNew()
    }

}
