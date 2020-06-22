/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com._4dconcept.springframework.data.basex.core;

import com._4dconcept.springframework.data.basex.core.convert.BasexContentHolder;
import com._4dconcept.springframework.data.basex.core.convert.BasexConverter;
import com._4dconcept.springframework.data.basex.core.convert.BasexReader;
import com._4dconcept.springframework.data.basex.core.convert.MappingBasexConverter;
import com._4dconcept.springframework.data.basex.core.mapping.BasexMappingContext;
import org.basex.api.client.ClientQuery;
import org.basex.api.client.ClientSession;
import org.basex.core.cmd.Open;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabien Weyh
 * <p>
 * Modified from spring-data-marklogic
 * @author Stéphane Toussaint
 */
public class BasexTemplate implements BasexOperations {

    private final BasexConverter basexConverter;

    private final ClientSession clientSession;

    public BasexTemplate(ClientSession clientSession, String databaseName) throws IOException {
        Assert.notNull(clientSession, "ClientSession must not be null!");
        Assert.hasText(databaseName, "Database name must not be empty!");
        Assert.isTrue(databaseName.matches("[^/\\\\.$\"\\s]+"),
                "Database name must not contain slashes, dots, spaces, quotes, or dollar signs!");

        this.clientSession = clientSession;
        this.basexConverter = getDefaultBasexConverter();
        clientSession.execute(new Open(databaseName));
    }

    private static BasexConverter getDefaultBasexConverter() {
        MappingBasexConverter mappingBasexConverter = new MappingBasexConverter(new BasexMappingContext());
        mappingBasexConverter.afterPropertiesSet();
        return mappingBasexConverter;
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
    public void invokeAdhocQuery(String query, BasexInvokeOperationOptions options) {

    }

    @Override
    public <T> T invokeAdhocQuery(String query, Class<T> resultClass, BasexInvokeOperationOptions options) {
        List<T> resultList = invokeAdhocQueryAsList(query, resultClass, options);

        if (CollectionUtils.isEmpty(resultList)) {
            return null;
        } else if (resultList.size() == 1) {
            return resultList.get(0);
        } else {
            throw new DataRetrievalFailureException("Only one result expected. You should probably call invokeAdhocQueryAsList instead");
        }
    }

    @Override
    public <T> List<T> invokeAdhocQueryAsList(String query, Class<T> resultClass, BasexInvokeOperationOptions options) {
        List<T> resultList = new ArrayList<>();
        try (ClientQuery clientQuery = clientSession.query(query)) {
            while (clientQuery.more()) {
                resultList.add(prepareResultItem(clientQuery.next(), resultClass, options, basexConverter));
            }
        } catch (IOException e) {
            throw new DataRetrievalFailureException("Unable to invoke adhoc client query as list", e);
        }
        return resultList;
    }

    private <T> T prepareResultItem(String resultItem, Class<T> returnType, BasexInvokeOperationOptions options, BasexReader<Object> reader) {
        BasexContentHolder holder = new BasexContentHolder();
        holder.setContent(resultItem);
        return reader.read(returnType, holder);
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
        while (clientQuery.more()) {
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
