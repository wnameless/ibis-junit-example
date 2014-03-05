package gov.nih.tbi.dictionary.model.hibernate;

import gov.nih.tbi.commons.model.ExternalType;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * This object stores Data Element External IDs
 * 
 * @author Francis Chen
 */
@Entity
@Table(name = "DATA_ELEMENT_EXTERNAL_ID")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalId implements Serializable {

  private static final long serialVersionUID = 1736785038166589234L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "DATA_ELEMENT_EXTERNAL_ID_SEQ")
  @SequenceGenerator(name = "DATA_ELEMENT_EXTERNAL_ID_SEQ",
      sequenceName = "DATA_ELEMENT_EXTERNAL_ID_SEQ", allocationSize = 1)
  private Long id;

  @OneToOne
  @JoinColumn(name = "DATA_ELEMENT_ID")
  @XmlTransient
  private DataElement dataElement;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "EXTERNAL_TYPE_ID")
  private ExternalType type;

  @Column(name = "EXTERNAL_ID")
  private String value;

  public ExternalId() {

  }

  public ExternalId(DataElement dataElement, ExternalType type, String value) {

    this.dataElement = dataElement;
    this.type = type;
    this.value = value;
  }

  public ExternalId(ExternalId externalId) {

    this.dataElement = externalId.getDataElement();
    this.type = externalId.getType();
    this.value = externalId.getValue();
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public DataElement getDeId() {

    return dataElement;
  }

  public void setDeId(DataElement dataElement) {

    this.dataElement = dataElement;
  }

  public ExternalType getType() {

    return type;
  }

  public void setType(ExternalType type) {

    this.type = type;
  }

  public String getValue() {

    return value;
  }

  public void setValue(String value) {

    this.value = value;
  }

  public DataElement getDataElement() {

    return dataElement;
  }

  public void setDataElement(DataElement dataElement) {

    this.dataElement = dataElement;
  }
}
