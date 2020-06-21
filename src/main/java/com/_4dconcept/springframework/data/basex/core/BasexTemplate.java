package com._4dconcept.springframework.data.basex.core;

import com._4dconcept.springframework.data.basex.core.convert.BasexContentHolder;
import com._4dconcept.springframework.data.basex.core.convert.BasexConverter;
import com._4dconcept.springframework.data.basex.core.mapping.BasexPersistentEntity;
import com._4dconcept.springframework.data.basex.core.mapping.BasexPersistentProperty;
import org.basex.api.client.ClientQuery;
import org.basex.api.client.ClientSession;
import org.basex.core.cmd.Open;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

/**
 * @author Fabien Weyh
 *
 * Copied from spring-data-marklogic
 * @author Stéphane Toussaint
 */
public class BasexTemplate implements BasexOperations {

    /*private final BasexConverter basexConverter;

    public BasexTemplate(@Nullable BasexConverter basexConverter) {
        this.basexConverter = basexConverter == null ? getDefaultBasexConverter() : basexConverter;
    }*/

    private final ClientSession clientSession;

    public BasexTemplate(ClientSession clientSession, String databaseName) throws IOException {
        Assert.notNull(clientSession, "ClientSession must not be null!");
        Assert.hasText(databaseName, "Database name must not be empty!");
        Assert.isTrue(databaseName.matches("[^/\\\\.$\"\\s]+"),
                "Database name must not contain slashes, dots, spaces, quotes, or dollar signs!");

        this.clientSession = clientSession;
        clientSession.execute(new Open(databaseName));
    }

    private BasexConverter getDefaultBasexConverter() {
        // TODO :
        return new BasexConverter() {
            @Override
            public MappingContext<? extends BasexPersistentEntity<?>, BasexPersistentProperty> getMappingContext() {
                return null;
            }

            @Override
            public ConversionService getConversionService() {
                return null;
            }

            @Override
            public <R> R read(Class<R> type, BasexContentHolder source) {
                return null;
            }

            @Override
            public void write(Object source, BasexContentHolder sink) {

            }
        };
    }

    @Override
    public void insert(Object objectToSave) {

    }

    @Override
    public void insert(Object objectToSave, BasexCreateOperationOptions options) {

    }

    @Override
    public void save(Object objectToSave) {

    }

    @Override
    public void save(Object objectToSave, BasexCreateOperationOptions options) {

    }

    @Override
    public void remove(Object entity) {

    }

    @Override
    public <T> void remove(Object id, Class<T> entityClass) {

    }

    @Override
    public <T> void remove(Object id, Class<T> entityClass, BasexOperationOptions options) {

    }

    @Override
    public <T> void removeAll(Class<T> entityClass) {

    }

    @Override
    public <T> void removeAll(Class<T> entityClass, BasexOperationOptions options) {

    }

    @Override
    public <T> T findById(Object id, Class<T> entityClass) {
        return null;
    }

    @Override
    public <T> T findById(Object id, Class<T> entityClass, BasexOperationOptions options) {
        return null;
    }

    @Override
    public <T> T findOne(ClientQuery query, Class<T> entityClass) {
        return null;
    }

    @Override
    public <T> T findOne(ClientQuery query, Class<T> entityClass, BasexOperationOptions options) {
        return null;
    }

    @Override
    public <T> List<T> find(ClientQuery query, Class<T> entityClass) {
        return null;
    }

    @Override
    public <T> List<T> find(ClientQuery query, Class<T> entityClass, BasexOperationOptions options) {
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass, BasexOperationOptions options) {
        return null;
    }

    @Override
    public void invokeAdhocClientQuery(String query, BasexInvokeOperationOptions options) {

    }

    @Override
    public <T> T invokeAdhocClientQuery(String query, Class<T> resultClass, BasexInvokeOperationOptions options) {
        return null;
    }

    @Override
    public <T> List<T> invokeAdhocClientQueryAsList(String query, Class<T> resultClass, BasexInvokeOperationOptions options) {
        return null;
    }

    @Override
    public void invokeModule(String moduleName, BasexInvokeOperationOptions options) {

    }

    @Override
    public <T> T invokeModule(String moduleName, Class<T> resultClass, BasexInvokeOperationOptions options) {
        return null;
    }

    @Override
    public <T> List<T> invokeModuleAsList(String moduleName, Class<T> resultClass, BasexInvokeOperationOptions options) {
        return null;
    }

    @Override
    public <T> String resolveDefaultCollection(T entity, BasexOperationOptions options) {
        return null;
    }

    @Override
    public <T> Object resolveContentIdentifier(T entity) {
        return null;
    }

    // First (really) naive implementation
    @Override
    public long count(String query) throws IOException {
        ClientQuery clientQuery = clientSession.query(query);
        long count = 0;
        while(clientQuery.more()) {
            clientQuery.next();
            count++;
        }
        return count;
    }

    /*@Override
    public BasexConverter getConverter() {
        return this.basexConverter;
    }*/
}
