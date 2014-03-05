package gov.nih.tbi.dictionary.model.hibernate;

import gov.nih.tbi.dictionary.model.AbstractDataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

@Entity
@Table(name = "DATA_STRUCTURE")
@XmlRootElement(name = "dataStructure")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataStructure extends AbstractDataStructure implements
    Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 2830307803690132318L;
  private static final String MAIN_REPEATABLE_GROUP_NAME = "main";

  static Logger logger = Logger.getLogger(DataStructure.class);

  /******************************************************************************************************/

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataStructure", targetEntity = RepeatableGroup.class,
      orphanRemoval = true)
  @OrderBy(value = "position")
  private Set<RepeatableGroup> repeatableGroups;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "dataStructure", targetEntity = DiseaseStructure.class,
      orphanRemoval = true)
  private Set<DiseaseStructure> diseaseList;

  /******************************************************************************************************/

  public Set<RepeatableGroup> getRepeatableGroups() {

    if (repeatableGroups == null) {
      repeatableGroups = new HashSet<RepeatableGroup>();
    }

    return repeatableGroups;
  }

  public void setRepeatableGroups(Set<RepeatableGroup> repeatableGroups) {

    if (this.repeatableGroups == null) {
      this.repeatableGroups = new HashSet<RepeatableGroup>();
    }

    this.repeatableGroups.clear();

    if (repeatableGroups != null) {
      this.repeatableGroups.addAll(repeatableGroups);
    }
  }

  public Set<DiseaseStructure> getDiseaseList() {

    return diseaseList;
  }

  public void setDiseaseList(Set<DiseaseStructure> diseaseList) {

    this.diseaseList = diseaseList;
  }

  /**
   * Returns a set of all the MapElements that are attached to this data
   * structure through a repeatable group.
   * 
   * @return elements
   */
  public Set<MapElement> getDataElements() {

    if (repeatableGroups == null) {
      repeatableGroups = new HashSet<RepeatableGroup>();
    }

    Set<MapElement> elements = new HashSet<MapElement>();

    for (RepeatableGroup group : repeatableGroups) {
      for (MapElement mapElement : group.getMapElements()) {
        elements.add(mapElement);
      }
    }

    return elements;
  }

  /**
   * This will take a list of map elements in the data structure and return a
   * unique list of all the data elements in the structure
   * 
   * @return
   */
  public List<DataElement> getUniqueDataElements() {

    Set<MapElement> allData = getDataElements();
    List<DataElement> uniqueList = new ArrayList<DataElement>();

    // loop through all the map elements in the list
    for (MapElement element : allData) {
      boolean isUnique = true;
      DataElement currentElement = element.getDataElement();
      // loop through the return list of DEs
      for (DataElement inReturnList : uniqueList) {
        // if a duplicate DE is found in the list set the unique identifier to
        // false
        if (currentElement.equals(inReturnList)) {
          isUnique = false;
        }
      }
      // add the DE to the list if it is found to be unique
      if (isUnique) {
        uniqueList.add(element.getDataElement());
      }
    }

    return uniqueList;
  }

  public RepeatableGroup getMainRepeatableGroup() {

    if (repeatableGroups == null || repeatableGroups.isEmpty()) {
      return null;
    } else {
      for (RepeatableGroup rg : repeatableGroups) {
        if (rg.getName().equalsIgnoreCase(MAIN_REPEATABLE_GROUP_NAME)) {
          return rg;
        }
      }
    }

    return null;
  }

  /**
   * Returns the number of repeatable groups associated with this dataStructure
   * 
   * @return
   */
  public Integer getSize() {

    return repeatableGroups.size();
  }

  /**
   * Returns the repeatable group in this data structure with a certain name
   * 
   * @param name
   * @return
   */
  public RepeatableGroup getRepeatableGroupByName(String name) {

    if (repeatableGroups != null) {
      for (RepeatableGroup repeatableGroup : repeatableGroups) {
        if (name.equalsIgnoreCase(repeatableGroup.getName())) {
          return repeatableGroup;
        }
      }
    }

    return null;
  }

  public String getDiseaseStructureString() {

    StringBuilder toReturn = new StringBuilder();
    int index = 0;
    int diseaseListLen = getDiseaseList().size();
    for (DiseaseStructure ds : getDiseaseList()) {
      if (index == (diseaseListLen - 1))
        toReturn.append(ds.getDisease().getName());
      else
        toReturn.append(ds.getDisease().getName() + ", ");
      index++;
    }
    // This is clear out the extra white space
    toReturn.trimToSize();
    return toReturn.toString();
  }
}
