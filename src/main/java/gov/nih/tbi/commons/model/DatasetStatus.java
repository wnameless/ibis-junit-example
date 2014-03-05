package gov.nih.tbi.commons.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DatasetStatus {
  PRIVATE(0L, "Private", "Private"), SHARED(1L, "Shared", "Share"), ARCHIVED(
      2L, "Archived", "Archive"), DELETED(3L, "Deleted", "Delete"), UPLOADING(
      4L, "Uploading", "Upload"), LOADING(5L, "Loading Data", "Load Data"),
  ERROR(6L, "Error During Load", "Error During Load");

  private static final Map<Long, DatasetStatus> lookup =
      new HashMap<Long, DatasetStatus>();

  static {
    for (DatasetStatus s : EnumSet.allOf(DatasetStatus.class))
      lookup.put(s.getId(), s);
  }

  private Long id;
  private String name;
  private String verb;

  DatasetStatus(Long id, String name, String verb) {

    this.id = id;
    this.name = name;
    this.verb = verb;
  }

  public Long getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public String getVerb() {

    return verb;
  }

  public static DatasetStatus getById(Long id) {

    return lookup.get(id);
  }
}