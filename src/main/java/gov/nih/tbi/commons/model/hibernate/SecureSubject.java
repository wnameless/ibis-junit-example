package gov.nih.tbi.commons.model.hibernate;

import gov.nih.tbi.commons.model.GuidSubject;
import gov.nih.tbi.guid.model.hibernate.Site;
import gov.nih.tbi.guid.model.hibernate.SiteUser;

import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 * The class represents a secure subject which includes 5 hash codes generated
 * from subject's personally identifiable information(PII) by one-way hash
 * algorithm. Because the PII can not be inferred from the 5 hash codes.
 * 
 * @author wangh3
 * 
 */
@Entity
@Table(name = "subject")
public class SecureSubject implements Serializable, GuidSubject {

  /**
	 * 
	 */
  private static final long serialVersionUID = 434199278110864320L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBJECT_SEQ")
  @SequenceGenerator(name = "SUBJECT_SEQ", sequenceName = "SUBJECT_SEQ",
      allocationSize = 1)
  @Column(name = "subject_id", nullable = false)
  private Long id;

  /**
   * The global unique identifier which represents the subject.
   */
  @Column(name = "guid", nullable = false, length = 25)
  private String guid;

  @Column(name = "subject_type", nullable = false)
  private Integer subjectType;

  /**
   * First hash code which is generated from 4 PII fields: 1. Social Security
   * Number 2. Sex At Birth 3. Day of Birth 4. Year of Birth
   */
  @Column(name = "hashcode1")
  private byte[] hashCode1;

  /**
   * The second hash code which is generated from 7 PII fields: 1. First Name At
   * Birth 2. Last Name At Birth 3. Middle Name At Birth 4. Day of Birth 5.
   * Month of Birth 6. Year of Birth 7. City of Birth
   */
  @Column(name = "hashcode2")
  private byte[] hashCode2;

  /**
   * The third hash code which is generated from 6 PII fields: 1. First Name At
   * Birth of Mother 2. Last Name At Birth of Mother 3. First Name At Birth of
   * Father 4. Last Name At Birth Of Father 5. First Name At Birth 6. Year of
   * Birth
   */
  @Column(name = "hashcode3")
  private byte[] hashCode3;

  /**
   * The fourth hash code which is generated from 9 PII fields: 1. Day of Birth
   * of Mother 2. Month of Birth of Mother 3. Day of Birth of Father 4. Month of
   * Birth of Father 5. First Name At Birth 6. Last Name At Birth 7. Sex At
   * Birth 8. City of Birth 9. Year of Birth
   */
  @Transient
  private byte[] hashCode4;

  /**
   * The fifth hash code which is generated from 6 PII fields: 1. First Name At
   * Birth 2. Middle Name At Birth 3. First Name At Birth Of Mother 4. First
   * Name At Birth of Father 5. Last Name At Birth of Mother 6. Year of Birth
   */
  @Transient
  private byte[] hashCode5;

  @Transient
  private String filename;

  @Column(name = "date_created")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;
  @Column(name = "date_updated")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateUpdated;

  @ManyToOne
  @JoinColumn(name = "creator_id", insertable = false, updatable = false)
  protected Site creator;
  @ManyToOne
  @JoinColumn(name = "creator_user_id", insertable = false, updatable = false)
  protected SiteUser creatorSiteUser;
  @ManyToOne
  @JoinColumn(name = "updater_id", insertable = false, updatable = false)
  protected Site updater;
  @ManyToOne
  @JoinColumn(name = "updater_user_id", insertable = false, updatable = false)
  protected SiteUser updaterSiteUser;

  /**
   * Returns the first hash code.
   * 
   * @return the first hash code.
   */
  public byte[] getHashCode1() {

    return hashCode1;
  }

  /**
   * Sets the first hash code.
   * 
   * @param hashCode1
   *          the first hash code.
   */
  public void setHashCode1(byte[] hashCode1) {

    this.hashCode1 = hashCode1;
  }

  /**
   * Returns the secode hash code.
   * 
   * @return the secode hash code.
   */
  public byte[] getHashCode2() {

    return hashCode2;
  }

  /**
   * Sets the second hash code.
   * 
   * @param hashCode2
   *          the second hash code.
   */
  public void setHashCode2(byte[] hashCode2) {

    this.hashCode2 = hashCode2;
  }

  /**
   * Returns the third hash code.
   * 
   * @return the third hash code.
   */
  public byte[] getHashCode3() {

    return hashCode3;
  }

  /**
   * Sets the third hash code.
   * 
   * @param hashCode3
   *          the third hash code.
   */
  public void setHashCode3(byte[] hashCode3) {

    this.hashCode3 = hashCode3;
  }

  /**
   * Returns the fourth hash code.
   * 
   * @return the fourth hash code.
   */
  public byte[] getHashCode4() {

    return hashCode4;
  }

  /**
   * Sets the fourth hash code.
   * 
   * @param hashCode4
   *          the fourth hash code.
   */
  public void setHashCode4(byte[] hashCode4) {

    this.hashCode4 = hashCode4;
  }

  /**
   * Returns the fifth hash code.
   * 
   * @return the fifth hash code.
   */
  public byte[] getHashCode5() {

    return hashCode5;
  }

  /**
   * Sets the fifth hash code.
   * 
   * @param hashCode5
   *          the fifth hash code.
   */
  public void setHashCode5(byte[] hashCode5) {

    this.hashCode5 = hashCode5;
  }

  /**
   * Returns the GUID of the subject.
   * 
   * @return the GUID of the subject.
   */
  public String getGuid() {

    return guid;
  }

  /**
   * Sets the GUID of the subject.
   * 
   * @param guid
   *          the GUID of the subject.
   */
  public void setGuid(String guid) {

    this.guid = guid;
  }

  /**
   * Returns the Filename of the subject.
   * 
   * @return the Filename of the subject.
   */
  public String getFilename() {

    return filename;
  }

  /**
   * Sets the Filename of the subject.
   * 
   * @param filename
   *          the Filename of the subject.
   */
  public void setFilename(String filename) {

    this.filename = filename;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public Integer getSubjectType() {

    return subjectType;
  }

  public void setSubjectType(Integer subjectType) {

    this.subjectType = subjectType;
  }

  public Date getDateCreated() {

    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {

    this.dateCreated = dateCreated;
  }

  public Date getDateUpdated() {

    return dateUpdated;
  }

  public void setDateUpdated(Date dateUpdated) {

    this.dateUpdated = dateUpdated;
  }

  public Site getCreator() {

    return creator;
  }

  public void setCreator(Site creator) {

    this.creator = creator;
  }

  public SiteUser getCreatorSiteUser() {

    return creatorSiteUser;
  }

  public void setCreatorSiteUser(SiteUser creatorSiteUser) {

    this.creatorSiteUser = creatorSiteUser;
  }

  public Site getUpdater() {

    return updater;
  }

  public void setUpdater(Site updater) {

    this.updater = updater;
  }

  public SiteUser getUpdaterSiteUser() {

    return updaterSiteUser;
  }

  public void setUpdaterSiteUser(SiteUser updaterSiteUser) {

    this.updaterSiteUser = updaterSiteUser;
  }

  public String getCreatedBy() {

    return this.creatorSiteUser.getLastName() + ", "
        + this.creatorSiteUser.getFirstName();
  }

  public String getCreatedOrg() {

    return this.creator.getOrg().getName();
  }

}
