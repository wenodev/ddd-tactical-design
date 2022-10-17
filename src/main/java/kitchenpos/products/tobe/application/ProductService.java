package kitchenpos.products.tobe.application;


import kitchenpos.products.tobe.domain.DisplayedName;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.ProductPrice;
import kitchenpos.products.tobe.domain.ProductRepository;
import kitchenpos.products.tobe.domain.Profanity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final Profanity purgomalumClient;

    public ProductService(
            final ProductRepository productRepository,
            final Profanity purgomalumClient
    ) {
        this.productRepository = productRepository;
        this.purgomalumClient = purgomalumClient;
    }

    @Transactional
    public Product create(final ProductRequest request) {
        ProductPrice price = new ProductPrice(request.getPrice());
        DisplayedName name = new DisplayedName(request.getName(), purgomalumClient);
        final Product product = new Product(price, name);
        return productRepository.save(product);
    }
}
