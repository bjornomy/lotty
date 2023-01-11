package dev.myrold.lotty.domain;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

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
public class ParticipantEntity extends LottyBaseEntity implements BaseEntity<Tsid>, TsidBaseEntityAware {

    private String email;

    @Column(name = "participant_name")
    private String name;

    private String pictureUrl;

    @NaturalId
    private String openIdIdentity;

    private String provider;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = TsidGenerator.GENERATOR_NAME)
    @GenericGenerator(name = TsidGenerator.GENERATOR_NAME, strategy = TsidGenerator.GENERATOR_FQN)
    private Tsid id;

    @Version
    private Integer version;

}
