package com._4dconcept.springframework.data.basex;

import com._4dconcept.springframework.data.basex.config.AbstractBasexConfiguration;
import com._4dconcept.springframework.data.basex.config.DefaultBasexConfiguration;
import com._4dconcept.springframework.data.basex.core.BasexOperations;
import com._4dconcept.springframework.data.basex.core.BasexTemplate;
import org.basex.api.client.ClientSession;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

public class SampleApplication {

    public static void main(String[] args) throws IOException {

        AbstractBasexConfiguration basexConfig = new DefaultBasexConfiguration();
        ClientSession clientSession = basexConfig.getClientSession();

        /*MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "database");
        mongoOps.insert(new Person("Joe", 34));
        System.out.println(mongoOps.count(new Query(where("name").is("Joe")), Person.class));*/

        BasexOperations operations = new BasexTemplate(clientSession, "default-data");

        long count = operations.count("declare namespace tei=\"http://www.tei-c.org/ns/1.0\"; //tei:TEI[@n='2']//tei:p");
        System.out.println(count);
        Assert.state(count == 965);

        List<String> strings = operations.invokeAdhocQueryAsList("declare namespace tei=\"http://www.tei-c.org/ns/1.0\"; //tei:TEI[@n='2']//tei:p", String.class, null);
        System.out.println(strings.size());
        Assert.state(strings.size() == 965);
        System.out.println(strings.get(10));
        Assert.state("<p xmlns=\"http://www.tei-c.org/ns/1.0\" xmlns:local=\"http://www.philo.wiki\">Per ternitatem intelligo ipsam existentiam quatenus ex sola rei tern definitione necessario sequi concipitur.</p>".equals(strings.get(10)));

        String string = operations.invokeAdhocQuery("declare namespace tei=\"http://www.tei-c.org/ns/1.0\"; (//tei:TEI[@n='2']//tei:p)[15]", String.class, null);
        System.out.println(string);
        Assert.state("<p xmlns=\"http://www.tei-c.org/ns/1.0\" xmlns:local=\"http://www.philo.wiki\">Ex data causa determinata necessario sequitur effectus et contra si nulla detur determinata causa, impossibile est ut effectus sequatur.</p>".equals(string));

        Long paraLength = operations.invokeAdhocQuery("declare namespace tei=\"http://www.tei-c.org/ns/1.0\"; string-length((//tei:TEI[@n='2']//tei:p)[15])", Long.class, null);
        System.out.println(paraLength);
        Assert.state(new Long(136).equals(paraLength));
    }

}
