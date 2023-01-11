package dev.myrold.lotty.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import dev.myrold.lotty.domain.base.BaseEntity;
import dev.myrold.lotty.domain.base.LotteryParticipantId;
import dev.myrold.lotty.domain.base.LottyBaseEntity;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;


@Data
@Entity
@Introspected
public class LotteryParticipant extends LottyBaseEntity implements BaseEntity<LotteryParticipantId> {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private LotteryEntity lottery;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ParticipantEntity participant;

    private boolean paymentReceived;

    @Version
    private Integer version;


    @Override public LotteryParticipantId getId() {
        LotteryParticipantId lp = new LotteryParticipantId();
        lp.setLotteryId(this.lottery.getId());
        lp.setParticipantId(this.participant.getId());

        return lp;
    }

    public static LotteryParticipant from(LotteryEntity lottery, ParticipantEntity participant) {
        LotteryParticipant lp = new LotteryParticipant();
        lp.setLottery(lottery);
        lp.setParticipant(participant);

        return lp;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LotteryParticipant that = (LotteryParticipant) o;

        return this.paymentReceived == that.isPaymentReceived()
            && this.lottery.idEquals(that.getLottery())
            && this.participant.idEquals(that.getParticipant());
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), this.lottery.getId(), this.participant.getId(), this.paymentReceived);
    }

    @Override
    public String toString() {
        return "LotteryParticipant{" +
            "lottery=" + lottery.getId() +
            ", participant=" + participant.getId() +
            ", paymentReceived=" + paymentReceived +
            ", version=" + version +
            '}';
    }
}
