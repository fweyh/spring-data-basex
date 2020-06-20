package com._4dconcept.springframework.data.basex.core.convert;

import com._4dconcept.springframework.data.basex.core.mapping.BasexPersistentEntity;
import com._4dconcept.springframework.data.basex.core.mapping.BasexPersistentProperty;
import org.springframework.data.convert.EntityConverter;

/**
 * Central Basex specific converter interface which combines {@link BasexWriter} and {@link BasexReader}.
 *
 * @author Fabien Weyh
 *
 * Copied from spring-data-marklogic:
 *
 * @author Stéphane Toussaint
 */
public interface BasexConverter extends EntityConverter<BasexPersistentEntity<?>, BasexPersistentProperty, Object, BasexContentHolder>,
        BasexWriter<Object>, BasexReader<Object> {
}
