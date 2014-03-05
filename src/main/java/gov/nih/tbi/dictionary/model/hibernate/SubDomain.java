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
 * Model for sub-domain
 * 
 * @author Francis Chen
 * 
 */
@Entity
@Table(name = "SUB_DOMAIN")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubDomain implements Serializable, Comparable<SubDomain> {

  private static final long serialVersionUID = 2558321553252251145L;

  /**********************************************************************/

  @Id
  private Long id;

  @Column(name = "SUB_DOMAIN_NAME")
  private String name;

  @Column(name = "IS_ACTIVE")
  private Boolean isActive;

  public SubDomain() {

  }

  public SubDomain(Long id, String name) {

    this.id = id;
    this.name = name;
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

  @Override
  public int compareTo(SubDomain s1) {
    return this.getName().compareTo(s1.getName());
  }
}
