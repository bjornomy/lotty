package dev.myrold.domain.base;

import com.github.f4b6a3.tsid.Tsid;

import java.time.Instant;

public interface LottyBaseEntityAware extends BaseEntity<Tsid>, LottyBase {

    LottyBaseEntity getBase();

    default String getIdAsString() {
        return getId().toString();
    }

    @Override
    default String getCreatedBy() {
        return getBase().getCreatedBy();
    }

    @Override
    default Instant getCreatedAt() {
        return getBase().getCreatedAt();
    }

    @Override
    default String getModifiedBy() {
        return getBase().getModifiedBy();
    }

    @Override
    default Instant getModifiedAt() {
        return getBase().getModifiedAt();
    }
}
