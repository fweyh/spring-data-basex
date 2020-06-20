package com._4dconcept.springframework.data.basex.core.mapping;

import org.springframework.core.annotation.AliasFor;
import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * Identifies a domain object to be persisted to Basex.
 *
 * @author Fabien Weyh
 *
 * Inspired from MongoDB project by:
 *
 * @author Jon Brisbin
 * @author Oliver Gierke
 * @author Christoph Strobl
 */
@Persistent
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Document {

    /**
     * The collection the document representing the entity is supposed to be stored in. If not configured, a default
     * collection name will be derived from the type's name. The attribute supports SpEL expressions to dynamically
     * calculate the collection to based on a per operation basis.
     *
     * @return the name of the collection to be used.
     */
    @AliasFor("collection")
    String value() default "";

    /**
     * The collection the document representing the entity is supposed to be stored in. If not configured, a default
     * collection name will be derived from the type's name. The attribute supports SpEL expressions to dynamically
     * calculate the collection to based on a per operation basis.
     *
     * @return the name of the collection to be used.
     */
    @AliasFor("value")
    String collection() default "";

    /**
     * Defines the default language to be used with this document.
     *
     * @return an empty String by default.
     * @since 1.6
     */
    String language() default "";

    /**
     * Defines the collation to apply when executing a query or creating indexes.
     *
     * @return an empty {@link String} by default.
     * @since 2.2
     */
    String collation() default "";

}
