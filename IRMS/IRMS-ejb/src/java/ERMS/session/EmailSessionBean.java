/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ACMS.entity.RoomEntity;
import java.util.Properties;
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
                return new PasswordAuthentication("is3102.it09", "weloveTWK");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("is3102.it09@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmailAdress));
            message.setSubject("Initial Password");
            message.setText("Greeting from Coral Island Resort!"
                    + "\nHere is your initial password:" + initialPassword +
                    "\nPlease login immediately to change your password.\n\n\nBest Regards,\nThe Coral Island Management Team");
                    

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void emailCorporateBill(String toEmailAdress, RoomEntity room) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chrislx.nus", "ldc!((!1991");
            }
        });

        try {
            double totalBill = room.getRoomPrice().getPrice()*5 + room.getRoomServiceCharge();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("chrislx.nus@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmailAdress));
            message.setSubject("Your bill from Coral Island Resort");
            message.setText("Thank you for coming to Coral Island Resort!"
                    + "\nYour staff " +room.getGuestName() + " has come to our hotel "
                    + "\nfrom " + room.getCheckInDate() + " to " + room.getCheckOutDate()
                    + "\nThe room fee is " + room.getRoomPrice().getPrice() + " per day,"
                    + " \nand the service charge is " +room.getRoomServiceCharge()
                    + "\nYou have a total bill of " + totalBill
                    + "\nPlease clear your bill in 10 days."
                    + "\nThank you. " 
                    + "\n\nIn case of any issues and inqueries, you may contact our corporate service manager "
                    + "\n@ 65-8180 1380"
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team");
                    
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
