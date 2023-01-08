package dev.myrold.domain;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import java.time.Instant;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

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
public class DrawingEntity implements BaseEntity<Tsid>, LottyBaseEntityAware {

    @OneToMany
    private List<PriceEntity> prices;

    private Instant drawnAt;


    @Id
    @Convert(converter = TsidConverter.class)
    @GeneratorType(type = TsidGenerator.class, when = GenerationTime.INSERT)
    private Tsid id;

    @Embedded
    LottyBaseEntity base;

    @Version
    private Integer version;

}
