package dev.myrold.domain.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.domain.ParticipantEntity;
import io.micronaut.data.annotation.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class ParticipantRepository {

    @PersistenceContext
    private final EntityManager em;

    public ParticipantEntity getDefaultParticipant() {
        return em.createQuery("""
                select p from ParticipantEntity p
                where p.id = 0""", ParticipantEntity.class
            )
            .getResultStream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                "The default participant (id = 0) does not exist. Are all migrations applied?"
            ));
    }

    public Optional<ParticipantEntity> findByIdentity(String identity) {
        return em.createQuery("""
                select p from ParticipantEntity p
                where p.identity = :identity""", ParticipantEntity.class
            )
            .setParameter("identity", identity)
            .getResultStream()
            .findAny();
    }
}
