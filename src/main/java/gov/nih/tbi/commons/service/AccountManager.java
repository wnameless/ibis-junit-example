package gov.nih.tbi.commons.service;

import gov.nih.tbi.account.model.AccountPrivilegesForm;
import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.BasicAccount;
import gov.nih.tbi.account.model.hibernate.EntityMap;
import gov.nih.tbi.account.model.hibernate.PermissionGroup;
import gov.nih.tbi.commons.model.EntityType;
import gov.nih.tbi.commons.model.PermissionType;
import gov.nih.tbi.commons.model.RoleType;
import gov.nih.tbi.commons.model.hibernate.User;
import gov.nih.tbi.commons.util.PaginationData;
import gov.nih.tbi.repository.model.hibernate.Study;
import gov.nih.tbi.semantic.model.QueryPermissions;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;

/**
 * This interface is used to abstract the internal workings of the account
 * manager module.
 * 
 * IMPORTANT: Calls to account manager should be made through the
 * AccountWebService. Any access to UM through BRICS or DDT should be made
 * through a web service call. (UM functionality such as request
 * account/privileges can access the accountManager directly). Exception: Calls
 * to the accountManger that do not hit the database are alright.
 * 
 * @author Andrew Johnson
 * 
 * 
 */
public interface AccountManager extends BaseManager {

  /**
   * Returns the unique account record with the given email address or null if
   * no record exists.
   * 
   * @param email
   * @return
   */
  public Account getAccountByEmail(String email);

  /**
   * Returns the unique account record with the given username or null if no
   * record exists
   * 
   * @param userName
   * @return
   */
  public Account getAccountByUserName(String userName);

  /**
   * Computes a hash token and returns it to the user. Stores the date the token
   * was created with the account.
   * 
   * @param account
   * @return
   */
  public String addRecoveryTokenForAccount(Account account);

  /**
   * Checks the token against the username and the recovery time of the account
   * 
   * @param account
   * @param token
   * @return
   */
  public Boolean validateRecoveryTokenForAccount(Account account, String token);

  /**
   * Send an email containing the recovery URL to the specified account email
   * address
   * 
   * @param account
   * @param Action
   *          path including token key, e.g.:
   *          publicAccounts/passwordRecoveryAction!recover.action?token=a0ce
   * @param url
   *          URL NAME + SERVER ROOT, e.g.:
   *          http://fitbir-dev.cit.nih.gov/portal/
   * @param subject
   * @param messagePattern
   * 
   * @throws MessagingException
   */
  public void sendPasswordRecoveryEmail(Account account, String token,
      String url, String subject, String messagePattern)
      throws MessagingException;

  /**
   * Send an email to the specified user notifying them of their account's
   * rejection or approval
   * 
   * @param account
   * @param subject
   *          TODO
   * @param messageText
   * @param accountApproved
   * @throws MessagingException
   */
  public void sendAccountApprovalRejectionEmail(Account account,
      String subject, String messageText, Boolean accountApproved)
      throws MessagingException;

  /**
   * This checks the password versus the old passwords in the database
   * 
   * @param account
   *          - account of user
   * @param password
   *          - new requested password
   * @return true if it has been used in the last 25 passwords
   */
  public boolean checkPassword(Account account, String password);

  /**
   * Hashes the the new password and saves it to the account
   * 
   * @param account
   * @param password
   */
  public void changePassword(Account account, String password);

  /**
   * This method saves the account and related permissions
   * 
   * @param account
   * @return
   */
  public Account saveAccount(Account account);

  /**
   * Gets a list of permission groups
   * 
   * @return
   */
  public List<PermissionGroup> getPermissionGroupList();

  /**
   * Validates if a username is valid
   * 
   * @param fieldValue
   * @return
   */
  public Boolean validateUserName(String userName);

  /**
   * Validates if a username is available
   * 
   * @param fieldValue
   * @return
   */
  public Boolean checkUserNameAvailability(String userName, Long id);

  /**
   * Validates if a era id is available
   * 
   * @param fieldValue
   * @return
   */
  public Boolean checkEraIdAvailability(String eraId, Long id);

  /**
   * Hashes the password into a byte array
   * 
   * @param password
   * @return
   */
  public byte[] hashPassword(String password);

  /**
   * Gets the account by ID, also checks user permission.
   * 
   * @param user
   * @param valueOf
   * @return
   */
  public Account getAccount(User curUser, Long accountId);

  /**
   * Gets the account by user
   * 
   * @param user
   * @return
   */
  public Account getAccountByUser(User user);

  /**
   * Validates email for uniqueness. ID is the ID of the user to not check
   * 
   * @param email
   *          , id
   * @return
   */
  public boolean validateEmail(String email, Long id);

  /**
   * Validates the format of password. One uppercase, one number, one special
   * character
   * 
   * @param fieldValue
   * @return
   */
  public boolean validatePasswordFormat(String fieldValue);

  /**
   * Get permission group by name
   * 
   * @param string
   * @return
   */
  public PermissionGroup getPermissionGroup(String groupName);

  /**
   * Gets permission group by ID
   * 
   * @param selectedId
   * @return
   */
  public PermissionGroup getPermissionGroupById(Long selectedId);

  /**
   * Gets a list of all the accounts
   * 
   * @return
   */
  public List<Account> getAccountList();

  /**
   * Gets the user with the specified ID
   * 
   * @param id
   * @return
   */
  public User getUserById(Long id);

  /**
   * Return a list of accounts based on the search and pagination criteria.
   * Passing a filter value > 0 will search for all status types.
   * 
   * @param key
   * @param statusId
   * @param pageData
   * @return
   */
  public List<BasicAccount> searchAccounts(String key, Long statusId,
      PaginationData pageData);

  /**
   * Deactivated the account specified. Pending requests are left as is.
   * 
   * @param id
   * @return a copy of the deactivated account.
   */
  public Account deactivateAccount(Long id);

  /**
   * Changes an account from inactive to active or pending. The account will be
   * changed to pending if there are any requested roles.
   * 
   * @param id
   * @return a copy of the activated account
   */
  public Account reactivateAccount(Long id, String userName);

  /**
   * Changes the given account to active or pending status, depending on if
   * there are any outstanding change requests.
   * 
   * @param account
   * @return The account object passed as a param
   */
  public Account approveAccount(Account account);

  /**
   * Changes the given account from requested to denied. Disables the account,
   * sets the account status to denied and adds a timestamp to the username.
   * 
   * @param The
   *          account to make inactive
   * @return The account object that is saved to the db.
   */
  public Account rejectAccount(Account account);

  /**
   * Gets a list of all active Accounts that have the role provided.
   * 
   * @param role
   * @param onlyActive
   *          : if this is true, then only active accounts will be returned.
   * @return
   */
  public List<Account> getAccountListByRole(RoleType role, boolean onlyActive);

  /**
   * Get the account of the current owner of the entity with and id matching
   * entityId
   * 
   * @param entityId
   *          entityId to match against the id of the entities
   * @param type
   *          of entity to search for
   * @return
   */
  public Account getEntityOwnerAccount(Long entityId, EntityType type);

  /**
   * Gets a list of all public permission groups
   * 
   * @return
   */
  public List<PermissionGroup> getPublicPermissionGroups();

  /**
   * Gets a list of all non-public permission groups
   * 
   * @return
   */
  public List<PermissionGroup> getPrivatePermissionGroups();

  /**
   * Gets a list of all permission groups the account has access to
   * 
   * @param account
   * @return
   */
  public List<PermissionGroup> getAccessablePermissionGroups(Account account);

  /**
   * Gets the number of accounts with a specific status based on the id given
   * 
   * @param statusId
   * @return
   */
  public Long getNumAccountsWithStatus(Long statusId);

  /**
   * Returns true if the given account is a member of the given permission group
   * 
   * @param account
   * @param permissionGroup
   * @return
   */
  public boolean isAccountInPermissionGroup(Account account,
      PermissionGroup permissionGroup);

  /**
   * Checks if a phone number is valid.
   * 
   * @param phone
   * @return valid -> true; invalid -> false
   */
  public boolean validatePhoneNumber(String phone);

  /**
   * Removes the entity map from a permission group
   * 
   * @param account
   * @param permissionGroupName
   * @param type
   * @param entityId
   * @return
   */
  public boolean removeEntityFromPermissionGroup(Account account,
      String permissionGroupName, EntityType type, Long entityId);

  /**
   * Removes the study from public study permission group
   * 
   * @param account
   * @param type
   * @param entityId
   */
  public boolean removeFromPublicStudy(Account account, Study study);

  /**
   * Checks if reactivating account would pass validation
   * 
   * @param id
   * @return
   */
  public boolean validateReactivateAccount(Long id);

  /**
   * Saves the permission group to the database
   * 
   * @param permissionGroup
   *          - The permission group to save to database.
   */
  public void savePermissionGroup(PermissionGroup permissionGroup);

  /**
   * Checks if the permission group name is unique
   * 
   * @param permissionGroupId
   * @param fieldValue
   * @return
   */
  public boolean validatePermissionGroupName(Long permissionGroupId,
      String fieldValue);

  /**
   * This method determines the differences between the old account and what has
   * changed in the form.
   * 
   * @param previousAccount
   *          (Account) - the old account with old permissions
   * @param tempPrivilegesForm
   *          (AccountPrivilegesForm) - the new list of privileges
   * @return map between role types and the changes made.
   */
  public Map<RoleType, String> determineChanges(Account previousAccount,
      AccountPrivilegesForm tempPrivilegesForm);

  /**
   * Validates that the email matches an email of an account in the system
   * 
   * @param email
   *          - email to validate
   * @return true if email matches, false otherwise
   */
  public boolean validateEmailMatch(String email);

  /**
   * Gets a list of all affiliated institutions that have been used in the past.
   */
  public List<String> getAffiliatedInstitutions();

  /**
   * Checks an account for access to a specific entity. Throws an
   * UserPermissionException if user does not have access
   * 
   * @param account
   * @param entityType
   * @param entityId
   * @param permissionType
   * @throws UserPermissionException
   */
  public void checkEntityAccess(Account account, EntityType entityType,
      Long entityId, PermissionType permissionType)
      throws UserPermissionException;

  /**
   * Returns all the accounts that are active.
   * 
   * @return
   */
  public List<Account> getActiveAccounts();

  /**********************************
   * 
   * 
   * Entity Manager Accesss Used to be it's own interface
   * 
   * 
   * *********************************
   */

  /**
   * register a new entry into the account management system
   * 
   * @param userId
   * @param type
   * @param entityId
   * @param permissions
   */
  public void registerEntity(Account account, EntityType type, Long entityId,
      PermissionType permission);

  /**
   * register a new entry into the account management system
   * 
   * @param group
   * @param type
   * @param entityId
   * @param permissions
   */
  public void registerEntity(PermissionGroup group, EntityType type,
      Long entityId, PermissionType permission);

  /**
   * Saves all changes to listed entity maps
   * 
   * @param entityMapList
   */
  public void registerEntity(List<EntityMap> entityMapList);

  /**
   * Removes a entity from a group
   * 
   * @param type
   * @param entityId
   * @param permissionGroupId
   */
  public void unregisterEntityFromGrop(EntityType type, Long entityId,
      Long permissionGroupId);

  /**
   * unregister an entry from the account management system
   * 
   * @param type
   * @param entityId
   */
  public void unregisterEntity(EntityType type, Long entityId);

  /**
   * unregister a list of entities
   * 
   * @param removedEntityMapList
   */
  public void unregisterEntity(List<EntityMap> removedEntityMapList);

  /**
   * List all entities that the user has access to
   * 
   * @param userId
   * @param type
   * @param onlyGranted
   * @return
   */
  public List<EntityMap> listUserAccess(Account account, EntityType type,
      boolean onlyGranted);

  /**
   * List the ids of the entities a user has the provided permission to.
   * 
   * @param account
   * @param type
   * @param permission
   * @return
   */
  public Set<Long> listUserAccess(Account account, EntityType type,
      PermissionType permission);

  /**
   * List the ids of the entities a user has the provided permission to.
   * 
   * @param account
   * @param type
   * @param permission
   * @param onlyGranted
   *          (Boolean) - indicates if the method should only return actually
   *          granted permissions (no permission groups or admins)
   * @return
   */
  public Set<Long> listUserAccess(Account account, EntityType type,
      PermissionType permission, Boolean onlyGranted);

  /**
   * List all users and their access to this entity
   * 
   * @param entityId
   * @param type
   * @return
   */
  public List<EntityMap> listEntityAccess(Long entityId, EntityType type);

  /**
   * 
   * Get access information for entity type, entity id, and user id [by default
   * includes permission groups]
   * 
   * @param userId
   * @param type
   * @param entityId
   * @return
   */
  public EntityMap getAccess(Account account, EntityType type, Long entityId);

  /**
   * Get access information for entity type, entity id, and user id
   * 
   * @param account
   * @param type
   * @param entityId
   * @param includePermissionGroups
   * @return
   */
  public EntityMap getAccess(Account account, EntityType type, Long entityId,
      Boolean includePermissionGroups);

  /**
   * Get access by type
   * 
   * @param account
   * @param type
   * @param entityId
   * @param permissions
   * @return
   */
  public Boolean getAccess(Account account, EntityType type, Long entityId,
      PermissionType permission);

  /**
   * 
   * @param account
   * @param userName
   *          Will only be not null when wsSecured = false
   * @return type is a Map with key StringURI and value of ArrayList of FormURI
   */
  public QueryPermissions getQTPermissionsMap(Account account);

  public Account accountActivation(Account acct);

  public boolean requestedAccountGroupCheck(Account acct);

  public boolean requestedAccountRoleCheck(Account acct);

}
