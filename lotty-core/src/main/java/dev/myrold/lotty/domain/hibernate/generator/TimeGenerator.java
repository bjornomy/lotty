package dev.myrold.lotty.domain.hibernate.generator;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import java.time.Instant;

public class TimeGenerator implements ValueGenerator<Instant> {

    @Override
    public Instant generateValue(Session session, Object owner) {
        return Instant.now();
    }

}
