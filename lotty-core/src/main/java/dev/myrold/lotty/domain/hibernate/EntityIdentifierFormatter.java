package dev.myrold.lotty.domain.hibernate;

import org.hibernate.boot.model.naming.Identifier;

public interface EntityIdentifierFormatter {

    String ENTITY_SUFFIXED_CLASS_REGEX = "(.*)Entity$";
    String NO_SUFFIX_REGEX = "$1";

    String CAMEL_CASE_REGEX = "([a-z]+)([A-Z]+)";

    String SNAKE_CASE_PATTERN = "$1\\_$2";

    default Identifier formatIdentifier(Identifier identifier) {
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
