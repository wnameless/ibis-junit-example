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
 * Model for subgroups
 * 
 * @author Michael Valeiras
 * 
 */
@Entity
@Table(name = "SUBGROUP")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subgroup implements Serializable, Comparable<Subgroup> {

  private static final long serialVersionUID = 8171282185003817028L;

  /**********************************************************************/

  @Id
  private Long id;

  @Column(name = "SUBGROUP_NAME")
  private String subgroupName;

  public Subgroup() {

  }

  public Subgroup(Long id, String subgroupName) {

    this.id = id;
    this.subgroupName = subgroupName;
  }

  /**********************************************************************/

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getSubgroupName() {

    return subgroupName;
  }

  public void setName(String subgroupName) {

    this.subgroupName = subgroupName;
  }

  public String toString() {

    return "Subgroup[ID:" + id + ", Name: " + subgroupName + "]";
  }

  /**********************************************************************/

  @Override
  public int compareTo(Subgroup s1) {
    return this.getSubgroupName().compareTo(s1.getSubgroupName());
  }

}
