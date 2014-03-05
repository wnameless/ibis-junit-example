package gov.nih.tbi.commons.service.util;

import java.io.Serializable;

import gov.nih.tbi.CoreConstants;
import gov.nih.tbi.ModulesConstants;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Mail Engine class creates a simple way for the service layer to send an
 * email.
 * 
 * @author Andrew Johnson
 * 
 */
@Component
public class MailEngine implements Serializable {

  private static final long serialVersionUID = 7533221379126705380L;

  @Autowired
  protected ModulesConstants modulesConstants;

  private MailSender mailSender;
  private String defaultFrom;

  public MailEngine(MailSender mailSender) {

    this.mailSender = mailSender;
  }

  /**
   * Send a basic email, explicitly call out the from email address.
   * 
   * @param subject
   * @param htmlMessage
   * @param from
   *          (null implies default from address, most common case)
   * @param to
   * @throws MessagingException
   */
  public void sendMail(String subject, String htmlMessage, String from,
      String... to) throws MessagingException {

    MimeMessage message = ((JavaMailSenderImpl) mailSender).createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    if (to == null || to.length == 0) {
      throw new IllegalArgumentException("TO must have at least one address");
    }

    helper.setTo(to);
    if (from == null || CoreConstants.EMPTY_STRING.equals(from.trim())) {
      this.defaultFrom = modulesConstants.getModulesOrgEmail();
      helper.setFrom(defaultFrom);
    } else {
      helper.setFrom(from);
    }
    helper.setText(htmlMessage, true);
    helper.setSubject(subject);

    ((JavaMailSenderImpl) mailSender).send(message);
  }

}
