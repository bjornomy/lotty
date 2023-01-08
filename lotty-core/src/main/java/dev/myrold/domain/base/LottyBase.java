package dev.myrold.domain.base;

import java.time.Instant;

public interface LottyBase {

    String getCreatedBy();

    Instant getCreatedAt();

    String getModifiedBy();

    Instant getModifiedAt();

}
