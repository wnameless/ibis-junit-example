/**
 * 
 */

package gov.nih.tbi.dictionary.model.hibernate;

import gov.nih.tbi.ModelConstants;
import gov.nih.tbi.commons.model.DataElementStatus;
import gov.nih.tbi.commons.model.DataType;
import gov.nih.tbi.commons.model.InputRestrictions;
import gov.nih.tbi.commons.model.RequiredType;
import gov.nih.tbi.dictionary.model.AbstractDataElement;
import gov.nih.tbi.dictionary.model.AbstractDataStructure;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mvalei
 * 
 */
@Entity
@Table(name = "DATA_ELEMENT")
@XmlRootElement(name = "dataElement")
@XmlAccessorType(XmlAccessType.FIELD)
public class BasicDataElement extends AbstractDataElement implements
    Serializable {

  private static final long serialVersionUID = -784802562959104210L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "DATA_ELEMENT_SEQ")
  @SequenceGenerator(name = "DATA_ELEMENT_SEQ",
      sequenceName = "DATA_ELEMENT_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "ELEMENT_NAME")
  private String name;

  @Column(name = "ELEMENT_SIZE")
  private Integer size;

  @Column(name = "MAXIMUM_VALUE")
  private String maximumValue;

  @Column(name = "MINIMUM_VALUE")
  private String minimumValue;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "SHORT_DESCRIPTION")
  private String shortDescription;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "ELEMENT_TYPE_ID")
  private DataType type;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "DATA_ELEMENT_STATUS_ID")
  private DataElementStatus status;

  @Column(name = "DATE_CREATED")
  private Date dateCreated;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "CATEGORY_ID")
  private Category category;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "POPULATION_ID")
  private Population population;

  @ManyToOne(cascade = { CascadeType.DETACH })
  @JoinColumn(name = "MEASURING_UNIT_ID")
  private MeasuringUnit measuringUnit;

  /**
   * Converts a MapElement object to a BasicDataElement object
   * 
   * @param mapElement
   *          the element to convert
   * @return the converted element
   */
  public BasicDataElement fromMapElement(MapElement mapElement) {

    if (mapElement != null) {
      this.setId(mapElement.getDataElement().getId());
      this.setName(mapElement.getName());
      this.setSize(mapElement.getSize());
      this.setDescription(mapElement.getDescription());
      this.setShortDescription(mapElement.getShortDescription());
      this.setType(mapElement.getType());
      this.setCategory(mapElement.getCategory());
      this.setPopulation(mapElement.getPopulation());

      if (mapElement.getMeasuringUnit() != null) {
        this.setMeasuringUnit(mapElement.getMeasuringUnit());
      }
    }

    return this;
  }

  /**
   * Converts a DataElement object to a BasicDataElement object
   * 
   * @param dataElement
   *          the element to convert
   * @return the converted element
   */
  public BasicDataElement fromDataElement(DataElement dataElement) {

    if (dataElement != null) {
      this.setId(dataElement.getId());
      this.setName(dataElement.getName());
      this.setSize(dataElement.getSize());
      this.setDescription(dataElement.getDescription());
      this.setShortDescription(dataElement.getShortDescription());
      this.setType(dataElement.getType());
      this.setCategory(dataElement.getCategory());
      this.setPopulation(dataElement.getPopulation());

      if (dataElement.getMeasuringUnit() != null) {
        this.setMeasuringUnit(dataElement.getMeasuringUnit());
      }

    }
    return this;
  }

  @Override
  public Long getDataElementId() {

    return getId();
  }

  @Override
  public Long getMapElementId() {

    throw new UnsupportedOperationException();
  }

  @Override
  public Set<ValueRange> getValueRangeList() {

    return null;
  }

  @Override
  public void setValueRangeList(Set<ValueRange> valueRangeList) {

    throw new UnsupportedOperationException(
        "ValueRangeList does not exist in BasicDataElement");

  }

  @Override
  public Set<KeywordElement> getKeywordList() {

    return null;
  }

  @Override
  public void setKeywordList(Set<KeywordElement> keywordList) {

    throw new UnsupportedOperationException(
        "KeywordList does not exist in BasicDataElement");

  }

  @Override
  public ValidationPlugin getValidator() {

    return null;
  }

  @Override
  public void setValidator(ValidationPlugin validator) {

    throw new UnsupportedOperationException(
        "Validator does not exist in BasicDataElement");

  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public void setSize(Integer size) {

    this.size = size;
  }

  public Integer getSize() {

    return size;
  }

  @Override
  public BigDecimal getMaximumValue() {

    if (maximumValue == null
        || maximumValue.equals(ModelConstants.EMPTY_STRING)) {
      return null;
    }
    return new BigDecimal(maximumValue);
  }

  @Override
  public void setMaximumValue(BigDecimal maximumValue) {

    if (maximumValue == null) {
      this.maximumValue = null;
    } else {
      this.maximumValue = maximumValue.toString();
    }
  }

  @Override
  public BigDecimal getMinimumValue() {

    if (minimumValue == null
        || minimumValue.equals(ModelConstants.EMPTY_STRING)) {
      return null;
    }
    return new BigDecimal(minimumValue);
  }

  @Override
  public void setMinimumValue(BigDecimal minimumValue) {

    if (minimumValue == null) {
      this.minimumValue = null;
    } else {
      this.minimumValue = minimumValue.toString();
    }
  }

  public DataType getType() {

    return type;
  }

  public void setType(DataType type) {

    this.type = type;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public void setCategory(Category category) {

    this.category = category;
  }

  public Category getCategory() {

    return category;
  }

  @Override
  public String getShortDescription() {

    return shortDescription;
  }

  @Override
  public void setShortDescription(String shortDescription) {

    this.shortDescription = shortDescription;
  }

  public String getDescription() {

    return description;
  }

  public void setDescription(String description) {

    this.description = description;
  }

  @Override
  public String getFormat() {

    return null;
  }

  @Override
  public void setFormat(String format) {

    throw new UnsupportedOperationException(
        "Format does not exist in BasicDataElement");

  }

  @Override
  public String getNotes() {

    return null;
  }

  @Override
  public void setNotes(String notes) {

    throw new UnsupportedOperationException(
        "Notes does not exist in BasicDataElement");

  }

  @Override
  public String getGuidelines() {

    return null;
  }

  @Override
  public void setGuidelines(String guidelines) {

    throw new UnsupportedOperationException(
        "Guidelines does not exist in BasicDataElement");

  }

  @Override
  public String getHistoricalNotes() {

    return null;
  }

  @Override
  public void setHistoricalNotes(String historicalNotes) {

    throw new UnsupportedOperationException(
        "Historical Notes does not exist in BasicDataElement");
  }

  @Override
  public String getReferences() {

    return null;
  }

  @Override
  public void setReferences(String references) {

    throw new UnsupportedOperationException(
        "References does not exist in BasicDataElement");

  }

  @Override
  public String getTitle() {

    return title;
  }

  @Override
  public void setTitle(String title) {

    this.title = title;
  }

  @Override
  public Set<ClassificationElement> getClassificationElementList() {

    return null;
  }

  @Override
  public void setClassificationElementList(
      TreeSet<ClassificationElement> classificationElement) {

    throw new UnsupportedOperationException(
        "ClassificationElementList does not exist in BasicDataElement");

  }

  @Override
  public Population getPopulation() {

    return population;
  }

  @Override
  public void setPopulation(Population population) {

    this.population = population;
  }

  public Set<DiseaseElement> getDiseaseList() {

    return null;
  }

  public void setDiseaseList(Set<DiseaseElement> diseaseList) {

    // throw new
    // UnsupportedOperationException("Diseases do not exist in BasicDataElement");
  }

  @Override
  public String getDocumentationUrl() {

    return null;
  }

  @Override
  public void setDocumentationUrl(String documentationUrl) {

    // throw new
    // UnsupportedOperationException("DocumentationUrl does not exist in BasicDataElement");
  }

  @Override
  public Long getDocumentationFileId() {

    return null;
  }

  @Override
  public void setDocumentationFileId(Long documentationFileId) {

    // throw new
    // UnsupportedOperationException("DocumentationFileId does not exist in BasicDataElement");
  }

  @Override
  public InputRestrictions getRestrictions() {

    return null;
  }

  @Override
  public void setRestrictions(InputRestrictions restrictions) {

    // throw new
    // UnsupportedOperationException("Restrictions does not exist in BasicDataElement");
  }

  @Override
  public Set<ExternalId> getExternalIdSet() {

    return null;
  }

  @Override
  public void setExternalIdSet(Set<ExternalId> externalIdSet) {

    // throw new
    // UnsupportedOperationException("ExternalId does not exist in BasicDataElement");
  }

  @Override
  public String getSuggestedQuestion() {

    return null;
  }

  @Override
  public void setSuggestedQuestion(String suggestedQuestion) {

    // throw new
    // UnsupportedOperationException("SuggestedQuestion does not exist in BasicDataElement");
  }

  // Moved these from abstract data element because of JAX B issues

  @Override
  public MeasuringUnit getMeasuringUnit() {

    return measuringUnit;
  }

  @Override
  public void setMeasuringUnit(MeasuringUnit measuringUnit) {

    this.measuringUnit = measuringUnit;
  }

  public Set<Alias> getAliasList() {

    return null;
  }

  public void setAliasList(Set<Alias> aliasList) {

    // throw new
    // UnsupportedOperationException("AliasList does not exist in BasicDataElement");
  }

  public DataElementStatus getStatus() {

    return status;
  }

  public void setStatus(DataElementStatus status) {

    this.status = status;
  }

  // These are all really map element variables
  // They used to return an exception when it was accessed but that caused
  // issues w/ JAX B

  @Override
  public AbstractDataStructure getDataStructure() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer getPosition() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setPosition(Integer position) {

    // TODO Auto-generated method stub

  }

  @Override
  public RequiredType getRequiredType() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setRequiredType(RequiredType requiredType) {

    // TODO Auto-generated method stub

  }

  @Override
  public void setDateCreated(Date date) {

    dateCreated = date;

  }

  @Override
  public String getDateCreated() {

    StringBuffer date = new StringBuffer();

    if (dateCreated != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
      sdf.format(dateCreated, date, new FieldPosition(DateFormat.MONTH_FIELD));
    } else {
      date.append("");
    }
    return date.toString();
  }

  public String getDateCreatedStr(StringBuffer date) {

    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
    sdf.format(dateCreated, date, new FieldPosition(DateFormat.MONTH_FIELD));

    return date.toString();
  }
}
