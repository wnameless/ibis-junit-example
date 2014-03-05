package gov.nih.tbi.commons.model;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlTransient
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
public abstract class AbstractBricsObject {

  public abstract Long getId();

  public abstract void setId(Long id);

}
