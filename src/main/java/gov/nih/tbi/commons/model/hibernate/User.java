package gov.nih.tbi.commons.model.hibernate;

import gov.nih.tbi.ModelConstants;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "TBI_USER")
public class User implements Serializable {

  private static final long serialVersionUID = -2525276531296178501L;

  static Logger logger = Logger.getLogger(User.class);

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "TBI_USER_SEQ")
  @SequenceGenerator(name = "TBI_USER_SEQ", sequenceName = "TBI_USER_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "EMAIL")
  private String email;

  /*******************************************************************/

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getFirstName() {

    return firstName;
  }

  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  public String getLastName() {

    return lastName;
  }

  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  public String getEmail() {

    return email;
  }

  public void setEmail(String email) {

    this.email = email;
  }

  /*******************************************************************/

  /**
   * Returns the full name of the user in the format: First Last
   * 
   * @return String
   */
  public String getFullName() {

    String first = ModelConstants.EMPTY_STRING;
    String second = ModelConstants.EMPTY_STRING;

    logger.debug("getFullName Function called on: " + toString());

    if (firstName != null) {
      if (firstName.length() > 1) {
        first =
            firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
      } else {
        first = firstName.toUpperCase();
      }
    }

    if (lastName != null) {
      if (lastName.length() > 1) {
        second = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
      } else {
        second = lastName.toUpperCase();
      }
    }

    return first + " " + second;
  }

  @Override
  public String toString() {

    return "User [firstName=" + firstName + ", id=" + id + ", lastName="
        + lastName + "]";
  }

  @Override
  public boolean equals(Object obj) {

    if (obj instanceof User) {
      if (this == obj) {
        return true;
      }
      User other = (User) obj;
      if (this.firstName.equals(other.firstName) == true) {
        if (this.lastName.equals(other.lastName) == true) {
          if (this.email.equals(other.email) == true) {
            return true;
          }
        }
      }
    }

    return false;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    return result;
  }

}
