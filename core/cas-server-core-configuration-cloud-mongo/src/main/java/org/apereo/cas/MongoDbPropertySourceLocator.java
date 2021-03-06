package org.apereo.cas;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * This is {@link MongoDbPropertySourceLocator}.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
@Slf4j
@AllArgsConstructor
public class MongoDbPropertySourceLocator implements PropertySourceLocator {
    private final MongoOperations mongo;

    @Override
    public PropertySource<?> locate(final Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {
            final String sourceName = MongoDbPropertySource.class.getSimpleName();
            final CompositePropertySource composite = new CompositePropertySource(sourceName);
            final MongoDbPropertySource source = new MongoDbPropertySource(sourceName, mongo);
            composite.addFirstPropertySource(source);
            return composite;
        }
        return null;
    }
}
