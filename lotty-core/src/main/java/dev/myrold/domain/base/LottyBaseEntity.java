package dev.myrold.domain.base;

import java.time.Instant;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import dev.myrold.util.UserUtil;
import lombok.Data;

@Data
@Embeddable
public class LottyBaseEntity implements LottyBase {

    private String createdBy;
    private Instant createdAt;
    private String modifiedBy;
    private Instant modifiedAt;

    @PrePersist
    public void prePersist() {
        createdBy = UserUtil.getUserIdentityFromRequestContext();
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        modifiedBy = UserUtil.getUserIdentityFromRequestContext();
        modifiedAt = Instant.now();
    }

}
