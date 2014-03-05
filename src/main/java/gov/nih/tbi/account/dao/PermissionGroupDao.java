package gov.nih.tbi.account.dao;

import java.util.List;

import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.PermissionGroup;
import gov.nih.tbi.commons.dao.GenericDao;

public interface PermissionGroupDao extends GenericDao<PermissionGroup, Long> {

  public List<PermissionGroup> getPublicGroups();

  public List<PermissionGroup> getGrantedGroups(Account account);

  public PermissionGroup getByName(String groupName);

  public List<PermissionGroup> getPrivateGroups();
}
