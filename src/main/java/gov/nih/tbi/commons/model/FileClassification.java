package gov.nih.tbi.commons.model;

public enum FileClassification {
  ADMIN(0L, "Admin"), SUPPORTING_DOCUMENT(1L, "Supporting Document"),
  DATASET_DATA(2L, "Dataset File");

  private Long id;
  private String name;

  FileClassification(Long id, String name) {

    this.id = id;
    this.name = name;
  }

  public Long getId() {

    return id;
  }

  public String getName() {

    return name;
  }
}
