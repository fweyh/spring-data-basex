package com._4dconcept.springframework.data.basex.repository.support;

import com._4dconcept.springframework.data.basex.core.BasexOperations;
import com._4dconcept.springframework.data.basex.repository.BasexRepository;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * {@link org.springframework.beans.factory.FactoryBean} to create {@link BasexRepository} instances.
 *
 * @author Fabien Weyh
 *
 * Copied from spring-data-mongodb:
 *
 * @author Oliver Gierke
 */
public class BasexRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends RepositoryFactoryBeanSupport<T, S, ID> {

    private @Nullable
    BasexOperations operations;
    private boolean createIndexesForQueryMethods = false;
    private boolean mappingContextConfigured = false;

    /**
     * Creates a new {@link BasexRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public BasexRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    /**
     * Configures the {@link BasexOperations} to be used.
     *
     * @param operations the operations to set
     */
    public void setMongoOperations(BasexOperations operations) {
        this.operations = operations;
    }

    /**
     * Configures whether to automatically create indexes for the properties referenced in a query method.
     *
     * @param createIndexesForQueryMethods the createIndexesForQueryMethods to set
     */
    public void setCreateIndexesForQueryMethods(boolean createIndexesForQueryMethods) {
        this.createIndexesForQueryMethods = createIndexesForQueryMethods;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport#setMappingContext(org.springframework.data.mapping.context.MappingContext)
     */
    @Override
    protected void setMappingContext(MappingContext<?, ?> mappingContext) {

        super.setMappingContext(mappingContext);
        this.mappingContextConfigured = true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.data.repository.support.RepositoryFactoryBeanSupport
     * #createRepositoryFactory()
     */
    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {

        RepositoryFactorySupport factory = getFactoryInstance(operations);

        // TODO: Adapt this method for Basex
        /*if (createIndexesForQueryMethods) {
            factory.addQueryCreationListener(
                    new IndexEnsuringQueryCreationListener(collectionName -> operations.indexOps(collectionName)));
        }*/

        return factory;
    }

    /**
     * Creates and initializes a {@link RepositoryFactorySupport} instance.
     *
     * @param operations
     * @return
     */
    protected RepositoryFactorySupport getFactoryInstance(BasexOperations operations) {
        return new BasexRepositoryFactory(operations);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.data.repository.support.RepositoryFactoryBeanSupport
     * #afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {

        super.afterPropertiesSet();
        Assert.state(operations != null, "MongoTemplate must not be null!");

        if (!mappingContextConfigured) {
            setMappingContext(operations.getConverter().getMappingContext());
        }
    }
}
