package dev.myrold.domain.repository;

import com.github.f4b6a3.tsid.Tsid;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.domain.ScheduleEntity;
import io.micronaut.data.annotation.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class ScheduleRepository {

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

}
