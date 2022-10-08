package kitchenpos.products.tobe.domain;

import java.util.Objects;

public class DisplayedName {
    private static final String CONTAIN_PROFANITY_MESSAGE = "상품 이름에 비속어가 포함될 수 없습니다.";

    private final String name;

    public DisplayedName(final String name, final Profanity profanity) {
        if (profanity.isContains(name)) {
            throw new IllegalArgumentException(CONTAIN_PROFANITY_MESSAGE);
        }
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;
        final DisplayedName that = (DisplayedName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}