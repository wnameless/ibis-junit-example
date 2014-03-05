package gov.nih.tbi.account.service.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gov.nih.tbi.ModulesConstants;
import gov.nih.tbi.account.dao.AccountDao;
import gov.nih.tbi.account.dao.EntityMapDao;
import gov.nih.tbi.account.dao.PermissionGroupDao;
import gov.nih.tbi.account.dao.PermissionGroupMemberDao;
import gov.nih.tbi.account.dao.PermissionMapDao;
import gov.nih.tbi.account.dao.PreviousPasswordDao;
import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.EntityMap;
import gov.nih.tbi.commons.dao.UserDao;
import gov.nih.tbi.commons.model.EntityType;
import gov.nih.tbi.commons.model.PermissionType;
import gov.nih.tbi.commons.service.AccountManager;
import gov.nih.tbi.commons.service.StaticReferenceManager;
import gov.nih.tbi.commons.service.util.MailEngine;
import gov.nih.tbi.repository.dao.DatasetDao;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;

public class AccountManagerImplTest {

  @InjectMocks
  AccountManagerImpl manager;

  @Mock
  EntityMapDao entityMapDao;

  @Mock
  PermissionMapDao permissionMapDao;

  @Mock
  AccountDao accountDao;

  @Mock
  PreviousPasswordDao previousPasswordDao;

  @Mock
  MailEngine mailEngine;

  @Mock
  UserDao userDao;

  @Mock
  PermissionGroupDao permissionGroupDao;

  @Mock
  PermissionGroupMemberDao permissionGroupMemberDao;

  @Mock
  StaticReferenceManager staticManager;

  @Mock
  RoleHierarchy roleHierarchy;

  @Mock
  ModulesConstants modulesConstants;

  @Mock
  DatasetDao datasetDao;

  @Mock
  EntityMap entityMap;

  @Mock
  Account account;

  @Mock
  Account account;

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void accountManagerInterfaceIsImplemented() {
    assertTrue(manager instanceof AccountManager);
  }

  @Test
  public void classNameIsUsedForLoggerName() {
    assertEquals(AccountManagerImpl.class.getName(),
        AccountManagerImpl.logger.getName());
  }

  @Test
  public void existedAccountCanBeUpdatedByRegistering() {
    when(
        entityMapDao.get(any(Account.class), any(EntityType.class),
            any(Long.class))).thenReturn(entityMap);
    manager.registerEntity(account, EntityType.DATA_ELEMENT, 123L,
        PermissionType.ADMIN);
    verify(entityMap, times(1)).setPermission(any(PermissionType.class));
    verify(entityMapDao, times(1)).save(any(EntityMap.class));
  }

  @Test
  public void newAccountCanBeSavedByRegistering() {
    when(
        entityMapDao.get(any(Account.class), any(EntityType.class),
            any(Long.class))).thenReturn(null);
    ArgumentCaptor<EntityMap> captor = ArgumentCaptor.forClass(EntityMap.class);
    manager.registerEntity(account, EntityType.DATA_ELEMENT, 123L,
        PermissionType.ADMIN);
    verify(entityMapDao).save(captor.capture());
    EntityMap entityMap = captor.getValue();
    assertEquals(Long.valueOf(123L), entityMap.getEntityId());
    assertSame(account, entityMap.getAccount());
    assertSame(EntityType.DATA_ELEMENT, entityMap.getType());
    assertSame(PermissionType.ADMIN, entityMap.getPermission());
    verify(entityMapDao, times(1)).save(any(EntityMap.class));
  }

  @Test
  public void nullAccountIsNotAcceptedByRegisterEntity() {
    expectedEx.expect(NullPointerException.class);
    expectedEx.expectMessage("Account can't be null.");
    manager.registerEntity((Account) null, EntityType.DATA_ELEMENT, 123L,
        PermissionType.ADMIN);
  }

  @Test
  public void nullEntityTypeIsNotAcceptedByRegisterEntity() {
    expectedEx.expect(NullPointerException.class);
    expectedEx.expectMessage("EntityType can't be null.");
    manager.registerEntity(account, null, 123L, PermissionType.ADMIN);
  }

  @Test
  public void nullEntityIdIsNotAcceptedByRegisterEntity() {
    expectedEx.expect(NullPointerException.class);
    expectedEx.expectMessage("EntityId can't be null.");
    manager.registerEntity(account, EntityType.DATA_ELEMENT, null,
        PermissionType.ADMIN);
  }

  @Test
  public void nullPermissionTypeIsNotAcceptedByRegisterEntity() {
    expectedEx.expect(NullPointerException.class);
    expectedEx.expectMessage("PermissionType can't be null.");
    manager.registerEntity(account, EntityType.DATA_ELEMENT, 123L, null);
  }

}
