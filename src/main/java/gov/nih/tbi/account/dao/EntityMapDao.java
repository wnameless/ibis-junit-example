package gov.nih.tbi.account.dao;

import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.EntityMap;
import gov.nih.tbi.account.model.hibernate.PermissionGroup;
import gov.nih.tbi.commons.dao.GenericDao;
import gov.nih.tbi.commons.model.DatasetStatus;
import gov.nih.tbi.commons.model.EntityType;

import java.util.List;
import java.util.Set;

public interface EntityMapDao extends GenericDao<EntityMap, Long> {

  /**
   * Used to get singular account privileges
   * 
   * @param account
   * @param type
   * @param entityId
   * @return
   */
  public EntityMap get(Account account, EntityType type, Long entityId);

  /**
   * Overloaded get, used to include rules for admin and permission groups
   * 
   * @param account
   * @param currentGroups
   * @param type
   * @param entityId
   * @param isAdmin
   * @return
   */
  public EntityMap get(Account account, Set<PermissionGroup> currentGroups,
      EntityType type, Long entityId, boolean isAdmin);

  public List<EntityMap> listUserAccess(Account account,
      Set<PermissionGroup> currentGroups, EntityType type, boolean isAdmin);

  public List<EntityMap> listEntityAccess(Long entityId, EntityType type);

  /**
   * Returns the EntityMap with PermissionType of Owner for the given entityId
   * 
   * @param entityId
   *          of the entity to search for the owner of
   * @param type
   *          of the entity
   * @return
   */
  public EntityMap getOwnerEntityMap(Long entityId, EntityType type);

  /**
   * Returns the set of entities of a given type owned by the given account
   * 
   * @param account
   *          the owner of the entities
   * @param type
   *          the type of entities
   * @return
   */
  public Set<EntityMap> listOwnedEntities(Account account, EntityType type);

  public EntityMap get(PermissionGroup permissionGroup, EntityType type,
      Long entityId);

  public List<EntityMap> getEntityMapsForDatasetByDatasetStatus(
      DatasetStatus status);

  public List<EntityMap> get(Long entityId, EntityType type);

  public EntityMap get(PermissionGroup permissionGroup, EntityType type,
      Long entityId, boolean isFlag);

}
