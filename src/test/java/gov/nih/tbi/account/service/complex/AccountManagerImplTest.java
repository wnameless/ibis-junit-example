package gov.nih.tbi.account.service.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.nih.tbi.commons.service.AccountManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class AccountManagerImplTest {

  @InjectMocks
  AccountManagerImpl manager;

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

}
