package com._4dconcept.springframework.data.basex.core.mapping;

import org.springframework.data.mapping.PersistentEntity;

public interface BasexPersistentEntity<T> extends PersistentEntity<T, BasexPersistentProperty> {

    /**
     * @return the uri the entity shall be persisted to.
     */
    String getUri();

    /**
     * @return the default defaultCollection the entity should be created with
     */
    String getDefaultCollection();

    /**
     * @return true if the id is to be found in property fragment
     */
    boolean idInPropertyFragment();

}