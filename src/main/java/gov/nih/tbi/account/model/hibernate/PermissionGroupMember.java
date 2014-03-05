package gov.nih.tbi.account.model.hibernate;

import gov.nih.tbi.commons.model.AbstractBricsObject;
import gov.nih.tbi.commons.model.PermissionGroupStatus;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "PERMISSION_GROUP_MEMBER")
@XmlAccessorType(XmlAccessType.FIELD)
public class PermissionGroupMember extends AbstractBricsObject implements
    Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 8035550616038599510L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "PERMISSION_GROUP_MEMBER_SEQ")
  @SequenceGenerator(name = "PERMISSION_GROUP_MEMBER_SEQ",
      sequenceName = "PERMISSION_GROUP_MEMBER_SEQ", allocationSize = 1)
  private Long id;

  @XmlTransient
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
  @JoinColumn(name = "ACCOUNT_ID")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = PermissionGroup.class)
  @JoinColumn(name = "PERMISSION_GROUP_ID")
  private PermissionGroup permissionGroup;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "PERMISSION_GROUP_STATUS_ID")
  private PermissionGroupStatus permissionGroupStatus;

  @Column(name = "EXPIRATION_DATE")
  private Date expirationDate;

  /**********************************************************************/

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public PermissionGroup getPermissionGroup() {

    return permissionGroup;
  }

  public void setPermissionGroup(PermissionGroup permissionGroup) {

    this.permissionGroup = permissionGroup;
  }

  public Account getAccount() {

    return account;
  }

  public void setAccount(Account account) {

    this.account = account;
  }

  public PermissionGroupStatus getPermissionGroupStatus() {

    return permissionGroupStatus;
  }

  public void setPermissionGroupStatus(
      PermissionGroupStatus permissionGroupStatus) {

    this.permissionGroupStatus = permissionGroupStatus;
  }

  public Date getExpirationDate() {

    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {

    this.expirationDate = expirationDate;
  }
}
