package dev.myrold.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<A, E> {

    A mapToApi(E entity);

    default List<A> mapToApi(List<E> entities) {
        return entities.stream().map(this::mapToApi).collect(Collectors.toList());
    }
}
