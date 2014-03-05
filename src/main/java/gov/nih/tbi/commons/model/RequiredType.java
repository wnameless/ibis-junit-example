package gov.nih.tbi.commons.model;

import java.util.EnumSet;

public enum RequiredType {
  REQUIRED(0L, "Required"), RECOMMENDED(1L, "Recommended"), OPTIONAL(2L,
      "Optional"), CONDITIONAL_REQUIRED(3L, "Conditionally Required"),
  CONDITIONAL_PROHIBITIED(4L, "Conditionally Prohibited");

  private Long id;
  private String value;

  RequiredType(Long id, String value) {

    this.id = id;
    this.value = value;
  }

  public Long getId() {

    return id;
  }

  public String getValue() {

    return value;
  }

  public static RequiredType getById(Long id) {

    for (RequiredType type : EnumSet.allOf(RequiredType.class)) {
      if (type.getId() == id) {
        return type;
      }
    }
    return null;
  }

  public static final RequiredType getDefault() {

    return RequiredType.RECOMMENDED;
  }

}
