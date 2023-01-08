package dev.myrold.domain.repository;

import com.github.f4b6a3.tsid.Tsid;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.domain.LotteryEntity;
import dev.myrold.domain.ParticipantEntity;
import io.micronaut.data.annotation.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class LotteryRepository {

    private static final int FETCH_LAST_DEFAULT = 10;

    @PersistenceContext
    private final EntityManager em;

    public Optional<LotteryEntity> findOne(Tsid id) {
        Stream<LotteryEntity> lotteries = em.createQuery("""
                select l from LotteryEntity l
                where l.id = :id""", LotteryEntity.class
            )
            .setParameter("id", id.toLong())
            .getResultStream();

        lotteries = fetchRelationships(lotteries);

        return lotteries.findFirst();
    }

    public Stream<LotteryEntity> findLast() {
        return findLast(FETCH_LAST_DEFAULT);
    }

    public Stream<LotteryEntity> findLast(int limit) {
        Stream<LotteryEntity> lotteries = em.createQuery("""
                select l from LotteryEntity l
                order by l.base.createdAt""", LotteryEntity.class
            )
            .setMaxResults(limit)
            .getResultStream();

        return fetchRelationships(lotteries);
    }

    public Stream<LotteryEntity> findLast(ParticipantEntity participant) {
        return findLast(participant, FETCH_LAST_DEFAULT);
    }

    public Stream<LotteryEntity> findLast(ParticipantEntity participant, int limit) {
        Stream<LotteryEntity> lotteries = em.createQuery("""
                select l from LotteryEntity l
                where l.ownedBy = :owner
                order by l.base.createdAt""", LotteryEntity.class
            )
            .setParameter("owner", participant)
            .setMaxResults(limit)
            .getResultStream();

        return fetchRelationships(lotteries);
    }


    /**
     * We do double join fetch to avoid {@link org.hibernate.loader.MultipleBagFetchException}.
     * Hibernate should optimize the fetch internally
     * <a href="https://vladmihalcea.com/hibernate-multiplebagfetchexception/">Hibernate: MultipleBagFetchException</a>
     **/
    private Stream<LotteryEntity> fetchRelationships(Stream<LotteryEntity> lotteries) {
        lotteries = em.createQuery("""
                select l from LotteryEntity l
                  join fetch ParticipantEntity p
                where l in :lotteries""", LotteryEntity.class
            )
            .setParameter("lotteries", lotteries)
            .getResultStream();

        lotteries = em.createQuery("""
                select l from LotteryEntity l
                  join fetch DrawingEntity d
                where l in :lotteries""", LotteryEntity.class
            )
            .setParameter("lotteries", lotteries)
            .getResultStream();

        return lotteries;
    }

}
