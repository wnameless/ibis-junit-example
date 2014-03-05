package gov.nih.tbi.account.service.complex;

import gov.nih.tbi.ModulesConstants;
import gov.nih.tbi.account.model.AccountUserDetails;
import gov.nih.tbi.account.model.hibernate.Account;
import gov.nih.tbi.account.model.hibernate.AccountRole;
import gov.nih.tbi.account.ws.AccountProvider;
import gov.nih.tbi.account.ws.RestAccountProvider;
import gov.nih.tbi.commons.model.RoleStatus;
import gov.nih.tbi.commons.service.AccountManager;
import gov.nih.tbi.commons.service.DictionaryToolManager;
import gov.nih.tbi.commons.service.ServiceConstants;
import gov.nih.tbi.commons.ws.HashMethods;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

/**
 * This class is used by spring security to validate the user and identify what
 * granted roles should be given Also this now implements the post valid and
 * invalid login attempts
 * 
 * @author Andrew Johnson
 * 
 */
@Service
public class AccountDetailService implements UserDetailsService,
    AuthenticationUserDetailsService, Serializable,
    AuthenticationSuccessHandler, AuthenticationFailureHandler {

  private static Logger logger = Logger.getLogger(AccountDetailService.class);

  /**
	 * 
	 */
  private static final long serialVersionUID = 2628156955029307389L;

  @Autowired
  protected AccountManager accountManager;

  @Autowired
  protected DictionaryToolManager dictionaryManager;

  private static SavedRequestAwareAuthenticationSuccessHandler SUCCESS_HANDLER =
      new SavedRequestAwareAuthenticationSuccessHandler();

  @Autowired
  ModulesConstants modulesConstants;

  /**
   * Method Used for AuthenticationUserDetailsService Loads UserDetails for
   * Spring Security
   * 
   * @param auth
   * @return
   * @throws UsernameNotFoundException
   */
  public UserDetails loadUserDetails(Authentication auth)
      throws UsernameNotFoundException {

    logger.debug("loadUserDetails: " + auth.getName());

    // TODO:FIXME This web service can't exist
    AccountProvider accountProvider = null;
    try {
      accountProvider =
          new AccountProvider(modulesConstants.getModulesAccountURL());
    } catch (MalformedURLException e) {

      e.printStackTrace();
    }
    Account acc = accountProvider.getByUserName(auth.getName());

    AccountUserDetails userDetails = null;

    if (acc != null) {
      userDetails = new AccountUserDetails(acc);
    } else {
      throw new UsernameNotFoundException(auth.getName());
    }

    return userDetails;
  }

  /**
   * Method used for UserDetailsService Loads UserDetails for Spring Security
   * 
   * @param username
   * @return
   * @throws UsernameNotFoundException
   * @throws DataAccessException
   */
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {

    logger.debug("loadUserByUserName: " + username);

    AccountProvider accountProvider = null;
    try {
      accountProvider =
          new AccountProvider(modulesConstants.getModulesAccountURL(),
              modulesConstants.getAdministratorUsername(),
              HashMethods.getServerHash(
                  modulesConstants.getAdministratorUsername(),
                  modulesConstants.getAdministratorPassword()));
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Account acc = accountProvider.getByUserName(username);
    AccountUserDetails userDetails = null;

    if (acc != null) {
      acc.setDiseaseKey("-1");
      // XXX Form based login provides a dieaseId of -1L (The default disease)
      // (unrelieable)
      userDetails =
          new AccountUserDetails(acc, ServiceConstants.DEFAULT_PROVIDER);
    } else {
      throw new UsernameNotFoundException(username);
    }

    return userDetails;
  }

  /**
   * Method to load authorization when security is using CAS system
   * 
   * @param auth
   * @return
   * @throws UsernameNotFoundException
   * @throws DataAccessException
   * @throws UnsupportedEncodingException
   */
  public UserDetails loadUserByAuthentication(Authentication auth)
      throws UsernameNotFoundException, DataAccessException,
      UnsupportedEncodingException {

    logger.debug("loadUserByAuth: " + auth.getName());

    // Get the disease attribute
    Long diseaseId = null;
    String diseaseString =
        (String) ((CasAssertionAuthenticationToken) auth).getAssertion()
            .getPrincipal().getAttributes().get("disease");
    if (diseaseString != null) {
      diseaseId = Long.valueOf(diseaseString);
    }

    // Use a web-service call to get account information if the current module
    // does not support account
    Account acc = null;
    Boolean accountsActive = modulesConstants.getModulesAccountEnabled();
    if (accountsActive != null && accountsActive.equals(true)) {
      acc = accountManager.getAccountByUserName(auth.getName());
    } else {
      // This is a manual call for what PortalUtils.getProxyTicket() does in the
      // rest of the application.
      String accUrl = modulesConstants.getModulesAccountURL(diseaseId);
      String pt =
          ((CasAssertionAuthenticationToken) auth)
              .getAssertion()
              .getPrincipal()
              .getProxyTicketFor(
                  modulesConstants.getModulesAccountURL(diseaseId)
                      + "portal/j_spring_cas_security_check");
      logger
          .debug("Attempting to get security account through rest with proxy ticket: "
              + pt);
      logger.debug("Creating accountProvider with url: " + accUrl);
      RestAccountProvider accountProvider = new RestAccountProvider(accUrl, pt);
      acc = accountProvider.getByUserName(auth.getName());
      logger.debug("Account aquired: " + acc.getUserName());
    }

    if (diseaseString != null) {
      acc.setDiseaseKey(diseaseString);
    }
    AccountUserDetails userDetails = null;

    if (acc != null) {
      userDetails = new AccountUserDetails(acc, diseaseId);
    } else {
      throw new UsernameNotFoundException(auth.getName());
    }

    return userDetails;
  }

  /**
   * Convenience method to convert AccountRoles into GrantedAuthorities
   * 
   * @param accountRoleList
   * @return
   */
  public static Collection<GrantedAuthority> getAuthorities(
      Collection<AccountRole> accountRoleList) {

    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

    Date currentDate = new Date();

    for (AccountRole ar : accountRoleList) {
      if (ar.getRoleStatus().equals(RoleStatus.ACTIVE)
          && (ar.getExpirationDate() == null || currentDate.before(ar
              .getExpirationDate()))) {
        authorities.add(new GrantedAuthorityImpl(ar.getRoleType().getName()));
      }
    }

    return authorities;
  }

  /**
   * Method used by the spring security background to clear failed login
   * attempts
   */
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {

    logger.info(authentication.getName() + " has successfully logged in.");

    // Make updates to account
    // {
    // Account account = accountDao.getByUserName(authentication.getName());
    //
    // account.setLastSuccessfulLogin(new Date());
    // account.setUnlockDate(null);
    //
    // accountDao.save(account);
    //
    // // wipe out the failed login table
    // failedLoginAttemptDao.removeAll(account);
    // }

    // forward the page onto the original url
    SUCCESS_HANDLER.onAuthenticationSuccess(request, response, authentication);
  }

  /**
   * Method used by the spring security to update the failed login attempts
   * table, or help inform the user when they can login next.
   */
  public void onAuthenticationFailure(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {

    // Got a null pointer here so added check
    if (exception != null && exception.getAuthentication() != null
        && exception.getAuthentication().getName() != null) {
      logger.debug("Someone failed to log into the system with the username: "
          + exception.getAuthentication().getName());
      logger.debug("exception type was: " + exception.getClass());
    }

    String redirectUrl = ServiceConstants.LOGIN_FAILURE_DEFAULT;

    // Make updates to failed attempts table if the credentials were wrong
    if (exception instanceof BadCredentialsException) {
      // Don't log a blank username/password combination
      if (!(ServiceConstants.EMPTY_STRING.equals(exception.getAuthentication()
          .getName()) && ServiceConstants.EMPTY_STRING
          .equals((String) exception.getAuthentication().getCredentials()))) {

        // Account account =
        // accountDao.getByUserName(exception.getAuthentication().getName());
        //
        // if (account != null)
        // {
        // Calendar cal15 = Calendar.getInstance();
        // cal15.add(Calendar.MINUTE, ServiceConstants.LOCKOUT_WINDOW_FIRST);
        //
        // // Get all account failed logins for the last 15 minutes.
        // List<FailedLoginAttempt> loginAttempts =
        // failedLoginAttemptDao.getAll(account, cal15.getTime());
        //
        // if (loginAttempts.size() == ServiceConstants.LOCKOUT_ATTEMPTS_FIRST)
        // {
        // Calendar calNext = Calendar.getInstance();
        // calNext.add(Calendar.MINUTE, ServiceConstants.LOCKOUT_LENGTH_FIRST);
        //
        // account.setUnlockDate(calNext.getTime());
        // request.getSession().setAttribute(ServiceConstants.UNLOCK_DATE_PARAM,
        // account.getUnlockDate());
        //
        // accountDao.save(account);
        // }
        //
        // Get all the consecutive failed logins
        // because this we clear the log, this is every failed attempt in the
        // logs
        // loginAttempts = failedLoginAttemptDao.getAll(account,
        // account.getLastSuccessfulLogin());
        //
        // if (loginAttempts.size() == ServiceConstants.LOCKOUT_ATTEMPTS_SECOND)
        // {
        // Calendar calNext = Calendar.getInstance();
        // calNext.add(Calendar.MINUTE, ServiceConstants.LOCKOUT_LENGTH_SECOND);
        //
        // account.setUnlockDate(calNext.getTime());
        // request.getSession().setAttribute(ServiceConstants.UNLOCK_DATE_PARAM,
        // account.getUnlockDate());
        //
        // accountDao.save(account);
        // }
        //
        // }
        //
        // Add a new row into the failed attempts table
        // FailedLoginAttempt currentAttempt = new FailedLoginAttempt();
        // currentAttempt.setAccount(account);
        // currentAttempt.setEventDate(new Date());
        //
        // failedLoginAttemptDao.save(currentAttempt);
      }

    }
    // sets information for the login-error page to display when the account is
    // unlocked
    // else
    if (exception instanceof LockedException) {
      // Account account =
      // accountDao.getByUserName(exception.getAuthentication().getName());
      // request.getSession().setAttribute(ServiceConstants.UNLOCK_DATE_PARAM,
      // account.getUnlockDate());
    } else if (exception instanceof CredentialsExpiredException) {
      redirectUrl = ServiceConstants.LOGIN_FAILURE_EXPIRED;
    }

    SimpleUrlAuthenticationFailureHandler FAILURE_HANDLER =
        new SimpleUrlAuthenticationFailureHandler(redirectUrl);

    // forwards the user onto the login error page.
    FAILURE_HANDLER.onAuthenticationFailure(request, response, exception);
  }

}
