package dev.myrold.lotty.domain.repository;

import com.github.f4b6a3.tsid.Tsid;

import org.hibernate.annotations.QueryHints;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.lotty.domain.LotteryEntity;
import dev.myrold.lotty.domain.ParticipantEntity;
import io.micronaut.data.annotation.Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class LotteryRepository {

    private static final int FETCH_LAST_DEFAULT = 10;

    @PersistenceContext
    private final EntityManager em;

    public Optional<LotteryEntity> findOne(Tsid id) {
        List<LotteryEntity> lotteries = em.createQuery("""
                select l from LotteryEntity l
                where l.id = :id""", LotteryEntity.class
            )
            .setParameter("id", id)
            .getResultList();

        lotteries = fetchRelationships(lotteries);

        return lotteries.stream().findFirst();
    }

    public List<LotteryEntity> findLast() {
        return findLast(FETCH_LAST_DEFAULT);
    }

    public List<LotteryEntity> findLast(int limit) {
        List<LotteryEntity> lotteries = em.createQuery("""
                select l from LotteryEntity l
                order by l.createdAt""", LotteryEntity.class
            )
            .setMaxResults(limit)
            .getResultList();

        return fetchRelationships(lotteries);
    }

    public List<LotteryEntity> findLast(ParticipantEntity participant) {
        return findLast(participant, FETCH_LAST_DEFAULT);
    }

    public List<LotteryEntity> findLast(ParticipantEntity participant, int limit) {
        List<LotteryEntity> lotteries = em.createQuery("""
                select l from LotteryEntity l
                where l.ownedBy = :owner
                order by l.createdAt""", LotteryEntity.class
            )
            .setParameter("owner", participant)
            .setMaxResults(limit)
            .getResultList();

        return fetchRelationships(lotteries);
    }


    /**
     * We do double join fetch to avoid {@link org.hibernate.loader.MultipleBagFetchException}.
     * Hibernate should optimize the fetch internally
     * <a href="https://vladmihalcea.com/hibernate-multiplebagfetchexception/">Hibernate: MultipleBagFetchException</a>
     **/
    private List<LotteryEntity> fetchRelationships(List<LotteryEntity> lotteries) {

        if (lotteries.size() == 0) {
            return lotteries;
        }

        log.info("L: {}", lotteries);

        lotteries = em.createQuery("""
                select distinct l from LotteryEntity l
                  left join fetch l.participants
                where l in :lotteries""", LotteryEntity.class
            )
            .setParameter("lotteries", lotteries)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();

        lotteries = em.createQuery("""
                select distinct l from LotteryEntity l
                  left join fetch l.drawings
                where l in :lotteries""", LotteryEntity.class
            )
            .setParameter("lotteries", lotteries)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();

        return lotteries;
    }

}
