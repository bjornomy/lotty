package dev.myrold.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Entity
@Introspected
@Table(schema = "lotty")
public class DrawingEntity implements BaseEntity<Long> {

    @Id
    private Long id;

    @OneToOne
    private ParticipantEntity winner;

    @OneToOne
    private PriceEntity price;

    private Instant drawnAt;

    @Version
    private Integer version;

}
