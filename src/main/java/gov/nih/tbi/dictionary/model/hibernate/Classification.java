package gov.nih.tbi.dictionary.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Model for classification
 * 
 * @author Francis Chen
 * 
 */
@Entity
@Table(name = "CLASSIFICATION")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Classification implements Serializable {

  private static final long serialVersionUID = -1018721506010883157L;

  /**********************************************************************/

  @Id
  private Long id;

  @Column(name = "CLASSIFICATION_NAME")
  private String name;

  @Column(name = "IS_ACTIVE")
  private Boolean isActive;

  @Column(name = "CAN_CREATE")
  private Boolean canCreate;

  public Classification() {

  }

  public Classification(Long id, String name, Boolean isActive,
      Boolean canCreate) {

    this.id = id;
    this.name = name;
    this.isActive = isActive;
    this.canCreate = canCreate;
  }

  /**********************************************************************/

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public Boolean getIsActive() {

    return isActive;
  }

  public void setIsActive(Boolean isActive) {

    this.isActive = isActive;
  }

  public Boolean getCanCreate() {

    return canCreate;
  }

  public void setCanCreate(Boolean canCreate) {

    this.canCreate = canCreate;
  }

  public String toString() {

    return "[ID:" + id + ", Name: " + name + ", isActive: " + isActive
        + ", canCreate: " + canCreate + "]";
  }
  /**********************************************************************/

}
