package gov.nih.tbi.account.service.complex;

import gov.nih.tbi.CoreConstants;
import gov.nih.tbi.ModulesConstants;
import gov.nih.tbi.account.dao.AccountDao;
import gov.nih.tbi.account.dao.EntityMapDao;
import gov.nih.tbi.account.dao.PermissionGroupDao;
import gov.nih.tbi.account.dao.PermissionGroupMemberDao;
import gov.nih.tbi.account.dao.PermissionMapDao;
import gov.nih.tbi.account.dao.PreviousPasswordDao;
import gov.nih.tbi.account.model.AccountPrivilegesForm;
import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.AccountRole;
import gov.nih.tbi.account.model.hibernate.BasicAccount;
import gov.nih.tbi.account.model.hibernate.EntityMap;
import gov.nih.tbi.account.model.hibernate.PermissionGroup;
import gov.nih.tbi.account.model.hibernate.PermissionGroupMember;
import gov.nih.tbi.account.model.hibernate.PreviousPassword;
import gov.nih.tbi.commons.dao.UserDao;
import gov.nih.tbi.commons.model.AccountStatus;
import gov.nih.tbi.commons.model.DatasetStatus;
import gov.nih.tbi.commons.model.EntityType;
import gov.nih.tbi.commons.model.PermissionGroupStatus;
import gov.nih.tbi.commons.model.PermissionType;
import gov.nih.tbi.commons.model.RoleStatus;
import gov.nih.tbi.commons.model.RoleType;
import gov.nih.tbi.commons.model.hibernate.User;
import gov.nih.tbi.commons.service.AccountManager;
import gov.nih.tbi.commons.service.ServiceConstants;
import gov.nih.tbi.commons.service.StaticReferenceManager;
import gov.nih.tbi.commons.service.UserPermissionException;
import gov.nih.tbi.commons.service.util.MailEngine;
import gov.nih.tbi.commons.util.PaginationData;
import gov.nih.tbi.commons.ws.HashMethods;
import gov.nih.tbi.repository.dao.DatasetDao;
import gov.nih.tbi.repository.model.hibernate.Dataset;
import gov.nih.tbi.repository.model.hibernate.DatasetDataStructure;
import gov.nih.tbi.repository.model.hibernate.Study;
import gov.nih.tbi.semantic.model.DatasetRDF;
import gov.nih.tbi.semantic.model.QueryPermissions;
import gov.nih.tbi.semantic.model.StudyRDF;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.stereotype.Service;

/**
 * This is the account manager implemenation that uses hibernate
 * 
 * @author Andrew Johnson
 * 
 */
@Service
@Scope("singleton")
public class AccountManagerImpl extends BaseManagerImpl implements
    AccountManager, Serializable {

  private static final long serialVersionUID = -3856535486237089289L;

  static Logger logger = Logger.getLogger(AccountManagerImpl.class);

  @Autowired
  EntityMapDao entityMapDao;

  @Autowired
  PermissionMapDao permissionMapDao;

  @Autowired
  AccountDao accountDao;

  @Autowired
  PreviousPasswordDao previousPasswordDao;

  @Autowired
  MailEngine mailEngine;

  @Autowired
  UserDao userDao;

  @Autowired
  PermissionGroupDao permissionGroupDao;

  @Autowired
  PermissionGroupMemberDao permissionGroupMemberDao;

  @Autowired
  StaticReferenceManager staticManager;

  @Autowired
  RoleHierarchy roleHierarchy;

  @Autowired
  protected ModulesConstants modulesConstants;

  @Autowired
  DatasetDao datasetDao;

  /**
   * @inheritDoc
   */
  public void registerEntity(Account account, EntityType type, Long entityId,
      PermissionType permission) {

    EntityMap oldMap = entityMapDao.get(account, type, entityId);
    if (oldMap != null) {
      oldMap.setPermission(permission);
      entityMapDao.save(oldMap);
    } else {
      EntityMap entityMap = new EntityMap();
      entityMap.setEntityId(entityId);
      entityMap.setType(type);
      entityMap.setAccount(account);
      entityMap.setPermission(permission);
      entityMapDao.save(entityMap);
    }
  }

  /**
   * @inheritDoc
   */
  public void registerEntity(PermissionGroup group, EntityType type,
      Long entityId, PermissionType permission) {

    EntityMap oldMap = entityMapDao.get(group, type, entityId);

    if (oldMap != null) {
      oldMap.setPermission(permission);
      entityMapDao.save(oldMap);
    } else {

      EntityMap entityMap = new EntityMap();
      entityMap.setEntityId(entityId);
      entityMap.setType(type);
      entityMap.setPermissionGroup(group);

      entityMap.setPermission(permission);

      entityMapDao.save(entityMap);
    }
  }

  public void registerEntity(List<EntityMap> entityMapList) {

    if (entityMapList != null) {
      for (EntityMap em : entityMapList) {
        entityMapDao.save(em);
      }
    }
  }

  /**
   * @inheritDoc
   */
  public void checkEntityAccess(Account account, EntityType entityType,
      Long entityId, PermissionType permissionType)
      throws UserPermissionException {

    if (PermissionType.READ.equals(permissionType)
        && !getAccess(account, entityType, entityId, permissionType)) {
      throw new UserPermissionException(ServiceConstants.READ_ACCESS_DENIED);
    } else if (PermissionType.WRITE.equals(permissionType)
        && !getAccess(account, entityType, entityId, permissionType)) {
      throw new UserPermissionException(ServiceConstants.WRITE_ACCESS_DENIED);
    } else if (PermissionType.ADMIN.equals(permissionType)
        && !getAccess(account, entityType, entityId, permissionType)) {
      throw new UserPermissionException(ServiceConstants.ADMIN_ACCESS_DENIED);
    }
  }

  @Deprecated
  private boolean hasPermission(EntityMap entityMap, PermissionType permission) {

    if (permission.equals(PermissionType.READ)) {
      if (entityMap.getPermission().equals(PermissionType.READ)
          || entityMap.getPermission().equals(PermissionType.WRITE)
          || entityMap.getPermission().equals(PermissionType.ADMIN)
          || entityMap.getPermission().equals(PermissionType.OWNER)) {
        return true;
      }
    }

    if (permission.equals(PermissionType.WRITE)) {
      if (entityMap.getPermission().equals(PermissionType.WRITE)
          || entityMap.getPermission().equals(PermissionType.ADMIN)
          || entityMap.getPermission().equals(PermissionType.OWNER)) {
        return true;
      }
    }

    if (permission.equals(PermissionType.ADMIN)) {
      if (entityMap.getPermission().equals(PermissionType.ADMIN)
          || entityMap.getPermission().equals(PermissionType.OWNER)) {
        return true;
      }
    }

    if (permission.equals(PermissionType.OWNER)) {
      if (entityMap.getPermission().equals(PermissionType.OWNER)) {
        return true;
      }
    }

    return false;
  }

  public void unregisterEntityFromGrop(EntityType type, Long entityId,
      Long permissionGroupId) {

    PermissionGroup pg = permissionGroupDao.get(permissionGroupId);
    EntityMap emToRemove = entityMapDao.get(pg, type, entityId, false);
    if (emToRemove != null) {
      entityMapDao.remove(emToRemove.getId());
    }
  }

  /**
   * @inheritDoc
   */
  public void unregisterEntity(EntityType type, Long entityId) {

    List<EntityMap> entityMapList =
        entityMapDao.listEntityAccess(entityId, type);

    for (EntityMap entityMap : entityMapList) {
      entityMapDao.remove(entityMap.getId());
    }

    // entityMapList.removeAll(entityMapList);

  }

  /**
   * @inheritDoc
   */
  public void unregisterEntity(List<EntityMap> removedEntityMapList) {

    // for (EntityMap entityMap : removedEntityMapList)
    // {
    // entityMapDao.remove( entityMap.getId() );
    // }

    entityMapDao.removeAll(removedEntityMapList);
  }

  /**
   * @inheritDoc
   */
  public List<EntityMap> listUserAccess(Account account, EntityType type,
      boolean onlyGranted) {

    Set<PermissionGroup> currentGroups = new HashSet<PermissionGroup>();
    currentGroups.addAll(permissionGroupDao.getPublicGroups());
    currentGroups.addAll(permissionGroupDao.getGrantedGroups(account));

    List<EntityMap> emList;
    if (onlyGranted) {
      emList = entityMapDao.listUserAccess(account, currentGroups, type, false);
    } else {
      emList =
          entityMapDao.listUserAccess(account, currentGroups, type,
              hasRole(account, type.getAdminRole()));
    }
    return emList;
  }

  public Set<Long> listUserAccess(Account account, EntityType type,
      PermissionType permission) {

    return listUserAccess(account, type, permission, false);
  }

  /**
   * @inheritDoc
   */
  public Set<Long> listUserAccess(Account account, EntityType type,
      PermissionType permission, Boolean onlyGranted) {

    List<EntityMap> entityMapList = null;

    // Being an admin gets you admin to all entites, but not owner. Therefore,
    // if the permission being sought is
    // OWNER, the admin acts like a normal user.
    boolean isAdmin = false;
    if (hasRole(account, type.getAdminRole())
        && !PermissionType.OWNER.equals(permission)) {
      isAdmin = true;
    }

    if (onlyGranted == false) {
      Set<PermissionGroup> currentGroups = new HashSet<PermissionGroup>();
      currentGroups.addAll(permissionGroupDao.getPublicGroups());
      currentGroups.addAll(permissionGroupDao.getGrantedGroups(account));

      entityMapList =
          entityMapDao.listUserAccess(account, currentGroups, type, isAdmin);
    } else {
      entityMapList =
          entityMapDao.listUserAccess(account, new HashSet<PermissionGroup>(),
              type, false);
    }

    Set<Long> ids = new HashSet<Long>();

    for (EntityMap em : entityMapList) {

      if (em.getPermission().contains(permission)) {
        ids.add(em.getEntityId());
      }
    }
    return ids;
  }

  /**
   * @inheritDoc
   */
  public EntityMap getAccess(Account account, EntityType type, Long entityId) {

    return getAccess(account, type, entityId, true);
  }

  /**
   * @inheritDoc
   */
  public EntityMap getAccess(Account account, EntityType type, Long entityId,
      Boolean includePermissionGroups) {

    EntityMap em = null;

    if (includePermissionGroups == true) {
      Set<PermissionGroup> currentGroups = new HashSet<PermissionGroup>();
      currentGroups.addAll(permissionGroupDao.getPublicGroups());
      currentGroups.addAll(permissionGroupDao.getGrantedGroups(account));

      em =
          entityMapDao.get(account, currentGroups, type, entityId,
              hasRole(account, type.getAdminRole()));
    } else {
      em = entityMapDao.get(account, type, entityId);
    }

    return em;
  }

  /**
   * @inheritDoc
   */
  public Boolean getAccess(Account account, EntityType type, Long entityId,
      PermissionType permission) {

    Set<PermissionGroup> currentGroups = new HashSet<PermissionGroup>();
    currentGroups.addAll(permissionGroupDao.getPublicGroups());
    currentGroups.addAll(permissionGroupDao.getGrantedGroups(account));

    EntityMap em =
        entityMapDao.get(account, currentGroups, type, entityId,
            hasRole(account, type.getAdminRole()));

    // The user has NO permissions
    if (em.getPermission() == null) {
      return false;
    }

    return em.getPermission().contains(permission);
  }

  /**
   * @inheritDoc
   */
  public List<EntityMap> listEntityAccess(Long entityId, EntityType type) {

    List<EntityMap> emList = entityMapDao.listEntityAccess(entityId, type);

    return emList;
  }

  /**
   * @inheritDoc
   */
  public Account saveAccount(Account account) {

    account = accountDao.save(account);

    return account;
  }

  /**
   * @inheritDoc
   */
  public List<PermissionGroup> getPermissionGroupList() {

    return permissionGroupDao.getAll();
  }

  /**
   * @inheritDoc
   */
  public void saveSitePermission(List<Long> siteList, Account account) {

    for (Long siteId : siteList) {
      PermissionGroup currentGroup = permissionGroupDao.get(siteId);
      if (currentGroup != null) {
        PermissionGroupMember groupMember = new PermissionGroupMember();
        groupMember.setPermissionGroup(currentGroup);
        groupMember.setAccount(account);
        permissionGroupMemberDao.save(groupMember);
      }
    }
  }

  /**
   * @inheritDoc
   */
  public Boolean validateUserName(String userName) {

    Pattern pattern = Pattern.compile(ServiceConstants.USERNAME__REGEX);
    Matcher matcher = pattern.matcher(userName);
    return matcher.matches();
  }

  /**
   * @inheritDoc
   */
  public Boolean checkUserNameAvailability(String userName, Long id) {

    List<Account> accountList = accountDao.getAll();

    boolean notContains = true;

    for (Account account : accountList) {
      if (userName.equalsIgnoreCase(account.getUserName())
          && (id == null || !account.getId().equals(id))) {
        notContains = false;
      }
    }

    return notContains;
  }

  /**
   * @inheritDoc
   */
  public Boolean checkEraIdAvailability(String eraId, Long id) {

    boolean notContains = true;

    if (eraId != null && eraId.length() > 0) {
      List<BasicAccount> accountList = accountDao.getAccountsByEraId(eraId);

      for (BasicAccount account : accountList) {
        if (eraId.equalsIgnoreCase(account.getEraId())
            && (id == null || !account.getId().equals(id))) {
          notContains = false;
        }
      }
    }

    return notContains;
  }

  /**
   * @inheritDoc
   */
  public Account getAccountByEmail(String email) {

    User user = userDao.getByEmail(email);
    if (user == null) {
      return null;
    }
    return getAccountByUser(user);
  }

  /**
   * @inheritDoc
   */
  public Account getAccountByUser(User user) {

    return accountDao.getByUser(user);
  }

  /**
   * @inheritDoc
   */
  public Account getAccountByUserName(String userName) {

    return accountDao.getByUserName(userName);
  }

  /**
   * @inheritDoc
   */
  public String addRecoveryTokenForAccount(Account account) {

    // Get the current time
    // SQL returns timestamp object. Create date with current time and convert
    // to timestamp
    Date date = new Date();
    Date timestamp = new Timestamp(date.getTime());

    // Get the token for the user
    String token = hashRecoveryString(account.getUserName(), timestamp);

    // Save the date into the account record
    account.setRecoveryDate(date);
    accountDao.save(account);

    return token;
  }

  /**
   * @inheritDoc
   */
  public Boolean validateRecoveryTokenForAccount(Account account, String token) {

    return token.equals(hashRecoveryString(account.getUserName(),
        account.getRecoveryDate()));
  }

  /**
   * A private helper method for creating a hash from a username and a Date
   * 
   * @param userName
   * @param date
   * @return
   */
  private String hashRecoveryString(String userName, Date date) {

    byte[] raw = null;

    // Digest the String
    try {
      raw =
          MessageDigest.getInstance("SHA-256").digest(
              (userName + date.toString() + CoreConstants.SALT).getBytes());
    } catch (NoSuchAlgorithmException e) {
      logger.error(e.getLocalizedMessage());
      e.printStackTrace();
      return null;
    }

    // Convert to a hex String
    if (raw == null) {
      return null;
    }
    final StringBuilder hex = new StringBuilder(2 * raw.length);
    for (final byte b : raw) {
      hex.append(CoreConstants.HEXES.charAt((b & 0xF0) >> 4)).append(
          CoreConstants.HEXES.charAt((b & 0x0F)));
    }
    return hex.toString();
  }

  /**
   * @throws MessagingException
   * @inheritDoc
   */
  public void sendPasswordRecoveryEmail(Account account, String token,
      String url, String subject, String messagePattern)
      throws MessagingException {

    // Create an email message
    String message = null;

    if (messagePattern == null) {
      // TODO:NIMESH Add PORTAL ROOT instead of taking it from the URL from
      // previous method
      message =
          CoreConstants.RECOVERY_EMAIL_BODY + url
              + CoreConstants.RECOVERY_EMAIL_URI + token;
    } else {
      // TODO:NIMESH Add PORTAL ROOT instead of taking it from the URL from
      // previous method
      message =
          java.text.MessageFormat.format(messagePattern, account.getUser()
              .getFullName(), url + CoreConstants.RECOVERY_EMAIL_URI + token);
    }

    User user = userDao.get(account.getUserId());
    try {
      mailEngine.sendMail(subject, message, null, user.getEmail());
    } catch (MessagingException e) {
      logger.error(e.getLocalizedMessage());
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * @throws MessagingException
   * @inheritDoc
   */
  public void sendAccountApprovalRejectionEmail(Account account,
      String subject, String messageText, Boolean accountApproved)
      throws MessagingException {

    try {
      mailEngine.sendMail(subject, messageText, null, account.getUser()
          .getEmail());
    } catch (MessagingException e) {
      logger.error(e.getLocalizedMessage());
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * @inheritDoc
   */
  public boolean checkPassword(Account account, String password) {

    List<PreviousPassword> passwordList =
        previousPasswordDao.getLast(account, 25);

    String encryptPassword =
        HashMethods.convertFromByte(hashPassword(password));
    String currentPassword = "";

    if (account != null && account.getPassword() != null) {
      currentPassword = HashMethods.convertFromByte(account.getPassword());
    }

    logger.debug("new: " + encryptPassword);
    logger.debug("current: " + currentPassword);

    if (currentPassword.equals(encryptPassword)) {
      return true;
    }

    if (passwordList != null && passwordList.size() > 0) {

      for (PreviousPassword pass : passwordList) {
        String encryptedTest = HashMethods.convertFromByte(pass.getPassword());
        logger.debug("test: " + encryptedTest + " ? "
            + encryptedTest.equals(encryptPassword));

        // if the we find the password in our list of passwords then this check
        // fails!
        if (encryptedTest.equals(encryptPassword)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * @inheritDoc
   */
  public void changePassword(Account account, String password) {

    // double check the password before committing it!
    if (checkPassword(account, password)) {
      throw new RuntimeException(ServiceConstants.INVALID_PASSWORD);
    }

    // add old password to used passwords
    PreviousPassword pass = new PreviousPassword();
    pass.setAccount(account);
    pass.setPassword(account.getPassword());
    pass.setEventDate(new Date());

    previousPasswordDao.save(pass);

    accountDao.changePassword(account.getId(), hashPassword(password));

  }

  /**
   * @inheritDoc
   */
  public byte[] hashPassword(String password) {

    // Way to complicated method to fill a Byte array with the encrypted
    // password
    byte[] raw = null;
    try {
      raw = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
    } catch (NoSuchAlgorithmException e) {
      logger.error(e.getLocalizedMessage());
      e.printStackTrace();
    }
    byte[] passwordArray = new byte[raw.length];
    for (int i = 0; i < raw.length; i++) {
      passwordArray[i] = raw[i];
    }
    return passwordArray;
  }

  /**
   * @inheritDoc
   */
  public Account getAccount(User curUser, Long accountId) {

    // TODO: may need to add permission checks later
    if (curUser == null) {
      throw new RuntimeException("curUser cannot be null.");
    }

    // this should never happen
    if (accountId == null) {
      throw new RuntimeException("accountId cannot be null.");
    }

    return accountDao.get(accountId);
  }

  public PermissionGroup getPermissionGroup(String groupName) {

    return permissionGroupDao.getByName(groupName);
  }

  /**
   * @inheritDoc
   */
  public PermissionGroup getPermissionGroupById(Long selectedId) {

    return permissionGroupDao.get(selectedId);
  }

  /**
   * @inheritDoc
   */
  public boolean validateEmail(String email, Long userId) {

    List<User> userList = userDao.getAll();

    boolean notContains = true;

    for (User user : userList) {
      if (email.equalsIgnoreCase(user.getEmail())
          && (userId == null || !userId.equals(user.getId()))) {
        notContains = false;
      }
    }

    return notContains;
  }

  /**
   * @inheritDoc
   */
  public Long getNumAccountsWithStatus(Long statusId) {

    if (statusId == null) {
      return null;
    }
    return accountDao.getStatusCount(AccountStatus.getById(statusId));
  }

  /**
   * @inheritDoc
   */
  public boolean validatePhoneNumber(String phone) {

    Pattern pattern = Pattern.compile(ServiceConstants.PHONE_NUMBER_PATTERN);
    Matcher matcher = pattern.matcher(phone);
    return matcher.matches();
  }

  /**
   * @inheritDoc
   */
  public boolean validatePasswordFormat(String password) {

    int counter = 0;

    if (password == null)
      return false;

    Pattern pattern = Pattern.compile(ServiceConstants.PASSWORD_DIGIT_PATTERN);
    Matcher matcher = pattern.matcher(password);
    if (matcher.matches()) {
      counter++;
    }

    pattern = Pattern.compile(ServiceConstants.PASSWORD_LOWER_PATTERN);
    matcher = pattern.matcher(password);
    if (matcher.matches()) {
      counter++;
    }

    pattern = Pattern.compile(ServiceConstants.PASSWORD_UPPER_PATTERN);
    matcher = pattern.matcher(password);
    if (matcher.matches()) {
      counter++;
    }

    pattern = Pattern.compile(ServiceConstants.PASSWORD_SPECIAL_PATTERN);
    matcher = pattern.matcher(password);
    if (matcher.matches()) {
      counter++;
    }

    if (counter >= 3
        && password.length() <= ServiceConstants.PASSWORD_MAX_LENGTH
        && password.length() >= ServiceConstants.PASSWORD_MIN_LENGTH) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * @inheritDoc
   */
  public List<Account> getAccountList() {

    return accountDao.getAll();
  }

  /**
   * @inheritDoc
   */
  public List<Account> getActiveAccounts() {

    return accountDao.getAllActive();
  }

  /**
   * @inheritDoc
   */
  public List<Account> getAccountListByRole(RoleType role, boolean onlyActive) {

    // List<Account> accountList = new ArrayList<Account>();
    //
    // List<Account> activeAccounts = this.getActiveAccounts();
    // for (Account account : activeAccounts)
    // {
    // if (this.hasRole(account, role))
    // {
    // accountList.add(account);
    // }
    // }
    //
    // return accountList;

    // ///////////////////////////////

    return accountDao.getAccountsWithRoles(
        staticManager.rolesThatImplyRole(role), onlyActive);

  }

  /**
   * @inheritDoc
   */
  public User getUserById(Long id) {

    return userDao.get(id);
  }

  /**
   * @inheritDoc
   */
  public List<BasicAccount> searchAccounts(String key, Long statusId,
      PaginationData pageData) {

    AccountStatus accountStatus;
    // Make sure statusType is not less than -1
    if (statusId < 0L) {
      accountStatus = null;
    } else {
      accountStatus = AccountStatus.getById(statusId);
    }

    return accountDao.search(key, accountStatus, pageData);
  }

  /**
   * @inheritDoc
   */
  public Account deactivateAccount(Long id) {

    Account account = accountDao.get(id);
    if (account == null
        || (account.getAccountStatus() != AccountStatus.PENDING && account
            .getAccountStatus() != AccountStatus.ACTIVE)) {
      return null;
    }

    Date date = new Date();
    String newUsername = account.getUserName() + '-' + date.getTime();
    if (newUsername.length() > 55) {
      newUsername = newUsername.substring(0, 55);
    }
    account.setUserName(newUsername);
    account.setIsActive(false);
    account.setAccountStatus(AccountStatus.INACTIVE);
    accountDao.save(account);
    return account;
  }

  /**
   * @inheritDoc
   */
  public boolean validateReactivateAccount(Long id) {

    Account account = accountDao.get(id);
    String newName = account.getUserName().split("-")[0];
    return checkUserNameAvailability(newName, id);
  }

  /**
   * @inheritDoc
   */
  public Account reactivateAccount(Long id, String userName) {

    Account account = accountDao.get(id);

    if (account == null || account.getAccountStatus() != AccountStatus.INACTIVE) {
      return null;
    }
    account.setIsActive(true);
    account.setAccountStatus(AccountStatus.ACTIVE);
    for (AccountRole role : account.getAccountRoleList()) {
      if (role.getRoleStatus() == RoleStatus.REQUESTED) {
        account.setAccountStatus(AccountStatus.PENDING);
        break;
      }
    }
    // For this to work, '-' must remain an illegal character in a username
    if (userName == null) {
      account.setUserName(account.getUserName().split("-")[0]);
    } else {
      account.setUserName(userName);
    }

    accountDao.save(account);
    return account;
  }

  /**
   * @inheritDoc
   */
  public Account approveAccount(Account account) {

    if (account == null
        || account.getAccountStatus() != AccountStatus.REQUESTED) {
      logger
          .error("The requested account cannot be approved because it does not exist or it does not have requested status.");
      return null;
    }

    // set next password expiration
    {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.DAY_OF_YEAR, CoreConstants.EXPIRE_ACCOUNT_AFTER_DAYS);

      logger.debug(account.getUserName()
          + "'s next password change must be before " + cal.getTime());

      account.setPasswordExpirationDate(cal.getTime());
    }

    account.setIsActive(true);
    account.setAccountStatus(AccountStatus.ACTIVE);
    for (AccountRole role : account.getAccountRoleList()) {
      if (role.getRoleStatus() == RoleStatus.REQUESTED) {
        account.setAccountStatus(AccountStatus.PENDING);
        break;
      }
    }
    return account;
  }

  /**
   * @inheritDoc
   */
  public Account rejectAccount(Account account) {

    if (account == null
        || account.getAccountStatus() != AccountStatus.REQUESTED) {
      logger
          .error("The requeted account cannot be denied because it does not exist or it does not have requested status");
      return null;
    }
    account.setIsActive(false);
    account.setAccountStatus(AccountStatus.DENIED);

    // Add the timestamp onto the username
    Date date = new Date();
    String newUsername = account.getUserName() + '-' + date.getTime();
    if (newUsername.length() > 55) {
      newUsername = newUsername.substring(0, 55);
    }
    account.setUserName(newUsername);

    String newUserEmail = account.getUser().getEmail() + '-' + date.getTime();
    if (newUserEmail.length() > 55) {
      newUserEmail = newUserEmail.substring(0, 55);
    }

    account.getUser().setEmail(newUserEmail);

    return account;
  }

  /**
   * @inheritDoc
   */
  public Account getEntityOwnerAccount(Long entityId, EntityType type) {

    return entityMapDao.getOwnerEntityMap(entityId, type).getAccount();
  }

  /**
   * @inheritDoc
   */
  public List<PermissionGroup> getPublicPermissionGroups() {

    return permissionGroupDao.getPublicGroups();
  }

  /**
   * @inheritDoc
   */
  public boolean isAccountInPermissionGroup(Account account,
      PermissionGroup permissionGroup) {

    if (permissionGroup == null || account == null) {
      return false;
    }

    for (PermissionGroupMember p : account.getPermissionGroupMemberList()) {
      if (p.getPermissionGroup().equals(permissionGroup)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @inheritDoc
   */
  public boolean removeEntityFromPermissionGroup(Account account,
      String permissionGroupName, EntityType type, Long entityId) {

    PermissionGroup publicStudy = this.getPermissionGroup(permissionGroupName);

    if (publicStudy != null) {
      for (EntityMap entity : publicStudy.getEntityMapSet()) {
        if (type.equals(entity.getType())
            && entityId.equals(entity.getEntityId())) {
          if (publicStudy.getEntityMapSet().remove(entity)) {
            permissionGroupDao.save(publicStudy);
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * @inheritDoc
   */
  public boolean removeFromPublicStudy(Account account, Study study) {

    return removeEntityFromPermissionGroup(account,
        ServiceConstants.PUBLIC_STUDY, EntityType.STUDY, study.getId());
  }

  /**
   * {@inheritDoc}
   */
  public List<PermissionGroup> getPrivatePermissionGroups() {

    return permissionGroupDao.getPrivateGroups();
  }

  /**
   * {@inheritDoc}
   */
  public List<PermissionGroup> getAccessablePermissionGroups(Account account) {

    return permissionGroupDao.getGrantedGroups(account);
  }

  /**
   * {@inheritDoc}
   */
  public void savePermissionGroup(PermissionGroup permissionGroup) {

    permissionGroupDao.save(permissionGroup);
  }

  /**
   * {@inheritDoc}
   */
  public boolean
      validatePermissionGroupName(Long permissionGroupId, String name) {

    for (PermissionGroup permissionGroup : permissionGroupDao.getAll()) {
      if ((permissionGroupId == null || !permissionGroupId
          .equals(permissionGroup.getId()))
          && name.equals(permissionGroup.getGroupName())) {
        return false;
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  public Map<RoleType, String> determineChanges(Account previousAccount,
      AccountPrivilegesForm tempPrivilegesForm) {

    Map<RoleType, String> changeMap = new HashMap<RoleType, String>();
    Set<RoleType> foundList = new HashSet<RoleType>();

    for (AccountRole ar : previousAccount.getAccountRoleList()) {
      AccountRole found = null;

      for (AccountRole test : tempPrivilegesForm.getAccountRoleList()) {
        if (test.getRoleType().equals(ar.getRoleType())) {
          found = test;
          foundList.add(ar.getRoleType());
          break;
        }
      }

      if (found != null) {
        // STATUS CHANGES
        if (!ar.getRoleStatus().equals(found.getRoleStatus())) {
          // CHANGE IN STATUS

          if (RoleStatus.REQUESTED.equals(ar.getRoleStatus())
              && RoleStatus.ACTIVE.equals(found.getRoleStatus())) {
            // GRANTED

            changeMap.put(ar.getRoleType(),
                ServiceConstants.ACCOUNT_ROLE_GRANTED);
          }

          if (RoleStatus.REQUESTED.equals(ar.getRoleStatus())
              && RoleStatus.INACTIVE.equals(found.getRoleStatus())) {
            // GRANTED

            changeMap.put(ar.getRoleType(),
                ServiceConstants.ACCOUNT_ROLE_DENIED);
          }

          if (RoleStatus.ACTIVE.equals(ar.getRoleStatus())
              && RoleStatus.INACTIVE.equals(found.getRoleStatus())) {
            // REVOKED

            changeMap.put(ar.getRoleType(),
                ServiceConstants.ACCOUNT_ROLE_REVOKED);
          }
        }

        // EXPIRATION DATES
        if (ar.getExpirationDate() != null && found.getExpirationDate() != null
            && !ar.getExpirationDate().equals(found.getExpirationDate())) {
          // DATE CHANGE

          if (changeMap.get(ar.getRoleType()) == null) {
            changeMap.put(
                ar.getRoleType(),
                ServiceConstants.ACCOUNT_DATE_CHANGED
                    + found.getExpirationString());
          } else {
            changeMap.put(
                ar.getRoleType(),
                changeMap.get(ar.getRoleType()) + ServiceConstants.STRING_AND
                    + ServiceConstants.ACCOUNT_DATE_CHANGED
                    + found.getExpirationString());
          }
        } else if (ar.getExpirationDate() == null
            && found.getExpirationDate() != null) {
          // ADDED DATE

          if (changeMap.get(ar.getRoleType()) == null) {
            changeMap.put(ar.getRoleType(), ServiceConstants.ACCOUNT_DATE_SET
                + found.getExpirationString());
          } else {
            changeMap.put(
                ar.getRoleType(),
                changeMap.get(ar.getRoleType()) + ServiceConstants.STRING_AND
                    + ServiceConstants.ACCOUNT_DATE_SET
                    + found.getExpirationString());
          }
        } else if (ar.getExpirationDate() != null
            && found.getExpirationDate() == null) {
          // DATE REMOVED

          if (changeMap.get(ar.getRoleType()) == null) {
            changeMap.put(ar.getRoleType(),
                ServiceConstants.ACCOUNT_DATE_REMOVED);
          } else {
            changeMap.put(ar.getRoleType(), changeMap.get(ar)
                + ServiceConstants.STRING_AND
                + ServiceConstants.ACCOUNT_DATE_REMOVED);
          }
        }
      } else {
        // PRIVILEGE REMOVED COMPLETELY (BOX WAS UNCHECKED BY ADMIN)
        // In this case we still want the role there, but we are going to change
        // the status to inactive (no
        // expiration)
        tempPrivilegesForm.getAccountRoleList().add(
            new AccountRole(ar.getAccount(), ar.getRoleType(),
                RoleStatus.INACTIVE, ar.getExpirationDate()));
        changeMap.put(ar.getRoleType(), ServiceConstants.ACCOUNT_ROLE_REVOKED);
      }

    }

    // Check for new permissions
    for (AccountRole ar : tempPrivilegesForm.getAccountRoleList()) {
      if (!foundList.contains(ar.getRoleType())) {
        if (RoleStatus.REQUESTED.equals(ar.getRoleStatus())) {
          // GRANTED

          changeMap.put(ar.getRoleType(),
              ServiceConstants.ACCOUNT_ROLE_REQUESTED);
        }

        if (RoleStatus.ACTIVE.equals(ar.getRoleStatus())) {
          // REVOKED

          changeMap
              .put(ar.getRoleType(), ServiceConstants.ACCOUNT_ROLE_GRANTED);
        }
      }
    }

    return changeMap;
  }

  /**
   * @inheritDoc
   */
  public boolean validateEmailMatch(String email) {

    Account account = accountDao.getByEmail(email);

    if (account == null) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * @inheritDoc
   */
  @Override
  public List<String> getAffiliatedInstitutions() {

    // caste it into linkedhashset to get unique results of the affiliated
    // institutions
    LinkedHashSet<String> affiliatedInstitutionSet =
        new LinkedHashSet<String>(accountDao.getAffiliatedInstitutions());
    return new ArrayList<String>(affiliatedInstitutionSet);
  }

  /**
   * @inheritDoc
   */
  public QueryPermissions getQTPermissionsMap(Account account) {

    QueryPermissions queryPermissions = new QueryPermissions();

    List<EntityMap> datasetMaps =
        listUserAccess(account, EntityType.DATASET, false);

    Set<Long> datasetIds = new HashSet<Long>();

    if (datasetMaps != null && !datasetMaps.isEmpty()) {
      for (EntityMap map : datasetMaps) {
        datasetIds.add(map.getEntityId());
      }
    }

    List<Dataset> datasetList = null;

    // moved the dataset dao calls here to get all the dataset in memory so we
    // don't to do the same get dataset
    // database call ~500; once for each entity map.
    if (datasetIds != null && !datasetIds.isEmpty()) {
      // using getByIds instead of getAll improves performance by about
      // 300-500ms (woohoo!)
      datasetList = datasetDao.getByIds(datasetIds);
    }

    HashMap<Long, Dataset> datasetMap = new HashMap<Long, Dataset>();

    // puts all the datasets into hashmap of it's ID -> Dataset for faster
    // access (maybe?)
    if (datasetList != null && !datasetList.isEmpty()) {
      for (Dataset ds : datasetList) {
        datasetMap.put(ds.getId(), ds);
      }
    }

    // Go through maps
    for (EntityMap map : datasetMaps) {
      // Get Datasets
      Dataset ds = null;

      // Francis: why u put dao calls here?
      if (datasetMap != null && !datasetMap.isEmpty()) {
        ds = datasetMap.get(map.getEntityId());
      }

      // If study is not null, has forms and is private or shared
      if (ds != null
          && (ds.getDatasetStatus().equals(DatasetStatus.PRIVATE) || ds
              .getDatasetStatus().equals(DatasetStatus.SHARED))
          && ds.getStudy() != null && ds.getStudy().getTitle() != null
          && !ds.getDatasetDataStructure().isEmpty()) {
        String studyURI = StudyRDF.createStudyResource(ds.getStudy()).getURI();
        String datasetURI = DatasetRDF.createDatasetResource(ds).getURI();

        // Get all forms for this dataset
        Set<Long> formIdList = new HashSet<Long>();
        for (DatasetDataStructure dsds : ds.getDatasetDataStructure()) {
          formIdList.add(dsds.getDataStructureId());

          // Add FormResultPermission to queryPermission
          QueryPermissions.FormResultPermission existingFormResultPermission =
              queryPermissions.getFormResultPermissionByFormId(dsds
                  .getDataStructureId());

          // if list has form id
          if (existingFormResultPermission != null) {
            QueryPermissions.StudyDataset studyDataset =
                new QueryPermissions.StudyDataset();
            studyDataset.setDatasetId(ds.getId());
            studyDataset.setDatasetURI(datasetURI);
            studyDataset.setStudyURI(studyURI);
            existingFormResultPermission.getStudyDatasets().add(studyDataset);
          }
          // if list does not have form id
          else {
            QueryPermissions.StudyDataset studyDataset =
                new QueryPermissions.StudyDataset();
            studyDataset.setDatasetId(ds.getId());
            studyDataset.setDatasetURI(datasetURI);
            studyDataset.setStudyURI(studyURI);

            QueryPermissions.FormResultPermission formResultPermission =
                new QueryPermissions.FormResultPermission();
            formResultPermission.setFormId(dsds.getDataStructureId());
            formResultPermission.getStudyDatasets().add(studyDataset);
            queryPermissions.getFormResultPermissions().add(
                formResultPermission);
          }

        }

        logger.debug("DatasetId" + ds.getId() + " FormsCount:"
            + formIdList.size() + " StudyName:" + ds.getStudy().getTitle());

        // Add StudyResult to queryPermissions

        QueryPermissions.StudyResultPermission existingStudyResultPermission =
            queryPermissions.getStudyResultPermissionByStudyURI(studyURI);

        // If list already has the study add the form uri's
        if (existingStudyResultPermission != null) {
          existingStudyResultPermission.getFormIds().addAll(formIdList);
        }
        // If list does not have study uri
        else {
          QueryPermissions.StudyResultPermission studyResultPermission =
              new QueryPermissions.StudyResultPermission();
          studyResultPermission.setStudyURI(studyURI);
          studyResultPermission.setFormIds(formIdList);
          queryPermissions.getStudyResultPermissions().add(
              studyResultPermission);
        }
      }
    }

    // Adds the Role string to the object
    populateAccountRoles(queryPermissions, account);

    return queryPermissions;
  }

  private void populateAccountRoles(QueryPermissions qtPermissions,
      Account account) {

    qtPermissions.setHasAccessToAccount(false);
    qtPermissions.setHasAccessToGUID(false);
    qtPermissions.setHasAccessToProforms(false);
    qtPermissions.setHasAccessToProforms(false);
    qtPermissions.setHasAccessToRepository(false);
    qtPermissions.setHasAccessToWorkspace(false);
    qtPermissions.setHasAccessToDictionary(false);

    // if Admin give them all permissions
    if (hasRole(account, RoleType.ROLE_ADMIN)) {
      qtPermissions.setHasAccessToAccount(true);
      qtPermissions.setHasAccessToGUID(true);
      qtPermissions.setHasAccessToProforms(true);
      qtPermissions.setHasAccessToProforms(true);
      qtPermissions.setHasAccessToRepository(true);
      qtPermissions.setHasAccessToWorkspace(true);
      qtPermissions.setHasAccessToDictionary(true);
    } else {
      if (hasRole(account, RoleType.ROLE_USER)) {
        qtPermissions.setHasAccessToWorkspace(true);
        qtPermissions.setHasAccessToAccount(true);
      }

      // Proforms Permissions
      if (hasRole(account, RoleType.ROLE_PROFORMS)
          || hasRole(account, RoleType.ROLE_PROFORMS_ADMIN)) {
        qtPermissions.setHasAccessToProforms(true);
      }

      // GUID
      if (hasRole(account, RoleType.ROLE_GUID)
          || hasRole(account, RoleType.ROLE_GUID_ADMIN)) {
        qtPermissions.setHasAccessToGUID(true);
      }

      // Dictionary
      if (hasRole(account, RoleType.ROLE_DICTIONARY)
          || hasRole(account, RoleType.ROLE_DICTIONARY_ADMIN)) {
        qtPermissions.setHasAccessToDictionary(true);
      }

      // Repository
      if (hasRole(account, RoleType.ROLE_REPOSITORY_ADMIN)
          || hasRole(account, RoleType.ROLE_STUDY)
          || hasRole(account, RoleType.ROLE_STUDY_ADMIN)) {
        qtPermissions.setHasAccessToRepository(true);
      }

    }
  }

  public Account accountActivation(Account acct) {

    if (!requestedAccountRoleCheck(acct) && !requestedAccountGroupCheck(acct)) {
      acct.setAccountStatus(AccountStatus.ACTIVE);
    }
    return acct;
  }

  public boolean requestedAccountGroupCheck(Account acct) {

    Set<PermissionGroupMember> permissionGroupMemberList =
        acct.getPermissionGroupMemberList();
    for (PermissionGroupMember pgm : permissionGroupMemberList) {
      if (pgm.getPermissionGroupStatus()
          .equals(PermissionGroupStatus.REQUESTED)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks to see if they are any outstanding Account Role requests still
   * pending; which will return true if there are.
   * 
   * @return
   */
  public boolean requestedAccountRoleCheck(Account acct) {

    Set<AccountRole> accountRoleList = acct.getAccountRoleList();
    for (AccountRole ar : accountRoleList) {
      if (ar.getRoleStatus().equals(RoleStatus.REQUESTED))
        return true;
    }
    return false;
  }
}
