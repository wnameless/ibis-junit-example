package gov.nih.tbi.dictionary.model;

import gov.nih.tbi.commons.model.AbstractAuditObject;
import gov.nih.tbi.commons.model.StatusType;
import gov.nih.tbi.dictionary.model.hibernate.DiseaseStructure;
import gov.nih.tbi.repository.model.SubmissionType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

@MappedSuperclass
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractDataStructure extends AbstractAuditObject {

  static Logger logger = Logger.getLogger(AbstractDataStructure.class);

  public static final int DEFAULT_STATUS = 0;

  public static final String ID = "id";
  public static final String SHORT_NAME = "shortName";
  public static final String VERSION = "version";
  public static final String TITLE = "title";
  public static final String DESCRIPTION = "description";
  public static final String STATUS = "status";
  public static final String PUBLICATION_DATE = "publicationDate";
  public static final String VALIDATABLE = "validatable";
  public static final String ORGANIZATION = "organization";

  public static final String DOCUMENTATION_URL = "documentationUrl";
  public static final String DOCUMENTATION_FILE_ID = "documentationFileId";

  private static final SimpleDateFormat dateFormating = new SimpleDateFormat(
      "MM/dd/yyyy");

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "DATA_STRUCTURE_SEQ")
  @SequenceGenerator(name = "DATA_STRUCTURE_SEQ",
      sequenceName = "DATA_STRUCTURE_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "SHORT_NAME")
  private String shortName;
  @Column(name = "VERSION")
  private Integer version;
  @Column(name = "TITLE")
  private String title;
  @Column(name = "DESCRIPTION")
  private String description;
  @Enumerated(EnumType.ORDINAL)
  @Column(name = "STATUS_ID")
  private StatusType status;
  @Column(name = "PUBLICATION_DATE")
  private Date publicationDate;
  @Column(name = "VALIDATABLE")
  private Boolean validatable;
  @Column(name = "ORGANIZATION")
  private String organization;
  @Column(name = "DOCUMENTATION_URL")
  private String documentationUrl;
  @Column(name = "DOCUMENTATION_FILE_ID")
  private Long documentationFileId;
  @Enumerated(EnumType.ORDINAL)
  @Column(name = "FILE_TYPE_ID")
  private SubmissionType fileType;
  @Column(name = "IS_COPYRIGHTED")
  private Boolean isCopyrighted;

  /**********************************************************************/

  @Override
  public Long getId() {

    return id;
  }

  @Override
  public void setId(Long id) {

    this.id = id;
  }

  public String getShortName() {

    return shortName;
  }

  public void setShortName(String shortName) {

    this.shortName = shortName.trim();
  }

  public Integer getVersion() {

    return version;
  }

  public void setVersion(Integer version) {

    this.version = version;
  }

  public String getTitle() {

    return title;
  }

  public void setTitle(String title) {

    this.title = title;
  }

  public String getDescription() {

    return description;
  }

  public void setDescription(String description) {

    this.description = description;
  }

  public StatusType getStatus() {

    return status;
  }

  public void setStatus(StatusType status) {

    this.status = status;
  }

  public Date getPublicationDate() {

    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) {

    this.publicationDate = publicationDate;
  }

  public String getPubDate() {

    Date pubDate = getPublicationDate();
    if (pubDate == null) {
      return "";
    }
    StringBuilder date = new StringBuilder(dateFormating.format(pubDate));
    return date.toString();
  }

  public Boolean getValidatable() {

    return validatable;
  }

  public void setValidatable(Boolean validatable) {

    this.validatable = validatable;
  }

  public String getOrganization() {

    return organization;
  }

  public void setOrganization(String organization) {

    this.organization = organization;
  }

  public String getDocumentationUrl() {

    return documentationUrl;
  }

  public void setDocumentationUrl(String documentationUrl) {

    this.documentationUrl = documentationUrl;
  }

  public Long getDocumentationFileId() {

    return documentationFileId;
  }

  public void setDocumentationFileId(Long documentationFileId) {

    this.documentationFileId = documentationFileId;
  }

  public SubmissionType getFileType() {

    return fileType;
  }

  public void setFileType(SubmissionType fileType) {

    this.fileType = fileType;
  }

  public Boolean getIsCopyrighted() {

    return isCopyrighted;
  }

  public void setIsCopyrighted(Boolean isCopyrighted) {

    this.isCopyrighted = isCopyrighted;
  }

  public abstract Set<DiseaseStructure> getDiseaseList();

  public abstract void setDiseaseList(Set<DiseaseStructure> diseaseList);

  /**********************************************************************/

  public String getReadableName() {

    return shortName + " v" + version;
  }

  @Override
  public boolean equals(Object arg0) {

    if (arg0 instanceof AbstractDataStructure) {
      AbstractDataStructure ads = (AbstractDataStructure) arg0;

      if (this.id != null && ads.getId() != null && this.id.equals(ads.getId())) {
        return true;
      }

      if (this.shortName.equals(ads.shortName)
          && this.version.equals(ads.version)) {
        return true;
      }
    } else {
      System.out.println(arg0.getClass());
    }

    return false;
  }

}
