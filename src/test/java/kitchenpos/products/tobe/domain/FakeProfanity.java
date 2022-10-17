package kitchenpos.products.tobe.domain;

import java.util.List;

public class FakeProfanity implements Profanity {
    private final List<String> values = List.of("damn");

    @Override
    public boolean containsProfanity(final String name) {
        return values.contains(name);
    }
}
