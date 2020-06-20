package com._4dconcept.springframework.data.basex.core;

import com._4dconcept.springframework.data.basex.core.convert.BasexConverter;

public interface BasexOperations {

    /**
     * Returns the underlying {@link BasexConverter}.
     *
     * @return never {@literal null}.
     */
    BasexConverter getConverter();

}
