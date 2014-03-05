package gov.nih.tbi.account.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mode for country references
 * 
 * @author Francis Chen
 * 
 */
@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable {

  private static final long serialVersionUID = 8427017867199158327L;

  @Id
  private Long id;

  @Column(name = "NAME")
  private String name;

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return name;
  }
}
