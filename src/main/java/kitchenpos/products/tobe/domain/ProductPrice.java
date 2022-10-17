package kitchenpos.products.tobe.domain;


import java.math.BigDecimal;
import java.util.Objects;

public class ProductPrice {
    private static final String INVALID_PRICE_MESSAGE = "상품 가격은 0보다 크거다 같아야 합니다.";

    private final BigDecimal amount;

    public ProductPrice(final BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(INVALID_PRICE_MESSAGE);
        }
        this.amount = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;
        final ProductPrice that = (ProductPrice) o;
        return amount == null ? that.amount == null : amount.compareTo(that.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
