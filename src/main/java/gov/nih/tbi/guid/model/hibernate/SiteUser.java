package gov.nih.tbi.guid.model.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "siteuser")
public class SiteUser implements Serializable {

  private static final long serialVersionUID = -2685566145834525431L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "SITEUSER_SEQ")
  @SequenceGenerator(name = "SITEUSER_SEQ", sequenceName = "SITEUSER_SEQ",
      allocationSize = 1)
  @Column(name = "user_map_id", nullable = false)
  private Long id;
  @Column(name = "site_id", nullable = false)
  private Long userId;
  @Column(name = "user_id", nullable = false)
  private String siteUserName;
  @Column(name = "date_created", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;
  @Column(name = "deleted", nullable = false)
  private boolean deleted;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email_address")
  private String email;
  @Column(name = "phone_number")
  private String phone;
  @Column(name = "user_name")
  private String userName;
  @Column(name = "user_password")
  private String password;
  @Column(name = "creator_id")
  private Long creatorId;
  @Column(name = "date_updated")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateUpdated;
  @Column(name = "updater_id")
  private Long updaterId;
  @Column(name = "num_incorrect_pw", nullable = false)
  private int numIncorrectPw;
  @Column(name = "lockout_start")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lockoutStart;
  @Column(name = "last_pass_update_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastPassUpdate;
  @Column(name = "nih_username")
  private String nihUsername;

  @ManyToOne
  @JoinColumn(name = "site_id", insertable = false, updatable = false)
  private Site site;

  /**
   * @return the numIncorrectPw
   */
  public int getNumIncorrectPw() {

    return numIncorrectPw;
  }

  /**
   * @param numIncorrectPw
   *          the numIncorrectPw to set
   */
  public void setNumIncorrectPw(int numIncorrectPw) {

    this.numIncorrectPw = numIncorrectPw;
  }

  /**
   * @return the lockoutStart
   */
  public Date getLockoutStart() {

    return lockoutStart;
  }

  /**
   * @param lockoutStart
   *          the lockoutStart to set
   */
  public void setLockoutStart(Date lockoutStart) {

    this.lockoutStart = lockoutStart;
  }

  /**
   * @return the lastPassUpdate
   */
  public Date getLastPassUpdate() {

    return lastPassUpdate;
  }

  /**
   * @param lastPassUpdate
   *          the lastPassUpdate to set
   */
  public void setLastPassUpdate(Date lastPassUpdate) {

    this.lastPassUpdate = lastPassUpdate;
  }

  /**
   * @return the creatorId
   */
  public Long getCreatorId() {

    return creatorId;
  }

  /**
   * @param creatorId
   *          the creatorId to set
   */
  public void setCreatorId(Long creatorId) {

    this.creatorId = creatorId;
  }

  /**
   * @return the dateUpdated
   */
  public Date getDateUpdated() {

    return dateUpdated;
  }

  /**
   * @param dateUpdated
   *          the dateUpdated to set
   */
  public void setDateUpdated(Date dateUpdated) {

    this.dateUpdated = dateUpdated;
  }

  /**
   * @return the updaterId
   */
  public Long getUpdaterId() {

    return updaterId;
  }

  /**
   * @param updaterId
   *          the updaterId to set
   */
  public void setUpdaterId(Long updaterId) {

    this.updaterId = updaterId;
  }

  public void setSite(Site newSite) {

    site = newSite;
  }

  public Site getSite() {

    return site;
  }

  /**
   * @return the id
   */
  public Long getId() {

    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @return the userId
   */
  public Long getUserId() {

    return userId;
  }

  /**
   * @param userId
   *          the userId to set
   */
  public void setUserId(Long userId) {

    this.userId = userId;
  }

  /**
   * @return the siteUserName
   */
  public String getSiteUserName() {

    return siteUserName;
  }

  /**
   * @param siteUserName
   *          the siteUserName to set
   */
  public void setSiteUserName(String siteUserName) {

    this.siteUserName = siteUserName;
  }

  /**
   * @return the dateCreated
   */
  public Date getDateCreated() {

    return dateCreated;
  }

  /**
   * @param dateCreated
   *          the dateCreated to set
   */
  public void setDateCreated(Date dateCreated) {

    this.dateCreated = dateCreated;
  }

  /**
   * @return the deleted
   */
  public boolean getDeleted() {

    return deleted;
  }

  /**
   * @param deleted
   *          the deleted to set
   */
  public void setDeleted(boolean deleted) {

    this.deleted = deleted;
  }

  public String getFirstName() {

    return this.firstName;
  }

  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  public String getLastName() {

    return this.lastName;
  }

  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  public String getEmail() {

    return this.email;
  }

  public void setEmail(String email) {

    this.email = email;
  }

  public String getPhone() {

    return this.phone;
  }

  public void setPhone(String phone) {

    this.phone = phone;
  }

  public String getUserName() {

    return this.userName;
  }

  public void setUserName(String username) {

    this.userName = username;
  }

  public String getPassword() {

    return this.password;
  }

  public void setPassword(String password) {

    this.password = password;
  }

  @Override
  public String toString() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean equals(Object o) {

    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int hashCode() {

    // TODO Auto-generated method stub
    return 0;
  }

  public void setNihUsername(String nihUsername) {

    this.nihUsername = nihUsername;
  }

  public String getNihUsername() {

    return nihUsername;
  }

  public String getUsername() {

    return this.userName;
  }

  public boolean isAccountNonExpired() {

    return true;
  }

  public boolean isAccountNonLocked() {

    return true;
  }

  public boolean isCredentialsNonExpired() {

    return true;
  }

  public boolean isEnabled() {

    return !this.getDeleted();
  }

}
