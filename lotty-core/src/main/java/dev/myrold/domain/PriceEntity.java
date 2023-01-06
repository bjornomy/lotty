package dev.myrold.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import dev.myrold.api.PriceType;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Entity
@Introspected
@Table(schema = "lotty")
public class PriceEntity implements BaseEntity<Long> {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private PriceType priceType;

    private String description;

    @Version
    private Integer version;

}
