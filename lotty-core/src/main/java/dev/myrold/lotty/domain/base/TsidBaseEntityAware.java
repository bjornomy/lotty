package dev.myrold.lotty.domain.base;

import com.github.f4b6a3.tsid.Tsid;

public interface TsidBaseEntityAware extends BaseEntity<Tsid> {

    default String getIdAsString() {
        return getId().toString();
    }
}
