package dev.myrold.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Entity
@Introspected
@Table(schema = "lotty")
public class ParticipantEntity implements BaseEntity<Long> {

    @Id
    private Long id;

    @Column(name = "oauth_identity")
    private String identity;

    @Column(name = "participant_name")
    private String name;

    private Instant createdAt = Instant.now();

    @Version
    private Integer version;

}
