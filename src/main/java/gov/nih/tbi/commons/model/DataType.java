package gov.nih.tbi.commons.model;

import gov.nih.tbi.PostgreConstants;

public enum DataType {
  ALPHANUMERIC(0L, "Alphanumeric", "unrestricted text",
      PostgreConstants.CREATE_CHARACTER_VARYING), NUMERIC(1L, "Numeric Values",
      "", PostgreConstants.CREATE_NUMERIC), DATE(2L, "Date or Date & Time", "",
      PostgreConstants.CREATE_TIMESTAMP), GUID(3L, "GUID",
      "Globally Unique Patient Identifier", PostgreConstants.CREATE_GUID),
  FILE(4L, "File", "", PostgreConstants.CREATE_FILE), THUMBNAIL(5L,
      "Thumbnail", "Image files only.", PostgreConstants.CREATE_THUMBNAIL),
  BIOSAMPLE(6L, "Biosample", "", PostgreConstants.CREATE_BIOSAMPLE);

  private Long id;
  private String value;
  private String specialInstructions;
  private String sqlFormatString; // Used by Formatter to generate SQL to create
                                  // a column for this type

  DataType(Long id, String value, String specialInstructions,
      String sqlFormatString) {

    this.id = id;
    this.value = value;
    this.specialInstructions = specialInstructions;
    this.sqlFormatString = sqlFormatString;
  }

  public Long getId() {

    return id;
  }

  public String getValue() {

    return value;
  }

  public String getSpecialInstructions() {

    return specialInstructions;
  }

  public String getSqlFormatString() {

    return sqlFormatString;
  }
}
