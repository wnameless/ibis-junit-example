package gov.nih.tbi.dictionary.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import gov.nih.tbi.commons.model.AbstractBricsObject;

@Entity
@Table(name = "KEYWORD")
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Keyword extends AbstractBricsObject implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = -1047590153446644157L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KEYWORD_SEQ")
  @SequenceGenerator(name = "KEYWORD_SEQ", sequenceName = "KEYWORD_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "KEYWORD_NAME")
  private String keyword;

  @Column(name = "COUNT")
  private Long count;

  /**********************************************************************/

  @Override
  public Long getId() {

    return id;
  }

  @Override
  public void setId(Long id) {

    this.id = id;
  }

  public String getKeyword() {

    return keyword;
  }

  public void setKeyword(String keyword) {

    this.keyword = keyword;
  }

  public Long getCount() {

    return count;
  }

  public void setCount(Long count) {

    this.count = count;
  }

  /**********************************************************************/

  @Override
  public String toString() {

    return "Keyword [id=" + id + ", keyword=" + keyword + ", count=" + count
        + "]";
  }

  @Override
  public boolean equals(Object obj) {

    if (obj instanceof Keyword) {
      Keyword tempKeyword = (Keyword) obj;

      if ((tempKeyword.getId() != null && id != null && id.equals(tempKeyword
          .getId()))
          || (tempKeyword.getKeyword() != null && keyword != null && keyword
              .equals(tempKeyword.getKeyword()))) {
        return true;
      }
    }

    return false;
  }

}
