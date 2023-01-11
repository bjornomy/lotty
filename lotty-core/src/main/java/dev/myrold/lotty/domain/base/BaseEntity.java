package dev.myrold.lotty.domain.base;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Transient;

public interface BaseEntity<I> extends Serializable {

    I getId();

    Integer getVersion();

    @Transient default boolean isNew() {
        return getId() == null;
    }

    default boolean idEquals(BaseEntity<I> other) {
        return Objects.equals(this.getId(), other.getId());
    }

}
