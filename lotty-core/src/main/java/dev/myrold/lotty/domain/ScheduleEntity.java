package dev.myrold.lotty.domain;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import dev.myrold.lotty.api.ScheduleFrequency;
import dev.myrold.lotty.domain.base.BaseEntity;
import dev.myrold.lotty.domain.base.LottyBaseEntity;
import dev.myrold.lotty.domain.base.TsidBaseEntityAware;
import dev.myrold.lotty.domain.hibernate.generator.TsidGenerator;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Introspected
@EqualsAndHashCode(callSuper = true, exclude = {"version"})
public class ScheduleEntity extends LottyBaseEntity implements BaseEntity<Tsid>, TsidBaseEntityAware {

    @Enumerated(value = EnumType.STRING)
    private ScheduleFrequency frequency;

    private String target;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TsidGenerator.GENERATOR_NAME)
    @GenericGenerator(name = TsidGenerator.GENERATOR_NAME, strategy = TsidGenerator.GENERATOR_FQN)
    private Tsid id;

    @Version
    private Integer version;

}
