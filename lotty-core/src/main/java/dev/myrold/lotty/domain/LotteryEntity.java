package dev.myrold.lotty.domain;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import dev.myrold.lotty.domain.base.BaseEntity;
import dev.myrold.lotty.domain.base.LottyBaseEntity;
import dev.myrold.lotty.domain.base.TsidBaseEntityAware;
import dev.myrold.lotty.domain.hibernate.generator.TsidGenerator;
import io.micronaut.core.annotation.Introspected;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@Entity
@Introspected
@EqualsAndHashCode(callSuper = true, exclude = {"version"})
public class LotteryEntity extends LottyBaseEntity implements BaseEntity<Tsid>, TsidBaseEntityAware {

    @Column(name = "lottery_name")
    private String name;

    private BigDecimal participationFee;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ScheduleEntity schedule;

    private Instant endingAt;

    @OneToMany(mappedBy = "lottery")
    @Setter(AccessLevel.PRIVATE)
    private Set<LotteryParticipant> participants = new HashSet<>();

    @Setter(AccessLevel.PRIVATE)
    @JoinColumn(name = "lottery_id")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<DrawingEntity> drawings = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    private ParticipantEntity ownedBy;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TsidGenerator.GENERATOR_NAME)
    @GenericGenerator(name = TsidGenerator.GENERATOR_NAME, strategy = TsidGenerator.GENERATOR_FQN)
    private Tsid id;

    @Version
    private Integer version;


    public boolean addDrawing(DrawingEntity drawing) {
        if (drawing == null) {
            return false;
        }

        drawing.setLotteryId(this.getId());
        return this.drawings.add(drawing);
    }

    public void addDrawings(Set<DrawingEntity> drawings) {
        drawings.forEach(this::addDrawing);
    }

    public boolean addParticipant(ParticipantEntity participant) {
        if (participant == null) {
            return false;
        }

        return this.participants.add(LotteryParticipant.from(this, participant));
    }

    public void addParticipants(Set<ParticipantEntity> participants) {
        participants.forEach(this::addParticipant);
    }

    public Set<ParticipantEntity> getParticipants() {
        return participants.stream()
            .map(LotteryParticipant::getParticipant)
            .collect(Collectors.toSet());
    }
}
