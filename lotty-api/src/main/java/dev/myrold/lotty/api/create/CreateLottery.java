package dev.myrold.lotty.api.create;

import java.math.BigDecimal;

import dev.myrold.lotty.api.Schedule;

public record CreateLottery(
    String name,
    BigDecimal participationFee,
    Schedule schedule
) {
}
