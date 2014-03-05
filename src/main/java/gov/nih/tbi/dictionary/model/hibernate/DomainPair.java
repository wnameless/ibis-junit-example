package gov.nih.tbi.dictionary.model.hibernate;

import gov.nih.tbi.commons.model.AbstractBricsObject;

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
 * DomainPair: Represents a legal pairing of a domain/subdomain that is placed
 * in a given subgroup. A data_element maintains a list of these pairings and
 * must have at least one for every subgroup for every disease that data element
 * is a part of.
 * 
 * @author mvalei
 * 
 */
@Entity
@Table(name = "DOMAIN_PAIR")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class DomainPair extends AbstractBricsObject implements Serializable {

  private static final long serialVersionUID = 5956377029164425372L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "DOMAIN_PAIR_SEQ")
  @SequenceGenerator(name = "DOMAIN_PAIR_SEQ",
      sequenceName = "DOMAIN_PAIR_SEQ", allocationSize = 1)
  private Long id;

  @XmlTransient
  @ManyToOne(targetEntity = DiseaseElement.class)
  @JoinColumn(name = "DISEASE_ELEMENT_ID")
  private DiseaseElement diseaseElement;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "DOMAIN_ID")
  private Domain domain;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "SUB_DOMAIN_ID")
  private SubDomain subDomain;

  /**********************************************************************/

  public DomainPair() {

  }

  public DomainPair(DiseaseElement diseaseElement, Domain domain,
      SubDomain subDomain) {

    this.diseaseElement = diseaseElement;
    this.domain = domain;
    this.subDomain = subDomain;
  }

  @Override
  public Long getId() {

    return id;
  }

  @Override
  public void setId(Long id) {

    this.id = id;
  }

  public Domain getDomain() {

    return domain;
  }

  public void setDomain(Domain domain) {

    this.domain = domain;
  }

  public SubDomain getSubdomain() {

    return subDomain;
  }

  public void setSubdomain(SubDomain subDomain) {

    this.subDomain = subDomain;
  }

  public DiseaseElement getDiseaseElement() {

    return diseaseElement;
  }

  public void setDiseaseElement(DiseaseElement diseaseElement) {

    this.diseaseElement = diseaseElement;
  }

  /**********************************************************************/

  @Override
  public String toString() {

    return "DomainPair [id=" + id + ", domain=" + domain + ", subDomain="
        + subDomain + "]";
  }

}
