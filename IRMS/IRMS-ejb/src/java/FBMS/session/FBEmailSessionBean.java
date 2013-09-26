/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.IndReservationEntity;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class FBEmailSessionBean implements FBEmailSessionBeanRemote {

    String emailServerName = "smtp.gmail.com";
    
    public FBEmailSessionBean(){}
    
    @Override
    public boolean sendConfirmation (String toEmailAddress,IndReservationEntity ire)
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("is3102.it09", "weloveTWK");
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("is3102.it09@gmail.com"));
            //message.setRecipients(new InternetAddress(""));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmailAddress));
            message.setSubject("COREL ISLAND RESORT: Your confirmation for restaurant");
            message.setText("Greeting from Coral Island Resort!"
                    + "\nHere is confirmation number:"+ ire.getIndReservationId()+
                    "\nPlease use this confirmation number for later modification on our website \n\n"
                    +"Title: "+ire.getTitle()+"\nName: "+ire.getName()
                    +"\nNumber of people: "+ire.getNumberPeople()
                    +"\nRestaurant Name: "+ire.getRestaurant().getRestName()
                    +"\nDate & Time: "+ire.getIndReservationDateTime()
                    +"\nMobile Number: "+ire.getMobile()
                    +"\nNotes: "+ire.getNotes()
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team");
                    

            Transport.send(message);

            System.out.println("EmailSessionBean: the email has been done!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
            
        return true;
    }

}
