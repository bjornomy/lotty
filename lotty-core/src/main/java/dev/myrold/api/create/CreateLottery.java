package dev.myrold.api.create;

import java.math.BigDecimal;

import dev.myrold.api.Schedule;
import io.micronaut.core.annotation.Introspected;

@Introspected
public record CreateLottery(
    String name,
    BigDecimal participationFee,
    Schedule schedule
) {
}
