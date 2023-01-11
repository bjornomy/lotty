package dev.myrold.lotty.domain.base;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.TypeDef;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import dev.myrold.lotty.domain.hibernate.generator.TimeGenerator;
import dev.myrold.lotty.domain.hibernate.type.TsidType;
import dev.myrold.lotty.domain.hibernate.generator.UserGenerator;
import dev.myrold.lotty.util.UserUtil;
import lombok.Data;

@Data
@MappedSuperclass
@TypeDef(name = TsidType.TSID_TYPE, typeClass = TsidType.class, defaultForType = Tsid.class)
public class LottyBaseEntity {

    @Column(updatable = false)
    @GeneratorType(type = UserGenerator.class, when = GenerationTime.INSERT)
    private String createdBy;

    @Column(updatable = false)
    @GeneratorType(type = TimeGenerator.class, when = GenerationTime.INSERT)
    private Instant createdAt;

    @GeneratorType(type = UserGenerator.class, when = GenerationTime.ALWAYS)
    private String modifiedBy;

    @GeneratorType(type = TimeGenerator.class, when = GenerationTime.ALWAYS)
    private Instant modifiedAt;

    //@PrePersist
    public void prePersist() {
        createdBy = UserUtil.getUserIdentityFromRequestContext();
        createdAt = Instant.now();
        modifiedBy = UserUtil.getUserIdentityFromRequestContext();
        modifiedAt = Instant.now();
    }

    //@PreUpdate
    public void preUpdate() {
        modifiedBy = UserUtil.getUserIdentityFromRequestContext();
        modifiedAt = Instant.now();
    }

}
