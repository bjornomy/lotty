package dev.myrold.domain.base;

import com.github.f4b6a3.tsid.Tsid;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TsidConverter implements AttributeConverter<Tsid, Long> {

    @Override
    public Long convertToDatabaseColumn(Tsid attribute) {
        return attribute.toLong();
    }

    @Override
    public Tsid convertToEntityAttribute(Long dbData) {
        return Tsid.from(dbData);
    }

}
