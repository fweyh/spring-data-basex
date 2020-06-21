package com._4dconcept.springframework.data.basex.core.mapping;

import org.springframework.data.mapping.model.BasicPersistentEntity;
import org.springframework.data.util.TypeInformation;

public class BasicBasexPersistentEntity<T> extends BasicPersistentEntity<T, BasexPersistentProperty> implements BasexPersistentEntity<T> {

    public BasicBasexPersistentEntity(TypeInformation<T> information) {
        super(information);
    }
}
