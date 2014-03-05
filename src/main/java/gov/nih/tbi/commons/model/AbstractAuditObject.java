package gov.nih.tbi.commons.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractAuditObject extends AbstractBricsObject {

  // AUDIT FIELDS
  @Column(name = "MODIFIED_USER_ID")
  private Long modifiedUserId;
  @Column(name = "MODIFIED_DATE")
  private Date modifiedDate;

  // @OneToOne(cascade = { CascadeType.DETACH })
  // @JoinColumn(name = "MODIFIED_USER_ID")
  // private User modifiedUser;

  public Long getModifiedUserId() {

    return modifiedUserId;
  }

  public void setModifiedUserId(Long modifiedUserId) {

    this.modifiedUserId = modifiedUserId;
  }

  public Date getModifiedDate() {

    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {

    this.modifiedDate = modifiedDate;
  }

  // public Long getModifiedUserId()
  // {
  // return modifiedUserId;
  // }
  //
  // public void setModifiedUserId( Long modifiedUserId )
  // {
  // this.modifiedUserId = modifiedUserId;
  // }
}
