package gov.nih.tbi.account.model.hibernate;

import gov.nih.tbi.account.model.PermissionAuthority;
import gov.nih.tbi.commons.model.AbstractBricsObject;
import gov.nih.tbi.commons.model.AccountStatus;
import gov.nih.tbi.commons.model.RoleStatus;
import gov.nih.tbi.commons.model.RoleType;
import gov.nih.tbi.commons.model.hibernate.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Model for storing accounts
 * 
 * @author Francis Chen
 */
@Entity
@Table(name = "ACCOUNT")
@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account extends AbstractBricsObject implements Serializable,
    PermissionAuthority {

  private static final long serialVersionUID = -288792190452003023L;
  /**********************************************************************/
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
  @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "ACCOUNT_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "PASSWORD")
  private byte[] password;

  @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "USER_ID")
  private User user;

  @Column(name = "AFFILIATED_INSTITUTION")
  private String affiliatedInstitution;

  @Column(name = "ERA_COMMONS_ID")
  private String eraId;

  @Column(name = "ADDRESS_1")
  private String address1;

  @Column(name = "ADDRESS_2")
  private String address2;

  @Column(name = "CITY")
  private String city;

  @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "STATE_ID")
  private State state;

  @Column(name = "POSTAL_CODE")
  private String postalCode;

  @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "COUNTRY_ID")
  private Country country;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "INTEREST_IN_TBI")
  private String interestInTbi;

  @Column(name = "RECOVERY_DATE")
  private Date recoveryDate;

  @Column(name = "IS_ACTIVE")
  private Boolean isActive;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "account", targetEntity = AccountRole.class,
      orphanRemoval = true)
  private Set<AccountRole> accountRoleList;

  @XmlTransient
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "account", targetEntity = PermissionGroupMember.class,
      orphanRemoval = true)
  private Set<PermissionGroupMember> permissionGroupMemberList;

  @XmlTransient
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "account", targetEntity = EntityMap.class,
      orphanRemoval = true)
  private Set<EntityMap> entityMapList;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "ACCOUNT_STATUS_ID")
  private AccountStatus accountStatus;

  @Column(name = "ADMIN_NOTE")
  private String adminNote;

  @Column(name = "APPLICATION_DATE")
  private Date applicationDate;

  @Column(name = "SUCCESSFUL_LOGIN_DATE")
  private Date lastSuccessfulLogin;

  @Column(name = "ACCOUNT_UNLOCK_DATE")
  private Date unlockDate;

  @Column(name = "PASSWORD_EXPIRATION_DATE")
  private Date passwordExpirationDate;

  @Transient
  private String diseaseKey = "";

  /**********************************************************************/

  public Account() {

    user = new User();
    accountRoleList = new HashSet<AccountRole>();
    permissionGroupMemberList = new HashSet<PermissionGroupMember>();
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getUserName() {

    return userName;
  }

  public void setUserName(String userName) {

    this.userName = userName;
  }

  public byte[] getPassword() {

    return password;
  }

  public void setPassword(byte[] password) {

    this.password = password;
  }

  public User getUser() {

    return user;
  }

  public void setUser(User user) {

    this.user = user;
  }

  public Long getUserId() {

    if (user != null) {
      return user.getId();
    } else {
      return null;
    }
  }

  public String getAffiliatedInstitution() {

    return affiliatedInstitution;
  }

  public void setAffiliatedInstitution(String affiliatedInstitution) {

    this.affiliatedInstitution = affiliatedInstitution;
  }

  public String getEraId() {

    return eraId;
  }

  public void setEraId(String eraId) {

    this.eraId = eraId;
  }

  public String getAddress1() {

    return address1;
  }

  public void setAddress1(String address1) {

    this.address1 = address1;
  }

  public String getAddress2() {

    return address2;
  }

  public void setAddress2(String address2) {

    this.address2 = address2;
  }

  public String getCity() {

    return city;
  }

  public void setCity(String city) {

    this.city = city;
  }

  public State getState() {

    return state;
  }

  public void setState(State state) {

    this.state = state;
  }

  public String getPostalCode() {

    return postalCode;
  }

  public void setPostalCode(String postalCode) {

    this.postalCode = postalCode;
  }

  public Country getCountry() {

    return country;
  }

  public void setCountry(Country country) {

    this.country = country;
  }

  public String getPhone() {

    return phone;
  }

  public void setPhone(String phone) {

    this.phone = phone;
  }

  public String getInterestInTbi() {

    return interestInTbi;
  }

  public void setInterestInTbi(String interestInTbi) {

    this.interestInTbi = interestInTbi;
  }

  public Date getLastSuccessfulLogin() {

    return lastSuccessfulLogin;
  }

  public void setLastSuccessfulLogin(Date lastSuccessfulLogin) {

    this.lastSuccessfulLogin = lastSuccessfulLogin;
  }

  public Date getUnlockDate() {

    return unlockDate;
  }

  public void setUnlockDate(Date unlockDate) {

    this.unlockDate = unlockDate;
  }

  public Date getPasswordExpirationDate() {

    return passwordExpirationDate;
  }

  public void setPasswordExpirationDate(Date passwordExpirationDate) {

    this.passwordExpirationDate = passwordExpirationDate;
  }

  public AccountStatus getAccountStatus() {

    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {

    this.accountStatus = accountStatus;
  }

  /**
   * @return the recoveryDate
   */
  public Date getRecoveryDate() {

    return recoveryDate;
  }

  /**
   * @param recoveryDate
   *          the recoveryDate to set
   */
  public void setRecoveryDate(Date recoveryDate) {

    this.recoveryDate = recoveryDate;
  }

  public Set<AccountRole> getAccountRoleList() {

    return accountRoleList;
  }

  public void setAccountRoleList(Set<AccountRole> accountRoleList) {

    if (this.accountRoleList == null) {
      this.accountRoleList = new HashSet<AccountRole>();
    }

    this.accountRoleList.clear();

    if (accountRoleList != null) {
      this.accountRoleList.addAll(accountRoleList);
    }
  }

  public Set<EntityMap> getEntityMapList() {

    return entityMapList;
  }

  public void setEntityMapList(Set<EntityMap> entityMapList) {

    if (this.entityMapList == null) {
      this.entityMapList = new HashSet<EntityMap>();
    }

    this.entityMapList.clear();

    if (entityMapList != null) {
      this.entityMapList.addAll(entityMapList);
    }
  }

  public Boolean getIsActive() {

    if (isActive != null) {
      return isActive;
    }

    return false;
  }

  public void setIsActive(Boolean isActive) {

    this.isActive = isActive;
  }

  // public List<GrantedAuthority> getRoleList()
  // {
  // // TODO Auto-generated method stub
  // return null;
  // }

  public Set<PermissionGroupMember> getPermissionGroupMemberList() {

    return permissionGroupMemberList;
  }

  public void setPermissionGroupMemberList(
      Set<PermissionGroupMember> permissionGroupMemberListUpdate) {

    if (this.permissionGroupMemberList == null) {
      this.permissionGroupMemberList = new HashSet<PermissionGroupMember>();
    }

    // this.permissionGroupMemberList.clear();

    if (permissionGroupMemberList != null) {
      this.permissionGroupMemberList.addAll(permissionGroupMemberListUpdate);
    }
  }

  public String getAdminNote() {

    return adminNote;
  }

  public void setAdminNote(String adminNote) {

    this.adminNote = adminNote;
  }

  // TODO: REMOVE THIS FROM EVERYTHING, instead use
  // AccountManager.hasRole(Account, RoleType)
  @Deprecated
  public boolean hasRole(RoleType testRole) {

    for (AccountRole ar : accountRoleList) {
      if (ar.getRoleType().equals(testRole)
          && ar.getRoleStatus().equals(RoleStatus.ACTIVE)) {
        return true;
      }
    }

    return false;
  }

  public Date getApplicationDate() {

    return applicationDate;
  }

  public void setApplicationDate(Date applicationDate) {

    this.applicationDate = applicationDate;
  }

  public String getApplicationString() {

    if (applicationDate == null) {
      return "-";
    }
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    return df.format(applicationDate);
  }

  /**
   * Overrides display name for use in permission pages
   */
  public String getDisplayName() {

    return this.getUser().getFullName() + " (" + this.getUserName() + ")";
  }

  /**
   * Overrides display key for use in permission pages
   */
  public String getDisplayKey() {

    return "ACCOUNT:" + getDiseaseKey() + ";" + getId();
  }

  @Override
  public String getDiseaseKey() {

    return diseaseKey;
  }

  @Override
  public void setDiseaseKey(String diseaseKey) {

    this.diseaseKey = diseaseKey;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    /*
     * only the User object and user name should be equal
     */
    if (obj instanceof Account) {
      if (this == obj) {
        return true;
      }

      Account other = (Account) obj;
      if (this.userName.equals(other.userName) == true) {
        return this.user.equals(other.user);
      }
    }

    return false;
  }

  @Override
  public String toString() {

    return "Account [id=" + getId() + ", username=" + getUserName() + ", user="
        + getUser().toString() + ", accountStatus=" + getAccountStatus();
  }

}
