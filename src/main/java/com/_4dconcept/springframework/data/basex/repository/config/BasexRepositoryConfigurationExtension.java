package com._4dconcept.springframework.data.basex.repository.config;

import com._4dconcept.springframework.data.basex.core.mapping.Document;
import com._4dconcept.springframework.data.basex.repository.BasexRepository;
import com._4dconcept.springframework.data.basex.repository.support.BasexRepositoryFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.data.config.ParsingUtils;
import org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
import org.springframework.data.repository.config.XmlRepositoryConfigurationSource;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.w3c.dom.Element;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Fabien Weyh
 *
 * Inspired from MongoDB project by:
 *
 * @author Oliver Gierke
 * @author Mark Paluch
 */
public class BasexRepositoryConfigurationExtension extends RepositoryConfigurationExtensionSupport {

    private static final String BASEX_TEMPLATE_REF = "basex-template-ref";
    private static final String CREATE_QUERY_INDEXES = "create-query-indexes";

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#getModuleName()
     */
    @Override
    public String getModuleName() {
        return "BaseX";
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#getModulePrefix()
     */
    @Override
    protected String getModulePrefix() {
        return "basex";
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtension#getRepositoryFactoryBeanClassName()
     */
    public String getRepositoryFactoryBeanClassName() {
        return BasexRepositoryFactoryBean.class.getName();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#getIdentifyingAnnotations()
     */
    @Override
    protected Collection<Class<? extends Annotation>> getIdentifyingAnnotations() {
        return Collections.singleton(Document.class);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#getIdentifyingTypes()
     */
    @Override
    protected Collection<Class<?>> getIdentifyingTypes() {
        return Collections.singleton(BasexRepository.class);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#postProcess(org.springframework.beans.factory.support.BeanDefinitionBuilder, org.springframework.data.repository.config.XmlRepositoryConfigurationSource)
     */
    @Override
    public void postProcess(BeanDefinitionBuilder builder, XmlRepositoryConfigurationSource config) {

        Element element = config.getElement();

        ParsingUtils.setPropertyReference(builder, element, BASEX_TEMPLATE_REF, "basexOperations");
        ParsingUtils.setPropertyValue(builder, element, CREATE_QUERY_INDEXES, "createIndexesForQueryMethods");
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#postProcess(org.springframework.beans.factory.support.BeanDefinitionBuilder, org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource)
     */
    @Override
    public void postProcess(BeanDefinitionBuilder builder, AnnotationRepositoryConfigurationSource config) {

        AnnotationAttributes attributes = config.getAttributes();

        // Todo: builder.addPropertyReference("basexOperations", attributes.getString("basexTemplateRef"));
        // Todo: builder.addPropertyValue("createIndexesForQueryMethods", attributes.getBoolean("createIndexesForQueryMethods"));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#useRepositoryConfiguration(org.springframework.data.repository.core.RepositoryMetadata)
     */
    @Override
    protected boolean useRepositoryConfiguration(RepositoryMetadata metadata) {
        return !metadata.isReactiveRepository();
    }

}
