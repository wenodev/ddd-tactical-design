package kitchenpos.menus.tobe.domain.product;

import java.util.Objects;

public class DisplayedName {

  private static final String NAME_MUST_NOT_BE_EMPTY = "이름은 빈 값이 아니어야 합니다. 입력 값 : %s";
  private static final String NAME_MUST_NOT_BE_PROFANITY = "이름은 비속어가 아니어야 합니다. 입력 값 : %s";

  private String name;

  protected DisplayedName() {}

  public DisplayedName(String name, boolean isProfanity) {
    validateName(name, isProfanity);
    this.name = name;
  }

  private void validateName(String name, boolean isProfanity) {
    if (isEmptyName(name)) {
      throw new IllegalArgumentException(String.format(NAME_MUST_NOT_BE_EMPTY, name));
    }
    if (isProfanity) {
      throw new IllegalArgumentException(String.format(NAME_MUST_NOT_BE_PROFANITY, name));
    }
  }

  private boolean isEmptyName(String name) {
    return name == null || name.isEmpty();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DisplayedName that = (DisplayedName) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
