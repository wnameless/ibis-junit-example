package gov.nih.tbi.repository.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public enum SubmissionType {
  CLINICAL(0l, "Clinical Assessment"), GENOMICS(1l, "Genomics"), IMAGING(2l,
      "Imaging"), DATA_FILE(3l, "Data File");

  @XmlTransient
  private long id;

  private String type;

  SubmissionType(long id, String type) {

    this.id = id;
    this.type = type;
  }

  public long getId() {

    return id;
  }

  public void setId(long id) {

    this.id = id;
  }

  public String getType() {

    return type;
  }

  public void setType(String type) {

    this.type = type;
  }

  public static SubmissionType[] getMainTypes() {

    SubmissionType[] out = { CLINICAL, GENOMICS, IMAGING };
    return out;
  }
}
