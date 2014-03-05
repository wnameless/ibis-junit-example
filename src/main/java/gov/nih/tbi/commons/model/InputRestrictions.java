package gov.nih.tbi.commons.model;

public enum InputRestrictions {
  FREE_FORM(0L, "Free-Form Entry"), SINGLE(1L,
      "Single Pre-Defined Value Selected"), MULTIPLE(2L,
      "Multiple Pre-Defined Values Selected");

  private Long id;
  private String value;

  InputRestrictions(Long id, String value) {

    this.id = id;
    this.value = value;
  }

  public Long getId() {

    return id;
  }

  public String getValue() {

    return value;
  }
}
