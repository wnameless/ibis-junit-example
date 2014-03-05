package gov.nih.tbi.commons.model.hibernate;

import gov.nih.tbi.commons.model.AbstractAuditObject;
import gov.nih.tbi.commons.model.AuditType;
import gov.nih.tbi.commons.model.EntityType;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TRAIL")
public class AuditLog extends AbstractAuditObject implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = -1342559822327330804L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAIL_SEQ")
  @SequenceGenerator(name = "TRAIL_SEQ", sequenceName = "TRAIL_SEQ",
      allocationSize = 1)
  private Long id;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "TABLE_ID")
  private EntityType entityType;

  @Column(name = "ORIGINAL_ID")
  private Long entityId;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "CHANGE_ID")
  private AuditType auditType;

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public EntityType getEntityType() {

    return entityType;
  }

  public void setEntityType(EntityType entityType) {

    this.entityType = entityType;
  }

  public Long getEntityId() {

    return entityId;
  }

  public void setEntityId(Long entityId) {

    this.entityId = entityId;
  }

  public AuditType getAuditType() {

    return auditType;
  }

  public void setAuditType(AuditType auditType) {

    this.auditType = auditType;
  }

}
