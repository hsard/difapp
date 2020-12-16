package com.waes.diffapp.repository;

import java.util.Optional;

/**
 * Basic Crud Repository interface
 *
 * @param <K> class of Key object
 * @param <V> class of value object
 * @author HS
 */
public interface ObjectRepository<K, V> {

    /**
     * Retrieves entity value by the key
     *
     * @param key the identifier key of the entity in repository
     * @return the object for the given key, {@literal null} otherwise.
     */
    Optional<V> get(K key);

    /**
     * adds entity for the key
     *
     * @param key   the identifier key of the entity in repository
     * @param entity the entity object associated with {@literal key} in repository
     * @throws IllegalArgumentException if {@literal key} is {@literal null}.
     */
    V add(K key, V entity);

    /**
     * removes the entity for the key from repository
     *
     * @param key the identifier key of the entity in repository
     */
    void remove(K key);
}
