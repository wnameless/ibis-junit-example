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
 * This is the model for Clinical Trial
 * 
 * @author Francis Chen
 */
@Entity
@Table(name = "CLINICAL_TRIAL")
public class ClinicalTrial implements Serializable {

  private static final long serialVersionUID = 8471844357829883956L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "CLINICAL_TRIAL_SEQ")
  @SequenceGenerator(name = "CLINICAL_TRIAL_SEQ",
      sequenceName = "CLINICAL_TRIAL_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "CLINICAL_TRIAL_ID")
  private String clinicalTrialId;

  @ManyToOne
  @JoinColumn(name = "STUDY_ID")
  private Study study;

  public Long getId() {

    return id;
  }

  public String getClinicalTrialId() {

    return clinicalTrialId;
  }

  public Study getStudy() {

    return study;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public void setClinicalTrialId(String clinicalTrialId) {

    this.clinicalTrialId = clinicalTrialId;
  }

  public void setStudy(Study study) {

    this.study = study;
  }
}
