package com._4dconcept.springframework.data.basex.config;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class AbstractBasexConfigurationTest {

    final URI testUri = URI.create("xdbc://user:password@localhost:8888");

    @Test
    public void getMappingBasePackage__ReturnsCurrentPackage() throws URISyntaxException {
        TestAbstractBaseXConfiguration configuration = new TestAbstractBaseXConfiguration("xdbc://user:password@localhost:8888");

        assertEquals(testUri, configuration.getBasexUri());
    }

    private static class TestAbstractBaseXConfiguration extends AbstractBasexConfiguration {

        private URI uri;

        TestAbstractBaseXConfiguration(String uri) {
            this.uri = URI.create(uri);
        }

        @Override
        protected URI getBasexUri() {
            return uri;
        }
    }

}