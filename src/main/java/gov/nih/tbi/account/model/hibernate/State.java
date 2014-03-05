package gov.nih.tbi.account.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model for storing USA State references
 * 
 * @author Francis Chen
 */
@Entity
@Table(name = "STATE")
public class State implements Serializable {

  private static final long serialVersionUID = -4355534373965146780L;

  @Id
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "CODE")
  private String code;

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public String getCode() {

    return code;
  }

  public void setCode(String code) {

    this.code = code;
  }
}
