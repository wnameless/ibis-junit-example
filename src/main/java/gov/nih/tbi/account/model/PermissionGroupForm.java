package gov.nih.tbi.account.model;

import gov.nih.tbi.CoreConstants;
import gov.nih.tbi.account.dao.AccountDao;
import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.PermissionGroup;
import gov.nih.tbi.account.model.hibernate.PermissionGroupMember;
import gov.nih.tbi.commons.model.PermissionGroupStatus;
import gov.nih.tbi.commons.service.AccountManager;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * This form is for the permission group creation form
 * 
 * @author Francis Chen
 */
public class PermissionGroupForm {

  @Autowired
  AccountDao accountDao;

  @Autowired
  AccountManager accountManager;

  private Long id;
  private String groupName;
  private String groupDescription;
  private Boolean publicStatus;
  private Set<PermissionGroupMember> memberSet;
  private Set<PermissionGroupMember> memberApproval;

  public PermissionGroupForm() {

  }

  public PermissionGroupForm(PermissionGroup permissionGroup) {

    id = permissionGroup.getId();
    groupName = permissionGroup.getGroupName();
    groupDescription = permissionGroup.getGroupDescription();
    publicStatus = permissionGroup.getPublicStatus();
    memberSet = permissionGroup.getMemberSet();
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getGroupName() {

    return groupName;
  }

  public void setGroupName(String groupName) {

    this.groupName = groupName;
  }

  public String getGroupDescription() {

    return groupDescription;
  }

  public void setGroupDescription(String groupDescription) {

    this.groupDescription = groupDescription;
  }

  public Boolean getPublicStatus() {

    return publicStatus;
  }

  public void setPublicStatus(Boolean publicStatus) {

    this.publicStatus = publicStatus;
  }

  public Set<PermissionGroupMember> getMemberSet() {

    return memberSet;
  }

  /**
   * This method takes in the list of approval mapping and create
   * PermissionGroupMembers accordingly.
   * 
   * @param approvalList
   *          - each string in this list is formatted as XXX_Y where the X's are
   *          the ID of the PermissionGroupMember we're going to modify, and Y
   *          is the ID of the PermissionGroupStatus we're going to assign it.
   */
  public void setMemberApproval(int memberInt, String status) {

    if (memberApproval == null) {
      memberApproval = new HashSet<PermissionGroupMember>();
    }

    Long memberId = Long.valueOf(memberInt);
    Long statusId = Long.valueOf(status);

    for (PermissionGroupMember member : accountManager.getPermissionGroupById(
        id).getMemberSet()) {
      if (member.getId() != null
          && Long.valueOf(memberId).equals(member.getId())) {
        // if ID is active, set status to active
        if (PermissionGroupStatus.ACTIVE.getId().equals(statusId)) {
          member.setPermissionGroupStatus(PermissionGroupStatus.ACTIVE);
          memberApproval.add(member);
          // if no action is selected, the member will remain as requested

          /*
           * This is where the check to see if all the current permissions have
           * been set. If they are all are accepted then the account should be
           * set to ACTIVE
           */
          // member.getAccount().accountActivation();
        } else if (PermissionGroupStatus.REQUESTED.getId().equals(statusId)) {
          member.setPermissionGroupStatus(PermissionGroupStatus.REQUESTED);
          memberApproval.add(member);
        }
        // do nothing if deny is selected, the permission group member will be
        // removed.
      }
    }
  }

  /**
   * Sets the field according to the optiontransferselect on the form.
   * 
   * @param accountList
   *          - A comma delimited list of account ID's to become members of this
   *          permission group.
   */
  public void setMemberSet(String accountList) {

    Set<PermissionGroupMember> memberSet =
        new LinkedHashSet<PermissionGroupMember>();

    if (accountList.equals("empty")) {
      return;
    } else {
      // parse the comma delimited list into an array
      String[] accountArray = accountList.split(CoreConstants.COMMA);

      // create appropriate members and add them to the member set
      for (String accountId : accountArray) {
        Account account = accountDao.get(Long.valueOf(accountId.trim()));
        PermissionGroupMember newMember = new PermissionGroupMember();
        newMember.setAccount(account);
        newMember.setPermissionGroupStatus(PermissionGroupStatus.ACTIVE);
        memberSet.add(newMember);
      }
    }

    this.memberSet = memberSet;
  }

  /**
   * transfers the fields from the form to an actual permission group object
   * 
   * @param permissionGroup
   *          - The permissionGroup we want to transfer our fields into.
   */
  public void adapt(PermissionGroup permissionGroup) {

    permissionGroup.setGroupName(groupName);
    permissionGroup.setGroupDescription(groupDescription);
    permissionGroup.setPublicStatus(publicStatus);

    // set the memberSet
    if (memberSet != null && !publicStatus) {
      for (PermissionGroupMember member : memberSet) {
        member.setPermissionGroup(permissionGroup);
      }
    } else {
      memberSet = new HashSet<PermissionGroupMember>();
    }

    // add all the members from the approval list into the new memberSet
    if (memberApproval != null && !publicStatus) {
      memberSet.addAll(memberApproval);
    }

    permissionGroup.setMemberSet(memberSet);
  }
}
