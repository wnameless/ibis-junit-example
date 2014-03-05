package gov.nih.tbi.account.service.complex;

import static org.junit.Assert.assertTrue;
import gov.nih.tbi.commons.service.AccountManager;

import org.junit.Before;
import org.junit.Test;

public class AccountManagerImplTest {

  AccountManagerImpl manager;

  @Before
  public void setUp() throws Exception {
    manager = new AccountManagerImpl();
  }

  @Test
  public void accountManagerInterfaceIsImplemented() {
    assertTrue(manager instanceof AccountManager);
  }

}
