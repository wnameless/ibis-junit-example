package gov.nih.tbi.repository.model.hibernate;

import gov.nih.tbi.commons.model.hibernate.FileType;

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

@Entity
@Table(name = "SUPPORTING_DOCUMENTATION")
public class SupportingDocumentation implements Serializable {

  private static final long serialVersionUID = 5622704520312389253L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "SUPPORTING_DOCUMENTATION_SEQ")
  @SequenceGenerator(name = "SUPPORTING_DOCUMENTATION_SEQ",
      sequenceName = "SUPPORTING_DOCUMENTATION_SEQ", allocationSize = 1)
  private Long id;

  @OneToOne
  @JoinColumn(name = "STUDY_ID")
  private Study study;

  @OneToOne
  @JoinColumn(name = "USER_FILE_ID")
  private UserFile userFile;

  @OneToOne
  @JoinColumn(name = "FILE_TYPE_ID")
  private FileType fileType;

  @Column(name = "URL")
  private String url;

  @Column(name = "DESCRIPTION")
  private String description;

  public Long getId() {

    return id;
  }

  public Study getStudy() {

    return study;
  }

  public UserFile getUserFile() {

    return userFile;
  }

  public FileType getFileType() {

    return fileType;
  }

  public String getUrl() {

    return url;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public void setStudy(Study study) {

    this.study = study;
  }

  public void setUserFile(UserFile userFile) {

    this.userFile = userFile;
  }

  public void setFileType(FileType fileType) {

    this.fileType = fileType;
  }

  public void setUrl(String url) {

    this.url = url;
  }

  public Boolean getIsUrl() {

    return url != null;
  }

  // returns description if object is a url, otherwise return description of the
  // userfile
  public String getDescription() {

    return url == null ? userFile.getDescription() : description;
  }

  public void setDescription(String description) {

    this.description = description;
  }

  public String getName() {

    return url == null ? userFile.getName() : url;
  }
}
