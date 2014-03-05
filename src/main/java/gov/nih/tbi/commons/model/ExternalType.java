package gov.nih.tbi.commons.model;

/**
 * This is an enumerator for types of external IDs that can be added.
 * 
 * @author Francis Chen
 */
public enum ExternalType {
  LOINC(0L, "LOINC"), CADSR(1L, "caDSR"), SNOMED(2L, "SNOMED"), CDISC(3L,
      "CDISC"), NINDS(4L, "NINDS");

  private Long id;
  private String value;

  ExternalType(Long id, String value) {

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
