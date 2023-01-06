package dev.myrold.mapper;

import dev.myrold.api.Drawing;
import dev.myrold.domain.DrawingEntity;
import dev.myrold.util.TsidUtil;
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
            TsidUtil.longToString(entity.getId()),
            TsidUtil.longToString(entity.getWinner().getId()),
            priceMapper.mapToApi(entity.getPrice()),
            entity.getDrawnAt()
        );
    }

}
