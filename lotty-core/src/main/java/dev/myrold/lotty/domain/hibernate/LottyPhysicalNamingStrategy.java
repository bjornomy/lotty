package dev.myrold.lotty.domain.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import io.hypersistence.utils.hibernate.naming.CamelCaseToSnakeCaseNamingStrategy;
import io.hypersistence.utils.hibernate.type.util.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * Extending the basic {@link PhysicalNamingStrategyStandardImpl} to remove
 * 'Entity' suffix from class names and convert to snake case when converting to table names.
 * IE: 'LotteryEntity'  -> 'lottery'
 * 'EntityRegister' -> 'entity_register'
 *
 * @see CamelCaseToSnakeCaseNamingStrategy
 */
@Slf4j
public class LottyPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl implements EntityIdentifierFormatter {

    public static final LottyPhysicalNamingStrategy INSTANCE = new LottyPhysicalNamingStrategy();

    private final Configuration configuration;

    /**
     * Initialization constructor taking the default {@link Configuration} object.
     */
    public LottyPhysicalNamingStrategy() {
        this.configuration = Configuration.INSTANCE;
    }

    /**
     * Initialization constructor taking the {@link Class} and {@link Configuration} objects.
     *
     * @param configuration custom {@link Configuration} object.
     */
    public LottyPhysicalNamingStrategy(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalCatalogName(name, context));
    }

    @Override public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalSchemaName(name, context));
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalTableName(name, context));
    }

    @Override public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalSequenceName(name, context));
    }

    @Override public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalColumnName(name, context));
    }


}
