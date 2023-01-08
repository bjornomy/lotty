package dev.myrold.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class LotteryParticipantId implements Serializable {

    @Column
    private Long lotteryId;

    @Column
    private Long participantId;

}