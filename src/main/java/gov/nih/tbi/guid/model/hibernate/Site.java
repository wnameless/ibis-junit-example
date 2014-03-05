package gov.nih.tbi.guid.model.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "site")
public class Site implements Serializable {

  private static final long serialVersionUID = 4587234507468688178L;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SITE_SEQ")
  @SequenceGenerator(name = "SITE_SEQ", sequenceName = "SITE_SEQ",
      allocationSize = 1)
  @Column(name = "site_id", nullable = false)
  protected Long id;
  @Column(name = "user_name", length = 64, nullable = false)
  protected String username;
  @Column(name = "site_password", length = 255, nullable = false)
  protected String password;
  @Column(name = "email_address", length = 128)
  protected String email;
  @Column(name = "phone_number", length = 64)
  protected String phone;
  @Column(name = "creator_id")
  protected Long creatorId;
  @Column(name = "date_created", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  protected Date dateCreated;
  @Column(name = "updater_id")
  protected Long updaterId;
  @Column(name = "date_updated")
  @Temporal(TemporalType.TIMESTAMP)
  protected Date dateUpdated;
  @Column(name = "org_id", nullable = false)
  protected Long orgId;
  @Column(name = "deleted", nullable = false)
  protected boolean deleted;
  @ManyToOne
  @JoinColumn(name = "org_id", insertable = false, updatable = false)
  protected Organization org;

  public void setOrg(Organization organization) {

    org = organization;
  }

  public Organization getOrg() {

    return org;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }

  public String getPassword() {

    return password;
  }

  public void setPassword(String password) {

    this.password = password;
  }

  public String getEmail() {

    return email;
  }

  public void setEmail(String email) {

    this.email = email;
  }

  public String getPhone() {

    return phone;
  }

  public void setPhone(String phone) {

    this.phone = phone;
  }

  public Long getCreatorId() {

    return creatorId;
  }

  public void setCreatorId(Long creatorId) {

    this.creatorId = creatorId;
  }

  public Date getDateCreated() {

    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {

    this.dateCreated = dateCreated;
  }

  public Long getUpdaterId() {

    return updaterId;
  }

  public void setUpdaterId(Long updaterId) {

    this.updaterId = updaterId;
  }

  public Date getDateUpdated() {

    return dateUpdated;
  }

  public void setDateUpdated(Date dateUpdated) {

    this.dateUpdated = dateUpdated;
  }

  public Long getOrgId() {

    return orgId;
  }

  public void setOrgId(Long orgId) {

    this.orgId = orgId;
  }

  public boolean getDeleted() {

    return this.deleted;
  }

  public void setDeleted(boolean deleted) {

    this.deleted = deleted;
  }

  @Override
  public boolean equals(Object arg0) {

    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int hashCode() {

    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String toString() {

    // TODO Auto-generated method stub
    return null;
  }

}
