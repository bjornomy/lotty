package dev.myrold.lotty.domain.base;

import com.github.f4b6a3.tsid.Tsid;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LotteryParticipantId implements Serializable {

    private Tsid lotteryId;
    private Tsid participantId;

}