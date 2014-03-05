package gov.nih.tbi.account.model.hibernate;

import gov.nih.tbi.ModelConstants;
import gov.nih.tbi.commons.model.AbstractBricsObject;
import gov.nih.tbi.commons.model.RoleStatus;
import gov.nih.tbi.commons.model.RoleType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "ACCOUNT_ROLE")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountRole extends AbstractBricsObject implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = -9001636980907801431L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "ACCOUNT_ROLE_SEQ")
  @SequenceGenerator(name = "ACCOUNT_ROLE_SEQ",
      sequenceName = "ACCOUNT_ROLE_SEQ", allocationSize = 1)
  private Long id;

  @XmlTransient
  @ManyToOne
  @JoinColumn(name = "ACCOUNT_ID")
  private Account account;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "ROLE_TYPE_ID")
  private RoleType roleType;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "ROLE_STATUS_ID")
  private RoleStatus roleStatus;

  @Column(name = "EXPIRATION_DATE")
  private Date expirationDate;

  /**********************************************************************/

  public AccountRole() {

  }

  public AccountRole(Account account, RoleType roleType, RoleStatus roleStatus,
      Date expirationDate) {

    this.account = account;
    this.roleType = roleType;
    this.roleStatus = roleStatus;
    this.expirationDate = expirationDate;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public Account getAccount() {

    return account;
  }

  public void setAccount(Account account) {

    this.account = account;
  }

  public RoleType getRoleType() {

    return roleType;
  }

  public void setRoleType(RoleType roleType) {

    this.roleType = roleType;
  }

  public RoleStatus getRoleStatus() {

    return roleStatus;
  }

  public void setRoleStatus(RoleStatus roleStatus) {

    this.roleStatus = roleStatus;
  }

  public Date getExpirationDate() {

    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {

    this.expirationDate = expirationDate;
  }

  /**
   * Returns the expirationDate as a string formatted dd-MMM-yyyy
   * 
   * @return
   */
  public String getExpirationString() {

    if (expirationDate == null) {
      return "-";
    }
    SimpleDateFormat df =
        new SimpleDateFormat(ModelConstants.UNIVERSAL_DATE_FORMAT);

    return df.format(expirationDate);
  }

  public boolean isExpired() {

    Date now = new Date();
    if (expirationDate != null) {
      return now.after(expirationDate);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {

    return "[" + getRoleType() + ", " + getRoleStatus() + ", "
        + getExpirationString() + "]";
  }
}
