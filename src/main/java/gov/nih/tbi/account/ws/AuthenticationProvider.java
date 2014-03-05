package gov.nih.tbi.account.ws;

import gov.nih.tbi.account.ws.model.UserLogin;
import gov.nih.tbi.commons.ws.HashMethods;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;

import org.apache.log4j.Logger;

public class AuthenticationProvider {

  /***************************************************************************************************/

  private static Logger logger = Logger.getLogger(AuthenticationProvider.class);

  private static final String namespaceURI =
      "http://cxf.ws.account.tbi.nih.gov/";
  private static final String serviceName =
      "AuthenticationWebServiceImplService";
  private static final String portName = "AuthenticationWebServiceImplPort";
  private static final String wsdlExtension =
      "portal/ws/authenticationWebService?wsdl";

  private static URL WSDL_LOCATION;

  /***************************************************************************************************/

  private Service service;
  private AuthenticationWebService authenticationWebService;

  private UserLogin userLogin;

  /***************************************************************************************************/

  /**
   * Constructor that takes the web service WSDL URL, username, and password
   * 
   * @param serverLocation
   * @param userName
   * @param password
   * @throws MalformedURLException
   */
  public AuthenticationProvider(String serverLocation, String userName,
      String password) throws MalformedURLException {

    WSDL_LOCATION =
        new URL(AuthenticationProvider.class.getResource("."), serverLocation
            + wsdlExtension);
    userLogin =
        new UserLogin(userName, HashMethods.getClientHash(userName),
            HashMethods.getClientHash(HashMethods.getServerHash(userName),
                password));

    service =
        Service.create(WSDL_LOCATION, new QName(namespaceURI, serviceName));
    authenticationWebService = getWebService();

    if (!authenticationWebService.authenticate(userLogin)) {
      throw new RuntimeException("User failed to authenticate");
    }
  }

  /**
   * Constructor that takes the web service WSDL URL
   * 
   * @param dictionaryWsdlLocation
   * @throws MalformedURLException
   */
  public AuthenticationProvider(String serverLocation)
      throws MalformedURLException {

    this(serverLocation, "anonymous", "");
  }

  public AuthenticationProvider() throws MalformedURLException {

  }

  /**
   * Creates a new connection to consume the web service. This should only need
   * to be called once in a program.
   * 
   * @return
   */
  @WebEndpoint(name = "authenticationWebService")
  private AuthenticationWebService getWebService() {

    return service.getPort(new QName(namespaceURI, portName),
        AuthenticationWebService.class);
  }

  protected UserLogin getUserLogin() {

    return userLogin;
  }
}
