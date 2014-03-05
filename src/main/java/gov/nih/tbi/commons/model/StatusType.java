package gov.nih.tbi.commons.model;

public enum StatusType {
  // The status MULTIPLE refers to the Multiple statuses, usually the
  // combination of Awaiting Publication and
  // Published.
  DRAFT(0L, "Draft"), AWAITING_PUBLICATION(1L, "Awaiting Publication"),
  PUBLISHED(2L, "Published"), ARCHIVED(3L, "Archived"),
  MULTIPLE(4L, "Multiple"), SHARED_DRAFT(5L, "Shared Draft");

  private long id;
  private String type;

  StatusType(long id, String type) {

    this.id = id;
    this.type = type;
  }

  public long getId() {

    return id;
  }

  public String getType() {

    return type;
  }

  public static StatusType statusOf(long id) {

    for (StatusType status : StatusType.values()) {
      if (status.getId() == id) {
        return status;
      }
    }
    return null;
  }

  public static StatusType statusOf(String type) {

    for (StatusType status : StatusType.values()) {
      if (status.getType().equals(type)) {
        return status;
      }
    }
    return null;
  }
}
