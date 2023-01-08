package dev.myrold.domain;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import dev.myrold.api.ScheduleFrequency;
import dev.myrold.domain.base.BaseEntity;
import dev.myrold.domain.base.LottyBaseEntity;
import dev.myrold.domain.base.LottyBaseEntityAware;
import dev.myrold.domain.base.TsidConverter;
import dev.myrold.domain.base.TsidGenerator;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Entity
@Introspected
@Table(schema = "lotty")
public class ScheduleEntity implements BaseEntity<Tsid>, LottyBaseEntityAware {

    @Enumerated(value = EnumType.STRING)
    private ScheduleFrequency frequency;

    private String target;


    @Id
    @Convert(converter = TsidConverter.class)
    @GeneratorType(type = TsidGenerator.class, when = GenerationTime.INSERT)
    private Tsid id;

    @Embedded
    private LottyBaseEntity base;

    @Version
    private Integer version;

}
