package com._4dconcept.springframework.data.basex.core.convert;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;

/**
 * @author Fabien Weyh
 *
 * Modified from spring-data-marklogic
 * @author Stéphane Toussaint
 */
public abstract class AbstractBasexConverter implements BasexConverter, InitializingBean {

    private final GenericConversionService conversionService;

    protected AbstractBasexConverter(@Nullable GenericConversionService conversionService) {
        this.conversionService = conversionService == null ? new DefaultConversionService() : conversionService;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }
}
