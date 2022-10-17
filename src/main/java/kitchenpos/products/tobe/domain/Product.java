package kitchenpos.products.tobe.domain;

import java.util.UUID;

public class Product {
    private UUID id;
    private ProductPrice price;
    private DisplayedName name;

    public Product(final UUID id, final ProductPrice price, final DisplayedName name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Product(final ProductPrice price, final DisplayedName name) {
        this.price = price;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public ProductPrice getPrice() {
        return price;
    }

    public DisplayedName getName() {
        return name;
    }

    public void changePrice(final ProductPrice price) {
        this.price = price;
    }
}
