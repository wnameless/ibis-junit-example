package gov.nih.tbi.dictionary.model.hibernate;

import gov.nih.tbi.ModelConstants;
import gov.nih.tbi.commons.model.DataElementStatus;
import gov.nih.tbi.commons.model.DataType;
import gov.nih.tbi.commons.model.ExternalType;
import gov.nih.tbi.commons.model.InputRestrictions;
import gov.nih.tbi.commons.model.RequiredType;
import gov.nih.tbi.dictionary.model.AbstractDataElement;
import gov.nih.tbi.dictionary.model.AbstractDataStructure;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DATA_ELEMENT")
@XmlRootElement(name = "dataElement")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataElement extends AbstractDataElement implements Serializable {

  private static final long serialVersionUID = 3333413702783642452L;

  public static final String DOCUMENTATION_URL = "documentationUrl";
  public static final String DOCUMENTATION_FILE_ID = "documentationFileId";

  /**********************************************************************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "DATA_ELEMENT_SEQ")
  @SequenceGenerator(name = "DATA_ELEMENT_SEQ",
      sequenceName = "DATA_ELEMENT_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "ELEMENT_NAME")
  private String name;

  @Column(name = "ELEMENT_SIZE")
  private Integer size;

  @Column(name = "MAXIMUM_VALUE")
  private String maximumValue;

  @Column(name = "MINIMUM_VALUE")
  private String minimumValue;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "ELEMENT_TYPE_ID")
  private DataType type;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "INPUT_RESTRICTION_ID")
  private InputRestrictions restrictions;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "SHORT_DESCRIPTION")
  private String shortDescription;

  @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "VALIDATION_PLUGIN_ID")
  private ValidationPlugin validator;

  @Column(name = "FORMAT")
  private String format;

  @Column(name = "NOTES")
  private String notes;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "DATA_ELEMENT_STATUS_ID")
  private DataElementStatus status;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataElement", targetEntity = ValueRange.class,
      orphanRemoval = true)
  private Set<ValueRange> valueRangeList;

  @Column(name = "GUIDELINES")
  private String guidelines;

  @Column(name = "HISTORICAL_NOTES")
  private String historicalNotes;

  @Column(name = "QUESTION")
  private String suggestedQuestion;

  @Column(name = "REFERENCE")
  // 'REFERENCES' is a reserved name in SQL, use REFERENCE instead
  private String references;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "DATE_CREATED")
  private Date dateCreated;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataElement", targetEntity = ClassificationElement.class,
      orphanRemoval = true)
  private Set<ClassificationElement> classificationElementList;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "POPULATION_ID")
  private Population population;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataElement", targetEntity = DiseaseElement.class,
      orphanRemoval = true)
  private Set<DiseaseElement> diseaseList;

  @ManyToOne(cascade = { CascadeType.DETACH })
  @JoinColumn(name = "MEASURING_UNIT_ID")
  private MeasuringUnit measuringUnit;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataElement", targetEntity = KeywordElement.class,
      orphanRemoval = true)
  private Set<KeywordElement> keywordList;

  @Column(name = "DOCUMENTATION_URL")
  private String documentationUrl;
  @Column(name = "DOCUMENTATION_FILE_ID")
  private Long documentationFileId;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataElement", targetEntity = ExternalId.class,
      orphanRemoval = true)
  private Set<ExternalId> externalIdSet;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataElement", targetEntity = Alias.class,
      orphanRemoval = true)
  private Set<Alias> aliasList;

  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST })
  @JoinColumn(name = "CATEGORY_ID")
  private Category category;

  /**********************************************************************/

  @Override
  public Long getDataElementId() {

    return getId();
  }

  public Category getCategory() {

    return category;
  }

  public void setCategory(Category category) {

    this.category = category;
  }

  public Set<ExternalId> getExternalIdSet() {

    return externalIdSet;
  }

  public void setLoinc(String loincId) {

    if (externalIdSet == null) {
      externalIdSet = new HashSet<ExternalId>();
    }

    externalIdSet.add(new ExternalId(this, ExternalType.LOINC, loincId));
  }

  public ExternalId getLoinc() {

    if (externalIdSet != null) {
      for (ExternalId externalId : externalIdSet) {
        if (ExternalType.LOINC.equals(externalId.getType())) {
          return externalId;
        }
      }
    }

    return null;
  }

  public void setCadsr(String cadsrId) {

    if (externalIdSet == null) {
      externalIdSet = new HashSet<ExternalId>();
    }

    externalIdSet.add(new ExternalId(this, ExternalType.CADSR, cadsrId));
  }

  public ExternalId getCadsr() {

    if (externalIdSet != null) {
      for (ExternalId externalId : externalIdSet) {
        if (ExternalType.CADSR.equals(externalId.getType())) {
          return externalId;
        }
      }
    }

    return null;
  }

  public void setSnomed(String snomedId) {

    if (externalIdSet == null) {
      externalIdSet = new HashSet<ExternalId>();
    }

    externalIdSet.add(new ExternalId(this, ExternalType.SNOMED, snomedId));
  }

  public ExternalId getSnomed() {

    if (externalIdSet != null) {
      for (ExternalId externalId : externalIdSet) {
        if (ExternalType.SNOMED.equals(externalId.getType())) {
          return externalId;
        }
      }
    }

    return null;
  }

  public void setCdisc(String cdiscId) {

    if (externalIdSet == null) {
      externalIdSet = new HashSet<ExternalId>();
    }

    externalIdSet.add(new ExternalId(this, ExternalType.CDISC, cdiscId));
  }

  public ExternalId getCdisc() {

    if (externalIdSet != null) {
      for (ExternalId externalId : externalIdSet) {
        if (ExternalType.CDISC.equals(externalId.getType())) {
          return externalId;
        }
      }
    }

    return null;
  }

  public void setNinds(String nindsId) {

    if (externalIdSet == null) {
      externalIdSet = new HashSet<ExternalId>();
    }

    externalIdSet.add(new ExternalId(this, ExternalType.NINDS, nindsId));
  }

  public ExternalId getNinds() {

    if (externalIdSet != null) {
      for (ExternalId externalId : externalIdSet) {
        if (ExternalType.NINDS.equals(externalId.getType())) {
          return externalId;
        }
      }
    }

    return null;
  }

  public void setExternalIdSet(Set<ExternalId> externalIdSet) {

    if (this.externalIdSet == null) {
      this.externalIdSet = new LinkedHashSet<ExternalId>();
    }

    this.externalIdSet.clear();

    if (externalIdSet != null) {
      for (ExternalId externalId : externalIdSet) {
        if (externalId.getDataElement() == null) {
          externalId.setDataElement(this);
        }
      }

      this.externalIdSet.addAll(externalIdSet);
    }
  }

  public MeasuringUnit getMeasuringUnit() {

    return measuringUnit;
  }

  public void setMeasuringUnit(MeasuringUnit measuringUnit) {

    this.measuringUnit = measuringUnit;
  }

  public InputRestrictions getRestrictions() {

    return restrictions;
  }

  public void setRestrictions(InputRestrictions restrictions) {

    this.restrictions = restrictions;
  }

  @Override
  public Long getMapElementId() {

    throw new UnsupportedOperationException();
  }

  public void setDescription(String description) {

    this.description = description;
  }

  public String getDescription() {

    return description;
  }

  public void setShortDescription(String shortDescription) {

    this.shortDescription = shortDescription;
  }

  public String getShortDescription() {

    return shortDescription;
  }

  public ValidationPlugin getValidator() {

    return validator;
  }

  public void setValidator(ValidationPlugin validator) {

    this.validator = validator;
  }

  public void setFormat(String format) {

    this.format = format;
  }

  public String getFormat() {

    return format;
  }

  public void setNotes(String notes) {

    this.notes = notes;
  }

  public String getNotes() {

    return notes;
  }

  public void setValueRangeList(Set<ValueRange> valueRangeList) {

    this.valueRangeList = valueRangeList;
  }

  public Set<ValueRange> getValueRangeList() {

    if (valueRangeList == null) {
      valueRangeList = new TreeSet<ValueRange>();
      return valueRangeList;
    }

    // Sort the value range list (done in java because OrderBy annotation cannot
    // be used when getting the id
    // property (used for search w/ keyword)
    if (!(valueRangeList instanceof TreeSet)) {
      valueRangeList = new TreeSet<ValueRange>(valueRangeList);
    }

    return valueRangeList;
  }

  public boolean isCommonDataElement() {

    Category c = this.getCategory();
    if ((new Long(0L).equals(c.getId()))) {
      return true;
    }

    return false;
  }

  public void setKeywordList(Set<KeywordElement> keywordList) {

    if (this.keywordList == null) {
      this.keywordList = new HashSet<KeywordElement>();
    }

    this.keywordList.clear();

    if (keywordList != null) {
      for (KeywordElement keywordElement : keywordList) {
        if (keywordElement.getDataElement() == null) {
          keywordElement.setDataElement(this);
        }
      }
      this.keywordList.addAll(keywordList);
    }
  }

  public String getGuidelines() {

    return guidelines;
  }

  public void setGuidelines(String guidelines) {

    this.guidelines = guidelines;
  }

  public String getHistoricalNotes() {

    return historicalNotes;
  }

  public void setHistoricalNotes(String historicalNotes) {

    this.historicalNotes = historicalNotes;
  }

  public String getSuggestedQuestion() {

    return suggestedQuestion;
  }

  public void setSuggestedQuestion(String suggestedQuestion) {

    this.suggestedQuestion = suggestedQuestion;
  }

  public String getReferences() {

    return references;
  }

  public void setReferences(String references) {

    this.references = references;
  }

  public String getTitle() {

    return title;
  }

  public void setTitle(String title) {

    this.title = title;
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

  // TODO: these should be removed
  public String getAliases() {

    throw new UnsupportedOperationException(
        "Aliases field does not exist in DataElement");
  }

  public void setAliases(String aliases) {

    throw new UnsupportedOperationException(
        "Aliases field does not exist in DataElement");
  }

  public Set<ClassificationElement> getClassificationElementList() {

    return classificationElementList;
  }

  public void setClassificationElementList(
      TreeSet<ClassificationElement> classificationElementList) {

    this.classificationElementList = classificationElementList;
  }

  /**
   * Adds a single classificaitonElement entry to the list Used in data element
   * import when adding classificaiton elements as they are found in the import
   * file.
   * 
   * @param classificaitonElement
   */
  public void addClassificationElement(
      ClassificationElement classificaitonElement) {

    if (this.classificationElementList == null) {
      this.classificationElementList = new HashSet<ClassificationElement>();
    }
    classificationElementList.add(classificaitonElement);
  }

  public Population getPopulation() {

    return population;
  }

  public void setPopulation(Population population) {

    this.population = population;
  }

  public Set<DiseaseElement> getDiseaseList() {

    return diseaseList;
  }

  // Why is this set function so complex?
  public void setDiseaseList(Set<DiseaseElement> diseaseList) {

    if (this.diseaseList == null) {
      this.diseaseList = new HashSet<DiseaseElement>();
    }

    this.diseaseList.clear();

    if (diseaseList != null) {
      for (DiseaseElement diseaseElement : diseaseList) {
        if (diseaseElement.getDataElement() == null) {
          diseaseElement.setDataElement(this);
        }
      }

      this.diseaseList.addAll(diseaseList);
    }
  }

  /**
   * Adds a single diseaseElement entry to the list. Used in data element import
   * when adding classificaiton elements as they are found in the import file.
   * 
   * @param diseaseElement
   */
  public void addDiseaseElement(DiseaseElement diseaseElement) {

    if (this.diseaseList == null) {
      this.diseaseList = new HashSet<DiseaseElement>();
    }
    diseaseList.add(diseaseElement);
  }

  // @XmlElementWrapper(name="keywordList")
  // @XmlElement(name="keywordElement")
  public Set<KeywordElement> getKeywordList() {

    if (this.keywordList == null) {
      this.keywordList = new HashSet<KeywordElement>();
    }
    return keywordList;
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

  public Set<Alias> getAliasList() {

    if (aliasList == null) {
      aliasList = new LinkedHashSet<Alias>();
    }

    return this.aliasList;
  }

  // TODO: this should be renamed to follow standard bean conventions
  public void setAliasList(Set<Alias> aliasList) {

    this.aliasList = aliasList;
  }

  /**********************************************************************/

  @Override
  public String toString() {

    return "DataElement [description=" + description + ", format=" + format
        + ", population=" + population + ", id=" + getId() + ", ke ywordList="
        + keywordList + ", name=" + getName() + ", notes=" + notes
        + ", shortDescription=" + shortDescription + ", size=" + getSize()
        + ", valueRange=" + valueRangeList + "]";
  }

  @Override
  public DataElementStatus getStatus() {

    return status;
  }

  @Override
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

  public String getDateCreated() {

    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
    StringBuffer date = new StringBuffer();
    sdf.format(dateCreated, date, new FieldPosition(DateFormat.MONTH_FIELD));

    return date.toString();
  }

  public void setDateCreated(Date dateCreated) {

    this.dateCreated = dateCreated;
  }

  /**
   * This method will test the CSV specifically for blank comma rows The method
   * will only return true if all the data in the DE is null
   * 
   * @param testDE
   * @return
   */
  public boolean dataElementHasData(DataElement testDE)
      throws IllegalArgumentException, IllegalAccessException {

    for (Field field : testDE.getClass().getDeclaredFields()) {
      // allow access to the class
      field.setAccessible(true);
      Class<?> type = field.getType();
      // Ignore fields that are automatically set by the system
      if (!field.getName().equalsIgnoreCase("serialVersionUID")
          && !field.getName().equalsIgnoreCase("DOCUMENTATION_URL")
          && !field.getName().equalsIgnoreCase("DOCUMENTATION_FILE_ID")
          && !field.getName().equalsIgnoreCase("status")
          && !field.getName().equalsIgnoreCase("externalIdSet")) {
        // test to see if fields get by
        if (field.get(this) != null) {
          // test to see if the field is a string with blank data
          if (field.get(this) instanceof String
              && !field.get(this).toString().trim().isEmpty()) {
            return true;
          }
          // check to see if the fiel is a set, if it is and the set is empty,
          // ignore
          if (field.get(this) instanceof Set<?>) {
            Set<Object> set = (Set<Object>) field.get(this);
            if (!set.isEmpty()) {
              return true;
            }

          }
        }
      }
    }
    return false;
  }
}
