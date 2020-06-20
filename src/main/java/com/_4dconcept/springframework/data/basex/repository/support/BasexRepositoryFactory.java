package com._4dconcept.springframework.data.basex.repository.support;

import com._4dconcept.springframework.data.basex.core.BasexOperations;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class BasexRepositoryFactory extends RepositoryFactorySupport {
    public BasexRepositoryFactory(BasexOperations operations) {
    }
    
    @Override
    public <T, ID> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return null;
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation metadata) {
        return null;
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return null;
    }
}
