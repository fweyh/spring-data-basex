package com._4dconcept.springframework.data.basex.core;

import com._4dconcept.springframework.data.basex.core.convert.BasexConverter;
import org.basex.api.client.ClientSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BasexTemplateTest {

    @Mock
    private ClientSession clientSession;

    @Test
    public void getConverter() {
        BasexTemplate template = new BasexTemplate(clientSession, "test");
        // BasexConverter converter = template.getConverter();
        assertNotNull(template);
    }
}