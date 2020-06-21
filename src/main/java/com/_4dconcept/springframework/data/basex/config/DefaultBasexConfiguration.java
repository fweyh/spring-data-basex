package com._4dconcept.springframework.data.basex.config;

import com._4dconcept.springframework.data.basex.config.AbstractBasexConfiguration;
import org.basex.api.client.ClientSession;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

@Configuration
public class DefaultBasexConfiguration extends AbstractBasexConfiguration {

    @Override
    protected URI getBasexUri() {
        return URI.create("localhost:1984");
    }

    @Override
    public ClientSession getClientSession() throws IOException {
        return new ClientSession("localhost", 1984, "admin", "admin");
    }
}