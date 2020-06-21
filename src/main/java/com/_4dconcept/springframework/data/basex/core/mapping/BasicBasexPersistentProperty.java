package com._4dconcept.springframework.data.basex.core.mapping;

import org.springframework.data.mapping.Association;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.model.AnnotationBasedPersistentProperty;
import org.springframework.data.mapping.model.Property;
import org.springframework.data.mapping.model.SimpleTypeHolder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Modified from spring-data-marklogic
 * @author Stéphane Toussaint
 */
public class BasicBasexPersistentProperty extends AnnotationBasedPersistentProperty<BasexPersistentProperty>
        implements BasexPersistentProperty {
    /**
     * Creates a new {@link AnnotationBasedPersistentProperty}.
     *
     * @param property         must not be {@literal null}.
     * @param owner            must not be {@literal null}.
     * @param simpleTypeHolder
     */
    public BasicBasexPersistentProperty(Property property, PersistentEntity<?, BasexPersistentProperty> owner, SimpleTypeHolder simpleTypeHolder) {
        super(property, owner, simpleTypeHolder);
    }

    @Override
    protected Association<BasexPersistentProperty> createAssociation() {
        return null;
    }

    @Override
    public Method getRequiredGetter() {
        return null;
    }

    @Override
    public Method getRequiredSetter() {
        return null;
    }

    @Override
    public Method getRequiredWither() {
        return null;
    }

    @Override
    public Field getRequiredField() {
        return null;
    }

    @Override
    public Association<BasexPersistentProperty> getRequiredAssociation() {
        return null;
    }

    @Override
    public <A extends Annotation> A getRequiredAnnotation(Class<A> annotationType) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean hasActualTypeAnnotation(Class<? extends Annotation> annotationType) {
        return false;
    }
}
