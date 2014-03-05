package gov.nih.tbi.account.model;

import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.EntityMap;
import gov.nih.tbi.account.model.hibernate.PermissionGroup;
import gov.nih.tbi.account.model.hibernate.PermissionGroupMember;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The AccountWebServiceModel is a class that consists of a number of subclasses
 * used by the REST Account web services. These classes allow lists of objects
 * to be returned by these web services.
 * 
 * @author mgree1
 * 
 */

public class AccountRestServiceModel {

  @XmlRootElement(name = "accessibleEntityIDs")
  public static class LongListWrapper {

    @XmlElement(name = "ID")
    Set<Long> set = new HashSet<Long>();

    public LongListWrapper() {

    }

    public void addAll(Set<Long> l) {

      set.addAll(l);
    }

    public Set<Long> getList() {

      return set;
    }
  }

  @XmlRootElement(name = "Accounts")
  public static class AccountsWrapper {

    @XmlElement(name = "account")
    List<Account> account = new ArrayList<Account>();

    public AccountsWrapper() {

    }

    public void addAll(List<Account> c) {

      account.addAll(c);
    }

    public List<Account> getAccountList() {

      return account;
    }
  }

  @XmlRootElement(name = "PermissionGroups")
  public static class PermissionGroupsWrapper {

    @XmlElement(name = "permissionGroup")
    List<PermissionGroup> permGrps = new ArrayList<PermissionGroup>();

    public PermissionGroupsWrapper() {

    }

    public void addAll(List<PermissionGroup> c) {

      permGrps.addAll(c);
    }

    public List<PermissionGroup> getPermissionGroupsList() {

      return permGrps;
    }
  }

  @XmlRootElement(name = "EntityMap")
  public static class EntityMapsWrapper {

    @XmlElement(name = "entityMapGroup")
    List<EntityMap> entMaps = new ArrayList<EntityMap>();

    public EntityMapsWrapper() {

    }

    public void addAll(List<EntityMap> c) {

      entMaps.addAll(c);
    }

    public List<EntityMap> getEntityMapsList() {

      return entMaps;
    }
  }

  @XmlRootElement(name = "PermissionGroupMember")
  public static class PermissionGroupMemberWrapper {

    @XmlElement(name = "permissionGroupMember")
    List<PermissionGroupMember> pgMembers =
        new ArrayList<PermissionGroupMember>();

    public PermissionGroupMemberWrapper() {

    }

    public void addAll(List<PermissionGroupMember> c) {

      pgMembers.addAll(c);
    }

    public List<PermissionGroupMember> getPermissionGroupMembersList() {

      return pgMembers;
    }

    public void addAll(Set<PermissionGroupMember> memberSet) {

      pgMembers.addAll(memberSet);

    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "PermissionGroupMemberBoolean")
  public static class PerGrpMemBool {

    @XmlElement(name = "working")
    private Boolean working;

    public PerGrpMemBool() {

    }

    public Boolean isWorking() {

      return this.working;
    }

    public void setWorking(Boolean working) {

      this.working = working;
    }

  }

}
