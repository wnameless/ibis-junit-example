package gov.nih.tbi.repository.model.hibernate;

import gov.nih.tbi.commons.model.RecruitmentStatus;
import gov.nih.tbi.commons.model.StudyStatus;
import gov.nih.tbi.repository.model.AbstractStudy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A model for study without any Many to One relationships for proper searching
 * and and listing of studies.
 * 
 * @author mvalei
 */
@Entity
@Table(name = "STUDY")
@XmlRootElement(name = "BasicStudy")
public class BasicStudy extends AbstractStudy implements Serializable {

  private static final String BASIC_STUDY =
      "This operation is unsupported for a BasicStudy.";

  private static final long serialVersionUID = -3963107794602437604L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDY_SEQ")
  @SequenceGenerator(name = "STUDY_SEQ", sequenceName = "STUDY_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "TITLE")
  private String title;

  // abstract is reserved in java
  @Column(name = "ABSTRACT")
  private String abstractText;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "RECRUITMENT_STATUS_ID")
  private RecruitmentStatus recruitmentStatus;

  @Column(name = "PRINCIPAL_INVESTIGATOR")
  private String principalInvestigator;

  @Column(name = "PRINCIPAL_INVESTIGATOR_EMAIL")
  private String principalInvestigatorEmail;

  @Column(name = "DATA_MANAGER")
  private String dataManager;

  @Column(name = "DATA_MANAGER_EMAIL")
  private String dataManagerEmail;

  @Column(name = "PREFIX_ID")
  private String prefixedId;

  @Column(name = "DATE_CREATED")
  private Date dateCreated;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "STUDY_STATUS_ID")
  private StudyStatus studyStatus;

  public Long getId() {

    return id;
  }

  public String getTitle() {

    return title;
  }

  public String getAbstractText() {

    return abstractText;
  }

  public RecruitmentStatus getRecruitmentStatus() {

    return recruitmentStatus;
  }

  public String getPrincipalInvestigator() {

    return principalInvestigator;
  }

  public String getDataManager() {

    return dataManager;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public void setTitle(String title) {

    this.title = title;
  }

  public void setAbstractText(String abstractText) {

    this.abstractText = abstractText;
  }

  public void setRecruitmentStatus(RecruitmentStatus recruitmentStatus) {

    this.recruitmentStatus = recruitmentStatus;
  }

  public void setPrincipalInvestigator(String principalInvestigator) {

    this.principalInvestigator = principalInvestigator;
  }

  public void setDataManager(String dataManager) {

    this.dataManager = dataManager;
  }

  public Date getDateCreated() {

    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {

    this.dateCreated = dateCreated;
  }

  public String getPrincipalInvestigatorEmail() {

    return principalInvestigatorEmail;
  }

  public String getDataManagerEmail() {

    return dataManagerEmail;
  }

  public void setPrincipalInvestigatorEmail(String principalInvestigatorEmail) {

    this.principalInvestigatorEmail = principalInvestigatorEmail;
  }

  public void setDataManagerEmail(String dataManagerEmail) {

    this.dataManagerEmail = dataManagerEmail;
  }

  public StudyStatus getStudyStatus() {

    return studyStatus;
  }

  public void setStudyStatus(StudyStatus studyStatus) {

    this.studyStatus = studyStatus;
  }

  public String getPrefixedId() {

    return prefixedId;
  }

  public void setPrefixedId(String prefixedId) {

    this.prefixedId = prefixedId;
  }

  public boolean getIsPrivate() {

    return StudyStatus.PRIVATE.equals(studyStatus);
  }

  public boolean getIsPublic() {

    return StudyStatus.PUBLIC.equals(studyStatus);
  }

  public boolean getIsGenomic() {

    throw new UnsupportedOperationException(BasicStudy.BASIC_STUDY);
  }

  public boolean getIsImaging() {

    throw new UnsupportedOperationException(BasicStudy.BASIC_STUDY);
  }

  public boolean getIsClinical() {

    throw new UnsupportedOperationException(BasicStudy.BASIC_STUDY);
  }

}
