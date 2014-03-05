package gov.nih.tbi;

import gov.nih.tbi.commons.service.ServiceConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

public class ModulesConstants {

  private static final String DEFAULT_PORTAL_ROOT = "portal";
  // return null if server url is not defined
  private static final String DEFAULT_SERVER_URL = "";
  private static final String DEFAULT_RDF_FILE_EXPORT = "C:\\RDFExport\\";
  private static final Integer DEFAULT_RDF_SQL_LIMIT = 20000;

  public static final String NIH_PASSWORD_HINT_URL_PROPERTY =
      "renewPassword.passwordHints";
  public static final String RENEW_PASSWORD_SUBJECT_PROPERTY =
      "renewPassword.subject";
  public static final String RENEW_PASSWORD_URL_PROPERTY = "renewPassword.url";
  public static final String RENEW_PASSWORD_MESSAGE_BODY_PROPERTY =
      "renewPassword.body";
  public static final String RDF_UPLOAD_FAIL_EMAIL_SUBJECT_PROPERTY =
      "rdf.upload.fail.email.subject.property";
  public static final String RDF_UPLOAD_FAIL_EMAIL_MESSAGE_BODY_PROPERTY =
      "rdf.upload.fail.email.message.body.property";

  public static final String QUERY_ROOT = "query";
  // Pulls from property file stored on file system (via context.xml)

  // Module Properties
  @Value("#{applicationProperties['modules.portalRoot']}")
  private String modulesPortalRoot;
  @Value("#{applicationProperties['modules.org.name']}")
  private String modulesOrgName;
  @Value("#{applicationProperties['modules.org.email']}")
  private String modulesOrgEmail;
  @Value("#{applicationProperties['modules.org.noreply']}")
  private String modulesOrgNoreply;
  @Value("#{applicationProperties['modules.org.phone']}")
  private String modulesOrgPhone;
  @Value("#{applicationProperties['modules.dev.email']}")
  private String modulesDevEmail;

  // Describes if functionality is on or off
  @Value("#{applicationProperties['modules.pf.enabled']}")
  private String modulesPFEnabled;
  @Value("#{applicationProperties['modules.ddt.enabled']}")
  private String modulesDDTEnabled;
  @Value("#{applicationProperties['modules.vt.enabled']}")
  private String modulesVTEnabled;
  @Value("#{applicationProperties['modules.gt.enabled']}")
  private String modulesGTEnabled;
  @Value("#{applicationProperties['modules.st.enabled']}")
  private String modulesSTEnabled;
  @Value("#{applicationProperties['modules.udt.enabled']}")
  private String modulesUDTEnabled;
  @Value("#{applicationProperties['modules.dt.enabled']}")
  private String modulesDTEnabled;
  @Value("#{applicationProperties['modules.qt.enabled']}")
  private String modulesQTEnabled;
  @Value("#{applicationProperties['modules.om.enabled']}")
  private String modulesOMEnabled;
  @Value("#{applicationProperties['modules.account.enabled']}")
  private String modulesAccountEnabled;
  @Value("#{applicationProperties['modules.dashboard.enabled']}")
  private String modulesDashboardEnabled;
  @Value("#{applicationProperties['modules.admindashboard.enabled']}")
  private String modulesAdminDashboardEnabled;

  // Describer what URL to use for the functionality
  @Value("#{applicationProperties['modules.public.url.server']}")
  private String modulesPublicURL;
  @Value("#{applicationProperties['modules.pf.url.server']}")
  private String modulesPFURL;
  @Value("#{applicationProperties['modules.ddt.url.server']}")
  private String modulesDDTURL;
  @Value("#{applicationProperties['modules.vt.url.server']}")
  private String modulesVTURL;
  @Value("#{applicationProperties['modules.gt.url.server']}")
  private String modulesGTURL;
  @Value("#{applicationProperties['modules.st.url.server']}")
  private String modulesSTURL;
  @Value("#{applicationProperties['modules.udt.url.server']}")
  private String modulesUDTURL;
  @Value("#{applicationProperties['modules.dt.url.server']}")
  private String modulesDTURL;
  @Value("#{applicationProperties['modules.qt.url.server']}")
  private String modulesQTURL;
  @Value("#{applicationProperties['modules.om.url.server']}")
  private String modulesOMURL;
  @Value("#{applicationProperties['modules.account.url.server']}")
  private String modulesAccountURL;
  @Value("#{applicationProperties['modules.mipav.url.client']}")
  private String modulesMIPAVURL;
  @Value("#{applicationProperties['modules.scheduler.rdf.load.failure.notification.email']}")
  private String rdfLoadFailureNotificationEmail;
  @Value("#{applicationProperties['modules.order.manager.coriell.primary.biosample.identifier.key']}")
  private String coriellBiosamplePrimaryIdentifierKey;
  // This is a list longs. The numbers represent instances that are duplicates
  // of other instances (important when
  // writing across instances)
  @Value("#{applicationProperties['modules.duplicates']}")
  private String modulesDuplicates;

  // Mappings of disease types to URLS. Only entering one URL is possible
  private Map<Long, String> modulesDDTMap;
  private Map<Long, String> modulesVTMap;
  private Map<Long, String> modulesGTMap;
  private Map<Long, String> modulesSTMap;
  private Map<Long, String> modulesUDTMap;
  private Map<Long, String> modulesDTMap;
  private Map<Long, String> modulesQTMap;
  private Map<Long, String> modulesOMMap;
  private Map<Long, String> modulesAccountMap;
  private Map<Long, String> modulesPublicMap;
  private Map<Long, String> modulesPFMap;
  private List<Long> duplicateList;

  // Credentials for administrator account
  @Value("#{applicationProperties['modules.administrator.username']}")
  private String administratorName;
  @Value("#{applicationProperties['modules.administrator.password']}")
  private String administratorPassword;

  /**
   * The GUID Prefixes for the Validation Tool. Developer's Note: This is a
   * temporary fix for the validation process and should be eventually be
   * removed. MG
   */
  @Value("#{applicationProperties['modules.guid.prefix']}")
  private String guidPrefix;

  @Value("#{applicationProperties['modules.guid.invalidPrefix']}")
  private String guidInvPrefix;

  // Secure web services true or false (false on local only)
  // There is a hack here to support autowiring of a static field. (The set
  // property is defined in context.xml)
  @Value("#{applicationProperties['modules.webservices.secured']}")
  private boolean wsSecuredNonStatic;
  private static boolean wsSecured;

  // RDF Generation Properties
  @Value("#{applicationProperties['modules.rdf.file.path']}")
  private String rdfFileExportPath;

  @Value("#{applicationProperties['modules.rdf.db.url']}")
  private String rdfDBUrl;

  @Value("#{applicationProperties['modules.rdf.db.user']}")
  private String rdfDBUser;

  @Value("#{applicationProperties['modules.rdf.db.pass']}")
  private String rdfDBPass;

  @Value("#{applicationProperties['modules.rdf.db.graph']}")
  private String rdfDBGraph;

  @Value("#{applicationProperties['modules.rdf.sql.limit']}")
  private Integer rdfSQLLimit;

  public void setWsSet(boolean wsSecuredArg) {

    ModulesConstants.wsSecured = wsSecuredNonStatic;
  }

  /***************
   * 
   * Getters for General
   * 
   **************/
  public String getModulesPortalRoot() {

    if (modulesPortalRoot == null || modulesPortalRoot.isEmpty()) {
      return DEFAULT_PORTAL_ROOT;
    } else {
      return modulesPortalRoot;

    }
  }

  public String getModulesOrgName() {

    if (modulesOrgName == null || modulesOrgName.isEmpty()) {
      return "FITBIR";
    } else {
      return modulesOrgName;
    }
  }

  public String getModulesOrgEmail() {

    if (modulesOrgEmail == null || modulesOrgEmail.isEmpty()) {
      return "FITBIR-ops@mail.nih.gov";
    } else {
      return modulesOrgEmail;
    }
  }

  public String getModulesOrgNoreply() {

    if (modulesOrgNoreply == null || modulesOrgNoreply.isEmpty()) {
      return "noreply@fitbir.nih.gov";
    } else {
      return modulesOrgNoreply;
    }
  }

  public String getModulesOrgPhone() {

    if (modulesOrgPhone == null || modulesOrgPhone.isEmpty()) {
      return "301-594-3532";
    } else {
      return modulesOrgPhone;
    }
  }

  public String getModulesDevEmail() {

    if (modulesDevEmail == null || modulesDevEmail.isEmpty()) {
      return "FITBIR-DEV@mail.nih.gov";
    } else {
      return modulesDevEmail;
    }
  }

  /***************
   * 
   * Getters for Enabled
   * 
   **************/

  public Boolean getModulesPFEnabled() {

    if (modulesPFEnabled == null || modulesPFEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesPFEnabled);
    }
  }

  public Boolean getModulesDDTEnabled() {

    if (modulesDDTEnabled == null || modulesDDTEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesDDTEnabled);
    }
  }

  public Boolean getModulesVTEnabled() {

    if (modulesVTEnabled == null || modulesVTEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesVTEnabled);
    }
  }

  public Boolean getModulesGTEnabled() {

    if (modulesGTEnabled == null || modulesGTEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesGTEnabled);
    }
  }

  public Boolean getModulesSTEnabled() {

    if (modulesSTEnabled == null || modulesSTEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesSTEnabled);
    }
  }

  public Boolean getModulesUDTEnabled() {

    if (modulesUDTEnabled == null || modulesUDTEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesUDTEnabled);
    }
  }

  public Boolean getModulesDTEnabled() {

    if (modulesDTEnabled == null || modulesDTEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesDTEnabled);
    }
  }

  public Boolean getModulesQTEnabled() {

    if (modulesQTEnabled == null || modulesQTEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesQTEnabled);
    }
  }

  public Boolean getModulesOMEnabled() {

    if (modulesOMEnabled == null || modulesOMEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesOMEnabled);
    }
  }

  public Boolean getModulesAccountEnabled() {

    if (modulesAccountEnabled == null || modulesAccountEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesAccountEnabled);
    }
  }

  public Boolean getModulesDashboardEnabled() {

    if (modulesDashboardEnabled == null || modulesDashboardEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesDashboardEnabled);
    }
  }

  public Boolean getModulesAdminDashboardEnabled() {

    if (modulesAdminDashboardEnabled == null
        || modulesAdminDashboardEnabled.isEmpty()) {
      return false;
    } else {
      return Boolean.parseBoolean(modulesAdminDashboardEnabled);
    }
  }

  /***************
   * 
   * Getters for URL
   * 
   **************/

  public String getModulesPublicURL() {

    if (modulesPublicURL == null || modulesPublicURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesPublicMap == null || modulesPublicMap.isEmpty()) {
        modulesPublicMap = getURLMap(modulesPublicURL);
      }
      return modulesPublicMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesPublicURL(Long diseaseId) {

    if (modulesPublicMap == null || modulesPublicMap.isEmpty()) {
      modulesPublicMap = getURLMap(modulesPublicURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesPublicMap.size() == 1) {
      return modulesPublicMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesPublicMap.get(diseaseId);
  }

  public Map<Long, String> getModulesPublicMap() {

    if (modulesPublicMap == null || modulesPublicMap.isEmpty()) {
      modulesPublicMap = getURLMap(modulesPublicURL);
    }
    return modulesPublicMap;
  }

  public String getModulesPFURL() {

    if (modulesPFURL == null || modulesPFURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesPFMap == null || modulesPFMap.isEmpty()) {
        modulesPFMap = getURLMap(modulesPFURL);
      }
      return modulesPFMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesPFURL(Long diseaseId) {

    if (modulesPFMap == null || modulesPFMap.isEmpty()) {
      modulesPFMap = getURLMap(modulesPFURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesPFMap.size() == 1) {
      return modulesPFMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesPFMap.get(diseaseId);
  }

  public Map<Long, String> getModulesPFMap() {

    if (modulesPFMap == null || modulesPFMap.isEmpty()) {
      modulesPFMap = getURLMap(modulesPFURL);
    }
    return modulesPFMap;
  }

  public String getModulesDDTURL() {

    if (modulesDDTURL == null || modulesDDTURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesDDTMap == null || modulesDDTMap.isEmpty()) {
        modulesDDTMap = getURLMap(modulesDDTURL);
      }
      return modulesDDTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesDDTURL(Long diseaseId) {

    if (modulesDDTMap == null || modulesDDTMap.isEmpty()) {
      modulesDDTMap = getURLMap(modulesDDTURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesDDTMap.size() == 1) {
      return modulesDDTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesDDTMap.get(diseaseId);
  }

  public Map<Long, String> getModulesDDTMap() {

    if (modulesDDTMap == null || modulesDDTMap.isEmpty()) {
      modulesDDTMap = getURLMap(modulesDDTURL);
    }
    return modulesDDTMap;
  }

  public String getModulesQTURL() {

    if (modulesQTURL == null || modulesQTURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesQTMap == null || modulesQTMap.isEmpty()) {
        modulesQTMap = getURLMap(modulesQTURL);
      }
      return modulesQTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesQTURL(Long diseaseId) {

    if (modulesQTMap == null || modulesQTMap.isEmpty()) {
      modulesQTMap = getURLMap(modulesQTURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesQTMap.size() == 1) {
      return modulesQTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesQTMap.get(diseaseId);
  }

  public Map<Long, String> getModulesQTMap() {

    if (modulesQTMap == null || modulesQTMap.isEmpty()) {
      modulesQTMap = getURLMap(modulesQTURL);
    }
    return modulesQTMap;
  }

  public String getModulesOMURL() {

    if (modulesOMURL == null || modulesOMURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesOMMap == null || modulesOMMap.isEmpty()) {
        modulesOMMap = getURLMap(modulesOMURL);
      }
      return modulesOMMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesOMURL(Long diseaseId) {

    if (modulesOMMap == null || modulesOMMap.isEmpty()) {
      modulesOMMap = getURLMap(modulesOMURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesOMMap.size() == 1) {
      return modulesOMMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesOMMap.get(diseaseId);
  }

  public Map<Long, String> getModulesOMMap() {

    if (modulesOMMap == null || modulesOMMap.isEmpty()) {
      modulesOMMap = getURLMap(modulesOMURL);
    }
    return modulesOMMap;
  }

  public String getModulesVTURL() {

    if (modulesVTURL == null || modulesVTURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesVTMap == null || modulesVTMap.isEmpty()) {
        modulesVTMap = getURLMap(modulesVTURL);
      }
      return modulesVTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesVTURL(Long diseaseId) {

    if (modulesVTMap == null || modulesVTMap.isEmpty()) {
      modulesVTMap = getURLMap(modulesVTURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesVTMap.size() == 1) {
      return modulesVTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesVTMap.get(diseaseId);
  }

  public Map<Long, String> getModulesVTMap() {

    if (modulesVTMap == null || modulesVTMap.isEmpty()) {
      modulesVTMap = getURLMap(modulesVTURL);
    }
    return modulesVTMap;
  }

  public String getModulesGTURL() {

    if (modulesGTURL == null || modulesGTURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesGTMap == null || modulesGTMap.isEmpty()) {
        modulesGTMap = getURLMap(modulesGTURL);
      }
      return modulesGTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesGTURL(Long diseaseId) {

    if (modulesGTMap == null || modulesGTMap.isEmpty()) {
      modulesGTMap = getURLMap(modulesGTURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesGTMap.size() == 1) {
      return modulesGTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesGTMap.get(diseaseId);
  }

  public Map<Long, String> getModuleGTMap() {

    if (modulesGTMap == null || modulesGTMap.isEmpty()) {
      modulesGTMap = getURLMap(modulesGTURL);
    }
    return modulesGTMap;
  }

  public String getModulesSTURL() {

    if (modulesSTURL == null || modulesSTURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesSTMap == null || modulesSTMap.isEmpty()) {
        modulesSTMap = getURLMap(modulesSTURL);
      }
      return modulesSTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesSTURL(Long diseaseId) {

    if (modulesSTMap == null || modulesSTMap.isEmpty()) {
      modulesSTMap = getURLMap(modulesSTURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesSTMap.size() == 1) {
      return modulesSTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesSTMap.get(diseaseId);
  }

  public Map<Long, String> getModulesSTMap() {

    if (modulesSTMap == null || modulesSTMap.isEmpty()) {
      modulesSTMap = getURLMap(modulesSTURL);
    }
    return modulesSTMap;
  }

  public String getModulesUDTURL() {

    if (modulesUDTURL == null || modulesUDTURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesUDTMap == null || modulesUDTMap.isEmpty()) {
        modulesUDTMap = getURLMap(modulesUDTURL);
      }
      return modulesUDTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesUDTURL(Long diseaseId) {

    if (modulesUDTMap == null || modulesUDTMap.isEmpty()) {
      modulesUDTMap = getURLMap(modulesUDTURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesUDTMap.size() == 1) {
      return modulesUDTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesUDTMap.get(diseaseId);
  }

  public Map<Long, String> getModuleUDTMap() {

    if (modulesUDTMap == null || modulesUDTMap.isEmpty()) {
      modulesUDTMap = getURLMap(modulesUDTURL);
    }
    return modulesUDTMap;
  }

  public String getModulesDTURL() {

    if (modulesDTURL == null || modulesDTURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesDTMap == null || modulesDTMap.isEmpty()) {
        modulesDTMap = getURLMap(modulesDTURL);
      }
      return modulesDTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesDTURL(Long diseaseId) {

    if (modulesDTMap == null || modulesDTMap.isEmpty()) {
      modulesDTMap = getURLMap(modulesDTURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesDTMap.size() == 1) {
      return modulesDTMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesDTMap.get(diseaseId);
  }

  public Map<Long, String> getModulesDTMap() {

    if (modulesDTMap == null || modulesDTMap.isEmpty()) {
      modulesDTMap = getURLMap(modulesDTURL);
    }
    return modulesDTMap;
  }

  public String getModulesAccountURL() {

    if (modulesAccountURL == null || modulesAccountURL.isEmpty()) {
      return DEFAULT_SERVER_URL;
    } else {
      if (modulesAccountMap == null || modulesAccountMap.isEmpty()) {
        modulesAccountMap = getURLMap(modulesAccountURL);
      }
      return modulesAccountMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
  }

  public String getModulesAccountURL(Long diseaseId) {

    if (modulesAccountMap == null || modulesAccountMap.isEmpty()) {
      modulesAccountMap = getURLMap(modulesAccountURL);
    }
    // Case: A disease was given but there is only 1 option (The default option
    // at
    // ServiceConstants.DEFAULT_PROVIDER)
    if (modulesAccountMap.size() == 1) {
      return modulesAccountMap.get(ServiceConstants.DEFAULT_PROVIDER);
    }
    return modulesAccountMap.get(diseaseId);
  }

  public String getMIPAVURL() throws RuntimeException {

    if (modulesMIPAVURL == null || modulesMIPAVURL.isEmpty()) {
      throw new RuntimeException("No MIPAV client specified in properties!");
    }
    return modulesMIPAVURL;
  }

  public Map<Long, String> getModulesAccountMap() {

    if (modulesAccountMap == null || modulesAccountMap.isEmpty()) {
      modulesAccountMap = getURLMap(modulesAccountURL);
    }
    return modulesAccountMap;
  }

  public List<Long> getDuplicateModules() {

    if (duplicateList != null) {
      return duplicateList;
    }

    // Parse the duplicateList and store in memory
    duplicateList = new ArrayList<Long>();
    if (modulesDuplicates == null
        || modulesDuplicates.equals(ServiceConstants.EMPTY_STRING)) {
      return duplicateList;
    }
    String[] dupArray = modulesDuplicates.split(",");
    for (String s : dupArray) {
      duplicateList.add(Long.valueOf(s));
    }

    return duplicateList;
  }

  public boolean isDuplicateModule(Long diseaseId) {

    return getDuplicateModules().contains(diseaseId);
  }

  public String getAdministratorUsername() {

    return administratorName;
  }

  public String getAdministratorPassword() {

    return administratorPassword;
  }

  // public boolean getWsSecured()
  // {
  //
  // return wsSecured;
  // }

  /**
   * Takes a string A,X;B,Y;C,Z... and creates a map where A,B,C are Long and
   * XYZ are String. A string that is not a mapping (module only contains 1 URL)
   * will create a map with the element in ServiceConstants.DEFAULT_PROVIDER.
   * 
   * 
   * @param inputString
   * @return
   */
  private Map<Long, String> getURLMap(String inputString) {

    Map<Long, String> map = new LinkedHashMap<Long, String>();
    if (!inputString.contains(",") && !inputString.contains(";")) {
      // Case: The string is not in mapping format. It is just a single URL
      map.put(ServiceConstants.DEFAULT_PROVIDER, inputString);
    } else {
      for (String keyValue : inputString.split(" *; *")) {
        String[] pairs = keyValue.split(" *, *", 2);
        map.put(Long.valueOf(pairs[0]), pairs.length == 1 ? "" : pairs[1]);
        // The first item in the map also gets put with key: -1
        if (map.get(ServiceConstants.DEFAULT_PROVIDER) == null) {
          map.put(ServiceConstants.DEFAULT_PROVIDER, pairs.length == 1 ? ""
              : pairs[1]);
        }
      }
    }
    return map;
  }

  public static boolean getWsSecured() {

    return wsSecured;
  }

  /***********************************************
   * 
   * Developer's Note: The getters and setters below are for Validation Tool
   * GUID validation process. This is temporary.
   * 
   * 
   * 
   ***********************************************/
  public String getGuidPrefix() {

    return guidPrefix;
  }

  public void setGuidPrefix(String guidPrefix) {

    this.guidPrefix = guidPrefix;
  }

  public String getGuidInvPrefix() {

    return guidInvPrefix;
  }

  public void setGuidInvPrefix(String guidInvPrefix) {

    this.guidInvPrefix = guidInvPrefix;
  }

  public String getRdfFileExportPath() {

    if (rdfFileExportPath == null || rdfFileExportPath.isEmpty()) {
      rdfFileExportPath = DEFAULT_RDF_FILE_EXPORT;
    }

    return rdfFileExportPath;
  }

  public String getRdfDBUrl() {

    if (rdfDBUrl == null || rdfDBUrl.isEmpty()) {
      rdfDBUrl = "PROPERTY NOT SET IN MODULES";
    }

    return rdfDBUrl;
  }

  public String getRdfDBUser() {

    if (rdfDBUser == null || rdfDBUser.isEmpty()) {
      rdfDBUser = "PROPERTY NOT SET IN MODULES";
    }
    return rdfDBUser;
  }

  public String getRdfDBPass() {

    if (rdfDBPass == null || rdfDBPass.isEmpty()) {
      rdfDBPass = "PROPERTY NOT SET IN MODULES";
    }

    return rdfDBPass;
  }

  public String getRdfDBGraph() {

    if (rdfDBGraph == null || rdfDBGraph.isEmpty()) {
      rdfDBGraph = "PROPERTY NOT SET IN MODULES";
    }

    return rdfDBGraph;
  }

  public Integer getRdfSQLLimit() {

    if (rdfSQLLimit == null || rdfSQLLimit <= 0) {
      rdfSQLLimit = DEFAULT_RDF_SQL_LIMIT;
    }

    return rdfSQLLimit;
  }

  public String getRdfLoadFailureNotificationEmail() {

    if (rdfLoadFailureNotificationEmail == null
        || rdfLoadFailureNotificationEmail.isEmpty()) {
      return "FITBIR-DEV@mail.nih.gov";
    } else {
      return rdfLoadFailureNotificationEmail;
    }

  }

  /**
   * This function will return the value of the property
   * 'modules.order.manager.coriell.primary.biosample.identifier.key' picked up
   * from the modules.properties file. If no property is found with this key
   * then it will return the default value of "Repository_Biosample"
   * 
   * @return value of the coriell biosample primary key picked up from the
   *         modules.properties file
   */
  public String getCoriellBiosamplePrimaryIdentifierKey() {

    if (this.coriellBiosamplePrimaryIdentifierKey == null
        || (this.coriellBiosamplePrimaryIdentifierKey.isEmpty() == true)) {
      return "Repository_Biosample";
    } else {
      return this.coriellBiosamplePrimaryIdentifierKey;
    }
  }
}
