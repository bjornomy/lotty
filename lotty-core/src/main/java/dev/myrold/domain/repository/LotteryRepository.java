package dev.myrold.domain.repository;

import com.github.f4b6a3.tsid.Tsid;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.domain.LotteryEntity;
import io.micronaut.data.annotation.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class LotteryRepository {

    @PersistenceContext
    private final EntityManager em;

    public Optional<LotteryEntity> findOne(Tsid id) {
        List<LotteryEntity> lotteries = em.createQuery("""
                select l from LotteryEntity l
                where l.id = :id""", LotteryEntity.class
            )
            .setParameter("id", id.toLong())
            .getResultList();

        lotteries = fetchRelationships(lotteries);

        return lotteries.stream().findFirst();
    }

    public List<LotteryEntity> findLast() {
        return findLast(10);
    }

    public List<LotteryEntity> findLast(int limit) {

        // we do double join fetch to avoid MultipleBagsException.
        // Hibernate should optimize the fetch internally
        // https://vladmihalcea.com/hibernate-multiplebagfetchexception/
        List<LotteryEntity> lotteries = em.createQuery("""
                select l from LotteryEntity l
                order by l.createdAt""", LotteryEntity.class
            )
            .setMaxResults(limit)
            .getResultList();

        return fetchRelationships(lotteries);
    }

    private List<LotteryEntity> fetchRelationships(List<LotteryEntity> lotteries) {
        lotteries = em.createQuery("""
                select l from LotteryEntity l
                  join fetch ParticipantEntity p
                where l in :lotteries""", LotteryEntity.class
            )
            .setParameter("lotteries", lotteries)
            .getResultList();

        lotteries = em.createQuery("""
                select l from LotteryEntity l
                  join fetch DrawingEntity d
                where l in :lotteries""", LotteryEntity.class
            )
            .setParameter("lotteries", lotteries)
            .getResultList();

        return lotteries;
    }

}
