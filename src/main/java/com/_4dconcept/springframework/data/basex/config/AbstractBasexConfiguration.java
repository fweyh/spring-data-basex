package com._4dconcept.springframework.data.basex.config;

import org.basex.api.client.ClientSession;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

@Configuration
public abstract class AbstractBasexConfiguration {

    protected abstract URI getBasexUri();

    public abstract ClientSession getClientSession() throws IOException;

}
