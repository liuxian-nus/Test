/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.IndReservationEntity;
import FBMS.entity.OrderEntity;
import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
            
            String INPUTFILE = createBill(toEmailAddress,ire);
            
            //Below attach the bill within the email
                MimeBodyPart messageBodyPart = new MimeBodyPart();

                Multipart multipart = new MimeMultipart();

                messageBodyPart = new MimeBodyPart();
                String file;
                    file = INPUTFILE;
                String fileName = "CorelResort:Table Reservation";
               // DataSource source = new FileDataSource(file);
               // messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName);
                messageBodyPart.attachFile(file);
                multipart.addBodyPart(messageBodyPart);

                ((MimeMessage)message).setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

            System.out.println("EmailSessionBean: the email has been done!");
            
            

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FBEmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FBEmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FBEmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return true;
    }

    @Override
     public boolean sendConfirmation (String toEmailAddress,OrderEntity oe){
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
            message.setSubject("COREL ISLAND RESORT: Your confirmation for catering order");
            message.setText("Greeting from Coral Island Resort!"
                    + "\nHere is confirmation number:"+ oe.getOrderId()
                    +
                    "\nPlease use this confirmation number for later modification on our website \n\n"
                    +"Title: "+oe.getTitle()+"\nName: "+oe.getName()
                    +"\nNumber of people: "+oe.getMenu().getNumberOrder()
                    +"\nDate & Time: "+oe.getOrderDateTime()
                    +"\nMobile Number: "+oe.getMobile()
                    +"\nNotes: "+oe.getNotes()
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team");
                    

            Transport.send(message);

            System.out.println("EmailSessionBean: the email has been done!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
        return true;
     }
    
     public String createBill(String toEmailAddress,IndReservationEntity ire) throws FileNotFoundException, DocumentException
     {
        
            //Below generate a PDF file
            Document document;
            document = new Document(PageSize.A4,50,50,50,50);
            String OUTPUTFILE = "C:\\Users\\Diana Wang\\Documents\\Diana\\Table_Reservation"+ire.getRestaurant().getRestName()
                    +ire.getId()+".pdf";
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
            document.open();
            //Below draft the contents
            Anchor anchorTarget = new Anchor ("Your Reservation Details");
            anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();
            paragraph1.setSpacingBefore(50);
            document.add(new Paragraph("Please print this voucher and bring it along to redeem",
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,new CMYKColor(0, 255, 0, 0))));
            document.close();
            return OUTPUTFILE;
     }
}
