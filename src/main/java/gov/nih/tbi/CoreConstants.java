package gov.nih.tbi;

public class CoreConstants {

  public static final String SEMICOLON = ";";
  public static final String EQUALS = "=";
  public static final String WHITESPACE = " ";
  public static final String COMMA = ",";
  public static final String UNDERSCORE = "_";
  public static final String EMPTY_STRING = "";
  public static final String WILDCARD = "%";
  public static final int NO_PAGINATION = -3;
  public static final int NO_PROJECTION = -4;
  public static final String NO_SORT = "noSort";
  public static final String COMMON_DATA_ELEMENT = "Common Data Element";
  public static final String UNIQUE_DATA_ELEMENT = "Unique Data Element";
  public static final String ALL_ELEMENTS = "All Elements";
  public static final String KEYWORDS = "keywords";
  public final static String DESCRIPTION = "description";
  public final static String VALUERANGE = "valueRange";
  public final static String NAME = "name";
  public final static String TITLE = "title";
  public final static String USERNAME = "userName";
  public final static String EMAIL = "email";
  public static final String ROLE_TYPE = "roleType";
  public static final String IS_ACTIVE = "isActive";;
  public static final String ROLE_STATUS = "roleStatus";
  public final static String SALT = "7Dl9#dj-";
  public final static String HEXES = "0123456789abcdef";
  public static final String SYSTEM_COLUMN_SUB_SUFFIX = "_sub";
  public static final String SEQUENCE_SUFFIX = "_seq";
  public static final String UNITED_STATES = "United States of America";

  // Password Recovery
  public final static int RECOVERY_TIME = 120; // Time in minutes until password
                                               // recovery expires
  public final static String RECOVERY_EMAIL_BODY =
      "Please navigate to the link below to reset your password: \n\n";
  public final static String RECOVERY_EMAIL_URI =
      "publicAccounts/changePasswordAction!recover.action?token=";

  // Used to define Factory Qualifiers
  public static final String ACCOUNT_FACTORY = "metaFactory";
  public static final String DICTIONARY_FACTORY = "dictionaryFactory";
  public static final String COMMONS_FACTORY = "metaFactory";
  public static final String META_FACTORY = "metaFactory";
  public static final String GUID_FACTORY = "metaFactory";
  public static final String REPOS_FACTORY = "reposFactory";
  public static final String QUOTE = "\"";
  public static final String SINGLE_QUOTE = "'";

  public static final String INSERT1 = "insert into " + QUOTE;
  public static final String INSERT2 = QUOTE + " (";
  public static final String SEQUENCE1 = "nextval('";
  public static final String SEQUENCE2 = "')";
  public static final String INSERT3 = ") values (";

  public static final String DELETE1 = "DELETE FROM ";
  public static final String DELETE2 = " WHERE ";
  public static final String CASCADE = " CASCADE;";
  public static final String ID = "id";
  public static final Object VARIABLE_NAME = "variableName";

  public final static int EXPIRE_ACCOUNT_AFTER_DAYS = 60; // 60 days
  public final static String UNAUTHENTICATED_USER = "anonymous";
}
