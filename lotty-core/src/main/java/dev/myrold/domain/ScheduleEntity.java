package dev.myrold.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import dev.myrold.api.ScheduleFrequency;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Entity
@Introspected
@Table(schema = "lotty")
public class ScheduleEntity implements BaseEntity<Long> {

    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ScheduleFrequency frequency;

    private String target;

    @Version
    private Integer version;

}
