package gov.nih.tbi.commons.service;

import gov.nih.tbi.account.model.hibernate.Country;
import gov.nih.tbi.account.model.hibernate.State;
import gov.nih.tbi.commons.model.RoleType;
import gov.nih.tbi.commons.model.hibernate.FileType;
import gov.nih.tbi.dictionary.model.hibernate.Category;
import gov.nih.tbi.dictionary.model.hibernate.Classification;
import gov.nih.tbi.dictionary.model.hibernate.Disease;
import gov.nih.tbi.dictionary.model.hibernate.Domain;
import gov.nih.tbi.dictionary.model.hibernate.MeasuringType;
import gov.nih.tbi.dictionary.model.hibernate.MeasuringUnit;
import gov.nih.tbi.dictionary.model.hibernate.Population;
import gov.nih.tbi.dictionary.model.hibernate.SubDomain;
import gov.nih.tbi.dictionary.model.hibernate.Subgroup;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

/**
 * Contains the static listing of reference objects.
 * 
 * @author Francis Chen
 */
public interface StaticReferenceManager {

  /**
   * Returns a listing of all possible classifications
   * 
   * @return List<Classification>
   */
  public List<Classification> getClassificationList(boolean admin);

  /**
   * Returns a listing of classifications that fit in the given disease and
   * match the admin prefrences.
   * 
   * @param disease
   * @param isAdmin
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Classification> getClassificationList(Disease disease,
      boolean isAdmin) throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns the classification with the given name
   * 
   * @param name
   * @return
   */
  @Deprecated
  public Classification getClassificationByName(String name);

  /**
   * Returns a listing of all possible subgroups
   * 
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Subgroup> getSubgroupList() throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns a list of subgroups only for a particular disease.
   * 
   * @param disease
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Subgroup> getSubgroupsByDisease(Disease disease)
      throws MalformedURLException, UnsupportedEncodingException;

  /**
   * Returns the subgroup matching the given name
   * 
   * @param name
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public Subgroup getSubgroupByName(String name) throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns a listing of all possible populations
   * 
   * @return List<Population>
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Population> getPopulationList() throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns the population matching the given name
   * 
   * @param name
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public Population getPopulationByName(String name)
      throws MalformedURLException, UnsupportedEncodingException;

  /**
   * Returns a listing of all possible categories
   * 
   * @return List<Category>
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Category> getCategoryList() throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns the category matching the given name
   * 
   * @param name
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public Category getCategoryByName(String name) throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns the category matching the given id
   * 
   * @param id
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public Category getCategoryById(Long id) throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns a listing of all possible diseases
   * 
   * @return List<Disease>
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Disease> getDiseaseList() throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns the disease that matches the name
   * 
   * @param the
   *          name of the disease to get
   * @return the disease that matches the name
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public Disease getDiseaseByName(String name) throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns a listing of all possible domains
   * 
   * @return List<Domain>
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Domain> getDomainList() throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns a list of all the domains that match the given disease.
   * 
   * @param disease
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<Domain> getDomainsByDisease(Disease disease)
      throws MalformedURLException, UnsupportedEncodingException;

  /**
   * Returns the domain that matches the name
   * 
   * @param name
   *          of the domain to get
   * @return the domain with the matching name
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public Domain getDomainByName(String name) throws MalformedURLException,
      UnsupportedEncodingException;

  /**
   * Returns a listing of all possible Sub-Domains
   * 
   * @return List<SubDomain>
   */
  public List<SubDomain> getSubDomainList();

  /**
   * Returns the SubDomain that matches the name
   * 
   * @param name
   *          of the SubDomain to get
   * @return the SubDomain with the matching name
   */
  public SubDomain getSubDomainByName(String name);

  /**
   * Returns a listing of all possible Measurement Types
   * 
   * @return List<MeasurementType>
   */
  public List<MeasuringType> getMeasuringTypeList();

  /**
   * Returns a listing of all possible Measurement Units
   * 
   * @return List<MeasurementUnit>
   */
  public List<MeasuringUnit> getMeasuringUnitList();

  /**
   * Returns a listing of all possible states
   * 
   * @return
   */
  public List<State> getStateList();

  /**
   * Returns a listing of all possible countries
   * 
   * @return
   */
  public List<Country> getCountryList();

  /**
   * Returns a listing of all active administrative file types
   * 
   * @return
   */
  public List<FileType> getAdminFileTypeList();

  /**
   * Returns the AdminFileType with a name matching the provided name
   * 
   * @param name
   *          of the AdminFileType to return
   * @return
   */
  public FileType getAdminFileTypeByName(String name);

  /**
   * Returns a listing of all active supporting documentation file types.
   * 
   * @return
   */
  public List<FileType> getSupportingDocumentationTypeList();

  /**
   * Gets a static list of subdomains based on the selected domain and
   * subdomain.
   * 
   * @param domain
   * @param disease
   * @return
   * @throws MalformedURLException
   * @throws UnsupportedEncodingException
   */
  public List<SubDomain> getSubDomainsList(Domain domain, Disease disease)
      throws MalformedURLException, UnsupportedEncodingException;

  /**
   * Given a key role, return a set of roles that when given to a user,
   * implicitly give the user has the key role.
   * 
   * i.e. passing ROLE_DICTIONARY would return ROLE_ADMIN,
   * ROLE_DICTIONARY_ADMIN, and ROLE_DICTIONARY a user with any of those roles
   * has access to dictionary.
   * 
   * @param role
   * @return
   */
  public Set<RoleType> rolesThatImplyRole(RoleType key);

}
