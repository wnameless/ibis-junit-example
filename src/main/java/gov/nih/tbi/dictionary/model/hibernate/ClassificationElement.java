package gov.nih.tbi.dictionary.model.hibernate;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlType;

/**
 * Model for classification_element
 * 
 * @author Michael Valeiras
 * 
 */
@Entity
@Table(name = "CLASSIFICATION_ELEMENT")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassificationElement implements Serializable,
    Comparable<ClassificationElement> {

  private static final long serialVersionUID = 3384613692285967810L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "CLASSIFICATION_ELEMENT_SEQ")
  @SequenceGenerator(name = "CLASSIFICATION_ELEMENT_SEQ",
      sequenceName = "CLASSIFICATION_ELEMENT_SEQ", allocationSize = 1)
  private Long id;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "CLASSIFICATION_ID")
  private Classification classification;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "SUBGROUP_ID")
  private Subgroup subgroup;

  @XmlTransient
  @ManyToOne(targetEntity = DataElement.class)
  @JoinColumn(name = "DATA_ELEMENT_ID")
  private DataElement dataElement;

  public ClassificationElement() {

  }

  public ClassificationElement(Long id, Classification classification,
      Subgroup subgroup, DataElement dataElement) {

    this.id = id;
    this.classification = classification;
    this.subgroup = subgroup;
    this.dataElement = dataElement;
  }

  /**********************************************************************/

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public Classification getClassification() {

    return classification;
  }

  public void setClassification(Classification classification) {

    this.classification = classification;
  }

  public Subgroup getSubgroup() {

    return subgroup;
  }

  public void setSubgroup(Subgroup subgroup) {

    this.subgroup = subgroup;
  }

  public DataElement getDataElement() {

    return dataElement;
  }

  public void setDataElement(DataElement dataElement) {

    this.dataElement = dataElement;
  }

  public String toString() {

    return "[ID:" + id + ", Classification: " + classification + ", Subgroup: "
        + subgroup + "]";
  }

  /**********************************************************************/

  @Override
  public int compareTo(ClassificationElement o) {
    return this.getSubgroup().getSubgroupName()
        .compareTo(o.getSubgroup().getSubgroupName());
  }
}
