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
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MAP_ELEMENT")
@XmlRootElement(name = "mapElement")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapElement extends AbstractDataElement implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 2352968968139244483L;

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "MAP_ELEMENT_SEQ")
  @SequenceGenerator(name = "MAP_ELEMENT_SEQ",
      sequenceName = "MAP_ELEMENT_SEQ", allocationSize = 1)
  private Long id;

  @XmlIDREF
  @ManyToOne(targetEntity = RepeatableGroup.class)
  @JoinColumn(name = "REPEATABLE_GROUP_ID")
  private RepeatableGroup repeatableGroup;

  @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
      CascadeType.MERGE, CascadeType.PERSIST })
  @JoinColumn(name = "DATA_ELEMENT_ID")
  private DataElement dataElement;

  @Column(name = "POSITION")
  private Integer position;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "REQUIRED_TYPE_ID")
  private RequiredType requiredTypeId;

  @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL },
      orphanRemoval = true)
  @JoinColumn(name = "CONDITION_ID")
  @XmlElement
  private Condition condition;

  /**********************************************************************/

  public MapElement(MapElement mapElement) {

    repeatableGroup = mapElement.getRepeatableGroup();
    dataElement = mapElement.getDataElement();
    position = mapElement.getPosition();
    requiredTypeId = mapElement.getRequiredType();
    condition = mapElement.getCondition();
  }

  public MapElement() {

    this.dataElement = new DataElement();
  }

  public MapElement(DataElement dataElement) {

    this.dataElement = dataElement;
  }

  @Override
  public Long getId() {

    return id;
  }

  @Override
  public void setId(Long id) {

    this.id = id;
  }

  public RepeatableGroup getRepeatableGroup() {

    return repeatableGroup;
  }

  public void setRepeatableGroup(RepeatableGroup repeatableGroup) {

    this.repeatableGroup = repeatableGroup;
  }

  public DataElement getDataElement() {

    return dataElement;
  }

  public void setDataElement(DataElement dataElement) {

    this.dataElement = dataElement;
  }

  public Integer getPosition() {

    return position;
  }

  public void setPosition(Integer position) {

    this.position = position;
  }

  public RequiredType getRequiredType() {

    return requiredTypeId;
  }

  public void setRequiredType(RequiredType requiredType) {

    this.requiredTypeId = requiredType;
  }

  public ValidationPlugin getValidator() {

    return dataElement.getValidator();
  }

  public void setValidator(ValidationPlugin validator) {

    this.dataElement.setValidator(validator);
  }

  public String getDescription() {

    return dataElement.getDescription();
  }

  public String getFormat() {

    return dataElement.getFormat();
  }

  public String getNotes() {

    return dataElement.getNotes();
  }

  public String getShortDescription() {

    return dataElement.getShortDescription();
  }

  public Integer getSize() {

    return dataElement.getSize();
  }

  @Override
  public BigDecimal getMaximumValue() {

    return dataElement.getMaximumValue();
  }

  @Override
  public BigDecimal getMinimumValue() {

    return dataElement.getMinimumValue();
  }

  @Override
  public Set<ValueRange> getValueRangeList() {

    return dataElement.getValueRangeList();
  }

  public void setDescription(String description) {

    dataElement.setDescription(description);
  }

  public void setFormat(String format) {

    dataElement.setFormat(format);
  }

  public void setNotes(String notes) {

    dataElement.setNotes(notes);
  }

  public void setShortDescription(String shortDescription) {

    dataElement.setShortDescription(shortDescription);
  }

  public void setSize(Integer size) {

    dataElement.setSize(size);
  }

  public void setMaximumValue(BigDecimal maximumValue) {

    dataElement.setMaximumValue(maximumValue);
  }

  public void setMinimumValue(BigDecimal minimumValue) {

    dataElement.setMinimumValue(minimumValue);
  }

  @Override
  public void setValueRangeList(Set<ValueRange> valueRangeList) {

    dataElement.setValueRangeList(valueRangeList);
  }

  public void setType(DataType type) {

    dataElement.setType(type);
  }

  public DataType getType() {

    return dataElement.getType();
  }

  @Override
  public Long getDataElementId() {

    return dataElement.getDataElementId();
  }

  @Override
  public Long getMapElementId() {

    return this.id;
  }

  public String getName() {

    return dataElement.getName();
  }

  public void setName(String name) {

    dataElement.setName(name);
  }

  @Override
  public Set<KeywordElement> getKeywordList() {

    return dataElement.getKeywordList();
  }

  @Override
  public void setKeywordList(Set<KeywordElement> keywordList) {

    dataElement.setKeywordList(keywordList);
  }

  public String getGuidelines() {

    return dataElement.getGuidelines();
  }

  public void setGuidelines(String guidelines) {

    dataElement.setGuidelines(guidelines);
  }

  public String getHistoricalNotes() {

    return dataElement.getHistoricalNotes();
  }

  public void setHistoricalNotes(String historicalNotes) {

    dataElement.setHistoricalNotes(historicalNotes);
  }

  public String getReferences() {

    return dataElement.getReferences();
  }

  public void setReferences(String references) {

    dataElement.setReferences(references);
  }

  public String getTitle() {

    return dataElement.getTitle();
  }

  public void setTitle(String title) {

    dataElement.setTitle(title);
  }

  public Set<ClassificationElement> getClassificationElementList() {

    return dataElement.getClassificationElementList();
  }

  public void setClassificationElementList(
      TreeSet<ClassificationElement> classificationElementList) {

    dataElement.setClassificationElementList(classificationElementList);
  }

  public Population getPopulation() {

    return dataElement.getPopulation();
  }

  public void setPopulation(Population population) {

    dataElement.setPopulation(population);
  }

  public Set<DiseaseElement> getDiseaseList() {

    return dataElement.getDiseaseList();
  }

  public void setDiseaseList(Set<DiseaseElement> disease) {

    dataElement.setDiseaseList(disease);
  }

  public String getDocumentationUrl() {

    return dataElement.getDocumentationUrl();
  }

  public void setDocumentationUrl(String documentationUrl) {

    dataElement.setDocumentationUrl(documentationUrl);
  }

  public Long getDocumentationFileId() {

    return dataElement.getDocumentationFileId();
  }

  public void setDocumentationFileId(Long documentationFileId) {

    dataElement.setDocumentationFileId(documentationFileId);
  }

  /**********************************************************************/

  public AbstractDataStructure getDataStructure() {

    return repeatableGroup.getDataStructure();
  }

  @Override
  public String toString() {

    return "MapElement [dataElement=" + dataElement + ", repeatableGroup="
        + repeatableGroup + ", id=" + id + ", position=" + position
        + ", requiredTypeId=" + requiredTypeId + "]";
  }

  @Override
  public InputRestrictions getRestrictions() {

    return dataElement.getRestrictions();
  }

  @Override
  public void setRestrictions(InputRestrictions restrictions) {

    dataElement.setRestrictions(restrictions);
  }

  @Override
  public Set<ExternalId> getExternalIdSet() {

    return dataElement.getExternalIdSet();
  }

  @Override
  public void setExternalIdSet(Set<ExternalId> externalIdSet) {

    Set<ExternalId> externalIdCopySet = new HashSet<ExternalId>();

    if (externalIdSet != null) {
      for (ExternalId externalId : externalIdSet) {
        externalIdCopySet.add(new ExternalId(externalId));
      }
    }

    dataElement.setExternalIdSet(externalIdCopySet);
  }

  public ExternalId getLoinc() {

    return dataElement.getLoinc();
  }

  public void setLoinc(String loincId) {

    dataElement.setLoinc(loincId);
  }

  public ExternalId getSnomed() {

    return dataElement.getSnomed();
  }

  public void setSnomed(String snomedId) {

    dataElement.setSnomed(snomedId);
  }

  public ExternalId getCadsr() {

    return dataElement.getCadsr();
  }

  public void setCadsr(String cadsrId) {

    dataElement.setCadsr(cadsrId);
  }

  public ExternalId getCdisc() {

    return dataElement.getCdisc();
  }

  public void setCdisc(String cdiscId) {

    dataElement.setCdisc(cdiscId);
  }

  public ExternalId getNinds() {

    return dataElement.getNinds();
  }

  public void setNinds(String nindsId) {

    dataElement.setNinds(nindsId);
  }

  public void setCategory(Category category) {

    dataElement.setCategory(category);
  }

  public Category getCategory() {

    return dataElement.getCategory();
  }

  public void setMeasuringUnit(MeasuringUnit measuringUnit) {

    dataElement.setMeasuringUnit(measuringUnit);
  }

  public MeasuringUnit getMeasuringUnit() {

    return dataElement.getMeasuringUnit();
  }

  @Override
  public String getSuggestedQuestion() {

    return dataElement.getSuggestedQuestion();
  }

  @Override
  public void setSuggestedQuestion(String suggestedQuestion) {

    dataElement.setSuggestedQuestion(suggestedQuestion);
  }

  public Set<Alias> getAliasList() {

    return dataElement.getAliasList();
  }

  public void setAliasList(Set<Alias> aliasList) {

    dataElement.setAliasList(aliasList);
  }

  @Override
  public DataElementStatus getStatus() {

    return dataElement.getStatus();
  }

  @Override
  public void setStatus(DataElementStatus status) {

    dataElement.setStatus(status);
  }

  public Condition getCondition() {

    return condition;
  }

  public void setCondition(Condition condition) {

    this.condition = condition;
  }

  public String getMapElementNameWithGroup() {

    String out =
        getName() + ModelConstants.WHITESPACE + ModelConstants.LEFT_PAREN
            + getRepeatableGroup().getName() + ModelConstants.RIGHT_PAREN;
    return out;
  }

  @Override
  public void setDateCreated(Date date) {

    dataElement.setDateCreated(date);

  }

  @Override
  public String getDateCreated() {

    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
    StringBuffer date = new StringBuffer();
    sdf.format(dataElement.getDateCreated(), date, new FieldPosition(
        DateFormat.MONTH_FIELD));

    return date.toString();
  }

}
