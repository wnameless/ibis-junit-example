/**
 *
 * @author Wei-Ming Wu
 *
 *
 * Copyright 2014 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package gov.nih.tbi.account.service.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.nih.tbi.commons.service.AccountManager;

import java.lang.annotation.Annotation;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Scope;

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

  @Test
  public void classNameIsUsedForLoggerName() {
    assertEquals(AccountManagerImpl.class.getName(),
        AccountManagerImpl.logger.getName());
  }

  @Test
  public void springScopeSingletonAnnotationIsAnnotated() {
    for (Annotation anno : AccountManagerImpl.class.getDeclaredAnnotations()) {
      if (anno instanceof Scope) {
        Scope scopeAnno = (Scope) anno;
        assertEquals("singleton", scopeAnno.value());
        return;
      }
    }
    fail();
  }

}
