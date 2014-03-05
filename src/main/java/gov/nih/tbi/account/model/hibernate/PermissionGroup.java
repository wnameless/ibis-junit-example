package gov.nih.tbi.account.model.hibernate;

import gov.nih.tbi.account.model.PermissionAuthority;
import gov.nih.tbi.commons.model.AbstractBricsObject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "PERMISSION_GROUP")
@XmlRootElement(name = "permissionGroup")
@XmlAccessorType(XmlAccessType.FIELD)
public class PermissionGroup extends AbstractBricsObject implements
    PermissionAuthority, Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = -5298242282060166275L;

  public static final String PUBLIC_STATUS = "publicStatus";
  public static final String GROUP_NAME = "groupName";

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "TBI_USER_SEQ")
  @SequenceGenerator(name = "TBI_USER_SEQ", sequenceName = "TBI_USER_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "GROUP_NAME")
  private String groupName;

  @Column(name = "GROUP_DESCRIPTION")
  private String groupDescription;

  @Column(name = "PUBLIC_STATUS")
  private Boolean publicStatus;

  @XmlTransient
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      mappedBy = "permissionGroup", targetEntity = PermissionGroupMember.class,
      orphanRemoval = true)
  private Set<PermissionGroupMember> memberSet;

  @XmlTransient
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      mappedBy = "permissionGroup", targetEntity = EntityMap.class,
      orphanRemoval = true)
  private Set<EntityMap> entityMapSet;

  @Transient
  private String diseaseKey = "";

  /**********************************************************************/

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

  public Set<PermissionGroupMember> getMemberSet() {

    if (memberSet == null) {
      return new HashSet<PermissionGroupMember>();
    }

    return memberSet;
  }

  public void setMemberSet(Set<PermissionGroupMember> memberSet) {

    this.memberSet = memberSet;
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

  public Set<EntityMap> getEntityMapSet() {

    return entityMapSet;
  }

  public void setEntityMapSet(Set<EntityMap> entityMapSet) {

    this.entityMapSet = entityMapSet;
  }

  /**
   * Overrides display name for use in permission pages
   */
  public String getDisplayName() {

    return this.getGroupName();
  }

  /**
   * Overrides display key for use in permission pages
   */
  public String getDisplayKey() {

    return "PERMISSION_GROUP:" + getDiseaseKey() + ";" + getId();
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
    result =
        prime * result
            + ((groupDescription == null) ? 0 : groupDescription.hashCode());
    result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result =
        prime * result + ((publicStatus == null) ? 0 : publicStatus.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj instanceof PermissionGroup) {
      PermissionGroup group = (PermissionGroup) obj;

      if (group.getGroupName().equals(this.getGroupName())) {
        return true;
      }
    }

    return super.equals(obj);
  }

}
