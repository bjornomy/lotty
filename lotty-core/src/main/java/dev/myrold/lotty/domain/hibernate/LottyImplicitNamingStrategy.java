package dev.myrold.lotty.domain.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.model.source.spi.AttributePath;

import static java.lang.String.format;

public class LottyImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl implements EntityIdentifierFormatter {

    @Override public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
        String defaultEntityName = transformEntityName(source.getEntityNaming());
        return formatIdentifier(toIdentifier(defaultEntityName, source.getBuildingContext()));
    }

    @Override public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
        String columnName = format("%s_%s", decideReferenceTypeName(source), source.getReferencedColumnName());
        return formatIdentifier(toIdentifier(columnName, source.getBuildingContext()));
    }

    @Override public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        return formatIdentifier(super.determineBasicColumnName(source));
    }

    private static String decideReferenceTypeName(ImplicitJoinColumnNameSource source) {
        return source.getAttributePath() == null || attributePathIsPluralOfEntityName(source)
            ? source.getReferencedTableName().toString()
            : source.getAttributePath().getFullPath();
    }

    private static boolean attributePathIsPluralOfEntityName(ImplicitJoinColumnNameSource source) {
        AttributePath attributePath = source.getAttributePath();
        if (attributePath == null) {
            return false;
        }
        String regex = "(?i)^" + source.getReferencedTableName() + "e?s$";
        return attributePath.getFullPath().matches(regex);
    }

}
