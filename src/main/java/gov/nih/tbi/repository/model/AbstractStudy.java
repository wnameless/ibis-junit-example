package gov.nih.tbi.repository.model;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import gov.nih.tbi.commons.model.AbstractBricsObject;
import gov.nih.tbi.commons.model.RecruitmentStatus;
import gov.nih.tbi.commons.model.StudyStatus;

/**
 * An abstract class with the minimum fields required for a Study
 * 
 * @author mvalei
 * 
 */
@MappedSuperclass
@XmlType(namespace = "http://tbi.nih.gov/RepositorySchema")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractStudy extends AbstractBricsObject {

  /**********************************************************************/

  public abstract Long getId();

  public abstract void setId(Long id);

  public abstract String getTitle();

  public abstract void setTitle(String title);

  public abstract String getPrefixedId();

  public abstract void setPrefixedId(String prefixedId);

  public abstract String getAbstractText();

  public abstract void setAbstractText(String abstractText);

  public abstract RecruitmentStatus getRecruitmentStatus();

  public abstract void
      setRecruitmentStatus(RecruitmentStatus recrutimentStatus);

  public abstract String getPrincipalInvestigator();

  public abstract void setPrincipalInvestigator(String principalInvestigator);

  public abstract String getPrincipalInvestigatorEmail();

  public abstract void setPrincipalInvestigatorEmail(
      String principalInvestigatorEmail);

  public abstract String getDataManager();

  public abstract void setDataManager(String dataManager);

  public abstract String getDataManagerEmail();

  public abstract void setDataManagerEmail(String dataManagerEmail);

  public abstract Date getDateCreated();

  public abstract void setDateCreated(Date dateCreated);

  public abstract StudyStatus getStudyStatus();

  public abstract void setStudyStatus(StudyStatus studyStatus);

  public abstract boolean getIsGenomic();

  public abstract boolean getIsClinical();

  public abstract boolean getIsImaging();
}
