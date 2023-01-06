package dev.myrold.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.domain.BaseEntity;
import io.hypersistence.utils.spring.repository.HibernateRepositoryImpl;
import jakarta.inject.Singleton;

@Singleton
@Transactional
@PersistenceContext
public class BaseRepository extends HibernateRepositoryImpl<BaseEntity<?>> {

    private final EntityManager em;

    public BaseRepository(EntityManager entityManager) {
        super(entityManager);
        this.em = entityManager;
    }

    public <I, E extends BaseEntity<I>> boolean exists(Class<E> clazz, I id) {
        try {
            em.getReference(clazz, id);
        } catch (EntityNotFoundException e) {
            return false;
        }

        return true;
    }

    public <I, E extends BaseEntity<I>> E getReference(Class<E> clazz, I id) {
        return em.getReference(clazz, id);
    }

    public void detach(BaseEntity<?> entity) {
        em.detach(entity);
    }

    public <I, E extends BaseEntity<I>> void detach(Class<E> clazz, I id) {
        detach(getReference(clazz, id));
    }

}
