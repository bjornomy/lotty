package dev.myrold.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class LotteryEntity {

    @Id
    private Long id;

    private String name;

    @OneToMany
    private List<ParticipantEntity> participants;

    @OneToMany
    private List<DrawingEntity> drawings;

}
