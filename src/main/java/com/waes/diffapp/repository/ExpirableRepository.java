package com.waes.diffapp.repository;

import com.waes.diffapp.model.ExpirableBean;

/**
 * Interface adds mandatoriness of tracked(Last access tracking) objects as values
 * in order to keep track of expired objects and remove them from repository, extends {@link ObjectRepository}
 *
 * @param <K> class of Key object
 * @param <V> class of value object, must extend {@link ExpirableBean}
 * @author HS
 */
public interface ExpirableRepository<K, V extends ExpirableBean> extends ObjectRepository<K, V> {

    /**
     * Method checks is the entity session is expired or not
     *
     * @param entity extending ExpirableBean
     * @return true: if expired, false: not expired
     *
     * @author HS
     */
    boolean isSessionExpired(V entity);

    /**
     * Refreshing session, by setting lastAccessedTime to system current millis
     *
     * @param entity extending ExpirableBean
     *
     * @author HS
     */
    void refreshSession(V entity);
}
