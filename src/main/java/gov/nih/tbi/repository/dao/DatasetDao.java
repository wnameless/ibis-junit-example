package gov.nih.tbi.repository.dao;

import gov.nih.tbi.commons.dao.GenericDao;
import gov.nih.tbi.commons.model.DatasetStatus;
import gov.nih.tbi.commons.model.hibernate.User;
import gov.nih.tbi.commons.util.PaginationData;
import gov.nih.tbi.repository.model.hibernate.Dataset;

import java.util.List;
import java.util.Set;

public interface DatasetDao extends GenericDao<Dataset, Long> {

  /**
   * Querys the database for a list of results based on the search string and
   * the pagination data.
   * 
   * @param key
   * @param datasetStatus
   * @param requested
   *          : true if we want to filter by the requested status instead of the
   *          current status
   * @param pageData
   * @return
   */
  public List<Dataset> search(Set<Long> ids, String key,
      DatasetStatus datasetStatus, boolean requested, PaginationData pageData);

  public List<Dataset> getByIds(Set<Long> ids);

  public List<Dataset> getUploadingDataset(User user);

  /**
   * returns the number of datasets with the given status as either the status
   * or requested status as specified by requested.
   * 
   * @param status
   * @param requested
   * @return
   */
  public Long getStatusCount(DatasetStatus status, boolean requested);

  /**
   * Returns the dataset with the specified name and study
   * 
   * @param study
   * @param name
   * @return
   */
  public Dataset getDatasetByName(String studyName, String datasetName);

  /**
   * Returns the dataset by the prefix id
   * 
   * @param datasetId
   * @return
   */
  public Dataset getByPrefixedId(String datasetId);

  public List<Dataset> getDatasetsByStatus(DatasetStatus status);

}
