package gov.nih.tbi.dictionary.model.hibernate;

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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import gov.nih.tbi.commons.model.AbstractBricsObject;

@Entity
@Table(name = "KEYWORD_ELEMENT")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class KeywordElement extends AbstractBricsObject implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 686117090758090300L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "KEYWORD_ELEMENT_SEQ")
  @SequenceGenerator(name = "KEYWORD_ELEMENT_SEQ",
      sequenceName = "KEYWORD_ELEMENT_SEQ", allocationSize = 1)
  private Long id;

  @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "KEYWORD_ID")
  private Keyword keyword;

  @OneToOne
  @JoinColumn(name = "DATA_ELEMENT_ID")
  @XmlTransient
  private DataElement dataElement;

  /**********************************************************************/

  @Override
  public Long getId() {

    return id;
  }

  @Override
  public void setId(Long id) {

    this.id = id;
  }

  public Keyword getKeyword() {

    return keyword;
  }

  public void setKeyword(Keyword keyword) {

    this.keyword = keyword;
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

    return "KeywordElement [id=" + id + ", keyword=" + keyword + "]";
  }
}
