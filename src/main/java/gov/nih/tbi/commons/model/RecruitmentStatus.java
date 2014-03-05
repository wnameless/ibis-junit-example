package gov.nih.tbi.commons.model;

public enum RecruitmentStatus {
  COMPLETED(0L, "Completed"), SUSPENDED(1L, "Suspended"), TERMINATED(2L,
      "Terminated"), WITHDRAWN(3L, "Withdrawn"), NOT_RECRUITING(4L,
      "Not Yet Recruiting"), RECRUITING(5L, "Recruiting"),
  ENROLLING_BY_INVITATION(6L, "Enrolling By Invitation"),
  ACTIVE_NOT_RECRUITING(7L, "Active, Not Recruiting");

  private Long id;
  private String name;

  RecruitmentStatus(Long id, String name) {

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
