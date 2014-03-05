package gov.nih.tbi.dictionary.model;

import gov.nih.tbi.commons.model.AbstractBricsObject;
import gov.nih.tbi.commons.model.DataElementStatus;
import gov.nih.tbi.commons.model.DataType;
import gov.nih.tbi.commons.model.InputRestrictions;
import gov.nih.tbi.commons.model.RequiredType;
import gov.nih.tbi.dictionary.model.hibernate.Alias;
import gov.nih.tbi.dictionary.model.hibernate.Category;
import gov.nih.tbi.dictionary.model.hibernate.ClassificationElement;
import gov.nih.tbi.dictionary.model.hibernate.DiseaseElement;
import gov.nih.tbi.dictionary.model.hibernate.ExternalId;
import gov.nih.tbi.dictionary.model.hibernate.KeywordElement;
import gov.nih.tbi.dictionary.model.hibernate.MeasuringUnit;
import gov.nih.tbi.dictionary.model.hibernate.Population;
import gov.nih.tbi.dictionary.model.hibernate.ValidationPlugin;
import gov.nih.tbi.dictionary.model.hibernate.ValueRange;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlType;

@MappedSuperclass
@XmlType(namespace = "http://tbi.nih.gov/DictionarySchema")
public abstract class AbstractDataElement extends AbstractBricsObject {

  /**********************************************************************/

  public abstract Long getMapElementId();

  public abstract Long getDataElementId();

  public abstract String getName();

  public abstract void setName(String name);

  public abstract InputRestrictions getRestrictions();

  public abstract void setRestrictions(InputRestrictions restrictions);

  public abstract String getDescription();

  public abstract void setDescription(String description);

  public abstract String getShortDescription();

  public abstract void setShortDescription(String shortDescription);

  public abstract DataType getType();

  public abstract void setType(DataType type);

  public abstract String getFormat();

  public abstract void setFormat(String format);

  public abstract String getNotes();

  public abstract void setNotes(String notes);

  public abstract Integer getSize();

  public abstract void setSize(Integer size);

  public abstract BigDecimal getMaximumValue();

  public abstract void setMaximumValue(BigDecimal maximumValue);

  public abstract BigDecimal getMinimumValue();

  public abstract void setMinimumValue(BigDecimal minimumValue);

  public abstract Set<ValueRange> getValueRangeList();

  public abstract void setValueRangeList(Set<ValueRange> valueRangeList);

  public abstract Set<KeywordElement> getKeywordList();

  public abstract void setKeywordList(Set<KeywordElement> keywordList);

  public abstract String getGuidelines();

  public abstract void setGuidelines(String guidelines);

  public abstract String getHistoricalNotes();

  public abstract void setHistoricalNotes(String historicalNotes);

  public abstract String getSuggestedQuestion();

  public abstract void setSuggestedQuestion(String suggestedQuestion);

  public abstract String getReferences();

  public abstract void setReferences(String references);

  public abstract String getTitle();

  public abstract void setTitle(String title);

  public abstract Set<Alias> getAliasList();

  public abstract void setAliasList(Set<Alias> aliasList);

  public abstract Set<ClassificationElement> getClassificationElementList();

  public abstract void setClassificationElementList(
      TreeSet<ClassificationElement> classificationElement);

  public abstract Set<DiseaseElement> getDiseaseList();

  public abstract void setDiseaseList(Set<DiseaseElement> disease);

  public abstract ValidationPlugin getValidator();

  public abstract void setValidator(ValidationPlugin validator);

  public abstract String getDocumentationUrl();

  public abstract void setDocumentationUrl(String documentationUrl);

  public abstract Long getDocumentationFileId();

  public abstract void setDocumentationFileId(Long documentationFileId);

  public abstract Set<ExternalId> getExternalIdSet();

  public abstract void setExternalIdSet(Set<ExternalId> externalIdSet);

  public abstract Category getCategory();

  public abstract void setCategory(Category category);

  public abstract DataElementStatus getStatus();

  public abstract void setStatus(DataElementStatus status);

  public abstract Population getPopulation();

  public abstract void setPopulation(Population population);

  public abstract MeasuringUnit getMeasuringUnit();

  public abstract void setMeasuringUnit(MeasuringUnit measuringUnit);

  // Added Abstract versions to solve JAXB issue

  public abstract AbstractDataStructure getDataStructure();

  public abstract Integer getPosition();

  public abstract void setPosition(Integer position);

  public abstract RequiredType getRequiredType();

  public abstract void setRequiredType(RequiredType requiredType);

  public abstract void setDateCreated(Date date);

  public abstract String getDateCreated();

  /*
   * 
   * //Making these methods abstract because it's causing problems w/ JaxB
   * 
   * 
   * @XmlTransient public AbstractDataStructure getDataStructure() {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public void setDataStructure(AbstractDataStructure
   * dataStructure) {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public Integer getPosition() {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public void setPosition(Integer position) {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public MeasuringUnit getMeasuringUnit() {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public void setMeasuringUnit(MeasuringUnit measuringUnit) {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public String getSection() {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public void setSection(String section) {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public RequiredType getRequiredType() {
   * 
   * throw new UnsupportedOperationException(); }
   * 
   * @XmlTransient public void setRequiredType(RequiredType requiredType) {
   * 
   * throw new UnsupportedOperationException(); }
   */

  public String displayValueRange() {

    String sb = "";
    sb += "{";

    if (InputRestrictions.FREE_FORM.equals(getRestrictions())) {
      if (getMinimumValue() != null) {
        sb += "MIN: " + getMinimumValue() + ", ";
      }

      if (getMaximumValue() != null) {
        sb += "MAX: " + getMaximumValue() + ", ";
      }

    } else {

      for (ValueRange vr : getValueRangeList()) {
        sb += vr.getValueRange() + ", ";
      }
    }

    if (sb.contains(",")) {
      sb = sb.substring(0, sb.lastIndexOf(","));
    }

    sb += "}";
    return sb.toString();
  }

}
