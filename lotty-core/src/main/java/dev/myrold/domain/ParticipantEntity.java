package dev.myrold.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class ParticipantEntity {

    @Id
    private Long id;

}
