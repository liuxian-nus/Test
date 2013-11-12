/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.CourseEntity;
import FBMS.entity.IndReservationEntity;
import FBMS.entity.OrderEntity;
import FBMS.entity.ReceiptEntity;
import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
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
public class FBEmailSessionBean implements FBEmailSessionBeanRemote, Serializable {

    String emailServerName = "smtp.gmail.com";

    public FBEmailSessionBean() {
    }

    @Override
    public boolean sendConfirmation(String toEmailAddress, IndReservationEntity ire) {
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
            String text = "Greeting from Coral Island Resort!"
                    + "\nHere is confirmation number:" + ire.getIndReservationId()
                    + "\nPlease use this confirmation number for later modification on our website \n\n"
                    + "Title: " + ire.getTitle() + "\nName: " + ire.getName()
                    + "\nNumber of people: " + ire.getNumberPeople()
                    + "\nRestaurant Name: " + ire.getRestaurant().getRestName()
                    + "\nDate & Time: " + ire.getIndReservationDateTime()
                    + "\nMobile Number: " + ire.getMobile()
                    + "\nNotes: " + ire.getNotes()
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team";
            // message.setText(text); No use already

            String INPUTFILE = createBill(toEmailAddress, ire);

            //Below attach the bill within the email
            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            String fileName = "CorelResort:Table Reservation";
            // DataSource source = new FileDataSource(file);
            // messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);

            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);


            ((MimeMessage) message).setContent(multipart);

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

    public boolean sendIssueGoods(String toEmailAddress, OrderEntity order) throws IOException, FileNotFoundException, DocumentException {
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
            message.setSubject("COREL ISLAND RESORT: Your order has been delivered");
            String text = "Greeting from Coral Island Resort!"
                    + "\nYour order has been delivered"
                    + "\nOrder ID: " + order.getOrderId()
                    + "\nMenu Type: " + order.getMenu().getType()
                    + "\nPlease check your order, and once you have any enquiries, please feel free to contact us! \n\n"
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team";
            // message.setText(text); No use already



            String INPUTFILE = createIssueGoodsPDF(toEmailAddress,order);
            
            //Below attach the bill within the email
            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            String fileName = "CorelResort:Goods Issued Confirmation";
            
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);

            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);


            ((MimeMessage) message).setContent(multipart);
            
            Transport.send(message);
            
            System.out.println("Sending");

            System.out.println("EmailSessionBean: the email has been done!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }

        return true;
    }

    public boolean sendInvoice(String toEmailAddress, OrderEntity order) throws IOException, FileNotFoundException, DocumentException
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
            message.setSubject("COREL ISLAND RESORT: Your order invoice has been issued!");
            String text = "Greeting from Coral Island Resort!"
                    + "\nYour order invoice has been issued"
                    + "\nOrder ID: " + order.getOrderId()
                    + "\nMenu Type: " + order.getMenu().getType()
                    + "\nPlease check your order, and once you have any enquiries, please feel free to contact us! \n\n"
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team";
            // message.setText(text); No use already



            String INPUTFILE = createInvoicePDF(toEmailAddress,order);
            
            //Below attach the bill within the email
            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            String fileName = "CorelResort:Catering Invoice";
            
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);

            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);


            ((MimeMessage) message).setContent(multipart);
            
            Transport.send(message);
            
            System.out.println("Sending");

            System.out.println("EmailSessionBean: the email has been done!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
            
            
       
    }
          return true;
    }
    
    public boolean sendReceipt(String toEmailAddress,ReceiptEntity re) throws IOException, FileNotFoundException, DocumentException
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
            message.setSubject("COREL ISLAND RESORT: Your order receipt has been issued!");
            String text = "Greeting from Coral Island Resort!"
                    + "\nYour order receipt has been issued"
                    + "\nReceipt ID: " + re.getId()
                    + "\nReceipt Issued Date: " + re.getReceiptDate()
                    + "\n"
                    + "\nPlease check your order, and once you have any enquiries, please feel free to contact us! \n\n"
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team";
            // message.setText(text); No use already



            String INPUTFILE = createReceiptPDF(toEmailAddress,re);
            
            //Below attach the bill within the email
            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            String fileName = "CorelResort:Catering Receipt";
            
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);

            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);


            ((MimeMessage) message).setContent(multipart);
            
            Transport.send(message);
            
            System.out.println("Sending");

            System.out.println("EmailSessionBean: the email has been done!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
            
            
       
    }
        return true;
    }
    @Override
    public boolean sendConfirmation(String toEmailAddress, OrderEntity oe) {
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
            String text = "Greeting from Coral Island Resort!"
                    + "\nHere is confirmation number:" + oe.getOrderId()
                    + "\nPlease use this confirmation number for later modification on our website \n\n"
                    + "Title: " + oe.getTitle() + "\nName: " + oe.getName()
                    + "\nNumber of people: " + oe.getMenu().getNumberOrder()
                    + "\nDate & Time: " + oe.getOrderDateTime()
                    + "\nMobile Number: " + oe.getMobile()
                    + "\nNotes: " + oe.getNotes()
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team";
            //message.setText(text);

            String INPUTFILE = createBill(toEmailAddress, oe);

            //Below attach the bill within the email
            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            String fileName = "CorelResort:Table Reservation";
            // DataSource source = new FileDataSource(file);
            // messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);

            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);


            ((MimeMessage) message).setContent(multipart);

            System.out.println("Sending");


            Transport.send(message);

            System.out.println("EmailSessionBean: the email has been done!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            Logger.getLogger(FBEmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FBEmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
    
    

    public String createBill(String toEmailAddress, IndReservationEntity ire) throws FileNotFoundException, DocumentException {


        //Below generate a PDF file
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        String OUTPUTFILE = "C:\\Users\\Diana Wang\\Documents\\Diana\\Table_Reservation" + ire.getRestaurant().getRestName()
                + ire.getId() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        // document = addMetaData(document);
        document.open();
        //Below draft the contents

        document = addContent(document);
        document = addTable(document, ire);

        /* Anchor anchorTarget = new Anchor ("Your Reservation Details");
         anchorTarget.setName("BackToTop");
         Paragraph paragraph1 = new Paragraph();
         paragraph1.setSpacingBefore(50);*/
        document.add(new Paragraph("Here is your reservation details, please use your reservation Id to make modifications.",
                FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));


        document.close();
        return OUTPUTFILE;
    }

    private Document addMetaData(Document document) {
        document.addAuthor("Corel Resort");
        document.addCreator("Corel Resort");
        return document;
    }

    private Document addContent(Document document) throws DocumentException {
        //Below specify different types of font
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);

        //Below specify contents
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your restaurant/table booking summary", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);
        //document.newPage();  
        return document;
    }

    //Add a empty line
    private Paragraph addEmptyLine(Paragraph paragraph, int number) {
        for (int k = 0; k < number; k++) {
            paragraph.add(new Paragraph(" "));
        }
        return paragraph;
    }

    private Document addTable(Document document, IndReservationEntity ire) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});

        //Add table header
        PdfPCell c1 = new PdfPCell(new Phrase("Reservation Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        //Add table content
        table.addCell("Reservation Number");
        table.addCell(ire.getIndReservationId().toString());
        table.addCell("Reservation time");
        table.addCell(ire.getIndReservationDateTime().toString());
        table.addCell("Restaurant ");
        table.addCell(ire.getRestaurant().getRestName());
        table.addCell("Number of people");
        table.addCell(ire.getNumberPeople().toString());
        table.addCell("Title & Name");
        table.addCell(ire.getTitle() + " " + ire.getName());
        table.addCell("Email");
        table.addCell(ire.getEmail());
        table.addCell("Contact");
        table.addCell(ire.getMobile());
        table.addCell("Notes");
        table.addCell(ire.getNotes());
        table.addCell("Status");
        table.addCell(ire.getStatus());

        document.add(table);

        return document;
    }

    private String createBill(String toEmailAddress, OrderEntity oe) throws DocumentException, FileNotFoundException {
        //Below generate a PDF file
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        String OUTPUTFILE = "C:\\Users\\Diana Wang\\Documents\\Diana\\Catering_Reservation" + oe.getName()
                + oe.getOrderId() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        // document = addMetaData(document);
        document.open();
        //Below draft the contents

        document = addContent(document);
        document = addTable(document, oe);

        /* Anchor anchorTarget = new Anchor ("Your Reservation Details");
         anchorTarget.setName("BackToTop");
         Paragraph paragraph1 = new Paragraph();
         paragraph1.setSpacingBefore(50);*/
        document.add(new Paragraph("Here is your reservation details, please use your reservation Id to make modifications.",
                FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));


        document.close();
        return OUTPUTFILE;
    }

    private Document addTable(Document document, OrderEntity oe) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});

        //Add table header
        PdfPCell c1 = new PdfPCell(new Phrase("Reservation Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        //Add table contents

        table.addCell("Order Id");
        table.addCell(oe.getOrderId().toString());
        table.addCell("Contact's Name");
        table.addCell(oe.getTitle() + " " + oe.getName());
        table.addCell("Email");
        table.addCell(oe.getEmail());
        table.addCell("Mobile");
        table.addCell(oe.getMobile());
        table.addCell("Order Date&Time");
        table.addCell(oe.getOrderDateTime().toString());
        table.addCell("Number of People");
        table.addCell(oe.getMenu().getNumberOrder().toString());

        table.addCell("Menu");
        table.addCell("");
        Set<CourseEntity> courses = oe.getMenu().getCourses();
        //check if courses is null
        if (!courses.isEmpty()) {
            Iterator<CourseEntity> itr = courses.iterator();
            while (itr.hasNext()) {
                table.addCell("");
                table.addCell(itr.next().getDish().getDishName());
            }
        }


        document.add(table);
        return document;
    }

    private String createIssueGoodsPDF(String toEmailAddress, OrderEntity order) throws FileNotFoundException, DocumentException {
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        
        String OUTPUTFILE = "C:\\Users\\Diana Wang\\Documents\\Diana\\Goods_Issued" + order.getName()
                + order.getId() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        
        document.open();
        
        //Below specify different types of font
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);
        
        //Below specify contents
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your order has been delivered!", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);
        
        //Below add a table
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});
        
        //Add table header
        PdfPCell c1 = new PdfPCell(new Phrase("Reservation Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
        
        //Add table contents

        table.addCell("Order Id");
        table.addCell(order.getOrderId().toString());
        table.addCell("Contact's Name");
        table.addCell(order.getTitle() + " " + order.getName());
        table.addCell("Email");
        table.addCell(order.getEmail());
        table.addCell("Mobile");
        table.addCell(order.getMobile());
        table.addCell("Delivery Date&Time");
        Date current = new Date();
        table.addCell(current.toString());
        table.addCell("Number of People");
        table.addCell(order.getMenu().getNumberOrder().toString());

        table.addCell("Menu");
        table.addCell("");
        Set<CourseEntity> courses = order.getMenu().getCourses();
        //check if courses is null
        if (!courses.isEmpty()) {
            Iterator<CourseEntity> itr = courses.iterator();
            while (itr.hasNext()) {
                table.addCell("");
                table.addCell(itr.next().getDish().getDishName());
            }
        }


        document.add(table);
        
        document.add(new Paragraph("Here is your delivery details, please use your reservation Id for any queries.",
                FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));


        document.close();
        return OUTPUTFILE;
    }

    private String createInvoicePDF(String toEmailAddress, OrderEntity order) throws FileNotFoundException, DocumentException {
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        
        String OUTPUTFILE = "C:\\Users\\Diana Wang\\Documents\\Diana\\Catering_Invoice" + order.getName()
                + order.getId() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        
        document.open();
        
        //Below specify different types of font
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);
        
        //Below specify contents
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your catering order invoice has been issued!", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);
        
        //Below add a table
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});
        
        //Add table header
        PdfPCell c1 = new PdfPCell(new Phrase("Reservation Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
        
         //Add table contents

        table.addCell("Order Id");
        table.addCell(order.getOrderId().toString());
//        table.addCell("Invoice Id");
//        table.addCell(order.getInvoice().getId().toString());
        table.addCell("Contact's Name");
        table.addCell(order.getTitle() + " " + order.getName());
        table.addCell("Email");
        table.addCell(order.getEmail());
        table.addCell("Mobile");
        table.addCell(order.getMobile());
        table.addCell("Order Date&Time");
        table.addCell(order.getOrderDateTime().toString());
        table.addCell("Number of People");
        table.addCell(order.getMenu().getNumberOrder().toString());
        table.addCell("Invoice Issued Date");
        table.addCell(order.getInvoice().getInvoiceDate().toString());
        

        table.addCell("Menu");
        table.addCell("");
        Set<CourseEntity> courses = order.getMenu().getCourses();
        //check if courses is null
        if (!courses.isEmpty()) {
            Iterator<CourseEntity> itr = courses.iterator();
            while (itr.hasNext()) {
                table.addCell("");
                table.addCell(itr.next().getDish().getDishName());
            }
        }
        table.addCell("Total Cost");
        table.addCell(order.getSalePrice().toString());
        
        document.add(table);
        
        document.add(new Paragraph("Here is your invoice details, please use your reservation Id for any queries.",
                FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));


        document.close();
        return OUTPUTFILE;
    }

    private String createReceiptPDF(String toEmailAddress, ReceiptEntity re) throws FileNotFoundException, DocumentException {
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        
        String OUTPUTFILE = "C:\\Users\\Diana Wang\\Documents\\Diana\\Catering_Invoice" + re.getReceiptDate()
                + re.getReceiptId() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        
        document.open();
        
        //Below specify different types of font
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);
        
         //Below specify contents
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your catering order receipt has been issued!", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);
        
        //Below add a table
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});
        
        //Add table header
        PdfPCell c1 = new PdfPCell(new Phrase("Reservation Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
        
        //Add Table Content
        table.addCell("Receipt ID");
        table.addCell(re.getReceiptId().toString());
        table.addCell("Receipt Issued Date");
        table.addCell(re.getReceiptDate().toString());
        table.addCell("Associated Invoice ID");
        table.addCell(re.getInvoice().getInvoiceId().toString());
        table.addCell("Received Amount");
        table.addCell(re.getInvoice().getOrder().getSalePrice().toString());
        
        document.add(table);
        
        document.add(new Paragraph("Here is your receipt details, please use your reservation Id for any queries.",
                FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));


        document.close();
        return OUTPUTFILE;
    }
        
        
}
