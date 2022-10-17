package kitchenpos.products.tobe.infra;

import kitchenpos.products.tobe.domain.DisplayedName;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.ProductPrice;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductJpaEntity mapToJpaEntity(Product product) {
        return new ProductJpaEntity(
                product.getPrice().getAmount(),
                product.getName().getValue());
    }

    public Product mapToDomainEntity(ProductJpaEntity productJpaEntity) {
        return new Product(
                productJpaEntity.getId(),
                new ProductPrice(productJpaEntity.getPrice()),
                DisplayedName.withOutProfanity(productJpaEntity.getName())
        );
    }
}
