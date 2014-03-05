package gov.nih.tbi.commons.dao;

import java.util.List;

import gov.nih.tbi.commons.model.hibernate.User;

public interface UserDao extends GenericDao<User, Long> {

  /**
   * Get a user record based on the email address
   * 
   * @param email
   * @return
   */
  public User getByEmail(String email);

  /**
   * Gets all of the emails
   * 
   * @return
   */
  public List<String> getAllEmails();
}
