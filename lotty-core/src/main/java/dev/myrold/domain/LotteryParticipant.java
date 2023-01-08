package dev.myrold.domain;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import dev.myrold.domain.base.LotteryParticipantId;
import dev.myrold.domain.base.LottyBaseEntity;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;


@Data
@Entity
@Introspected
@Table(schema = "lotty")
public class LotteryParticipant {

    @OneToMany
    private Set<LotteryEntity> lotteries;

    @OneToMany
    private Set<ParticipantEntity> participants;


    @EmbeddedId
    private LotteryParticipantId id;

    @Embedded
    private LottyBaseEntity base;

    @Version
    private Integer version;

}
