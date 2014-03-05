package gov.nih.tbi.commons.model;

import java.util.*;

public enum AccountStatus {
  ACTIVE(0L, "Active"), INACTIVE(1L, "Inactive"), DENIED(2L, "Denied"),
  REQUESTED(3L, "Requested"), PENDING(4L, "Pending");

  private static final Map<Long, AccountStatus> lookup =
      new HashMap<Long, AccountStatus>();

  static {
    for (AccountStatus s : EnumSet.allOf(AccountStatus.class))
      lookup.put(s.getId(), s);
  }

  private Long id;
  private String name;

  AccountStatus(Long id, String name) {

    this.id = id;
    this.name = name;
  }

  public Long getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public static AccountStatus getById(Long id) {

    return lookup.get(id);
  }
}
