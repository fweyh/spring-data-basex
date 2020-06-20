package com._4dconcept.springframework.data.basex.config;

import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public abstract class AbstractBasexConfiguration {

    protected abstract URI getBasexUri();

}
