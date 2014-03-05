package gov.nih.tbi;

public class ModelConstants {

  /************************ ID PREFIXES **********************************************/
  public static final String PREFIX_STUDY = "-STUDY";
  public static final String PREFIX_DATASET = "-DATA";
  public static final String PREFIX_LEAD_NUM = "0";
  public static final String PREFIX_REGEX = "[" + PREFIX_LEAD_NUM + "]*(\\d+)";

  public static final String STUDY_REGEX = PREFIX_STUDY + PREFIX_REGEX;
  public static final String DATASET_REGEX = PREFIX_DATASET + PREFIX_REGEX;

  public static final String PRINCIPAL_INVESTIGATOR = "Principal Investigator";

  public static final String EMPTY_STRING = "";
  public static final String RECORD_STRING = "record";
  public static final String MAIN_STRING = "Main";

  // Valid time formats, in order of precision
  public static final String UNIVERSAL_DATE_FORMAT = "dd-MMM-yyyy";
  public static final String[] UNIVERSAL_DATE_FORMATS = {
      "yyyy-MM-dd HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss.SSS",
      "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd",
      "dd-MMM-yyyy hh:mm:ss a z", "dd/MMM/yyyy hh:mm:ss a z",
      "dd-MMM-yyyy hh:mm:ss a", "dd/MMM/yyyy hh:mm:ss a",
      "dd-MMM-yyyy hh:mm a z", "dd/MMM/yyyy hh:mm a z", "dd-MMM-yyyy hh:mm a",
      "dd/MMM/yyyy hh:mm a", "dd-MMM-yyyy HH:mm:ss z",
      "dd/MMM/yyyy HH:mm:ss z", "dd-MMM-yyyy HH:mm:ss", "dd/MMM/yyyy HH:mm:ss",
      "dd-MMM-yyyy HH:mm z", "dd/MMM/yyyy HH:mm z", "dd-MMM-yyyy HH:mm",
      "dd/MMM/yyyy HH:mm", "dd-MMM-yyyy z", "dd/MMM/yyyy z", "dd-MMM-yyyy",
      "dd/MMM/yyyy" };

  public static final int MD5_HASH_SIZE = 1000;
  public static final String WHITESPACE = " ";
  public static final String LEFT_PAREN = "(";
  public static final String RIGHT_PAREN = ")";
}
