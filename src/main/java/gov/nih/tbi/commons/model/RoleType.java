package gov.nih.tbi.commons.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum RoleType {
  ROLE_USER(0L, "ROLE_USER", "User (Default Access)",
      "General access to the system", false),
  ROLE_ADMIN(1L, "ROLE_ADMIN", "Admin",
      "Administrator of the system. Provides full access to the entire site",
      true),
  ROLE_DICTIONARY(
      2L,
      "ROLE_DICTIONARY",
      "Dictionary",
      "Gives access to the Data Dictionary and allows users to view, define and create unique data elements, form structures, and Common Data Elements",
      false),
  ROLE_DICTIONARY_ADMIN(3L, "ROLE_DICTIONARY_ADMIN", "Dictionary Admin",
      "Administrative functionality for the Data Dictionary tool", true),
  ROLE_GUID(
      4L,
      "ROLE_GUID",
      "GUID",
      "Allows users to access the GUID Tool and generate a Global Unique Identifier (GUID) for each study participant",
      false),
  ROLE_GUID_ADMIN(5L, "ROLE_GUID_ADMIN", "GUID Admin",
      "Administrative functionality for the GUID tool", true),
  ROLE_STUDY(
      6L,
      "ROLE_STUDY",
      "Study",
      "Allows users to create and view Studies which contain research data and supporting documentation",
      false),
  ROLE_STUDY_ADMIN(7L, "ROLE_STUDY_ADMIN", "Study Admin",
      "Administrative functionality for the Study tool", true),
  ROLE_QUERY(
      8L,
      "ROLE_QUERY",
      "Query Tool",
      "Grants users permission to query and download shared data from the repository. Users must submit the SF-424 (R&R) and Data Access Agreement documents.",
      false), ROLE_QUERY_ADMIN(9L, "ROLE_QUERY_ADMIN", "Query Tool Admin",
      "Administrative functionlaity for the Query tool", true),
  ROLE_REPOSITORY_ADMIN(10L, "ROLE_REPOSITORY_ADMIN", "Repository Admin",
      "Administrative functionality for the Repository Manager", true),
  ROLE_PROFORMS(11L, "ROLE_PROFORMS", "ProFoRMS",
      "Protocol Management and Electronic Form Capture and Creation", false),
  ROLE_PROFORMS_ADMIN(12L, "ROLE_PROFORMS_ADMIN", "ProFoRMS Admin",
      "Administrative functionality for ProFoRMS", true), ROLE_ORDER(13L,
      "ROLE_ORDER", "Order Manager", "Allows users to place Biosample Orders",
      false), ROLE_ORDER_ADMIN(14L, "ROLE_ORDER_ADMIN", "Order Manager Admin",
      "Administrative functionailty for Order Manager", true),
  ROLE_ACCOUNT_ADMIN(15L, "ROLE_ACCOUNT_ADMIN", "Account Admin",
      "Administrative functionailty for User Accounts", true);

  private static final Map<Long, RoleType> lookup =
      new HashMap<Long, RoleType>();
  private static final Map<String, RoleType> lookupName =
      new HashMap<String, RoleType>();

  static {
    for (RoleType s : EnumSet.allOf(RoleType.class)) {
      lookup.put(s.getId(), s);
      lookupName.put(s.getName(), s);
    }

  }

  private Long id;
  private String name;
  private String title;
  private String description;
  private boolean isAdmin;

  RoleType(Long id, String name, String title, String description,
      boolean isAdmin) {

    this.id = id;
    this.name = name;
    this.title = title;
    this.description = description;
    this.isAdmin = isAdmin;
  }

  public Long getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public String getTitle() {

    return title;
  }

  public String getDescription() {

    return description;
  }

  public static RoleType getById(Long id) {

    return lookup.get(id);
  }

  public static RoleType getByName(String name) {

    return lookupName.get(name);
  }

  public boolean getIsAdmin() {

    return isAdmin;
  }
}
