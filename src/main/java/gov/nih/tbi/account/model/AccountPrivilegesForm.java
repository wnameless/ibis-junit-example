package gov.nih.tbi.account.model;

import gov.nih.tbi.account.dao.PermissionGroupDao;
import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.AccountRole;
import gov.nih.tbi.account.model.hibernate.PermissionGroupMember;
import gov.nih.tbi.commons.model.PermissionGroupStatus;
import gov.nih.tbi.commons.model.RoleStatus;
import gov.nih.tbi.commons.model.RoleType;
import gov.nih.tbi.commons.service.AccountManager;
import gov.nih.tbi.commons.service.StaticReferenceManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class sets the fields from the Account Privileges Page
 * 
 * @author Francis Chen
 */
public class AccountPrivilegesForm {

  static Logger logger = Logger.getLogger(AccountPrivilegesForm.class);

  @Autowired
  AccountManager accountManager;

  @Autowired
  StaticReferenceManager staticManager;

  @Autowired
  PermissionGroupDao permissionGroupDao;

  protected Set<AccountRole> accountRoleList;
  protected Set<PermissionGroupMember> permissionGroupMemberList;

  public AccountPrivilegesForm() {

  }

  public AccountPrivilegesForm(Account account) {

    accountRoleList = account.getAccountRoleList();
    permissionGroupMemberList = account.getPermissionGroupMemberList();
  }

  public Set<AccountRole> getAccountRoleList() {

    return accountRoleList;
  }

  public void setAccountRoleList(String[] roleList) {

    if (roleList != null) {
      accountRoleList = new HashSet<AccountRole>();
    }

    for (String role : roleList) {
      AccountRole newRole = new AccountRole();
      newRole.setRoleStatus(RoleStatus.REQUESTED);
      for (RoleType type : RoleType.values()) {
        if (Long.valueOf(role).equals(type.getId())) {
          newRole.setRoleType(type);
        }
      }

      accountRoleList.add(newRole);
    }
  }

  /**
   * Searchs for the AccountRole with an a role id == index and changes the
   * status of that role to value. If the AccountRole does not exist, then one
   * is created.
   * 
   * @param index
   *          - The id of the RoleType
   * @param value
   *          - The Id of the RoleStatus
   */
  public void setAccountRoleList(int index, String value) {

    // If the accountRoleLIst is null, then we create one
    if (accountRoleList == null) {
      accountRoleList = new HashSet<AccountRole>();
    }

    // Search for the account with the correct RoleType
    AccountRole currentRole = null;
    for (AccountRole role : accountRoleList) {
      if (role.getRoleType().getId() == index) {
        currentRole = role;
        role.setRoleStatus(RoleStatus.getById(Long.valueOf(value)));
        // If this role is not active, then clear any expiration date that may
        // have been set
        if (!currentRole.getRoleStatus().equals(RoleStatus.ACTIVE)) {
          role.setExpirationDate(null);
        }
        break;
      }
    }

    // If currentRole is null, then we need to create the appropriate
    // AccountRole entry
    if (currentRole == null) {
      currentRole = new AccountRole();
      currentRole.setRoleType(RoleType.getById(Long.valueOf(index)));
      currentRole.setRoleStatus(RoleStatus.getById(Long.valueOf(value)));
      accountRoleList.add(currentRole);
    }
  }

  /**
   * Searches for an AccountRole with a role matching index and sets the
   * expiration date of that role. If the role currently does not exist, then
   * one is created.
   * 
   * @param index
   *          - The id of the roleType
   * @param value
   *          - The expiration date of this role type in string format
   */
  public void setAccountRoleExpiration(int index, String value) {

    // Create an Date object from value.

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
    Date expirationDate = null;
    try {
      expirationDate = df.parse(value);
    } catch (ParseException e) {
      try {
        expirationDate = df2.parse(value);
      } catch (ParseException e2) {
        return;
      }
    }

    // If the accountRoleList is null, then create one
    if (accountRoleList == null) {
      accountRoleList = new HashSet<AccountRole>();
    }

    // Search for the account with the correct RoleType and set the expiration
    // date
    AccountRole currentRole = null;
    for (AccountRole role : accountRoleList) {
      if (role.getRoleType().getId() == index) {
        currentRole = role;
        // Only set the expiration date if the account is active
        if (currentRole.getRoleStatus().equals(RoleStatus.ACTIVE)) {
          role.setExpirationDate(expirationDate);
        }
        break;
      }
    }
    // If currentRole is null, then we need to create the appropriate
    // AccountRole entry
    if (currentRole == null) {
      currentRole = new AccountRole();
      currentRole.setRoleType(RoleType.getById(Long.valueOf(index)));
      currentRole.setExpirationDate(expirationDate);
      accountRoleList.add(currentRole);
    }
  }

  public Set<PermissionGroupMember> getPermissionGroupMemberList() {

    return permissionGroupMemberList;
  }

  public void setPermissionGroupMemberList(String[] permissionGroupList) {

    if (permissionGroupList != null) {
      permissionGroupMemberList = new HashSet<PermissionGroupMember>();
    }

    for (String permissionGroupId : permissionGroupList) {
      PermissionGroupMember newGroupMember = new PermissionGroupMember();
      newGroupMember.setPermissionGroupStatus(PermissionGroupStatus.REQUESTED);
      newGroupMember.setPermissionGroup(permissionGroupDao.get(Long
          .valueOf(permissionGroupId)));
      permissionGroupMemberList.add(newGroupMember);
    }
  }

  public void adapt(Account account, Boolean enforceStaticFields) {

    if (accountRoleList != null) {

      // Check for altered roles
      for (AccountRole role : accountRoleList) {
        boolean contains = false;
        role.setAccount(account);

        // checks if role type already exists in the accountRoleList
        for (AccountRole existingRole : account.getAccountRoleList()) {
          if (existingRole.getRoleType().equals(role.getRoleType())) {
            contains = true;
            // Developer's Note: MG - I commented out this section because it
            // prevented the existing roles
            // to be updated by the incoming role.
            // merge these two roles
            // if (RoleStatus.REQUESTED.equals(role.getRoleStatus()) ||
            // RoleStatus.ACTIVE.equals(role.getRoleStatus()))
            // {
            existingRole.setExpirationDate(role.getExpirationDate());
            existingRole.setRoleStatus(role.getRoleStatus());
            // }

          }
        }
        // if role type does not already exist, add the new one
        if (!contains) {
          account.getAccountRoleList().add(role);
        }
      }

    } else

    if (permissionGroupMemberList != null) {
      for (PermissionGroupMember groupMember : permissionGroupMemberList) {
        boolean contains = false;
        groupMember.setAccount(account);
        // checks if the permission group already exists in
        // permissionGroupMemberList
        for (PermissionGroupMember existingGroupMember : account
            .getPermissionGroupMemberList()) {
          if (existingGroupMember.getPermissionGroup().getGroupName()
              .equals(groupMember.getPermissionGroup().getGroupName())) {
            // if permission group status is not active, set to requested
            if (!PermissionGroupStatus.ACTIVE.equals(existingGroupMember
                .getPermissionGroupStatus())) {
              existingGroupMember
                  .setPermissionGroupStatus(PermissionGroupStatus.REQUESTED);
            }
            contains = true;
          }
        }
        // if permission group does not already exist, add the new one to the
        // set
        if (!contains) {
          account.getPermissionGroupMemberList().add(groupMember);
        }
      }
    }

  }
}
