package kitchenpos.eatinorders.tobe.domain;

import kitchenpos.eatinorders.domain.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static kitchenpos.eatinorders.tobe.domain.fixture.OrderFixture.createEatInOrder;
import static kitchenpos.eatinorders.tobe.domain.fixture.OrderFixture.createTakeOutOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/*
공통
- [x] 주문 유형이 올바르지 않으면 등록할 수 없다.
- [x] 메뉴가 없으면 등록할 수 없다.
- [x] 숨겨진 메뉴는 주문할 수 없다.
- 주문을 접수한다.
    - [x] 접수 대기 중인 주문만 접수할 수 있다.
- 주문을 서빙한다.
-   [x] 접수된 주문만 서빙할 수 있다.
- [] 주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.

매장
- [x] 매장 주문은 주문 항목의 수량이 0 미만일 수 있다.
- [x] 매장 주문의 경우 서빙된 주문만 완료할 수 있다.
- [x] 1개 이상의 등록된 메뉴로 매장 주문을 등록할 수 있다.
- [x] 주문 테이블의 모든 매장 주문이 완료되면 빈 테이블로 설정한다.
- [x] 완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.
- [x] 빈 테이블에는 매장 주문을 등록할 수 없다.

포장
- [x] 포장 주문의 경우 서빙된 주문만 완료할 수 있다.
- [x] 1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.
- [x] 포장 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.
배달
- 1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다.
- 배달 주문을 접수되면 배달 대행사를 호출한다.
- 주문을 배달한다.
- 배달 주문만 배달할 수 있다.
- 서빙된 주문만 배달할 수 있다.
- 주문을 배달 완료한다.
- 배달 중인 주문만 배달 완료할 수 있다.
- 배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.
- 배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.
  - 배달 주소는 비워 둘 수 없다.
- 배달 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.
 */
class OrderTest {
    @DisplayName("매장 주문 등록")
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {
            "주문 유형이 올바르지 않으면 등록할 수 없다.",
            "메뉴가 없으면 등록할 수 없다.",
            "숨겨진 메뉴는 주문할 수 없다.",
            "접수 대기 중인 주문만 접수할 수 있다.",
            "매장 주문은 주문 항목의 수량이 0 미만일 수 있다.",
            "빈 테이블에는 매장 주문을 등록할 수 없다."
    })
    void registerWithEatIn(String message) {
        assertThatCode(() -> createEatInOrder())
                .doesNotThrowAnyException();
    }

    @DisplayName("포장 주문 등록")
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {
            "1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.",
            "포장 주문의 경우 주문 항목의 수량은 0 이상이어야 한다."
    })
    void registerWithTakeOut(String message) {
        assertThatCode(() -> createTakeOutOrder())
                .doesNotThrowAnyException();
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @Test
    void serve() {
        final Order order = createEatInOrder();
        order.accept();

        order.serve();

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.SERVED);
    }

    @DisplayName("주문 테이블의 모든 매장 주문이 완료되면 빈 테이블로 설정한다.")
    @Test
    void completeAndEmpty() {
        final Order eatInOrder = createEatInOrder();
        eatInOrder.accept();
        eatInOrder.serve();

        eatInOrder.complete();

        assertThat(eatInOrder.getOrderTable().isOccupied()).isFalse();
    }

    @DisplayName("포장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @Test
    void completeTable() {
        final Order takeOutOrder = createTakeOutOrder();
        takeOutOrder.accept();
        takeOutOrder.serve();

        takeOutOrder.complete();

        assertThat(takeOutOrder.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

}
