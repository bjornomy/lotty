package dev.myrold.domain.util;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import dev.myrold.config.TsidConfig;

public class TsidGenerator implements ValueGenerator<Tsid> {

    @Override
    public Tsid generateValue(Session session, Object owner) {
        return TsidConfig.INSTANCE.create();
    }

}
