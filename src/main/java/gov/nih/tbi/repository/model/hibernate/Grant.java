package gov.nih.tbi.repository.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This is the model for Grant
 * 
 * @author Francis Chen
 */
@Entity
@Table(name = "GRANT_TABLE")
public class Grant implements Serializable {

  private static final long serialVersionUID = 5585560064594270704L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "GRANT_TABLE_SEQ")
  @SequenceGenerator(name = "GRANT_TABLE_SEQ",
      sequenceName = "GRANT_TABLE_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "GRANT_ID")
  private String grantId;

  @ManyToOne
  @JoinColumn(name = "STUDY_ID")
  private Study study;

  public Long getId() {

    return id;
  }

  public String getGrantId() {

    return grantId;
  }

  public Study getStudy() {

    return study;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public void setGrantId(String grantId) {

    this.grantId = grantId;
  }

  public void setStudy(Study study) {

    this.study = study;
  }
}
