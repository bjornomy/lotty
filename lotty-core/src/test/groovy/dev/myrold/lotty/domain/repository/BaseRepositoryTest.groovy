package dev.myrold.lotty.domain.repository

import ScheduleFrequency
import dev.myrold.lotty.domain.ScheduleEntity
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification
import spock.lang.Subject

@MicronautTest
class BaseRepositoryTest extends Specification {

    @Subject
    @Inject
    BaseRepository baseRepository

    def 'should persist entity'() {
        given:
            def schedule = new ScheduleEntity(frequency: ScheduleFrequency.Weekly, target: 'Monday')
        when:
            def persisted = baseRepository.persistAndFlush(schedule)
        then:
            !persisted.isNew()
    }

}
