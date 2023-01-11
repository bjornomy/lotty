package dev.myrold.lotty.mapper;

import dev.myrold.lotty.api.Price;
import dev.myrold.lotty.domain.PriceEntity;
import jakarta.inject.Singleton;

@Singleton
public class PriceMapper implements Mapper<Price, PriceEntity> {

    public Price mapToApi(PriceEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Price(
            entity.getIdAsString(),
            entity.getName(),
            entity.getDescription(),
            entity.getWinner().getIdAsString()
        );
    }
}
