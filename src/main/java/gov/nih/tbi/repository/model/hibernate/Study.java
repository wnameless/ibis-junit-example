package gov.nih.tbi.repository.model.hibernate;

import gov.nih.tbi.commons.model.RecruitmentStatus;
import gov.nih.tbi.commons.model.StudyStatus;
import gov.nih.tbi.repository.model.AbstractStudy;
import gov.nih.tbi.repository.model.SubmissionType;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Model for storing studies
 * 
 * @author Francis Chen
 */
@Entity
@Table(name = "STUDY")
@XmlRootElement(name = "study")
@XmlAccessorType(XmlAccessType.FIELD)
public class Study extends AbstractStudy implements Serializable {

  private static final long serialVersionUID = -3963107794602437604L;

  public Study() {

    clinicalTrialSet = new HashSet<ClinicalTrial>();
    grantSet = new HashSet<Grant>();
    supportingDocumentationSet = new HashSet<SupportingDocumentation>();
    datasetSet = new ArrayList<Dataset>();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDY_SEQ")
  @SequenceGenerator(name = "STUDY_SEQ", sequenceName = "STUDY_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "PREFIX_ID")
  private String prefixedId;

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

  @XmlTransient
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "study", targetEntity = ClinicalTrial.class,
      orphanRemoval = true)
  private Set<ClinicalTrial> clinicalTrialSet;

  @XmlTransient
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "study", targetEntity = Grant.class, orphanRemoval = true)
  private Set<Grant> grantSet;

  @XmlTransient
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "study", targetEntity = SupportingDocumentation.class,
      orphanRemoval = true)
  private Set<SupportingDocumentation> supportingDocumentationSet;

  @XmlTransient
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "study", targetEntity = Dataset.class, orphanRemoval = true)
  @javax.persistence.OrderBy("submitDate")
  private List<Dataset> datasetSet;

  @Column(name = "DATE_CREATED")
  private Date dateCreated;

  @Column(name = "STUDY_STARTED")
  private Date studyStartDate;

  @Column(name = "STUDY_ENDED")
  private Date studyEndDate;

  @OneToOne
  @JoinColumn(name = "DATA_SUBMISSION_DOCUMENT_ID")
  @XmlTransient
  private UserFile dataSubmissionDocument;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "STUDY_STATUS_ID")
  private StudyStatus studyStatus;

  private static SimpleDateFormat isoFormatting = new SimpleDateFormat(
      "yyyy-MM-dd");

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

  public Set<ClinicalTrial> getClinicalTrialSet() {

    return clinicalTrialSet;
  }

  public Set<Grant> getGrantSet() {

    return grantSet;
  }

  public Set<SupportingDocumentation> getSupportingDocumentationSet() {

    return supportingDocumentationSet;
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

  public void setClinicalTrialSet(Set<ClinicalTrial> clinicalTrialSet) {

    if (this.clinicalTrialSet == null) {
      this.clinicalTrialSet = new HashSet<ClinicalTrial>();
    }

    this.clinicalTrialSet.clear();

    if (clinicalTrialSet != null) {
      this.clinicalTrialSet.addAll(clinicalTrialSet);
    }
  }

  // public void setGrantSet(Set<Grant> grantSet) {
  // if(this.grantSet == null) {
  // this.grantSet = new HashSet<Grant> ();
  // }
  //
  // this.grantSet.clear();
  //
  // if(grantSet != null) {
  // this.grantSet.addAll(grantSet);
  // }
  // }

  // public void setSupportingDocumentationSet(
  // Set<SupportingDocumentation> supportingDocumentationSet) {
  // if(this.supportingDocumentationSet == null) {
  // this.supportingDocumentationSet = new HashSet<SupportingDocumentation> ();
  // }
  //
  // this.supportingDocumentationSet.clear();
  //
  // if(supportingDocumentationSet != null) {
  // this.supportingDocumentationSet.addAll(supportingDocumentationSet);
  // }
  // }

  public Date getDateCreated() {

    return dateCreated;
  }

  public String getDateCreatedIso() {

    return isoFormatting.format(getDateCreated());
  }

  public void setDateCreated(String dateCreated) {

    try {
      Date date = isoFormatting.parse(dateCreated);
      setDateCreated(date);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

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

  public Date getStudyStartDate() {

    return studyStartDate;
  }

  public String getStartDate() {

    Date startDate = getStudyStartDate();
    if (startDate == null) {
      return "";
    }
    StringBuilder date = new StringBuilder(isoFormatting.format(startDate));
    return date.toString();
  }

  public void setStudyStartDate(Date studyStartDate) {

    this.studyStartDate = studyStartDate;
  }

  public void setStudyStartDate(String studyStartDate) {

    try {
      Date date = isoFormatting.parse(studyStartDate);
      setStudyStartDate(date);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public Date getStudyEndDate() {

    return studyEndDate;
  }

  public String getEndDate() {

    Date endDate = getStudyEndDate();
    if (endDate == null) {
      return "";
    }
    StringBuilder date = new StringBuilder(isoFormatting.format(endDate));
    return date.toString();
  }

  public void setStudyEndDate(Date studyEndDate) {

    this.studyEndDate = studyEndDate;
  }

  public void setStudyEndDate(String studyEndDate) {

    try {
      Date date = isoFormatting.parse(studyEndDate);
      setStudyEndDate(date);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void setDataManagerEmail(String dataManagerEmail) {

    this.dataManagerEmail = dataManagerEmail;
  }

  public UserFile getDataSubmissionDocument() {

    return dataSubmissionDocument;
  }

  public void setDataSubmissionDocument(UserFile dataSubmissionDocument) {

    this.dataSubmissionDocument = dataSubmissionDocument;
  }

  public StudyStatus getStudyStatus() {

    return studyStatus;
  }

  public void setStudyStatus(StudyStatus studyStatus) {

    this.studyStatus = studyStatus;
  }

  public boolean getIsPrivate() {

    return StudyStatus.PRIVATE.equals(studyStatus);
  }

  public boolean getIsPublic() {

    return StudyStatus.PUBLIC.equals(studyStatus);
  }

  public List<Dataset> getDatasetSet() {

    return datasetSet;
  }

  public void setDatasetSet(List<Dataset> datasetSet) {

    if (this.datasetSet == null) {
      this.datasetSet = new ArrayList<Dataset>();
    }

    this.datasetSet.clear();

    if (datasetSet != null) {
      this.datasetSet.addAll(datasetSet);
    }
  }

  /**
   * Returns a set submission types by puling the type of each datasetFile
   * within a dataset contained in this study.
   * 
   * @return : The set of submission types contained in this study
   */
  public Set<SubmissionType> getSubmissionTypes() {

    Set<SubmissionType> returnSet = new HashSet<SubmissionType>();

    for (Dataset d : datasetSet) {
      for (DatasetFile df : d.getDatasetFileSet()) {
        returnSet.add(df.getFileType());
      }
    }

    return returnSet;
  }

  /**
   * Returns true if this study contains a genomic file in a dataset, false
   * otherwise
   * 
   * @return
   */
  public boolean getIsGenomic() {

    return getSubmissionTypes().contains(SubmissionType.GENOMICS);
  }

  /**
   * Returns true if this study contains a clinical file in a dataset, false
   * otherwise
   * 
   * @return
   */
  public boolean getIsClinical() {

    return getSubmissionTypes().contains(SubmissionType.CLINICAL);
  }

  /**
   * Returns true if this study contains a imaging file in a dataset, false
   * otherwise
   * 
   * @return
   */
  public boolean getIsImaging() {

    return getSubmissionTypes().contains(SubmissionType.IMAGING);
  }

  public String getPrefixedId() {

    return prefixedId;
  }

  public void setPrefixedId(String prefixedId) {

    this.prefixedId = prefixedId;
  }
}
