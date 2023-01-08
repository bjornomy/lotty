package dev.myrold.mapper;

import dev.myrold.api.Drawing;
import dev.myrold.domain.DrawingEntity;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class DrawingMapper implements Mapper<Drawing, DrawingEntity> {

    private final PriceMapper priceMapper;

    @Override
    public Drawing mapToApi(DrawingEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Drawing(
            entity.getIdAsString(),
            priceMapper.mapToApi(entity.getPrices()),
            entity.getDrawnAt()
        );
    }

}
