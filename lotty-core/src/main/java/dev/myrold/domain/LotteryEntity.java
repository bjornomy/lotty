package dev.myrold.domain;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import dev.myrold.domain.base.BaseEntity;
import dev.myrold.domain.base.LottyBaseEntity;
import dev.myrold.domain.base.LottyBaseEntityAware;
import dev.myrold.domain.base.TsidConverter;
import dev.myrold.domain.base.TsidGenerator;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Entity
@Introspected
@Table(schema = "lotty")
public class LotteryEntity implements BaseEntity<Tsid>, LottyBaseEntityAware {

    @Column(name = "lottery_name")
    private String name;

    private BigDecimal participationFee;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ScheduleEntity schedule;

    private Instant endingAt;

    @OneToMany
    private Set<ParticipantEntity> participants;

    @OneToMany
    private List<DrawingEntity> drawings;

    @OneToOne
    private ParticipantEntity ownedBy;


    @Id
    @Convert(converter = TsidConverter.class)
    @GeneratorType(type = TsidGenerator.class, when = GenerationTime.INSERT)
    private Tsid id;

    @Embedded
    private LottyBaseEntity base;

    @Version
    private Integer version;

}
