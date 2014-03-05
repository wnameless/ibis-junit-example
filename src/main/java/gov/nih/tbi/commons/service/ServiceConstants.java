package gov.nih.tbi.commons.service;

import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public class ServiceConstants {

  public final static String WHITESPACE = " ";
  public final static String ENTITY_DATA_ELEMENT = "DataElement";
  public final static String ENTITY_DATA_STRUCTURE = "DataStructure";
  public final static String COMMA = ",";
  public final static String PERIOD = ".";
  public static final String FILE_SEPARATER = "/";
  public static final String QUOTE = "\"";
  public static final String SINGLE_QUOTE = "'";
  public static final String UNDERSCORE = "_";
  public static final String SQL_ESCAPED_UNDERSCORE = "\\_";
  public final static String EMPTY_STRING = "";
  public final static String DEFAULT = "Default";
  public final static String REQUIRED_MESSAGE = " field is required for ";
  public final static String PROHIBITED_MESSAGE =
      " field is only allowed on data elements of type numeric for ";
  public final static String NUMERIC_MISMATCH =
      " field can only contain numeric values for ";
  public final static String MISPLACE_SIZE =
      " field can only have a value for data elements of free-form, alphanumeric, or biosample: ";
  public final static String MISPLACE_MU =
      " field can only have a value for data elements of type alphanumeric or numeric: ";
  public final static String BAD_SIZE =
      " field must be between 1 and 4000 for element: ";
  public final static String NOT_VALID =
      " field is not valid domain for this disease for ";
  public final static String OVER_CHARACTER_10 =
      " field must be 10 characters or less for ";
  public final static String OVER_CHARACTER_20 =
      " field must be 20 characters or less for ";
  public final static String OVER_CHARACTER_30 =
      " field must be 30 characters or less for ";
  public final static String OVER_CHARACTER_55 =
      " field must be 55 characters or less for ";
  public final static String OVER_CHARACTER_255 =
      " field must be 255 characters or less for ";
  public final static String OVER_CHARACTER_4000 =
      " field must be 4000 characters or less for ";
  public final static String OVER_CHARACTER_200 =
      " field must be 200 characters or less for ";
  public final static String OVER_CHARACTER_1000 =
      " field must be 1000 characters or less for ";
  public final static String NOT_UNIQUE = " list has a duplicate element ";
  public static final String ERROR_COLUMN_MISSING =
      "The required column header '%s' is missing from the CSV, please reference the Import CDE Template.";
  public static final String ERROR_SPECIAL_CHARACTER =
      "The CSV contains an invalid special character at line %d, please remove all special characters and try again.";
  public final static String NO_NAME_ERROR =
      "A data element is missing a name. All data elements must have a unique name: ";
  public final static String NAME_ERROR =
      " field must start with an alphabet and not contain any special characters for ";
  public final static String MIN_MAX_ERROR =
      "The Minimum Value must be less than the Maximum Value for the data element:  ";
  public final static int SIZE_LIMIT_20 = 20;
  public final static int SIZE_LIMIT_30 = 30;
  public final static int SIZE_LIMIT_55 = 55;
  public final static int SIZE_LIMIT_255 = 255;
  public final static int SIZE_LIMIT_4000 = 4000;
  public final static String CSV_FILE = "application/vnd.ms-excel";
  public final static String XML_FILE = "text/xml";
  public final static int DATA_STRUCTURE_LIMIT = 1000;
  public final static String STATIC_MANAGER = "staticManager";
  public final static String ACCOUNT_MANAGER = "accountManager";
  public static final String DATABASE_NAME_REGEX = "[A-Za-z][A-Za-z0-9_]*";
  public static final String USERNAME__REGEX =
      "^[A-Za-z]{1}[A-Za-z0-9@\\._-]{2,44}$";
  public static final String INTEGER_REGEX = "[0-9]*";
  public static final String CSV_EXPORT_NAME = "dataElementExport.csv";
  public static final String CSV_LIST_SEPARATER = ";";
  public static final int GUIDELINE_NOTES_REFERENCES_IMPORT_LENGTH_LIMIT = 4000; // maximum
                                                                                 // string
                                                                                 // length
                                                                                 // for
                                                                                 // those
                                                                                 // 3
  public static final String TIMEZONE_APPEND = " -04"; // fields
  public static final String URL_PREFIX_HTTP = "http://";
  public static final String URL_PREFIX_HTTPS = "https://";
  public static final String URL_PREFIX_FTP = "ftp://";
  public static final Long DEFAULT_PROVIDER = -1L;
  public static final String STUDY_PREFIX = "STDY";
  public static final String DATASET_PREFIX = "DATA";

  /****************************** Login Failure ***************************/

  public final static String UNLOCK_DATE_PARAM = "unlockDate";

  public final static String LOGIN_FAILURE_DEFAULT =
      "/jsp/login.jsp?login_error=1";
  public final static String LOGIN_FAILURE_EXPIRED =
      "/publicAccounts/passwordRecoveryAction!input.action?login_error=1";
  public final static int LOCKOUT_ATTEMPTS_FIRST = 3;
  public final static int LOCKOUT_WINDOW_FIRST = -15; // 15 minute window
  public final static int LOCKOUT_LENGTH_FIRST = 15; // 15 minutes
  public final static int LOCKOUT_ATTEMPTS_SECOND = 6;
  public final static int LOCKOUT_WINDOW_SECOND = 0; // we don't have a window
  public final static int LOCKOUT_LENGTH_SECOND = 60; // 60 minutes

  /****************************** Exception Messages ***************************/

  public final static String NULL_STATUS = "status cannot be null";
  public final static String NULL_DATASTUCTURE = "dataStructure cannot be null";
  public final static String NULL_CURUSER = "curUser cannot be null";
  public final static String NULL_ACCOUNT = "account cannot be null";
  public final static String INVALID_PASSWORD = "account cannot be null";

  /****************************** DEFAULT FILE INFORMATION ***************************/

  public static final Long TBI_DATAFILE_ENDPOINT_ID = 1L;
  public static final Long DDT_DATAFILE_ENDPOINT_ID = 3L;
  public static final Long CORRIEL_DATAFILE_ENDPOINT_ID = 4L;
  public static final String TBI_DEFAULT_FILE_PATH = "testFolder/";
  public static final String DDT_DEFAULT_FILE_PATH = "userDocUploads/";
  public static final String TBI_REPO_DEFAULT_FILE_PATH = "repoFolder/";
  public static final String TBI_ORDER_FILE_PATH = "orderXmls/";
  public static final String ORDER_MANAGER_FILE_PATH = "ordermanager/";

  /****************************** CDE Fields in CSV **********************************/
  public final static String ID_READABLE = "ID";
  public final static String NAME_READABLE = "Name";
  public final static String TITLE = "title";
  public final static String TITLE_READABLE = "Title";
  public final static String SHORT_DESCRIPTION = "shortDescription";
  public final static String SHORT_DESCRIPTION_READABLE = "Short Description";
  public final static String DESCRIPTION_READABLE = "Description";
  public final static String FORMAT = "format";
  public final static String FORMAT_READABLE = "Format";
  public final static String SIZE = "size";
  public final static String SIZE_READABLE = "Size";
  public final static String NOTES = "notes";
  public final static String NOTES_READABLE = "Notes";
  public final static String VALUE_RANGES = "valueRanges";
  public final static String VALUE_RANGES_READABLE = "Permissible Values";
  public final static String VALUE_RANGE_DESCRIPTIONS =
      "valueRangeDescriptions";
  public final static String VALUE_RANGE_DESCRIPTIONS_READABLE =
      "Permissible Value Descriptions";
  public final static String GUIDELINES = "guidelines";
  public final static String GUIDELINES_READABLE = "Guidelines/Instructions";
  public final static String HISTORICAL_NOTES = "historicalNotes";
  public final static String HISTORICAL_NOTES_READABLE = "Historical Notes";
  public final static String TYPE = "type";
  public final static String TYPE_READABLE = "Data Type";
  public final static String TYPE_ERROR1 = "Data Elements of type ";
  public final static String TYPE_ERROR2 = " must be a Free-Form Entry for, ";
  public final static String REFERENCES = "references";
  public final static String REFERENCES_READABLE = "References";
  public final static String CLASSIFICATION = "classification";
  public final static String CLASSIFICATION_READABLE = "Classification";
  public final static String KEYWORD = "keyword";
  public final static String KEYWORD_READABLE = "Keyword";
  public final static String SPEC_CHAR_ERROR =
      "Invalid character(s) %s has been detected in line %d for, ";
  public final static String CLASSIFICATION_ERROR1 =
      "A classification is required for the subgroup: ";
  public final static String CLASSIFICATION_ERROR2 = ", on the data element: ";
  public final static String BAD_CLASSIFICATION =
      " classification is not valid for ";
  public final static String ADMIN_CLASSIFICATION_ERROR1 =
      "It is illegal to give the subgroup '";
  public final static String ADMIN_CLASSIFICATION_ERROR2 =
      "' a classification of '";
  public final static String ADMIN_CLASSIFICATION_ERROR3 =
      "' as a non-admin user, on the data element: ";
  public final static String ADMIN_CATEGORY_ERROR =
      "It is illegal to create a Common Data Element (CDE) as a non-admin user, on the data element: ";
  public final static String BAD_DOMAIN_1 = " is not a valid domain for the '";
  public final static String BAD_DOMAIN_2 = "' disease for the data element ";
  public final static String DUPLICATE_DOMAIN = " is duplicated in the '";
  public final static String BAD_SUBDOMAIN =
      " is not a valid sub-domain for the domain ";
  public final static String BAD_SUBDOMAIN_2 = " in the disease ";
  public final static String POPULATION = "population";
  public final static String POPULATION_READABLE = "Population.All";
  public final static String DISEASE_LIST = "diseaseList";
  public final static String CLASSIFICATION_LIST = "classificationElementList";
  public final static String DISEASE_LIST_READABLE = "Diseases";
  public final static String DOMAIN = "domain";
  public final static String DOMAIN_READABLE = "Domain";
  public final static String SUBDOMAIN = "subdomain";
  public final static String SUBDOMAIN_READABLE = "Sub-Domain";
  public final static String CRF_MODULE_LIST_READABLE = "CRF Modules";
  public final static String KEYWORD_LIST = "keywordList";
  public final static String KEYWORD_LIST_READABLE = "Keywords";
  public final static String ALIASES = "aliases";
  public final static String ALIASES_READABLE = "Aliases";
  public final static String REQUIRED_TYPE = "requiredType";
  public final static String REQUIRED_TYPE_READABLE = "Required Type";
  public final static String SECTION = "section";
  public final static String SECTION_READABLE = "Section";
  public final static String RESTRICTIONS = "restrictions";
  public final static String RESTRICTIONS_READABLE = "Input Restrictions";
  public final static String MEASUREMENT_TYPE = "measurementType";
  public final static String MEASUREMENT_TYPE_READABLE = "Measurement Type";
  public final static String MEASUREMENT_UNIT = "measuringUnit";
  public final static String MEASUREMENT_UNIT_READABLE = "Measurement Unit";
  public final static String LOINC_READABLE = "External ID.LOINC";
  public final static String SNOMED_READABLE = "External ID.SNOMED";
  public final static String CADSR_READABLE = "External ID.caDSR";
  public final static String CDISC_READABLE = "External ID.CDISC";
  public final static String NINDS_READABLE = "External ID.NINDS";
  public final static String EXTERNAL_ID_READABLE = "External ID";
  public final static String LOINC = "loinc";
  public final static String SNOMED = "snomed";
  public final static String CADSR = "cadsr";
  public final static String CDISC = "cdisc";
  public final static String NINDS = "ninds";
  public final static String CATEGORY_READABLE = "Element Type";
  public final static String CATEGORY = "category";
  public final static String MINIMUM_VALUE_READABLE = "Minimum Value";
  public final static String MINIMUM_VALUE = "minimumValue";
  public final static String MAXIMUM_VALUE_READABLE = "Maximum Value";
  public final static String MAXIMUM_VALUE = "maximumValue";
  public final static String QUESTION_TEXT_READABLE = "Suggested Question Text";
  public final static String QUESTION_TEXT = "suggestedQuestion";

  /****************************** Error Messages **********************************/
  public static final String WARNING = "Warning: ";
  public static final String COLUMN_MISMATCH =
      " does not match any of the columns for a data element.";
  public static final String DUPLICATE_COLUMN =
      "Duplicate column found in CSV: ";
  public static final Long FILTER_BY_MINE_ID = -5L;
  public static final Long FILTER_BY_ALL_ID = -10L;
  public static final String FILTER_BY_MINE = "Mine";
  public static final String FILTER_BY_ALL = "All";
  public static final String FILTER_BY_UNATTACHED = "Unattached";
  public static final String INVALID_VALIDATOR = "Error: Invalid validator!";
  public static final String NO_DATA_STRUCTURE =
      "Repeatable Groups must be associated with a Data Structure";
  public static final String NO_REPEATABLE_GROUP =
      "Map Elements must be associated with a Repeatable Group";
  public static final String MISMATCHED_DS =
      "Repeatable Groups must be associated with the same Data Structure";
  public static final String MISMATCHED_RG =
      "Map Elements must be associated with the same Repeatable Group";
  public static final String NO_DATA_ELEMENT =
      "Map Elements must be associated with a Data Element";
  public static final String INCORRECT_TYPE =
      "Map Elements must have a required type";
  public static final String INCORRECT_THRESHOLD =
      "Repeatable Groups must have a threshold value greater than 0";
  public static final String INCORRECT_RG_TYPE =
      "Repeatable Groups must have a required type";
  public static final String ADDED_DS_TO_RG =
      "Repeatable Groups DS_id column was set to ";
  public static final String NO_NAME_DE = "All Elements must have a name.";
  public static final String NOT_UNIQUE_DE =
      "All Element names must be unique.";
  public static final String NO_NAME_RG =
      "All Repeatable Element Groups must have a name.";
  public static final String NOT_UNIQUE_RG =
      "Repeatable Element Groups cannot share a name with another"
          + " group in the same data structure.";
  public static final String DUPLICATE_NAME =
      "The import file contains multiple elements with the name: ";
  public static final String EXISTING_NAME =
      "This data element's name conflicts with one already in the database: ";
  public static final String MISSING_DESCRIPTION_VALUE =
      "Permissible Value Description missing for: ";
  public static final String MISSING_RANGE_VALUE =
      "Permissible Value missing for: ";
  public final static String PER_VALUE_RANGE_MISMATCH =
      "The number of Permissible Values and Permissible Value Descriptions are not equal.";
  public final static String PUBLISHED_DATA_ELEMENT =
      " cannot be overwritten because the Data Element has been published.";
  public final static String OVERWRITE_ERROR = "Overwrite Error: ";
  public final static String DE_VAR_NAME = " Data Element - ";
  public final static String SIZE_TOO_LARGE =
      "The size provided is larger than 100 for Data Element: ";
  public final static String INVALID_MEASURING_UNIT =
      "The measuring unit provided is invalid for: ";
  public final static String INVALID_PERMISSION =
      "cannot be overwritten beacuse you don't have proper permission to edit this Data Element.";

  public static final String[] CSV_HEADERS = { CATEGORY_READABLE,
      NAME_READABLE, TITLE_READABLE, SHORT_DESCRIPTION_READABLE,
      DESCRIPTION_READABLE, SIZE_READABLE, NOTES_READABLE,
      VALUE_RANGES_READABLE, VALUE_RANGE_DESCRIPTIONS_READABLE,
      MINIMUM_VALUE_READABLE, MAXIMUM_VALUE_READABLE, GUIDELINES_READABLE,
      HISTORICAL_NOTES_READABLE, TYPE_READABLE, REFERENCES_READABLE,
      POPULATION_READABLE, KEYWORD_LIST_READABLE, RESTRICTIONS_READABLE,
      MEASUREMENT_TYPE_READABLE, LOINC_READABLE, SNOMED_READABLE,
      CADSR_READABLE, CDISC_READABLE, NINDS_READABLE, QUESTION_TEXT_READABLE };

  /************************ Exception Messages *************************************/

  public static final String EXCEPTION_NULL_USER = "curUser cannot be null.";
  public static final String EXCEPTION_NULL_ACCOUNT_ID =
      "accountId cannot be null.";

  /************************ Account Roles ******************************************/

  public static final String ROLE_USER = "ROLE_USER";
  public static final String ROLE_ACCOUNT_ADMIN = "ROLE_ACCOUNT_ADMIN";
  public static final String ROLE_ADMIN = "ROLE_ADMIN";
  public static final String ROLE_DICTIONARY = "ROLE_DICTIONARY";
  public static final String ROLE_DICTIONARY_ADMIN = "ROLE_DICTIONARY_ADMIN";
  public static final String ROLE_GUID = "ROLE_GUID";
  public static final String ROLE_GUID_ADMIN = "ROLE_GUID_ADMIN";
  public static final String ROLE_STUDY = "ROLE_STUDY";
  public static final String ROLE_STUDY_ADMIN = "ROLE_STUDY_ADMIN";
  public static final String ROLE_QUERY = "ROLE_QUERY";
  public static final String ROLE_QUERY_ADMIN = "ROLE_QUERY_ADMIN";

  public static final String STATUS_ACTIVE = "Active";
  public static final String STATUS_INACTIVE = "Inactive";
  public static final String STATUS_REQUESTED = "Requested";

  public static final String PERMISSION_OWNER = "OWNER";

  public static final String SFTP_NAME = "sftp";
  public static final String STRICT_HOST_KEY_CHECK = "StrictHostKeyChecking";
  public static final String NO = "no";

  public static final int PASSWORD_MIN_LENGTH = 8;
  public static final int PASSWORD_MAX_LENGTH = 30;
  public static final String PASSWORD_DIGIT_PATTERN = "^.*(?=.*[0-9]).*$";
  public static final String PASSWORD_LOWER_PATTERN = "^.*(?=.*[a-z]).*$";
  public static final String PASSWORD_UPPER_PATTERN = "^.*(?=.*[A-Z]).*$";
  public static final String PASSWORD_SPECIAL_PATTERN =
      "^.(?=.*[`~!@#$%^&\\*()_\"{}=\\[\\]\'\"\\+-]).*$";
  public static final String NAMESPACE_ACCOUNTS = "accounts";
  public static final String ADMIN_NOTE = "adminNote";
  public static final String PHONE_NUMBER_PATTERN = "[\\s\\+0-9\\-\\(\\)xX]+";

  /************************* Permission Group **************************************/

  public static final String PUBLIC_STUDY = "Public Study";
  public static final String CLINICAL_TRIAL_PREFIX = "NCT";
  public static final String PUBLISHED_FORM_STRUCTURES =
      "Published Form Structures";
  public static final String PUBLIC_DATA_ELEMENTS = "Public Data Elements";

  /************************* SQL ***************************************************/

  public static final String TABLE_SUBMISSION_JOIN = "submission_record_join";
  public static final String COLUMN_SUBMISSION_JOIN =
      "submission_record_join_id";
  public static final String MAIN = "main";

  /************************* FILE TYPES ***************************************************/

  public static final String FILE_TYPE_DICTIONARY = "Dictionary Documentation";
  public static final String FILE_TYPE_STUDY = "Study Documentation";
  public static final String FILE_TYPE_ACCOUNT = "Account Documentation";

  /************************* ROLE CHANGE MESSAGES ***************************************************/

  public static final String STRING_AND = " and";
  public static final String ACCOUNT_ROLE_GRANTED =
      " Account Role has been granted";
  public static final String ACCOUNT_ROLE_REQUESTED =
      " Account Role has been requested";
  public static final String ACCOUNT_ROLE_REVOKED =
      " Account Role has been revoked";
  public static final String ACCOUNT_ROLE_DENIED =
      " Account Role has been denied";
  public static final String ACCOUNT_DATE_CHANGED =
      " Expiration Date has changed to ";
  public static final String ACCOUNT_DATE_SET =
      " Expiration Date has been set to ";
  public static final String ACCOUNT_DATE_REMOVED =
      " Expiration Date has been removed ";

  /******************* Exception Messages *************************************/

  public static final String ADMIN_ACCESS_DENIED =
      "User does not have admin access to this entity.";
  public static final String WRITE_ACCESS_DENIED =
      "User does not have write access to this entity.";
  public static final String READ_ACCESS_DENIED =
      "User does not have read access to this entity.";
  public static final String MAIN_GROUP = "main";

  /************************************** Biosample Validation **************************************/

  public static final Integer MAX_BIOSAMPLE_LENGTH = 100;

  /*************************************** Log Output **********************************************/
  public static final String OVERWRITE_NOTICE = "Data Element Overwrite";
  public static final String CHANGES_MADE = "The following changes were made: ";

  /*************************** Date Time Formats **************************************/
  public final static DateTimeFormatter BRICS_DATE_FORMATTER;
  public final static int TIMEZONE_UTC_OFFSET = -4;
  // working with Bianca on defining these
  // public static String[] BRICS_DATE_FORMATS = { "dd-MMM-yy", "dd-MMM-yyyy",
  // "dd/MMM/yy", "dd/MMM/yyyy", "dd/MM/yy",
  // "dd/MM/yyyy", "dd-MMM-yy HH:mm:ss", "dd-MMM-yyyy HH:mm:ss",
  // "yyyy-MM-dd'T'HH:mm:ss",
  // "yyyy-MM-dd'T'HH:mm.S", "yyyy-MM-dd'T'HH:mm.S", "yyyy-MM-dd'T'HH:mm:ss.S",
  // "yyyy-MM-dd'T'HH:mm:ss.SS",
  // "yyyy-MM-dd'T'HH:mm:ss'Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z",
  // "yyyy-MM-dd'T'HH:mm:ss.SSSZZ" };

  public static String[] BRICS_DATE_FORMATS = { "yyyy-MM-dd HH",
      "yyyy-MM-dd HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss.SSS",
      "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "dd-MMM-yy",
      "dd-MMM-yy HH", "dd-MMM-yy HH:mm", "dd-MMM-yy HH:mm:ss",
      "dd-MMM-yyyy hh:mm:ss a z", "dd/MMM/yy", "dd/MMM/yyyy hh:mm:ss a z",
      "dd-MMM-yyyy hh:mm:ss a", "dd/MMM/yyyy hh:mm:ss a",
      "dd-MMM-yyyy hh:mm a z", "dd/MMM/yyyy hh:mm a z", "dd-MMM-yyyy hh:mm a",
      "dd/MMM/yyyy hh:mm a", "dd-MMM-yyyy HH:mm:ss z",
      "dd/MMM/yyyy HH:mm:ss z", "dd-MMM-yyyy HH:mm:ss", "dd/MMM/yyyy HH:mm:ss",
      "dd-MMM-yyyy HH:mm z", "dd/MMM/yyyy HH:mm z", "dd-MMM-yyyy HH:mm",
      "dd/MMM/yyyy HH:mm", "dd-MMM-yyyy z", "dd/MMM/yyyy z", "dd-MMM-yyyy",
      "dd/MMM/yyyy", "yyyy-MM-dd'T'HH:mm", "yyyy-MM-dd'T'HH:mm:ss",
      "yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ssZ",
      "yyyy-MM-dd'T'HH:mm.S", "yyyy-MM-dd'T'HH:mm.S",
      "yyyy-MM-dd'T'HH:mm:ss.S", "yyyy-MM-dd'T'HH:mm:ss.SS",
      "yyyy-MM-dd'T'HH:mm:ss'Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z",
      "yyyy-MM-dd'T'HH:mm:ss.SSSZZ" };

  // build the brics formatter
  static {
    DateTimeParser[] parsers = new DateTimeParser[BRICS_DATE_FORMATS.length];

    for (int i = 0; i < BRICS_DATE_FORMATS.length; i++) {
      parsers[i] = DateTimeFormat.forPattern(BRICS_DATE_FORMATS[i]).getParser();
    }

    BRICS_DATE_FORMATTER =
        new DateTimeFormatterBuilder().append(null, parsers).toFormatter()
            .withZone(DateTimeZone.forOffsetHours(TIMEZONE_UTC_OFFSET));
  }

}
