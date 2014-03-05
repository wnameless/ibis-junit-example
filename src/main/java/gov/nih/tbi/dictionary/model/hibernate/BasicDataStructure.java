package gov.nih.tbi.dictionary.model.hibernate;

import gov.nih.tbi.dictionary.model.AbstractDataStructure;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "DATA_STRUCTURE")
@XmlType(name = "basicDataStructure")
public class BasicDataStructure extends AbstractDataStructure implements
    Serializable {

  private static final long serialVersionUID = 6840999374994317777L;

  @Override
  public Set<DiseaseStructure> getDiseaseList() {

    throw new UnsupportedOperationException(
        "Disease does not exist in BasicDataStructure");
  }

  @Override
  public void setDiseaseList(Set<DiseaseStructure> diseaseList) {

    throw new UnsupportedOperationException(
        "Disease does not exist in BasicDataStructure");
  }
}
