package dev.myrold.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Entity
@Introspected
@Table(schema = "lotty")
public class LotteryEntity implements BaseEntity<Long> {

    @Id
    private Long id;

    @Column(name = "lottery_name")
    private String name;

    private BigDecimal participationFee;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ScheduleEntity schedule;

    private Instant endingAt;

    @OneToMany
    private List<ParticipantEntity> participants;

    @OneToMany
    private List<DrawingEntity> drawings;

    @OneToOne
    private ParticipantEntity createdBy;

    private Instant createdAt;

    @Version
    private Integer version;

}
