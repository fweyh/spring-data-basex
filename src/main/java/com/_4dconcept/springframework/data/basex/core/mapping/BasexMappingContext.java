package com._4dconcept.springframework.data.basex.core.mapping;

import org.springframework.data.mapping.context.AbstractMappingContext;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.model.Property;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.util.TypeInformation;

/**
 * Default implementation of a {@link MappingContext} for Marklogic using {@link BasicBasexPersistentEntity} and
 * {@link BasexPersistentProperty} as primary abstractions.
 *
 * @author Stéphane Toussaint
 */
public class BasexMappingContext extends AbstractMappingContext<BasicBasexPersistentEntity<?>, BasexPersistentProperty> {

    @Override
    protected <T> BasicBasexPersistentEntity<T> createPersistentEntity(TypeInformation<T> typeInformation) {
        return new BasicBasexPersistentEntity<>(typeInformation);
    }

    @Override
    protected BasexPersistentProperty createPersistentProperty(Property property,
                                                                   BasicBasexPersistentEntity<?> owner, SimpleTypeHolder simpleTypeHolder) {
        return new BasicBasexPersistentProperty(property, owner, simpleTypeHolder);
    }
}
