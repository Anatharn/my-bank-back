package sds.home.bank.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.repository.support.RepositoryInvokerFactory;
import org.springframework.data.rest.core.UriToEntityConverter;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class SelfLinkToEntityConverter extends UriToEntityConverter {

    private static final TypeDescriptor URI_DESCRIPTOR = TypeDescriptor.valueOf(URI.class);
    SelfLinkToEntityConverter(PersistentEntities persistentEntities,
                              RepositoryInvokerFactory repositoryInvokerFactory,
                              ConversionService defaultConversionService) {
        super(persistentEntities, repositoryInvokerFactory, () -> defaultConversionService);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> findBySelfLink(Link self, Class<T> entityClass) {
        URI uri = self.expand().toUri();
        TypeDescriptor typeDescriptor = TypeDescriptor.valueOf(entityClass);

        try {
            T entity = (T) super.convert(uri, URI_DESCRIPTOR, typeDescriptor);
            return Optional.ofNullable(entity);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(String.format("Failed to load %s: %s",
                    entityClass.getSimpleName(), self.getHref()));
        }
    }
}
