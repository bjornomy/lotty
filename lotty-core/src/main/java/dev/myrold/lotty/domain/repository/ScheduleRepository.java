package dev.myrold.lotty.domain.repository;

import com.github.f4b6a3.tsid.Tsid;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.lotty.domain.ScheduleEntity;
import io.micronaut.data.annotation.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class ScheduleRepository {

    public static final int DEFAULT_MOST_USED = 10;

    @PersistenceContext
    private final EntityManager em;

    public Optional<ScheduleEntity> findOne(Tsid id) {
        return em.createQuery("""
                select s from ScheduleEntity s
                where s.id = :id""", ScheduleEntity.class
            )
            .setParameter("id", id.toLong())
            .getResultStream()
            .findFirst();
    }

    public List<ScheduleEntity> findMostUsed() {
        return findMostUsed(DEFAULT_MOST_USED);
    }

    public List<ScheduleEntity> findMostUsed(int limit) {
        // TODO: implement in JPQL? Would require BiDirectional mapping
        //noinspection unchecked
        return em.createNativeQuery("""
                select s.* from lotty.schedule s
                  join lotty.lottery l on l.schedule_id = s.id
                group by s.id
                order by count(l.id) desc""", ScheduleEntity.class
            )
            .setMaxResults(limit)
            .getResultList();
    }

}
