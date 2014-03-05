package gov.nih.tbi.dictionary.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "DISEASE")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Disease implements Serializable {

  private static final long serialVersionUID = -412275640270465798L;

  /**********************************************************************/

  @Id
  private Long id;

  @Column(name = "DISEASE_NAME")
  private String name;

  @Column(name = "IS_ACTIVE")
  private Boolean isActive;

  public Disease() {

  }

  public Disease(Long id, String name, String prefix, Boolean isActive) {

    super();
    this.id = id;
    this.name = name;
    this.isActive = isActive;
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

  public String toString() {

    return "[ID:" + id + ", Name: " + name + ", isActive: " + isActive + "]";
  }
  /**********************************************************************/

}
