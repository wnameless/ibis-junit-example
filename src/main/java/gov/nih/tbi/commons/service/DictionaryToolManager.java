package gov.nih.tbi.commons.service;

import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.ws.model.UserLogin;
import gov.nih.tbi.commons.model.DataElementStatus;
import gov.nih.tbi.commons.model.EntityType;
import gov.nih.tbi.commons.model.PermissionType;
import gov.nih.tbi.commons.model.StatusType;
import gov.nih.tbi.commons.model.hibernate.AuditLog;
import gov.nih.tbi.commons.util.PaginationData;
import gov.nih.tbi.dictionary.model.AbstractDataElement;
import gov.nih.tbi.dictionary.model.AbstractDataStructure;
import gov.nih.tbi.dictionary.model.hibernate.Alias;
import gov.nih.tbi.dictionary.model.hibernate.BasicDataElement;
import gov.nih.tbi.dictionary.model.hibernate.Category;
import gov.nih.tbi.dictionary.model.hibernate.Classification;
import gov.nih.tbi.dictionary.model.hibernate.DataElement;
import gov.nih.tbi.dictionary.model.hibernate.DataStructure;
import gov.nih.tbi.dictionary.model.hibernate.Disease;
import gov.nih.tbi.dictionary.model.hibernate.Domain;
import gov.nih.tbi.dictionary.model.hibernate.Keyword;
import gov.nih.tbi.dictionary.model.hibernate.KeywordElement;
import gov.nih.tbi.dictionary.model.hibernate.MapElement;
import gov.nih.tbi.dictionary.model.hibernate.RepeatableGroup;
import gov.nih.tbi.dictionary.model.hibernate.SubDomain;
import gov.nih.tbi.dictionary.model.hibernate.Subgroup;
import gov.nih.tbi.dictionary.model.hibernate.ValidationPlugin;
import gov.nih.tbi.dictionary.model.hibernate.ValueRange;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.httpclient.HttpException;

/**
 * Dictionary Tool Manager
 * 
 * @author Andrew Johnson
 * @author Francis Chen
 * 
 */
public interface DictionaryToolManager extends BaseManager {

  // LISTS

  /**
   * Returns a List of all BasicDataStructures
   * 
   * @param curUser
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<? extends AbstractDataStructure> listDataStructures(
      Account account, PaginationData pageData, String proxyticket)
      throws MalformedURLException, UnsupportedEncodingException;

  @Deprecated
  public List<? extends AbstractDataStructure> listDataStructures(
      UserLogin userLogin, PaginationData pageData)
      throws MalformedURLException;

  /**
   * Returns a list of PUBLISHED data structures. Data can be sorted/paged with
   * an optional PaginationData object. This function is account independent.
   * 
   * @param pageData
   * @return
   */
  public List<? extends AbstractDataStructure> listPublishedDataStructures(
      PaginationData pageData);

  /**
   * Returns a list of all BasicDataStructures with the statuses contained in
   * array statusStringList. Accepts an array of Strings and matches them to the
   * known statuses.
   * 
   * @Deprecated, instead use method that accepts known enumerations.
   * 
   * @param curUser
   * @param statusStringList
   * @return
   */

  /**
   * Returns a List of all BasicDataElement
   * 
   * @param curUser
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<? extends AbstractDataElement> listDataElements(Account account,
      String proxyTicket) throws MalformedURLException,
      UnsupportedEncodingException;

  // GETS

  /**
   * This method gets a keyword by ID
   * 
   * @param curUser
   * @param id
   * @return
   */
  public Keyword getKeyword(Account account, Long id);

  public AbstractDataStructure getDataStructure(Long dataStructureId);

  public DataStructure getDataStructure(String shortName, Integer version);

  public AbstractDataElement getMapElement(Long mapElementId);

  public AbstractDataElement getDataElement(Long dataElementId);

  /**
   * Gets a list of DataElements that match the list of IDs
   * 
   * @param ids
   *          List of DataElement ids to get
   * @return List of DataElements
   */
  public List<DataElement> getDataElementsListByIds(List<Long> ids);

  public AbstractDataElement getDataElementByName(String dataElementName);

  /**
   * Gets a list of DataStructures that are attached to DataElement with the
   * specified ID
   * 
   * @param dataElementId
   * @return
   */
  public List<? extends AbstractDataStructure> getAttachedDataStructure(
      Long dataElementId);

  /**
   * Returns a history of changes made to the entity
   * 
   * @param entityType
   * @param id
   * @return
   */
  public List<AuditLog> getHistory(EntityType entityType, Long id);

  /**
   * Returns the Unique Result from the Audit Trail that has a type
   * AuditType.INSERT
   * 
   * @param entityType
   * @param id
   * @return
   */
  public AuditLog getCreatedHistory(EntityType entityType, Long id);

  // SAVE

  /**
   * Saves a a data structure into the database. Returns any EntityMaps that
   * need to be registered with UM
   * 
   * @param acocunt
   * @param permission
   * @param dataStructure
   * @param errors
   * @param warnings
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws HttpException
   */
  public AbstractDataStructure saveDataStructure(Account acocunt,
      PermissionType permission, AbstractDataStructure dataStructure,
      List<String> errors, List<String> warnings, String proxyTicket)
      throws MalformedURLException, UnsupportedEncodingException,
      HttpException, IOException;

  /**
   * Saves a dataElement in the new database. Can be used for updating and
   * saving new structures NOTE: User must check for write permissions before
   * using this function
   * 
   * @param account
   * @param permission
   * @param dataElement
   * @param errors
   * @param warnings
   * @return returns true if new entityMap entries must be created in UM
   * @throws MalformedURLException
   * @throws IOException
   * @throws HttpException
   */
  public AbstractDataElement saveDataElement(Account account,
      AbstractDataElement dataElement, List<String> errors,
      List<String> warnings, String[] twoProxyTickets)
      throws MalformedURLException, UnsupportedEncodingException,
      HttpException, IOException;

  public AbstractDataStructure saveDraftCopy(Account account,
      PermissionType permission, AbstractDataStructure dataStructure,
      List<String> errors, List<String> warnings, String proxyTicket)
      throws MalformedURLException, UnsupportedEncodingException,
      HttpException, IOException;

  // DELETE

  public Boolean deleteDataStructure(Account account,
      AbstractDataStructure dataStructure, List<String> errors,
      List<String> warnings, String proxyTicket);

  /**
   * 
   * @param account
   * @param dataElement
   * @param errors
   * @param warnings
   * @return
   * @deprecated The system no longer supports the deletion of data elements.
   *             This function has not been kept up to date and will not
   *             properly delete a data element.
   */
  @Deprecated
  public Boolean deleteDataElement(Account account,
      AbstractDataElement dataElement, List<String> errors,
      List<String> warnings);

  // SEARCH

  public List<Keyword> searchKeywords(Account account,
      AbstractDataStructure abstractDataStructure, String searchKey);

  /**
   * Sets up and exectues a query for a list of data elments based on pagination
   * information, filters, and account access
   * 
   * @param account
   *          : Account performing the search. If null, then search is limited
   *          to public data elements only
   * @param diseaseSelection
   * @param domainSelection
   * @param subDomainSelection
   * @param populationSelection
   * @param subgroupSelection
   * @param classificationSelection
   * @param filterId
   *          : The id of the DataElementStatus we want to search for. If null
   *          or -1, then we want to search all status types
   * @param elementTypeSelection
   * @param searchKey
   * @param pageData
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<BasicDataElement> searchElements(Account account,
      String diseaseSelection, Boolean generalSearch, String domainSelection,
      String subDomainSelection, String populationSelection,
      String subgroupSelection, String classificationSelection, Long filterId,
      Category category, String searchKey, PaginationData pageData,
      String proxyTicket) throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Method that get a list of DataStructures that the user has access to,
   * filtered by filterType, which can be a status ID if it is 0,1,2,3,4 The
   * list is also filtered by possesionType which is either 0 (MINE) or 1 (ALL)
   * 
   * @param User
   * @param filterType
   * @param possesionType
   * @param pageData
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<? extends AbstractDataStructure> getFilteredDataStructureList(
      Account account, Long filterType, Long possesionType,
      PaginationData pageData, String proxyTicket)
      throws MalformedURLException, UnsupportedEncodingException;

  public List<? extends AbstractDataStructure> getDataStructureList(
      Account account, Long filterType, Long possesionType,
      PaginationData pageData, String proxyTicket)
      throws MalformedURLException, UnsupportedEncodingException;

  // ADD

  /**
   * The elements listed in the array are added to the repeatableGroup object.
   * If the mapElements that are created do not have ids, they are assigned
   * temporary (negative) ids starting at tempId and iterating downward. These
   * temporary ids allow the map elements to be identified in the
   * sessionDataStructure until they are added to the database.
   * 
   * @param dataElementIds
   * @param currentDataStructure
   * @param tempId
   */
  public void addDataElementsById(String[] dataElementIds,
      RepeatableGroup repeatableGroup, Integer tempId);

  /**
   * This method adds a MapElement to a RepeatableGroup, using the MapElement's
   * position to insert it in the correct place. If no position is given, it is
   * added to the end, if a negative number is given, then it is placed at the
   * beginning.
   * 
   * @param mapElement
   * @param dataStructure
   */
  public void addMapElementToList(MapElement mapElement,
      RepeatableGroup repeatableGroup);

  /**
   * Adds a repeatable group to a Data Structure. Returns the Data Structure
   * with the new repeatbale group in its list
   * 
   * @param repeatableGroup
   * @param dataStructure
   */
  public DataStructure addRepeatableGroupToList(
      RepeatableGroup repeatableGroup, DataStructure dataStructure);

  // REMOVE

  /**
   * Removes a MapElement from a DataStructure based on the mapElementId Updated
   * to include repeatable groups
   * 
   * @param mapElementId
   * @param groupElementId
   * @param dataStructure
   * @return
   */
  public MapElement removeMapElementFromList(Long mapElementId,
      Long groupElementId, DataStructure dataStructure);

  /**
   * Removes a MapElement from a DataStructure based on the dataElementName
   * 
   * @param dataElementName
   * @param dataStructure
   * @return
   */
  public MapElement removeMapElementFromList(String dataElementName,
      RepeatableGroup repeatableGroup);

  /**
   * Removes a Repeatable Group from a DataStructure based on the id of the RG
   * 
   * @param repeatableGroupId
   * @param dataStructure
   * @return
   */

  public RepeatableGroup removeRepeatableGroupFromList(Long repeatableGroupId,
      DataStructure dataStructure);

  // FIND

  /**
   * Returns the MapElement that is attached to a RepeatableGroup based on
   * mapElementId Updated to include RepeatableGroup table
   * 
   * @param mapElementId
   * @param repeatableGroup
   * @return
   */
  public MapElement findMapElementInList(Long mapElementId,
      RepeatableGroup repeatableGroup);

  public RepeatableGroup findRepeatableGroupInList(Long groupElemenetId,
      DataStructure dataStructure);

  /**
   * Returns the MapElement that is attached to a RepeatableGroup based on
   * dataElementName
   * 
   * @param dataElementName
   * @param repeatableGroup
   * @return
   */
  public MapElement findMapElementInList(String dataElementName,
      RepeatableGroup repeatableGroup);

  // UTIL

  public KeywordElement removeKeywordElementFromList(String key,
      AbstractDataElement dataElement);

  /**
   * Changes a MapElement's position in the DataStructure to given newPosition
   * 
   * @param mapElementId
   * @param newPosition
   * @param dataStructure
   */
  public void moveMapElementInList(Long mapElementId, Integer newPosition,
      Long groupElementId, DataStructure dataStructure);

  /**
   * Changes a RepeatableGroups's position in the DataStructure to given
   * newPosition
   * 
   * @param RepeatableGroupId
   * @param newPosition
   * @param dataStructure
   */
  public void moveGroupInList(Long repeatableGroupId, Integer newPosition,
      DataStructure dataStructure);

  /**
   * This method returns a list of validation plugins
   * 
   * @return String
   */
  public List<ValidationPlugin> getValidationPlugins();

  /**
   * Validates the alias
   * 
   * @param dataStructure
   *          : if non-null, the validator will validate against all the map
   *          elements in this dataStructure as well as the database
   * @param alias
   * @param dataElement
   * @return boolean
   */
  public boolean validateAlias(DataStructure dataStructure, Alias alias,
      AbstractDataElement dataElement);

  /**
   * Determines if a short name is valid. true = valid, false = invalid.
   * 
   * @param dataStructure
   * @param fieldValue
   * @return
   */
  public boolean validateShortName(AbstractDataStructure dataStructure,
      String fieldValue);

  /**
   * Removes the dataElement, given an ID
   * 
   * @param Long
   */
  public void removeDataElementFromList(Long deId);

  /**
   * Changes the status of the dataStructure and saves it.
   * 
   * @param account
   * @param permission
   * @param dataStructure
   * @param status
   * @return AbstractDataStructure
   * @throws UserPermissionException
   * @throws MalformedURLException
   */
  public AbstractDataStructure editDataStructureStatus(Account account,
      PermissionType permission, AbstractDataStructure dataStructure,
      StatusType status) throws UserPermissionException, MalformedURLException;

  /**
   * Returns the next crf version
   * 
   * @param shortName
   * @return
   */
  public Integer getNextDataStructureVersion(String shortName);

  /**
   * Returns true if element name is unique, false otherwise Validates against
   * aliases and element names in the database as well as attached to
   * dataStructure
   * 
   * @param dataStructure
   *          : a dataStructure whose map elements will be validated against
   *          dataElement
   * @param dataElement
   * @return Boolean
   */
  public Boolean validateDataElementName(DataStructure dataStructure,
      AbstractDataElement dataElement);

  /**
   * Returns true if the string passed is a unique keyword name, false otherwise
   * 
   * @param keywordName
   * @return
   */
  public Boolean validateKeywordName(String keywordName);

  /**
   * This method parses a file into a list of map elements. Only handles csv at
   * the moment.
   * 
   * @param upload
   * @return
   * @throws Exception
   */
  public List<DataElement> parseDataElement(File upload,
      String uploadContentType) throws Exception;

  /**
   * Returns a keyword element with keyword of same string as key. Null if not
   * found
   * 
   * @param key
   * @return
   */
  public KeywordElement findKeywordElement(String key);

  /**
   * Returns a mapping of mapElement to a list of invalid fields. A field is
   * invalid if it is required and not filled out.
   * 
   * @param mapElementList
   * @param true if user is doing an admin import rather than attachin to a data
   *        structure
   * @return
   * @throws MalformedURLException
   * @throws FileNotFoundException
   * @throws IOException
   */
  public List<String> validateImportedDataElement(File upload,
      DataElement dataElement, boolean inAdmin) throws MalformedURLException,
      FileNotFoundException, IOException;

  /**
   * Gets a list of all keywords
   * 
   * @return List<Keyword>
   */
  public List<Keyword> getAllKeywords();

  /**
   * Overload of the other method with the same name. Uses a String for name and
   * Long for formId
   * 
   * @param groupName
   * @param dsId
   * @return Boolean
   */
  public Boolean validateRepeatableGroupName(String groupName, Long dsId);

  /**
   * Returns the output stream of a basic CSV file to be exported
   * 
   * @param elementList
   * @return
   */
  public ByteArrayOutputStream exportToCsvBasic(
      List<BasicDataElement> elementList) throws IOException;

  /**
   * Returns the output stream of a detailed CSV file to be exported
   * 
   * @param elementList
   */
  public ByteArrayOutputStream
      exportToCsvDetailed(List<DataElement> elementList) throws IOException;

  /**
   * Checks to see if a set of ValueRanges contains a ValueRange with the same
   * value as permissibleValue
   * 
   * @param valueRanges
   * @param permissibleValue
   * @return
   */
  public boolean isPermissibleValueUnique(Set<ValueRange> valueRanges,
      String permissibleValue);

  /**
   * Changes the state of the Data Element by checking permissions first. NOTE:
   * After this function is called user must check return value to see if new
   * entityMaps must be created with WS
   * 
   * @param account
   * @param dataElement
   * @param status
   * @return : true if new entityMaps must be registered with UM
   * @throws MalformedURLException
   * @throws IOException
   * @throws HttpException
   */
  public AbstractDataElement editDataElementStatus(Account account,
      AbstractDataElement dataElement, DataElementStatus status,
      String[] threeProxyTickets) throws MalformedURLException,
      UnsupportedEncodingException, HttpException, IOException;

  /**
   * This method returns an output stream of the csv of a datastructure to be
   * exported
   * 
   * @param permission
   * @param dataStructure
   * @param includeData
   * @return
   * @throws IOException
   */
  public ByteArrayOutputStream exportDataStructure(String serverLocation,
      String proxyTicket, PermissionType permission,
      DataStructure dataStructure, boolean includeData) throws IOException;

  /**
   * return the number of data structures with a specific status determined by
   * the status id. If statusId is null, then return the total number of data
   * structures.
   * 
   * @param statusId
   * @return
   */
  public Long getNumDSWithStatus(Long statusId);

  /**
   * return the number of data elements with a specific status determined by the
   * status id. If the id is null, then the total count of DEs is returned
   * 
   * @param statusId
   * @return
   */
  public Long getNumDEWithStatus(Long statusId);

  /**
   * return the number of data elements with a specific status determined by the
   * status id and with a given category. If either argument is null then then
   * the query is not filtered by that parameter
   * 
   * @param statusId
   * @param category
   * @return
   */
  public Long getNumDEWithStatusAndCategory(Long statusId, Category category);

  /**
   * return the number of data elements with a certain category. If the category
   * is null then query all categories.
   * 
   * @param category
   * @return
   */
  public Long getNumDEWithCategory(Category category);

  /**
   * Returns an warning for each column present in the data file which does not
   * match a property in DataElement
   * 
   * @param upload
   * @param uploadContentType
   * @return
   * @throws IOException
   */
  public List<String>
      validateExtraColumns(File upload, String uploadContentType)
          throws IOException;

  /**
   * Deletes the condition from the database
   * 
   * @param id
   *          - id of the condition to delete
   */
  public void deleteCondition(Long id);

  /**
   * This method removes the MapElement and reorders the rest of the
   * MapElements' positions
   * 
   * @param removeElement
   * @param dataStructure
   */
  public void removeMapElement(MapElement removeElement,
      RepeatableGroup repeatableGroup);

  /**
   * Get the list of subDomains that are under the given domain
   * 
   * @param domain
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<SubDomain> getSubDomainList(Domain domain, Disease disease)
      throws MalformedURLException, UnsupportedEncodingException;

  public List<DataStructure> getDataStructureByIds(List<Long> dsIdList);

  /**
   * Returns the domain listing for a particular disease
   * 
   * @param disease
   *          - disease used to filter the domain list
   * @return list of domains relevant to the disease
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Domain> getDomainsByDisease(Disease disease)
      throws MalformedURLException, UnsupportedEncodingException;

  /**
   * Gets the disease object, given the disease name
   * 
   * @param diseaseName
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public Disease getDiseaseByName(String diseaseName)
      throws MalformedURLException, UnsupportedEncodingException;

  /**
   * Returns a list of subgroups based on the disease selected
   * 
   * @param disease
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Subgroup> getSubgroupsByDisease(Disease disease)
      throws MalformedURLException, UnsupportedEncodingException;

  /**
   * Returns a list of classifications
   * 
   * @param disease
   * @param isAdmin
   *          - true if getting admin choices for classification, false if
   *          getting user choices
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Classification> getClassificationList(Disease disease,
      boolean isAdmin) throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Converts a \n deliminated String into a List
   * 
   * @param stringIn
   */
  public List<String> locations(String stringIn);

  /**
   * Returns the prefix of the disease with id
   * 
   * @param diseaseId
   * @return
   */
  public String getDiseasePrefix(Long diseaseId);

  /**
   * Returns true if the character is valid for xml, false otherwise.
   * 
   * @param character
   * @return
   */
  public boolean isValidForXml(char character);

  /**
   * Returns a hashmap of line number to invalid characters. This is used to
   * display user friendly errors.
   * 
   * @param line
   * @return
   */
  public HashMap<Integer, ArrayList<Character>> isValidForXml(
      List<String[]> line);

  /**
   * Returns a new DataStructure that can be saved given an imported XML
   * DataStructure object
   * 
   * @param workingDataStructure
   * @return
   */
  public DataStructure retrieveImportDataStructure(
      DataStructure workingDataStructure);

  /**
   * This will verify that a short name provided by the user has not been
   * uploaded to the system.
   * 
   * @param dataStructure
   * @param shortName
   * @return
   */
  public boolean isImportFSNameUnique(AbstractDataStructure dataStructure,
      String shortName);

  public Set<Long> listUserAccessDEs(Account account, String proxyTicket);

  public List<String> validateOverwriteDE(DataElement ovr);

  public DataElement update(Account account, DataElement de,
      ArrayList<String> arrayList, ArrayList<String> arrayList2,
      String[] twoProxyTickets);
}
