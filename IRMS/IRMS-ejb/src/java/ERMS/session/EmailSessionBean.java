/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ser3na
 */
@Stateless
@LocalBean
public class EmailSessionBean {

    String emailServerName = "smtp.gmail.com";

    public EmailSessionBean() {
    }

    public void emailInitialPassward(String toEmailAdress, String initialPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("IRMS.IT09", "1qaz@WSX");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("is3102_it09@live.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmailAdress));
            message.setSubject("Initial Password");
            message.setText("Greeting from Coral Island Resort!"
                    + "\n\n Here is your initial password:" + initialPassword);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}