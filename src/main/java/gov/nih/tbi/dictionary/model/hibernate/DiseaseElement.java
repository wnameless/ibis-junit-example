package gov.nih.tbi.dictionary.model.hibernate;

import gov.nih.tbi.commons.model.AbstractBricsObject;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "DISEASE_ELEMENT")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class DiseaseElement extends AbstractBricsObject implements Serializable {

  private static final long serialVersionUID = -7115713871006818272L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "DISEASE_ELEMENT_SEQ")
  @SequenceGenerator(name = "DISEASE_ELEMENT_SEQ",
      sequenceName = "DISEASE_ELEMENT_SEQ", allocationSize = 1)
  private Long id;

  @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "DISEASE_ID")
  private Disease disease;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "diseaseElement", targetEntity = DomainPair.class,
      orphanRemoval = true)
  private Set<DomainPair> domainList;

  @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "DATA_ELEMENT_ID")
  @XmlTransient
  private DataElement dataElement;

  /**********************************************************************/

  public DiseaseElement() {

  }

  public DiseaseElement(Disease disease, DataElement dataElement,
      Set<DomainPair> domainList) {

    this.disease = disease;
    this.dataElement = dataElement;
    this.domainList = domainList;
  }

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

  public Set<DomainPair> getDomainList() {

    return domainList;
  }

  public void setDomainList(Set<DomainPair> domainList) {

    this.domainList = domainList;
  }

  /**
   * Add a new domain and subdomain to the given disease
   * 
   * @param pair
   */
  public void addDomainPair(Domain domain, SubDomain subdomain) {

    if (this.domainList == null) {
      this.domainList = new LinkedHashSet<DomainPair>();
    }
    domainList.add(new DomainPair(this, domain, subdomain));
  }

  public DataElement getDataElement() {

    return dataElement;
  }

  public void setDataElement(DataElement dataElement) {

    this.dataElement = dataElement;
  }

  /**********************************************************************/

  @Override
  public String toString() {

    return "DiseaseElement [id=" + id + ", disease=" + disease + "]";
  }
}
