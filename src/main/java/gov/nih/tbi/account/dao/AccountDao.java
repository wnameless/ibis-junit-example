package gov.nih.tbi.account.dao;

import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.BasicAccount;
import gov.nih.tbi.commons.dao.GenericDao;
import gov.nih.tbi.commons.model.AccountStatus;
import gov.nih.tbi.commons.model.RoleType;
import gov.nih.tbi.commons.model.hibernate.User;
import gov.nih.tbi.commons.util.PaginationData;

import java.util.List;
import java.util.Set;

public interface AccountDao extends GenericDao<Account, Long> {

  /**
   * gets account record that contains the user name 'userName' or returns null
   * if no record is found
   * 
   * @param userName
   * @return
   */
  public Account getByUserName(String userName);

  /**
   * Returns all the usernames in the database. Excludes "anonymous" user
   * 
   * @return
   */
  public List<String> getAllUserNames();

  /**
   * get an account record based on the era id
   * 
   * @param email
   * @return
   */
  public List<BasicAccount> getAccountsByEraId(String eraId);

  /**
   * get an account record based on the email address found in the user property
   * 
   * @param email
   * @return
   */

  public Account getByEmail(String email);

  /**
   * get an account record based on the userUPN (siteminder id) found in the
   * user property
   * 
   * @param UserUPN
   * @return
   */
  public Account getByUserUPN(String userUPN);

  /**
   * Gets the account with user User
   * 
   * @param user
   * @return
   */
  public Account getByUser(User user);

  /**
   * Search through the accounts table for records that match the desired
   * searchkey and acocunt status, and filter the results by the pagingation
   * data provided. Also sets the numSearchResults field in pageData. Excludes
   * "anonymous" user
   * 
   * @param key
   * @param status
   * @param pageData
   * @return
   */
  public List<BasicAccount> search(String key, AccountStatus status,
      PaginationData pageData);

  /**
   * Gets the number of accounts with the given status. Does not count the
   * anonymous user.
   * 
   * @param status
   * @return
   */
  public Long getStatusCount(AccountStatus status);

  /**
   * Gets a list of all affiliated institutions that have been used in the past
   * 
   * @return
   */
  public List<String> getAffiliatedInstitutions();

  /**
   * Gets all the accounts that are not denied or deactivated. Excludes
   * "anonymous" user
   * 
   * @return
   */
  public List<Account> getAllActive();

  /**
   * Get a list of accounts that have at least one of the given roles
   * (non-expired). Roles must be explicitly given and exist in in the
   * AccountRole table. If onlyActive is true, then also filter by accounts
   * which are active.
   * 
   * @param role
   * @param onlyActive
   * @return
   */
  public List<Account> getAccountsWithRoles(Set<RoleType> roles,
      boolean onlyActive);

  /**
   * Changes the password of the account
   * 
   * @param id
   * @param password
   */
  public Account changePassword(Long id, byte[] password);
}
