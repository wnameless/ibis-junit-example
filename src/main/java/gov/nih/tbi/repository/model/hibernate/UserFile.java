package gov.nih.tbi.repository.model.hibernate;

import gov.nih.tbi.ModelConstants;
import gov.nih.tbi.commons.model.hibernate.FileType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "USER_FILE")
@XmlRootElement(name = "Userfile")
public class UserFile implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = -136903741147861148L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "DATAFILE_ENDPOINT_INFO_SEQ")
  @SequenceGenerator(name = "DATAFILE_ENDPOINT_INFO_SEQ",
      sequenceName = "DATAFILE_ENDPOINT_INFO_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "FILE_NAME")
  private String name;

  @Column(name = "FILE_DESCRIPTION")
  private String description;

  @Column(name = "FILE_PATH")
  private String path;

  @OneToOne
  @JoinColumn(name = "DATAFILE_ENDPOINT_INFO_ID")
  @XmlTransient
  private DatafileEndpointInfo datafileEndpointInfo;

  @Column(name = "USER_ID")
  private Long userId;

  @ManyToOne
  @JoinColumn(name = "DATASTORE_BINARY_INFO_ID")
  private DataStoreBinaryInfo dataStoreBinaryInfo;

  @OneToOne
  @JoinColumn(name = "FILE_TYPE_ID")
  private FileType fileType;

  @Column(name = "USER_FILE_SIZE")
  private Long size;

  @Column(name = "upload_date")
  private Date uploadedDate;

  public Date getUploadedDate() {

    return uploadedDate;
  }

  public void setUploadedDate(Date uploadedDate) {

    this.uploadedDate = uploadedDate;
  }

  public Long getSize() {

    return size;
  }

  public void setSize(Long size) {

    this.size = size;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public String getDescription() {

    return description;
  }

  public void setDescription(String description) {

    this.description = description;
  }

  public String getPath() {

    return path;
  }

  public void setPath(String path) {

    this.path = path;
  }

  public DatafileEndpointInfo getDatafileEndpointInfo() {

    return datafileEndpointInfo;
  }

  public void
      setDatafileEndpointInfo(DatafileEndpointInfo datafileEndpointInfo) {

    this.datafileEndpointInfo = datafileEndpointInfo;
  }

  public DataStoreBinaryInfo getDataStoreBinaryInfo() {

    return dataStoreBinaryInfo;
  }

  public void setDataStoreBinaryInfo(DataStoreBinaryInfo dataStoreBinaryInfo) {

    this.dataStoreBinaryInfo = dataStoreBinaryInfo;
  }

  public FileType getFileType() {

    return fileType;
  }

  public void setFileType(FileType fileType) {

    this.fileType = fileType;
  }

  public Long getUserId() {

    return userId;
  }

  public void setUserId(Long userId) {

    this.userId = userId;
  }

  /**
   * Returns the expirationDate as a string formatted dd-MMM-yyyy
   * 
   * @return
   */
  public String getUploadDateString() {

    if (uploadedDate == null) {
      return null;
    }
    SimpleDateFormat df =
        new SimpleDateFormat(ModelConstants.UNIVERSAL_DATE_FORMAT);

    return df.format(uploadedDate);
  }
}
