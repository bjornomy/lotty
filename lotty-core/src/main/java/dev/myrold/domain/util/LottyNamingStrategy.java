package dev.myrold.domain.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import io.hypersistence.utils.hibernate.naming.CamelCaseToSnakeCaseNamingStrategy;
import io.hypersistence.utils.hibernate.type.util.Configuration;

/**
 * Extending the basic {@link PhysicalNamingStrategyStandardImpl} to remove
 * 'Entity' suffix from class names and convert to snake case when converting to table names.
 * IE: 'LotteryEntity'  -> 'lottery'
 * 'EntityRegister' -> 'entity_register'
 *
 * @see CamelCaseToSnakeCaseNamingStrategy
 */
public class LottyNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    public static final LottyNamingStrategy INSTANCE = new LottyNamingStrategy();

    private static final String ENTITY_SUFFIXED_CLASS_REGEX = "(.*)Entity$";
    private static final String NO_SUFFIX_REGEX = "$1";

    public static final String CAMEL_CASE_REGEX = "([a-z]+)([A-Z]+)";

    public static final String SNAKE_CASE_PATTERN = "$1\\_$2";

    private final Configuration configuration;

    /**
     * Initialization constructor taking the default {@link Configuration} object.
     */
    public LottyNamingStrategy() {
        this.configuration = Configuration.INSTANCE;
    }

    /**
     * Initialization constructor taking the {@link Class} and {@link Configuration} objects.
     *
     * @param configuration custom {@link Configuration} object.
     */
    public LottyNamingStrategy(Configuration configuration) {
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

    private Identifier formatIdentifier(Identifier identifier) {
        if (identifier != null) {
            String entityName = identifier.getText();

            String name = entityName.replaceAll(ENTITY_SUFFIXED_CLASS_REGEX, NO_SUFFIX_REGEX)
                .replaceAll(CAMEL_CASE_REGEX, SNAKE_CASE_PATTERN)
                .toLowerCase();

            if (!name.equals(entityName)) {
                return Identifier.toIdentifier(name, identifier.isQuoted());
            } else {
                return identifier;
            }
        } else {
            return null;
        }
    }
}
