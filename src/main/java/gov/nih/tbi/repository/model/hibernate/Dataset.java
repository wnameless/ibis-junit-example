package gov.nih.tbi.repository.model.hibernate;

import gov.nih.tbi.ModelConstants;
import gov.nih.tbi.commons.model.DatasetFileStatus;
import gov.nih.tbi.commons.model.DatasetStatus;
import gov.nih.tbi.commons.model.hibernate.User;
import gov.nih.tbi.repository.model.AbstractDataset;
import gov.nih.tbi.repository.model.SubmissionType;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Data model for Datasets
 * 
 * @author Francis Chen
 * 
 */
@Entity
@Table(name = "DATASET")
@XmlRootElement(name = "dataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dataset extends AbstractDataset implements Serializable {

  private static final long serialVersionUID = -2627365259205294466L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DATASET_SEQ")
  @SequenceGenerator(name = "DATASET_SEQ", sequenceName = "DATASET_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @ManyToOne
  @JoinColumn(name = "SUBMITTER_ID")
  private User submitter;

  @Column(name = "SUBMIT_DATE")
  private Date submitDate;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "DATASET_STATUS_ID")
  private DatasetStatus datasetStatus;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "DATASET_REQUEST_STATUS_ID")
  private DatasetStatus datasetRequestStatus;

  @Column(name = "PREFIX_ID")
  private String prefixedId;

  @ManyToOne
  @JoinColumn(name = "STUDY_ID")
  private BasicStudy study;

  @ManyToOne
  @JoinColumn(name = "REVIEWER_ID")
  private User reviewer;

  @ManyToOne
  @JoinColumn(name = "VERIFIER_ID")
  private User verifier;

  @Column(name = "PUBLICATION_DATE")
  private Date publicationDate;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataset", targetEntity = DatasetFile.class,
      orphanRemoval = true)
  private Set<DatasetFile> datasetFileSet;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataset", targetEntity = DatasetDataStructure.class,
      orphanRemoval = true)
  @XmlTransient
  private Set<DatasetDataStructure> datasetDataStructure;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      mappedBy = "dataset", targetEntity = DatasetSubject.class,
      orphanRemoval = true)
  @XmlTransient
  private Set<DatasetSubject> datasetSubject;

  //
  // @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
  // "dataset", targetEntity =
  // UserDownloadQueue.class, orphanRemoval = true)
  // @XmlTransient
  // private Set<UserDownloadQueue> userDownloadQueue;

  public Dataset() {

    datasetFileSet = new HashSet<DatasetFile>();
    datasetDataStructure = new HashSet<DatasetDataStructure>();
    datasetSubject = new HashSet<DatasetSubject>();
    // userDownloadQueue = new HashSet<UserDownloadQueue> ();
  }

  public Long getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public User getSubmitter() {

    return submitter;
  }

  public Date getSubmitDate() {

    return submitDate;
  }

  public DatasetStatus getDatasetStatus() {

    // if(DatasetStatus.UPLOADING.equals(datasetStatus)) {
    // if(this.getPendingDatasetFile().isEmpty()) {
    // datasetStatus = DatasetStatus.LOADING;
    // }
    // }

    return datasetStatus;
  }

  public String getPrefixedId() {

    return prefixedId;
  }

  public void setPrefixedId(String prefixedId) {

    this.prefixedId = prefixedId;
  }

  public BasicStudy getStudy() {

    return study;
  }

  public Set<DatasetFile> getDatasetFileSet() {

    return datasetFileSet;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public void setName(String name) {

    this.name = name;
  }

  public void setSubmitter(User submitter) {

    this.submitter = submitter;
  }

  public void setSubmitDate(Date submitDate) {

    this.submitDate = submitDate;
  }

  public void setDatasetStatus(DatasetStatus datasetStatus) {

    this.datasetStatus = datasetStatus;
  }

  public void setStudy(BasicStudy study) {

    this.study = study;
  }

  public void setDatasetFileSet(Set<DatasetFile> datasetFileSet) {

    if (this.datasetFileSet == null) {
      this.datasetFileSet = new HashSet<DatasetFile>();
    }

    this.datasetFileSet.clear();

    if (datasetFileSet != null) {
      this.datasetFileSet.addAll(datasetFileSet);
    }
  }

  public Date getPublicationDate() {

    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) {

    this.publicationDate = publicationDate;
  }

  public DatasetStatus getDatasetRequestStatus() {

    return datasetRequestStatus;
  }

  public void setDatasetRequestStatus(DatasetStatus datasetRequestStatus) {

    this.datasetRequestStatus = datasetRequestStatus;
  }

  /**
   * Returns the file types in this dataset as list of FileTypes
   * 
   * @return
   */
  public List<SubmissionType> getFileTypeList() {

    List<SubmissionType> out = new ArrayList<SubmissionType>();

    for (DatasetFile file : this.datasetFileSet) {
      if (file.getFileType() != null && !out.contains(file.getFileType())) {
        out.add(file.getFileType());
      }
    }

    return out;
  }

  /**
   * Returns the list of file types in this dataset as a single comma separated
   * list ie- "Genomics, Clinical Assessment, Imaging"
   * 
   * @return
   */
  public String getFileTypeString() {

    List<SubmissionType> fileTypeList = this.getFileTypeList();
    String out = ModelConstants.EMPTY_STRING;

    if (fileTypeList != null) {
      for (int i = 0; i < fileTypeList.size(); i++) {
        if (!SubmissionType.DATA_FILE.equals(fileTypeList.get(i))) {
          if (!ModelConstants.EMPTY_STRING.equals(out)) {
            out += ", ";
          }

          out += fileTypeList.get(i).getType();
        }
      }
    }

    return out;
  }

  public User getReviewer() {

    return reviewer;
  }

  public User getVerifier() {

    return verifier;
  }

  public void setReviewer(User reviewer) {

    this.reviewer = reviewer;
  }

  public void setVerifier(User verifier) {

    this.verifier = verifier;
  }

  public List<DatasetFile> getPendingDatasetFile() {

    List<DatasetFile> fileList = new ArrayList<DatasetFile>();
    for (DatasetFile datasetFile : this.datasetFileSet) {
      if (DatasetFileStatus.PENDING.equals(datasetFile.getDatasetFileStatus())) {
        fileList.add(datasetFile);
      }
    }
    return fileList;
  }

  public Set<DatasetDataStructure> getDatasetDataStructure() {

    return datasetDataStructure;
  }

  public void setDatasetDataStructure(
      Set<DatasetDataStructure> datasetDataStructure) {

    this.datasetDataStructure = datasetDataStructure;
  }

  public Set<DatasetSubject> getDatasetSubject() {

    return datasetSubject;
  }

  public void setDatasetSubject(Set<DatasetSubject> datasetSubject) {

    if (this.datasetSubject == null) {
      this.datasetSubject = new HashSet<DatasetSubject>();
    }

    this.datasetSubject.clear();

    if (datasetSubject != null) {
      this.datasetSubject.addAll(datasetSubject);
    }
  }

  public boolean containsGuid(String guid) {

    for (DatasetSubject subject : datasetSubject) {
      if (guid.equalsIgnoreCase(subject.getGuid())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public Date getSubmissionDate() {

    return getSubmitDate();
  }

  // public Set<UserDownloadQueue> getUserDownloadQueue()
  // {
  //
  // return userDownloadQueue;
  // }
  //
  //
  // public void setUserDownloadQueue(Set<UserDownloadQueue> userDownloadQueue)
  // {
  // if(this.userDownloadQueue == null)
  // {
  // this.userDownloadQueue = new HashSet<UserDownloadQueue> ();
  // }
  //
  // this.userDownloadQueue.clear();
  //
  // if(userDownloadQueue != null)
  // {
  // this.userDownloadQueue.addAll(userDownloadQueue);
  // }
  // }
}
