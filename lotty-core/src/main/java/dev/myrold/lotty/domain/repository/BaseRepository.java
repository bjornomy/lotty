package dev.myrold.lotty.domain.repository;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.myrold.lotty.domain.base.BaseEntity;
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

    public void flush() {
        em.flush();
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

    public <E extends BaseEntity<?>> void remove(E entity) {
        if (em.contains(entity)) {
            em.remove(entity);
        }
    }

    public <I, E extends BaseEntity<I>> void removeAll(Iterable<E> entities) {
        emDoForAll(this::remove, entities);
    }

    public <I, E extends BaseEntity<I>> void remove(Class<E> clazz, I id) {
        emDo(this::remove, clazz, id);
    }

    public <I, E extends BaseEntity<I>> void refresh(E entity) {
        emDo(em::refresh, entity);
    }

    public <I, E extends BaseEntity<I>> void refreshAll(Iterable<E> entities) {
        emDoForAll(em::refresh, entities);
    }


    private <I, E extends BaseEntity<I>> void emDo(Consumer<E> consumer, E entity) {
        if (entity == null) {
            return;
        }

        consumer.accept(entity);
    }

    private <I, E extends BaseEntity<I>> void emDo(Consumer<E> consumer, Class<E> clazz, I id) {
        if (clazz == null || id == null) {
            return;
        }

        consumer.accept(getReference(clazz, id));
    }

    private <I, E extends BaseEntity<I>> void emDoAndFlush(Consumer<E> consumer, E entity) {
        if (entity == null) {
            return;
        }

        emDo(consumer, entity);
        em.flush();
    }

    private <I, E extends BaseEntity<I>, R> void emDoForAll(Consumer<E> consumer, Iterable<E> entities) {
        if (entities == null) {
            return;
        }

        for (E entity : entities) {
            emDo(consumer, entity);
        }
    }

    private <I, E extends BaseEntity<I>, R> void emDoForAllAndFlush(Consumer<E> consumer, Iterable<E> entities) {
        if (entities == null) {
            return;
        }

        for (E entity : entities) {
            emDo(consumer, entity);
        }
        em.flush();
    }

}
