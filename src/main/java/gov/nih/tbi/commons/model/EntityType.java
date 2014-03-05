package gov.nih.tbi.commons.model;

public enum EntityType {
  DATA_STRUCTURE(0L, "Data Structure", RoleType.ROLE_DICTIONARY), DATA_ELEMENT(
      1L, "Data Element", RoleType.ROLE_DICTIONARY), STUDY(2L, "Study",
      RoleType.ROLE_STUDY), DATASET(3L, "Dataset", RoleType.ROLE_STUDY);

  private Long id;
  private String name;
  private RoleType roleName;

  EntityType(Long id, String name, RoleType roleName) {

    this.id = id;
    this.name = name;
    this.roleName = roleName;
  }

  public Long getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public RoleType getRole() {

    return roleName;
  }

  public RoleType getAdminRole() {

    String test = roleName.getName() + "_ADMIN";
    return RoleType.valueOf(test);
  }
}
