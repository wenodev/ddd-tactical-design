package kitchenpos.products.tobe.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataJpaProductRepository extends JpaRepository<ProductJpaEntity, UUID> {
    ProductJpaEntity save(ProductJpaEntity product);
}
