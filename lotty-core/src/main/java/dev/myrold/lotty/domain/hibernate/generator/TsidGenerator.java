package dev.myrold.lotty.domain.hibernate.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

import dev.myrold.lotty.config.TsidConfig;

public class TsidGenerator implements IdentifierGenerator {

    public static final String GENERATOR_NAME = "tsid";
    public static final String GENERATOR_FQN = "dev.myrold.lotty.domain.hibernate.generator.TsidGenerator";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return TsidConfig.INSTANCE.create();
    }

}
