package dev.myrold.lotty.domain.hibernate

import dev.myrold.lotty.domain.DrawingEntity
import dev.myrold.lotty.domain.LotteryEntity
import dev.myrold.lotty.domain.LotteryParticipant
import spock.lang.Specification
import spock.lang.Subject

import static org.hibernate.boot.model.naming.Identifier.toIdentifier

class LottyPhysicalNamingStrategyTest extends Specification {

    @Subject
    LottyPhysicalNamingStrategy namingStrategy = new LottyPhysicalNamingStrategy()

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
