package dev.myrold.lotty.domain.repository;

import com.github.f4b6a3.tsid.Tsid;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.lotty.domain.ParticipantEntity;
import io.micronaut.data.annotation.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class ParticipantRepository {

    public static final Tsid DEFAULT_PARTICIPANT = Tsid.from(0);

    @PersistenceContext
    private final EntityManager em;

    public ParticipantEntity getDefaultParticipant() {
        return em.createQuery("""
                select p from ParticipantEntity p
                where p.id = :id""", ParticipantEntity.class
            )
            .setParameter("id", DEFAULT_PARTICIPANT)
            .getResultStream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                "The default participant (id = 0) does not exist. Are all migrations applied?"
            ));
    }

    public Optional<ParticipantEntity> findByIdentity(String identity, String provider) {
        return em.createQuery("""
                select p from ParticipantEntity p
                where p.openIdIdentity = :identity
                  and p.provider = :provider""", ParticipantEntity.class
            )
            .setParameter("identity", identity)
            .setParameter("provider", provider)
            .getResultStream()
            .findAny();
    }
}
