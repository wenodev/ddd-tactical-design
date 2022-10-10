package kitchenpos.menus.tobe.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/*
- [x] 메뉴의 가격을 변경할 수 있다.
- [x] 메뉴의 가격이 올바르지 않으면 변경할 수 없다.
- 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
- 메뉴는 특정 메뉴 그룹에 속해야 한다.
- 1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.
- 상품이 없으면 등록할 수 없다.
- 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
- 메뉴를 노출할 수 있다.
- 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 높을 경우 메뉴를 노출할 수 없다.
- 메뉴를 숨길 수 있다.
- 메뉴의 목록을 조회할 수 있다.
 */
class MenuTest {
    @DisplayName("메뉴의 가격을 변경할 수 있다.")
    @Test
    void construct() {
        final MenuPrice price = new MenuPrice(BigDecimal.TEN);
        final DisplayedName name = new DisplayedName("치킨 세트", new FakeProfanity());
        final Menu menu = new Menu(price, name);

        menu.changePrice(new MenuPrice(BigDecimal.ONE));

        assertThat(menu.getPrice()).isEqualTo(new MenuPrice(BigDecimal.ONE));
    }
}