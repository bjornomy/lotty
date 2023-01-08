package dev.myrold.domain.util

import dev.myrold.domain.DrawingEntity
import dev.myrold.domain.LotteryEntity
import dev.myrold.domain.LotteryParticipant
import spock.lang.Specification
import spock.lang.Subject

import static org.hibernate.boot.model.naming.Identifier.toIdentifier

class LottyNamingStrategyTest extends Specification {

    @Subject
    LottyNamingStrategy namingStrategy = new LottyNamingStrategy()

    def 'should remove Entity suffix, from #className -> #tableName'() {
        expect:
            namingStrategy.toPhysicalTableName(toIdentifier(className), null) == toIdentifier(tableName)
        where:
            className                     | tableName
            LotteryEntity.simpleName      | 'lottery'
            DrawingEntity.simpleName      | 'drawing'
            LotteryParticipant.simpleName | 'lottery_participant'
    }
}
