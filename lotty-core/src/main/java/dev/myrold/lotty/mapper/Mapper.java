package dev.myrold.lotty.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Mapper<A, E> {

    A mapToApi(E entity);

    default List<A> mapToApi(List<E> entities) {
        return entities.stream().map(this::mapToApi).collect(Collectors.toList());
    }

    default Set<A> mapToApi(Set<E> entities) {
        return entities.stream().map(this::mapToApi).collect(Collectors.toSet());
    }
}
