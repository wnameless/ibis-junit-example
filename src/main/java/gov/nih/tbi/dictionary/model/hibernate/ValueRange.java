package gov.nih.tbi.dictionary.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "VALUE_RANGE")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValueRange implements Serializable, Comparable<ValueRange> {

  /**
	 * 
	 */
  private static final long serialVersionUID = 8515876842821422224L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "VALUE_RANGE_SEQ")
  @SequenceGenerator(name = "VALUE_RANGE_SEQ",
      sequenceName = "VALUE_RANGE_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "VALUE_RANGE")
  private String valueRange;

  @XmlTransient
  @OneToOne
  @JoinColumn(name = "DATA_ELEMENT_ID")
  private DataElement dataElement;

  @Column(name = "DESCRIPTION")
  private String description;

  /**********************************************************************/

  public ValueRange() {

  }

  public ValueRange(Long id, String valueRange, String description,
      DataElement dataElement) {

    this.id = id;
    this.valueRange = valueRange;
    this.description = description;
    this.dataElement = dataElement;
  }

  /**********************************************************************/

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getValueRange() {

    return valueRange;
  }

  public void setValueRange(String valueRange) {

    this.valueRange = valueRange;
  }

  public DataElement getDataElement() {

    return dataElement;
  }

  public void setDataElement(DataElement dataElement) {

    this.dataElement = dataElement;
  }

  public String getDescription() {

    return description;
  }

  public void setDescription(String description) {

    this.description = description;
  }

  public String toString() {

    return valueRange + " - " + description;
  }

  /**********************************************************************/

  @Override
  public int compareTo(ValueRange v1) {

    if (v1 == null) {
      return 1;
    }
    if (this.getValueRange() == null) {
      return -1;
    }
    if (v1.getValueRange() == null) {
      return 1;
    }
    return this.getValueRange().compareToIgnoreCase(v1.getValueRange());
  }
}
