package com._4dconcept.springframework.data.basex.core;

import com._4dconcept.springframework.data.basex.core.convert.BasexConverter;
import org.basex.api.client.ClientQuery;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * @author Fabien Weyh
 *
 * Modified from spring-data-marklogic
 * @author Stéphane Toussaint
 */
public interface BasexOperations {

    /**
     * Insert the given object.
     * Content will be converted if not one of supported type.
     * Uri will be computed as well as creation options (such as defaultCollection)
     * Insert is used to initially store the object into the database. To update an existing object use the save method.
     *
     * @param objectToSave the object to store
     */
    void insert(Object objectToSave);

    /**
     * Insert the given object at the specified uri with specified creation options.
     * Content will be converted if not one of supported type.
     * Insert is used to initially store the object into the database. To update an existing object use the save method.
     *
     * @param objectToSave the object to store
     * @param options content creation options
     */
    void insert(Object objectToSave, BasexCreateOperationOptions options);

    /**
     * Save the given object.
     * Content will be converted if not one of supported type.
     * The save method will retrieve uri location to store the content to.
     *
     * @param objectToSave the object to store
     */
    void save(Object objectToSave);

    /**
     * Save the given object.
     * Content will be converted if not one of supported type.
     * The save method will retrieve uri location to store the content to.
     *
     * @param objectToSave the object to store
     * @param options content creation options
     */
    void save(Object objectToSave, BasexCreateOperationOptions options);

    /**
     * Remove the given Entity
     * @param entity the entity to remove
     */
    void remove(Object entity);

    /**
     * Remove entity with corresponding identifier
     * @param id the identifier
     * @param entityClass the type of the document to remove
     * @param <T> The entity type
     */
    <T> void remove(Object id, Class<T> entityClass);

    /**
     * Remove entity with corresponding identifier
     * @param id the identifier
     * @param entityClass the type of the document to remove
     * @param options content deletion options
     * @param <T> The entity type
     */
    <T> void remove(Object id, Class<T> entityClass, BasexOperationOptions options);

    /**
     * Remove every entities of the given type.
     * @param entityClass the type of the documents to be removed
     * @param <T> The entity type
     */
    <T> void removeAll(Class<T> entityClass);

    /**
     * Remove every entities of the given type
     * @param entityClass the type of the documents to be removed
     * @param options content deletion options
     * @param <T> The entity type
     */
    <T> void removeAll(Class<T> entityClass, BasexOperationOptions options);

    /**
     * Returns the entity with the given id for the specified entity type.
     * @param id the id of the document to retrieve
     * @param entityClass the type of the document to retrieve
     * @param <T> The entity type
     *
     * @return the found entity or null if no entity found with the given id
     */
    @Nullable
    <T> T findById(Object id, Class<T> entityClass);

    /**
     * Returns the document with the given id for the specified entity type within explicit collection.
     * @param id the id of the document to retrieve
     * @param entityClass the type of the document to retrieve
     * @param options the explicit collection the document will be queried
     * @param <T> The entity type
     *
     * @return the found entity or null if no entity found with the given id
     */
    @Nullable
    <T> T findById(Object id, Class<T> entityClass, BasexOperationOptions options);

    /**
     * Returns content matching the given query
     * @param query the query that specifies criteria used to find contents
     * @param entityClass the entity class the content will be converted to
     * @param <T> The entity type
     *
     * @return the found entity or null if no entity found with the given {@link ClientQuery} constraint
     */
    @Nullable
    <T> T findOne(ClientQuery query, Class<T> entityClass);

    /**
     * Returns content matching the given query
     *
     * @param query the query that specifies criteria used to find contents
     * @param entityClass the entity class the content will be converted to
     * @param options search options
     * @param <T> The entity type
     *
     * @return the found entity or null if no entity found with the given {@link ClientQuery} constraint
     */
    @Nullable
    <T> T findOne(ClientQuery query, Class<T> entityClass, BasexOperationOptions options);

    /**
     * Returns content matching the given query
     * @param query the query that specifies criteria used to find contents
     * @param entityClass the entity class the content will be converted to
     * @param <T> The entity type
     *
     * @return the found entities
     */
    <T> List<T> find(ClientQuery query, Class<T> entityClass);

    /**
     * Returns content matching the given query
     *
     * @param query the query that specifies criteria used to find contents
     * @param entityClass the entity class the content will be converted to
     * @param options search options
     * @param <T> The entity type
     *
     * @return the found entities
     */
    <T> List<T> find(ClientQuery query, Class<T> entityClass, BasexOperationOptions options);

    /**
     * Returns every contents of the given type
     *
     * @param entityClass the entity class of the entity to find
     * @param <T> The entity type
     * @return the found entities
     */
    <T> List<T> findAll(Class<T> entityClass);

    /**
     * Returns every contents of the given type
     *
     * @param entityClass the entity class of the entity to find
     * @param options search options
     * @param <T> The entity type
     * @return the found entities
     */
    <T> List<T> findAll(Class<T> entityClass, BasexOperationOptions options);

    /**
     * Execute the given XQuery script. Optional external variables can be pass with options parameters
     * @param query the query to execute
     * @param options optional options used for the query execution
     */
    void invokeAdhocQuery(String query, BasexInvokeOperationOptions options);

    /**
     * Execute the given XQuery script. Optional external variables can be pass with options parameters
     * The returned content if any will be converted as resultClass type.
     * More than one result will trigger a DataRetrievalFailureException. You must use invokeAdhocQueryAsList in such cases.
     * @param query the query to execute
     * @param resultClass the expected return content type.
     * @param options optional options used for the query execution
     * @param <T> The entity type
     * @return one result or null if no content returned
     */
    @Nullable
    <T> T invokeAdhocQuery(String query, Class<T> resultClass, BasexInvokeOperationOptions options);

    /**
     * Execute the given XQuery script. Optional external variables can be pass with options parameters
     * The returned content if any will be converted as resultClass type.
     * @param query the query to execute
     * @param resultClass the expected return content type.
     * @param options optional options used for the query execution
     * @param <T> The entity type
     * @return a List of result or null if no content returned
     */
    <T> List<T> invokeAdhocQueryAsList(String query, Class<T> resultClass, BasexInvokeOperationOptions options);

    /**
     * Execute the remote module script. Optional external variables can be pass with options parameters
     * @param moduleName the uri of the module to invoke
     * @param options optional options used for the module execution
     */
    void invokeModule(String moduleName, BasexInvokeOperationOptions options);

    /**
     * Execute the remote module script. Optional external variables can be pass with options parameters
     * The returned content if any will be converted as resultClass type.
     * More than one result will trigger a DataRetrievalFailureException. You must use invokeModuleAsList in such cases.
     * @param moduleName the uri of the module to invoke
     * @param resultClass the expected return content type.
     * @param options optional options used for the query execution
     * @param <T> The entity type
     * @return one result or null if no content returned
     */
    @Nullable
    <T> T invokeModule(String moduleName, Class<T> resultClass, BasexInvokeOperationOptions options);

    /**
     * Execute the remote module script. Optional external variables can be pass with options parameters
     * The returned content if any will be converted as resultClass type.
     * @param moduleName the uri of the module to invoke
     * @param resultClass the expected return content type.
     * @param options optional options used for the query execution
     * @param <T> The entity type
     * @return a List of result or null if no content returned
     */
    <T> List<T> invokeModuleAsList(String moduleName, Class<T> resultClass, BasexInvokeOperationOptions options);

    /**
     * Retrieve the default collection of the given entity
     * @param entity the entity
     * @param options search options
     * @param <T> The entity type
     * @return the default collection
     */
    @Nullable
    <T> String resolveDefaultCollection(T entity, BasexOperationOptions options);

    /**
     * Retrieve the content idenfitier of the given entity
     * @param entity the entity
     * @param <T> The entity type
     * @return the content identifier
     */
    @Nullable
    <T> Object resolveContentIdentifier(T entity);

    /**
     * Returns the number of documents for the given {@link ClientQuery}.
     *
     * @param query the query
     * @return the number of content matching the query
     */
    // long count(ClientQuery query);
    long count(String query) throws IOException;

    /**
     * Returns the underlying {@link BasexConverter}.
     *
     * @return never {@literal null}.
     */
    /*BasexConverter getConverter();*/

}
