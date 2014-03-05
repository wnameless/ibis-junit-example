package gov.nih.tbi.dictionary.model.hibernate;

import gov.nih.tbi.commons.model.AbstractBricsObject;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "DISEASE_STRUCTURE")
@XmlRootElement(name = "diseaseStructure")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
public class DiseaseStructure extends AbstractBricsObject implements
    Serializable {

  private static final long serialVersionUID = 1L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "DISEASE_STRUCTURE_SEQ")
  @SequenceGenerator(name = "DISEASE_STRUCTURE_SEQ",
      sequenceName = "DISEASE_STRUCTURE_SEQ", allocationSize = 1)
  private Long id;

  @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "DISEASE_ID")
  private Disease disease;

  @OneToOne
  @JoinColumn(name = "DATA_STRUCTURE_ID")
  @XmlTransient
  private DataStructure dataStructure;

  public DiseaseStructure() {

  }

  public DiseaseStructure(Disease disease, DataStructure dataStructure) {

    this.disease = disease;
    this.dataStructure = dataStructure;
  }

  /**********************************************************************/

  @Override
  public Long getId() {

    return id;
  }

  @Override
  public void setId(Long id) {

    this.id = id;
  }

  public Disease getDisease() {

    return disease;
  }

  public void setDisease(Disease disease) {

    this.disease = disease;
  }

  public DataStructure getDataStructure() {

    return dataStructure;
  }

  public void setDataStructure(DataStructure dataStructure) {

    this.dataStructure = dataStructure;
  }

  /**********************************************************************/

  @Override
  public String toString() {

    return "DiseaseStructure [id=" + id + ", disease=" + disease + "]";
  }
}
