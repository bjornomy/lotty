package dev.myrold.mapper;

import dev.myrold.api.Price;
import dev.myrold.domain.PriceEntity;
import jakarta.inject.Singleton;

@Singleton
public class PriceMapper implements Mapper<Price, PriceEntity> {

    public Price mapToApi(PriceEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Price(
            entity.getPriceType(),
            entity.getDescription()
        );
    }
}
