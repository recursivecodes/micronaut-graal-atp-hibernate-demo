package codes.recursive.util;

import codes.recursive.model.Person;
import io.micronaut.configuration.hibernate.jpa.EntityManagerFactoryBean;
import io.micronaut.configuration.hibernate.jpa.JpaConfiguration;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.context.annotation.Replaces;
import org.hibernate.AnnotationException;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.service.ServiceRegistry;

@Factory
public class NoScanMetadataSourcesFactoryBean {
    @EachBean(StandardServiceRegistry.class)
    @Replaces(
            value = MetadataSources.class,
            factory = EntityManagerFactoryBean.class
    )
    protected MetadataSources hibernateMetadataSources(
            @Parameter JpaConfiguration jpaConfiguration,
            StandardServiceRegistry standardServiceRegistry
    ) {
        // It seems we can't scan @Entity with native-image
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        metadataSources.addAnnotatedClass(Person.class);

        try {
            metadataSources.buildMetadata();
        }
        catch (AnnotationException ignore) {
        }
        finally {
            ServiceRegistry metaServiceRegistry = metadataSources.getServiceRegistry();
            if(metaServiceRegistry instanceof BootstrapServiceRegistry) {
                BootstrapServiceRegistryBuilder.destroy( metaServiceRegistry );
            }
        }

        return metadataSources;
    }
}
