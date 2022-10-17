package kitchenpos.products.tobe.infra;

import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter implements ProductRepository {
    private final SpringDataJpaProductRepository springDataJpaProductRepository;
    private final ProductMapper productMapper;

    public ProductPersistenceAdapter(final SpringDataJpaProductRepository springDataJpaProductRepository, final ProductMapper productMapper) {
        this.springDataJpaProductRepository = springDataJpaProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(final Product product) {
        final ProductJpaEntity productJpaEntity = productMapper.mapToJpaEntity(product);
        final ProductJpaEntity savedEntity = springDataJpaProductRepository.save(productJpaEntity);
        return productMapper.mapToDomainEntity(savedEntity);
    }
}
