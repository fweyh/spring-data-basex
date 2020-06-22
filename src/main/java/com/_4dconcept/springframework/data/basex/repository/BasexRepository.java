package com._4dconcept.springframework.data.basex.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * Basex specific {@link org.springframework.data.repository.Repository} interface.
 *
 * @author Fabien Weyh
 *
 * Derived from spring-data-mongodb project by:
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @author Thomas Darimont
 * @author Mark Paluch
 * @author Khaled Baklouti
 */
@NoRepositoryBean
public interface BasexRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#saveAll(java.lang.Iterable)
     */
    @Override
    <S extends T> List<S> saveAll(Iterable<S> entities);

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
    @Override
    List<T> findAll();

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    List<T> findAll(Sort sort);

    /**
     * Inserts the given entity. Assumes the instance to be new to be able to apply insertion optimizations. Use the
     * returned instance for further operations as the save operation might have changed the entity instance completely.
     * Prefer using {@link #save(Object)} instead to avoid the usage of store-specific API.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity
     * @since 1.7
     */
    <S extends T> S insert(S entity);

    /**
     * Inserts the given entities. Assumes the given entities to have not been persisted yet and thus will optimize the
     * insert over a call to {@link #saveAll(Iterable)}. Prefer using {@link #saveAll(Iterable)} to avoid the usage of store
     * specific API.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities
     * @since 1.7
     */
    <S extends T> List<S> insert(Iterable<S> entities);

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example)
     */
    @Override
    <S extends T> List<S> findAll(Example<S> example);

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Sort)
     */
    @Override
    <S extends T> List<S> findAll(Example<S> example, Sort sort);
}

