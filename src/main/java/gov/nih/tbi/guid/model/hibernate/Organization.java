package gov.nih.tbi.guid.model.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "organization")
public class Organization implements Serializable {

  private static final long serialVersionUID = 6757582467397836012L;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "ORGANIZATION_SEQ")
  @SequenceGenerator(name = "ORGANIZATION_SEQ",
      sequenceName = "ORGANIZATION_SEQ", allocationSize = 1)
  @Column(name = "org_id", nullable = false)
  private Long id;
  @Column(name = "name", nullable = false, length = 255)
  private String name;
  @Column(name = "full_name", nullable = false, length = 500)
  private String fullName;
  @Column(name = "description", length = 255)
  private String description;
  @Column(name = "end_point_reference", length = 255)
  private String endPointReference;
  @Column(name = "date_created", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;
  @Column(name = "creator_id")
  private Long creatorId;
  @Column(name = "date_updated")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateUpdated;
  @Column(name = "updater_id")
  private Long updaterId;
  @Column(name = "deleted", nullable = false)
  private boolean deleted;

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
   * @return the name
   */
  public String getName() {

    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return the fullName
   */
  public String getFullName() {

    return fullName;
  }

  /**
   * @param fullName
   *          the fullName to set
   */
  public void setFullName(String fullName) {

    this.fullName = fullName;
  }

  /**
   * @return the description
   */
  public String getDescription() {

    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {

    this.description = description;
  }

  /**
   * @return the endPointReference
   */
  public String getEndPointReference() {

    return endPointReference;
  }

  /**
   * @param endPointReference
   *          the endPointReference to set
   */
  public void setEndPointReference(String endPointReference) {

    this.endPointReference = endPointReference;
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
   * @return the dasteUpdated
   */
  public Date getDateUpdated() {

    return dateUpdated;
  }

  /**
   * @param dasteUpdated
   *          the dasteUpdated to set
   */
  public void setDateUpdated(Date dasteUpdated) {

    this.dateUpdated = dasteUpdated;
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

  public boolean getDeleted() {

    return this.deleted;
  }

  public void setDeleted(boolean deleted) {

    this.deleted = deleted;
  }

  /**
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {

    return serialVersionUID;
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

}
