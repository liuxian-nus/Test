package ERMS.session;

import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomEntity;
import ATMS.entity.AttrComboEntity;
import ATMS.entity.AttrExpressPassEntity;
import ATMS.entity.AttrTicketEntity;
import ATMS.entity.ExpressPassPurchaseEntity;
import ATMS.entity.TicketPurchaseEntity;
import CEMS.entity.EventBookingEntity;
import CEMS.entity.EventEntity;
import CEMS.entity.EventServiceBookingEntity;
import CEMS.entity.EventServiceEntity;
import CRMS.entity.FeedbackEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.FeedbackSessionBean;
import CRMS.session.GenerateBarcodeSessionBean;
import CRMS.session.MemberMessageSessionBean;
import ESMS.entity.ShowContractEntity;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowTicketSaleEntity;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ser3na
 */
@Stateless
@LocalBean
public class EmailSessionBean implements EmailSessionBeanRemote {

    @EJB
    private GenerateBarcodeSessionBean generateBarcodeSessionBean;
    @EJB
    private MemberMessageSessionBean memberMessageSessionBean;
    @EJB
    private FeedbackSessionBean feedbackSessionBean;
    String emailServerName = "smtp.gmail.com";
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    public EmailSessionBean() {
    }

    public void sendShowContractBill(double rentalFee, double bill, double ticketCommission, double ticketRevenue, ShowContractEntity showContract, ShowEntity show) throws DocumentException, BadElementException, MalformedURLException, IOException {
        System.out.println("sendShowContractBill");
        String email = showContract.getShowMerchantEmail();

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

            Date today = new Date();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("is3102.it09@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Corel Island Resort: Your event contract is available now!");
            String textbody = "Greeting from Coral Island Resort!"
                    + "\nYour contract bill for show has been available now!"
                    + "\nPlease look at the contract details below: "
                    + "\n\n\nHere is the contract details:"
                    + "\nClient Name: " + showContract.getShowMerchantName()
                    + "\nClient Contact: " + showContract.getShowMerchantContact()
                    + "\nShow Name: " + show.getShowName()
                    + "\nShow Type: " + show.getShowType()
                    + "\nShow Venue Rental Fee: " + rentalFee
                    + "\nShow Ticket Commission Fee: " + ticketCommission
                    + "\nTotal Ticket Revenue: " + ticketRevenue
                    + "\nTotal Billing Amount: " + bill
                    + "\nThe Billing Date: " + today
                    + "\nThe bill has been attached within the email for your reference: if cannot view, please kindly let us know to mail it to you."
                    + "\n\n For any queries, please contact our customer service managers @(0065)9272-8768. Thank you for your support!";

            //below generate a bill for contract bill
            System.out.println("generateShowContractBill");

            //get class path here
            String classPath = EmailSessionBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            System.err.println("classPath: " + classPath);
            String[] fileNameParts = classPath.split("IRMS");
            String part = fileNameParts[0];
            part = part.replaceAll("%20", " ");
            System.err.println("part: " + part);
            File result = new File(part + "IRMS\\IRMS-war\\web\\images\\ShowContractBill_");
            String resultName = part + "\\pdf\\ShowContractBill_";

            //Below generate a PDF file 
            Document document;
            document = new Document(PageSize.A4, 50, 50, 50, 50);
            String OUTPUTFILE = resultName + showContract.getShowMerchantName() + "_" + showContract.getShowContractId() + ".pdf";

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
            document.open();

            //Below specify the font type 
            Font catFont = new Font(Font.TIMES_ROMAN, 18,
                    Font.BOLD);
            Font redFont = new Font(Font.TIMES_ROMAN, 12,
                    Font.NORMAL, Color.RED);
            Font subFont = new Font(Font.TIMES_ROMAN, 16,
                    Font.BOLD);
            Font tableFont;
            tableFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.DARK_GRAY);
            Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                    Font.BOLDITALIC);

            //Below specify contents 
//        String imagePath = "C:\\Users\\Diana Wang\\Documents\\NetBeansProjects\\coral_island_banner_customer.png";
            String imagePath = part + "IRMS\\IRMS-war\\web\\images\\coral_island_banner_customer.png";
            Image image = Image.getInstance(imagePath);
            document.add(image);

            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Your contract bill is displayed as below: ", catFont));
            addEmptyLine(preface, 1);

            document.add(preface);

            //Below add a table 
            PdfPTable table = new PdfPTable(2);
            table.setSpacingAfter(30);
            table.setSpacingBefore(30);
            table.setWidths(new int[]{1, 3});

            //Add table header 
            PdfPCell c1 = new PdfPCell(new Phrase("Billing Info"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Details & Remarks"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            table.setHeaderRows(1);

            //below add table contents
            table.addCell("Client Name");
            table.addCell(showContract.getShowMerchantName());
            table.addCell("Show Contract Id (For reference)");
            table.addCell(showContract.getShowContractId().toString());
            table.addCell("Client Billing Address");
            table.addCell(showContract.getShowMerchantAddress());
            table.addCell("Show Name");
            table.addCell(show.getShowName());
            table.addCell("Ticket Revenue");
            table.addCell(Double.toString(ticketRevenue));
            table.addCell("Ticket Commission");
            table.addCell(Double.toString(ticketCommission));
            table.addCell("Venue Rent");
            table.addCell(Double.toString(rentalFee));
            table.addCell("Total Billing Amount");
            table.addCell(Double.toString(bill));

            document.add(table);
            document.close();


            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            String file;
            file = OUTPUTFILE;
            //Below attach a file within the email 
            String fileName = "CorelResort:Show Contract Bill";
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);
            //Below draft the contents of email 
            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(textbody);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);

            ((MimeMessage) message).setContent(multipart);


            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    public void sendEventContractBill(double serviceTotalCost, double venueRate, double finalBill, EventEntity event) throws DocumentException, DocumentException, BadElementException, MalformedURLException, FileNotFoundException, IOException {
        System.out.println("sendEventContractBill");
        String email = event.getEmail();

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
                    InternetAddress.parse(email));
            message.setSubject("Corel Island Resort: Your event contract is available now!");
            String textbody = "Greeting from Coral Island Resort!"
                    + "\nDear Client: Your event contract is available now!"
                    + "\nPlease look at the contract details below: "
                    + "\n\n\nHere is the contract details:"
                    + "\nClient Name: " + event.getTitle() + " " + event.getName()
                    + "\nEvent Reference No.: " + event.getEventId()
                    + "\nEvent Name: " + event.getEventName()
                    + "\nEvent Type: " + event.getEventType()
                    + "\nClient Billing Address: " + event.getAddress()
                    + "\nTotal Service Cost: " + serviceTotalCost
                    + "\nTotal Venue Cost: " + venueRate
                    + "\nTotal Billing Amount: " + finalBill
                    + "\nBilling Date: " + new Date()
                    + "\nThe bill has been attached within the email for your reference: if cannot view, please kindly let us know to mail it to you."
                    + "\n\n For any queries, please contact our customer service managers @(0065)9272-8768. Thank you for your support!";

            System.out.println("EventContractBilling: text set already!");

            //get class path here
            String classPath = EmailSessionBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            System.err.println("classPath: " + classPath);
            String[] fileNameParts = classPath.split("IRMS");
            String part = fileNameParts[0];
            part = part.replaceAll("%20", " ");
            System.err.println("part: " + part);
            File result = new File(part + "IRMS\\IRMS-war\\web\\images\\EventContractBill_");
            String resultName = part + "\\pdf\\EventContractBill_";

            //Below generate a PDF file 
            Document document;
            document = new Document(PageSize.A4, 50, 50, 50, 50);
            String OUTPUTFILE = resultName + event.getName() + " " + event.getEventId() + ".pdf";
            System.err.println("OUTPUTFILE: " + OUTPUTFILE);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
            document.open();

            //Below specify the font type 
            Font catFont = new Font(Font.TIMES_ROMAN, 18,
                    Font.BOLD);
            Font redFont = new Font(Font.TIMES_ROMAN, 12,
                    Font.NORMAL, Color.RED);
            Font subFont = new Font(Font.TIMES_ROMAN, 16,
                    Font.BOLD);
            Font tableFont;
            tableFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.DARK_GRAY);
            Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                    Font.BOLDITALIC);

            //Below specify contents 
//        String imagePath = "C:\\Users\\Diana Wang\\Documents\\NetBeansProjects\\coral_island_banner_customer.png";
            String imagePath = part + "IRMS\\IRMS-war\\web\\images\\coral_island_banner_customer.png";
            Image image = Image.getInstance(imagePath);
            document.add(image);

            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Your event contract bill is displayed as below: ", catFont));
            addEmptyLine(preface, 1);

            document.add(preface);

            //Below add a table 
            PdfPTable table = new PdfPTable(2);
            table.setSpacingAfter(30);
            table.setSpacingBefore(30);
            table.setWidths(new int[]{1, 3});

            //Add table header 
            PdfPCell c1 = new PdfPCell(new Phrase("Contract Bill: Categories"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Details & Remarks"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            table.setHeaderRows(1);

            //below add table contents
            table.addCell("Client Name");
            table.addCell(event.getName());
            table.addCell("Client Billing Address");
            table.addCell(event.getAddress());
            table.addCell("Event ID");
            table.addCell(event.getEventId().toString());
            table.addCell("Event Name");
            table.addCell(event.getEventName());
            table.addCell("Event Type");
            table.addCell(event.getEventType());
            table.addCell("Service Booked");


            //Below add a sub-table for service booked 
            PdfPTable table2 = new PdfPTable(2);
            table2.setSpacingAfter(30);
            table2.setSpacingBefore(30);
            table2.setWidths(new int[]{1, 3});

            //Add table header 
            PdfPCell c2 = new PdfPCell(new Phrase("Bookings"));
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(c2);

            c2 = new PdfPCell(new Phrase("Details & Remarks"));
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(c2);

            table2.setHeaderRows(1);

            List<EventBookingEntity> bookings = event.getBookings();
            Iterator<EventBookingEntity> itr = bookings.iterator();
            while (itr.hasNext()) {
                EventBookingEntity current = itr.next();
                System.out.println("the first while loop: " + current.toString());

                table2.addCell(current.getBookingId().toString());//left

                List<EventServiceBookingEntity> services = current.getServiceBookings();
                Iterator<EventServiceBookingEntity> itr2 = services.iterator();

                PdfPTable table3 = null;

                while (itr2.hasNext()) {
                    if (services.isEmpty()) {
                        System.out.println("service is empty!");
                        table.addCell(""); //add right
                        break;
                    }
                    EventServiceBookingEntity thisService = itr2.next();
                    System.out.println("The current service booked for this booking is " + thisService.toString());

                    //Below add a sub-table for service booked for each booking
                    table3 = new PdfPTable(2);
                    table3.setSpacingAfter(30);
                    table3.setSpacingBefore(30);
                    table3.setWidths(new int[]{1, 3});

                    //Add table header 
                    PdfPCell c3 = new PdfPCell(new Phrase("Service"));
                    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table3.addCell(c3);

                    c3 = new PdfPCell(new Phrase("Details & Remarks"));
                    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table3.addCell(c3);

                    table3.setHeaderRows(1);

                    table3.addCell(thisService.getEventService().getServiceName());
                    table3.addCell(Integer.toString(thisService.getEventServiceQuantity()));
                }
                table2.addCell(table3);//right
                System.out.println("Table3 service table has been added into booking table");

            }
            table.addCell(table2);

            System.out.println("Table2 booking table has been added into total table");
            document.add(table);
            document.close();


            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            String file;
            file = OUTPUTFILE;
            //Below attach a file within the email 
            String fileName = "CorelResort:Show Contract Bill";
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);
            //Below draft the contents of email 
            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(textbody);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);

            ((MimeMessage) message).setContent(multipart);


            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateShowTicket(ShowTicketSaleEntity sts) throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException {
        System.out.println("generateShowTicket");

        //get class path here
        String classPath = EmailSessionBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.err.println("classPath: " + classPath);
        String[] fileNameParts = classPath.split("IRMS");
        String part = fileNameParts[0];
        part = part.replaceAll("%20", " ");
        System.err.println("part: " + part);
        File result = new File(part + "IRMS\\IRMS-war\\web\\images\\ShowTicket_");
        String resultName = part + "\\pdf\\ShowTicket_";

        //Below generate a PDF file 
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        String OUTPUTFILE = resultName
                + sts.getShow().getShowName() + " " + sts.getShowTicketSaleId() + ".pdf";

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        document.open();

        //Below specify the font type 
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font tableFont;
        tableFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.DARK_GRAY);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);

        //Below specify contents 
        String imagePath = part + "IRMS\\IRMS-war\\web\\images\\coral_island_banner_customer.png";
//        String imagePath = "C:\\Users\\Diana Wang\\Documents\\NetBeansProjects\\coral_island_banner_customer.png";
        Image image = Image.getInstance(imagePath);
        document.add(image);

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your e-ticket is displayed as below: ", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);

        //Below add a table 
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});

        //Add table header 
        PdfPCell c1 = new PdfPCell(new Phrase("Ticketing Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        //below add table contents
        table.addCell("Show Name");
        table.addCell(sts.getShow().getShowName());
        table.addCell("Show Start DateTime");
        table.addCell(sts.getShowStartDateTime().toString());
        table.addCell("Show Ticket Type");
        table.addCell(sts.getShowTicketType());
        table.addCell("Show Ticket Quantity");
        table.addCell(Integer.toString(sts.getShowTicketQuantity()));
        table.addCell("Show Language");
        table.addCell(sts.getShow().getShowLanguage());
        table.addCell("Show Duration");
        table.addCell(Integer.toString(sts.getShow().getShowDuration()));

        document.add(table);
        document.close();


    }

    @Override
    public void sendBirthdayCongrats(MemberEntity member) {
        System.out.println("sendBirthdayCongrats");

        String email = member.getMemberEmail();

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
            PromotionEntity promotion = em.find(PromotionEntity.class, Long.valueOf(83102));

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("is3102.it09@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Member Birthday Special Offer: Corel Island Resort  Welcomes you!");
            String textbody = "Greeting from Coral Island Resort!"
                    + "\nWe have EXCLUSIVE OFFER for your birthday now!"
                    + " Please look at the promotion details below: "
                    + "\n\n\nHere is the promotion details:"
                    + "\nPromotion Code: " + promotion.getPromotionCode()
                    + "\nPromotion Title: " + promotion.getPromotionTitle()
                    + "\nPromotion Description: " + promotion.getPromotionDescription()
                    + "\nPromotion Start Date: " + promotion.getPromotionStartDate()
                    + "\nPromotion End Date: " + promotion.getPromotionEndDate()
                    + "\nPromotion Discount (If Available)" + promotion.getDiscount()
                    + "\nWe also prepare a surprise gift for you on your birthday: please come to let us celebrate for you on the day"
                    + "\n\n For any queries, please contact our customer service managers @(0065)9272-8768. Thank you for your support!";
            message.setText(textbody);
            String textShort =  "\nWe have EXCLUSIVE OFFER for your birthday now!"
                    + " Please look at the promotion details below: "
                    + "\n\n\nHere is the promotion details:"
                    + "\nPromotion Code: " + promotion.getPromotionCode()
                    + "\nPromotion Title: " + promotion.getPromotionTitle()
                    + "\nPromotion Start Date: " + promotion.getPromotionStartDate()
                    + "\nPromotion End Date: " + promotion.getPromotionEndDate()
                    + "\nPromotion Discount (If Available)" + promotion.getDiscount()
                    + "\nWe also prepare a surprise gift for you on your birthday: please come to let us celebrate for you on the day"
                    + "\n\n For any queries, please contact our customer service managers @(0065)9272-8768. Thank you for your support!";
            Transport.send(message);
            Date today = new Date();
            //createNewMessage(MemberEntity member, String title, String content, String category, Date sentDate)
            memberMessageSessionBean.createNewMessage(member, "Happy Birthday from CIR!", textbody, "notification", today);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //Do not forget to add member targets to promotionentity after evalution everytime, or the list will be null
    @Override
    public void sendPromotionToTargets(PromotionEntity promotion) {
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


        List<MemberEntity> targets = promotion.getMcMemberTargets();
        Iterator<MemberEntity> itr = targets.iterator();

        String memExclu;
        if (promotion.isPromotionMemberExclusive()) {
            memExclu = "Yes";
        } else {
            memExclu = "No";
        }
        while (itr.hasNext()) {
            MemberEntity current = itr.next();

            System.out.println("Send email to a target member: start");


            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("is3102.it09@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(current.getMemberEmail()));
                message.setSubject("Member Exclusive Promotion: Corel Island Resort  Welcomes you!");
                String textbody = "Greeting from Coral Island Resort!"
                        + "\nWe have new promotions available now!"
                        + " Please look at the promotion details below: "
                        + "\n\n\nHere is the promotion details:"
                        + "\nPromotion Code: " + promotion.getPromotionCode()
                        + "\nPromotion Title: " + promotion.getPromotionTitle()
                        + "\nPromotion Description: " + promotion.getPromotionDescription()
                        + "\nPromotion Start Date: " + promotion.getPromotionStartDate()
                        + "\nPromotion End Date: " + promotion.getPromotionEndDate()
                        + "\nPromotion Exclusive For Member?" + memExclu
                        + "\nPromotion Discount (If Available)" + promotion.getDiscount()
                        + "\n\n For any queries, please contact our customer service managers @(0065)9272-8768. Thank you for your support!";
                message.setText(textbody);
                String textShort = "We have new promotions available now!"
                        + " Please look at the promotion details below: "
                        + "\n\n\nHere is the promotion details:"
                        + "\nPromotion Code: " + promotion.getPromotionCode()
                        + "\nPromotion Title: " + promotion.getPromotionTitle()
                        + "\nPromotion Description: " + promotion.getPromotionDescription()
                        + "\nPromotion Start Date: " + promotion.getPromotionStartDate()
                        + "\nPromotion End Date: " + promotion.getPromotionEndDate()
                        + "\nPromotion Exclusive For Member?" + memExclu
                        + "\nPromotion Discount (If Available)" + promotion.getDiscount();
                Transport.send(message);
                Date today = new Date();
                //createNewMessage(MemberEntity member, String title, String content, String category, Date sentDate)
                memberMessageSessionBean.createNewMessage(current, "You are invited in a new promotion event", textShort, "notification", today);
                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //This is the promotion only to subscribers
    @Override
    public void sendPromotionToSubs(PromotionEntity promotion) {
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

        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> list = q.getResultList();
        Iterator<MemberEntity> itr = list.iterator();
        List<MemberEntity> subscribers = new ArrayList();

        while (itr.hasNext()) {
            MemberEntity current = itr.next();
            if (current.isSubscriber()) {
                subscribers.add(current);
            }
        }

        Iterator<MemberEntity> itr2 = subscribers.iterator();
        while (itr2.hasNext()) {
            MemberEntity current = itr.next();

            System.out.println("Send email to a member: start");
            String memExclu;
            if (promotion.isPromotionMemberExclusive()) {
                memExclu = "Yes";
            } else {
                memExclu = "No";
            }

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("is3102.it09@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(current.getMemberEmail()));
                message.setSubject("Member Exclusive Promotion: Corel Island Resort  Welcomes you!");
                String textbody = "Greeting from Coral Island Resort!"
                        + "\nWe have new promotions available now!"
                        + " Please look at the promotion details below: "
                        + "\n\n\nHere is the promotion details:"
                        + "\nPromotion Code: " + promotion.getPromotionCode()
                        + "\nPromotion Title: " + promotion.getPromotionTitle()
                        + "\nPromotion Description: " + promotion.getPromotionDescription()
                        + "\nPromotion Start Date: " + promotion.getPromotionStartDate()
                        + "\nPromotion End Date: " + promotion.getPromotionEndDate()
                        + "\nPromotion Exclusive For Member?" + memExclu
                        + "\nPromotion Discount (If Available)" + promotion.getDiscount()
                        + "\n\n For any queries, please contact our customer service managers @(0065)9272-8768. Thank you for your support!";
                message.setText(textbody);


                Transport.send(message);
                Date today = new Date();
                //createNewMessage(MemberEntity member, String title, String content, String category, Date sentDate)
                memberMessageSessionBean.createNewMessage(current, "Member Exclusive Promotion", textbody, "memberSummary", today);
                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void emailInitialPassward(String toEmailAddress, String initialPassword) {
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
                    InternetAddress.parse(toEmailAddress));
            message.setSubject("Initial Password");
            String textbody = "Greeting from Coral Island Resort!"
                    + "\nHere is your initial password:" + initialPassword
                    + "\nPlease login immediately to change your password.\n\n\nBest Regards,\nThe Coral Island Management Team";
            message.setText(textbody);


            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(toEmailAddress, "Your initial password", textbody, "memberSummary", today);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emailGeneratedPassword(String toEmailAddress, String initialPassword) {
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
                    InternetAddress.parse(toEmailAddress));
            message.setSubject("System Generated Password");
            String textbody = "Greeting from Coral Island Resort!"
                    + "\nHere is a system generated password:" + initialPassword
                    + "\nPlease login immediately to change your password.\n\n\nBest Regards,\nThe Coral Island Management Team";
            message.setText(textbody);

            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(toEmailAddress, "Your initial password", textbody, "memberSummary", today);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emailCorporateBill(String toEmailAdress, RoomEntity room) throws IOException, FileNotFoundException, DocumentException {
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
            double totalBill = room.getRoomPrice().getPrice() * 5 + room.getRoomServiceCharge();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("chrislx.nus@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmailAdress));
            message.setSubject("Your bill from Coral Island Resort");
            String text = "Thank you for coming to Coral Island Resort!"
                    + "\nYour staff " + room.getGuestName() + " has come to our hotel "
                    + "\nfrom " + room.getCheckInDate() + " to " + room.getCheckOutDate()
                    + "\nThe room fee is " + room.getRoomPrice().getPrice() + " per day,"
                    + " \nand the service charge is " + room.getRoomServiceCharge()
                    + "\nYou have a total bill of " + totalBill
                    + "\nPlease clear your bill in 10 days."
                    + "\nThank you. "
                    + "\n\nIn case of any issues and inqueries, you may contact our corporate service manager "
                    + "\n@ 65-8180 1380"
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team";

            String INPUTFILE;
            INPUTFILE = createBill(toEmailAdress, room);


            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            //Below attach a file within the email 
            String fileName = "CorelResort:Room Reservation";
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);
            //Below draft the contents of email 
            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);

            ((MimeMessage) message).setContent(multipart);

            System.out.println("Sending");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emailReservationConfirmation(String toEmailAddress, ReservationEntity newReservation) throws IOException, FileNotFoundException, DocumentException {
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
                    InternetAddress.parse(toEmailAddress));
            message.setSubject("Thank you for your reservation");
            String text = "Greeting from Coral Island Resort!"
                    + "\nYou have successfully make a reservation in Coral Island Resort Group. Thank you for your new reservation!"
                    + "\nHere is your Reservation Id:" + newReservation.getReservationId()
                    + "\nBelow is your reservation detail:"
                    + "\nName: " + newReservation.getRcName()
                    + "\nEmail Address: " + newReservation.getRcEmail()
                    + "\nCheckIn Date: " + newReservation.getRcCheckInDate()
                    + "\nCheckOut Date: " + newReservation.getRcCheckOutDate()
                    + "\nRoom Count " + newReservation.getReservationRoomCount()
                    + "\nRoom Type" + newReservation.getReservationRoomType()
                    + "\n\nIn case of any issues and inqueries, you may contact our corporate service manager "
                    + "\n@ 65-8180 1380"
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team";
            String textShort = "Greeting from Coral Island Resort!"
                    + "\nYou have successfully make a reservation in Coral Island Resort Group. Thank you for your new reservation!"
                    + "\nHere is your Reservation Id:" + newReservation.getReservationId();
            String INPUTFILE;
            INPUTFILE = createBill(toEmailAddress, newReservation);

            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();

            String file;
            file = INPUTFILE;

            //Below attach a file within the email 
            String fileName = "CorelResort:Room Reservation Confirmation";
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);
            //Below draft the contents of email 
            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);

            ((MimeMessage) message).setContent(multipart);

            System.out.println("Sending");

            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(toEmailAddress, "Your Hotel Reservation", textShort, "bookingSummary", today);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emailRequest(String toEmailAdress, ContractEntity contract) {
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
            message.setSubject("New Request");
            message.setText("Dear Contract Manager"
                    + "\n Greetings from Coral Island Resort!"
                    + "\n A new contract has been sent to you for review. Contract ID:" + contract.getContractId()
                    + "\nPlease login immediately and review the contract.\n\n\nBest Regards,\nThe Coral Island Management Team");


            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emailApprovalAction(String toEmailAdress, ContractEntity contract) {
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
            message.setSubject("New Request");
            message.setText("Dear Contract Operator"
                    + "\n Greetings from Coral Island Resort!"
                    + "\n The contract has been reviewed by the manager. Contract ID:" + contract.getContractId()
                    + "\n Contract Status is now: " + contract.getLast().getEventStatus()
                    + "\n Please login immediately and review the contract.\n\n\nBest Regards,\nThe Coral Island Management Team");


            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emailAttractionTicketSingle(String toEmailAddress, TicketPurchaseEntity tpe) throws IOException, FileNotFoundException, DocumentException {
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
                    InternetAddress.parse(toEmailAddress));
            String attrName = tpe.getAttrTickets().get(0).getAttr().getAttrName();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = sdf.format(tpe.getAttrTicketBookDate());

            message.setSubject("Your ticket from Coral Island Resort: " + attrName + " " + tpe.getTpId());

            String text = "Thank you for booking ticket for Coral Island Resort Attraction services!"
                    + "\nYour ticket purchase ID is " + tpe.getTpId()
                    + "\nTicket is purchased for " + dateString
                    + "\nThe fee of tickets you have purchased is " + tpe.getAttrTicketFee()
                    + "\nPlease refer to the attachment for your e-ticket: print the pdf file and bring it on the show date"
                    + "\nThank you for your support!"
                    + "\n\n\n For any queries, please call (+65)9272-8760";

            String textForMobile = "Thank you for booking ticket for Coral Island Resort Attraction services!"
                    + "\nYour ticket purchase ID is " + tpe.getTpId()
                    + "\nTicket is purchased for " + dateString
                    + "\nThe fee of tickets you have purchased is " + tpe.getAttrTicketFee();


            String INPUTFILE = createTicket(tpe);



            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            //Below attach a file within the email 
            String fileName = "CorelResort:Room Reservation";
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);
            //Below draft the contents of email 
            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);

            ((MimeMessage) message).setContent(multipart);


            System.out.println("Sending");
            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(toEmailAddress, "Your Attraction Ticket Purchase", textForMobile, "bookingSummary", today);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void emailAttractionTicketExpress(String toEmailAddress, ExpressPassPurchaseEntity eppe) throws IOException, FileNotFoundException, DocumentException {
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
                    InternetAddress.parse(toEmailAddress));
            String attrName = "";
            if (eppe.getAttrEPs().get(0).getAttr() != null) {
                attrName = eppe.getAttrEPs().get(0).getAttr().getAttrName();
            } else {
                attrName = "All Attraction";
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = sdf.format(eppe.getEpBookDate());
            message.setSubject("Your express pass from Coral Island Resort: " + attrName + " " + eppe.getEppId());
            String text = "Thank you for booking express pass for Coral Island Resort Attraction services!"
                    + "\nYour Ticket ID is " + eppe.getEppId()
                    + "\nTicket purchase date is " + dateString
                    + "\nThe fee of tickets you have purchased is " + eppe.getEpFee()
                    + "\nPlease refer to the attachment for your e-ticket: print the pdf file and bring it on the show date"
                    + "\nThank you for your support!"
                    + "\n\n\n For any queries, please call (+65)9272-8760";

            String textForMobile = "Thank you for booking express pass for Coral Island Resort Attraction services!"
                    + "\nYour Ticket ID is " + eppe.getEppId()
                    + "\nTicket purchase date is " + dateString
                    + "\nThe fee of tickets you have purchased is " + eppe.getEpFee();

            String INPUTFILE = createTicketExpress(eppe);



            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            //Below attach a file within the email 
            String fileName = "CorelResort:Room Reservation";
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);
            //Below draft the contents of email 
            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);

            ((MimeMessage) message).setContent(multipart);

            System.out.println("Sending");
            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(toEmailAddress, "Your Attraction Ticket Purchase", textForMobile, "bookingSummary", today);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emailMerchantBill(String toEmailAdress, BillEntity bill) {
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
            message.setSubject("New Bill");
            message.setText("Dear " + bill.getContract().getMerchant().getMerchantName() + " manager: "
                    + "\n Greetings from Coral Island Resort!"
                    + "\n Your contract " + bill.getContract().getContractId() + " has a new bill available"
                    + "\n Bill Amount: " + bill.getBillAmount()
                    + "\n Bill Type: " + bill.getBillType()
                    + "\n Please login immediately and make the payment.\n\n\nBest Regards,\nThe Coral Island Management Team");


            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emailAttractionTicketCombo(String toEmailAddress, AttrComboEntity combo) throws IOException, FileNotFoundException, DocumentException {
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
                    InternetAddress.parse(toEmailAddress));
            message.setSubject("Your ticket from Coral Island Resort: Attraction " + combo.getAttrComboName());
            String text = "Thank you for booking ticket for Coral Island Resort Attraction services!"
                    + "\nYour Combo Ticket type is " + combo.getAttrComboType()
                    + "\nThe Combo Ticket is " + combo.getAttrComboName()
                    + "\nThe fee of tickets you have purchased is " + combo.getAttrComboPrice()
                    + "\nPlease refer to the attachment for your e-ticket: print the pdf file and bring it on the show date"
                    + "\nThank you for your support!"
                    + "\n\n\n For any queries, please call (+65)9272-8760";


            String INPUTFILE = createTicketCombo(combo);



            MimeBodyPart messageBodyPart;
            MimeBodyPart textBodyPart;

            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            String file;
            file = INPUTFILE;
            //Below attach a file within the email 
            String fileName = "CorelResort:Room Reservation";
            messageBodyPart.setFileName(fileName);
            messageBodyPart.attachFile(file);
            //Below draft the contents of email 
            textBodyPart = new MimeBodyPart();
            textBodyPart.setText(text);

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);

            ((MimeMessage) message).setContent(multipart);

            System.out.println("Sending");
            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(toEmailAddress, "Your Attraction Ticket Purchase", text, "bookingSummary", today);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private String createBill(String toEmailAdress, RoomEntity room) throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException {
        //get class path here
        String classPath = EmailSessionBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.err.println("classPath: " + classPath);
        String[] fileNameParts = classPath.split("IRMS");
        String part = fileNameParts[0];
        part = part.replaceAll("%20", " ");
        System.err.println("part: " + part);
        File result = new File(part + "IRMS\\IRMS-war\\web\\images\\Corporate_Bill_");
        String resultName = part + "\\pdf\\Corporate_Bill_";

        //Below generate a PDF file 
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        String OUTPUTFILE = resultName + room.getRoomCorporate()
                + room.getReservation().getReservationId() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        document.open();

        //Below specify the font type 
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font tableFont;
        tableFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.DARK_GRAY);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);

        //Below specify contents 

        String imagePath = part + "IRMS\\IRMS-war\\web\\images\\coral_island_banner_customer.png";
//        String imagePath = "C:\\Users\\Diana Wang\\Documents\\NetBeansProjects\\coral_island_banner_customer.png";
        Image image = Image.getInstance(imagePath);
        document.add(image);

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your hotel booking summary", catFont));
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

        //Add table content 
        table.addCell("Reservation ID");
        table.addCell(room.getReservation().getReservationId().toString());
        table.addCell("Guest Name");
        table.addCell(room.getGuestName());
        table.addCell("ReservationEmail");
        table.addCell(room.getReservation().getRcEmail());
        table.addCell("Check In Date");
        table.addCell(room.getCheckInDate().toString());
        table.addCell("Check Out Date");
        table.addCell(room.getCheckOutDate().toString());
        table.addCell("Hotel");
        table.addCell(Integer.toString(room.getRoomHotel()));
        table.addCell("Room Type");
        table.addCell(room.getRoomType());
        table.addCell("Number Of Rooms");
        table.addCell(Integer.toString(room.getReservation().getReservationRoomCount()));
        table.addCell("Number of Guests");
        table.addCell(Integer.toString(room.getReservation().getReservationGuestCount()));
        table.addCell("Status");
        table.addCell(room.getRoomStatus());
        table.addCell("Total Price");
        table.addCell(Double.toString(room.getReservation().getReservationTotal()));

        //below add notes paragraph 
        Paragraph p = new Paragraph("If it is a confirmed booking, please make sure you finish the booking"
                + "process within 3 days", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLDITALIC, Color.RED));

        Chunk c;
        c = new Chunk("via localhost:8080/IRMSCustomer-war/");
        c.setAction(new PdfAction(new URL("localhost:8080/IRMSCustomer-war/")));
        p.add(c);
        document.add(p);
        document.add(new Paragraph(""));
        document.close();

        return OUTPUTFILE;
    }

    //Add a empty line 
    private Paragraph addEmptyLine(Paragraph paragraph, int number) {
        for (int k = 0; k < number; k++) {
            paragraph.add(new Paragraph(" "));
        }
        return paragraph;
    }

    private String createBill(String toEmailAdress, ReservationEntity newReservation) throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException {

        //get class path here
        String classPath = EmailSessionBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.err.println("classPath: " + classPath);
        String[] fileNameParts = classPath.split("IRMS");
        String part = fileNameParts[0];
        part = part.replaceAll("%20", " ");
        part = part.substring(1);
        System.err.println("part: " + part);
        File result = new File(part + "IRMS\\IRMS-war\\web\\images\\RoomReservationConfirmation_");
        String resultName = part + "pdf/RoomReservationConfirmation_";



        //Below generate a PDF file 
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        String OUTPUTFILE = resultName + newReservation.getRcName()
                + newReservation.getReservationId() + ".pdf";
        
        System.out.println(OUTPUTFILE);
        File tempFile = new File(OUTPUTFILE);
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        document.open();

        //Below specify the font type 
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font tableFont;
        tableFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.DARK_GRAY);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);

        //Below specify contents 
        //String imagePath = part + "IRMS\\IRMS-war\\web\\images\\coral_island_banner_customer.png";
//        String imagePath = "C:\\Users\\Diana Wang\\Documents\\NetBeansProjects\\coral_island_banner_customer.png";
        //Image image = Image.getInstance(imagePath);
        //document.add(image);

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your room booking confirmation summary", catFont));
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
        table.addCell("Reservation ID");
        table.addCell(Long.toString(newReservation.getReservationId()));
        table.addCell("Guest Name");
        table.addCell(newReservation.getRcName());
        table.addCell("Guest Email");
        table.addCell(newReservation.getRcEmail());
        table.addCell("Hotel");
        table.addCell(Integer.toString(newReservation.getReservationHotelNo()));
        table.addCell("Type of Room");
        table.addCell(newReservation.getReservationRoomType());
        table.addCell("Check-in Date");
        table.addCell(newReservation.getRcCheckInDate().toString());
        table.addCell("Check-out Date");
        table.addCell(newReservation.getRcCheckOutDate().toString());
        table.addCell("Number of People");
        table.addCell(Integer.toString(newReservation.getReservationGuestCount()));
        table.addCell("Guest's Contact");
        table.addCell(newReservation.getRcHP());
        table.addCell("Credit Card Number");
        table.addCell(newReservation.getRcCreditCardNo());
        document.add(table);

        //below add notes paragraph 
        Paragraph p = new Paragraph("If it is a confirmed booking, please make sure you finish the booking"
                + "process within 3 days", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLDITALIC, Color.RED));

        Chunk c;
        c = new Chunk("via localhost:8080/IRMSCustomer-war/");
        c.setAction(new PdfAction(new URL("http://is3102.cloudapp.net/IRMSCustomer-war/")));
        p.add(c);
        document.add(p);
        document.add(new Paragraph(""));
        document.close();

        return OUTPUTFILE;
    }

    public String createTicket(TicketPurchaseEntity tpe) throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException {

        //get class path here
        String classPath = EmailSessionBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.err.println("classPath: " + classPath);
        String[] fileNameParts = classPath.split("IRMS");
        String part = fileNameParts[0];
        part = part.replaceAll("%20", " ");
        System.err.println("part: " + part);
        File result = new File(part + "IRMS\\IRMS-war\\web\\images\\TicketPurchase_");
        String resultName = part + "\\pdf\\TicketPurchase_";
        System.out.println("resultName: " + resultName);

        //Below generate a PDF file 
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        String OUTPUTFILE = resultName
                + tpe.getTpId() + ".pdf";

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        document.open();

        String imagePath = part + "IRMS\\IRMS-war\\web\\images\\coral_island_banner_customer.png";
//        String imagePath = "C:\\Users\\Administrator\\Desktop\\IS3102\\pdf\\coral_island_banner_customer.png";
        Image image = Image.getInstance(imagePath);
        document.add(image);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(tpe.getAttrTicketBookDate());

        //Below specify the font type 
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font tableFont;
        tableFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.DARK_GRAY);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);

        //Below specify contents 

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your e-ticket is displayed as below: ", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);
        //Below add a table 
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});

        //Add table header 
        PdfPCell c1 = new PdfPCell(new Phrase("Ticketing Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        //Add table contents 
        table.addCell("Attraction");
        if (!tpe.getAttrTickets().isEmpty()) {
            table.addCell(tpe.getAttrTickets().get(0).getAttr().getAttrName());
        } else {
            table.addCell("Attraction Not Assigned Yet!");
        }

        if (!tpe.getAttrTickets().isEmpty()) {
            Iterator<AttrTicketEntity> itr = tpe.getAttrTickets().iterator();
            Iterator<Integer> itr2 = tpe.getAttrTicketQuantities().iterator();
            int i = 1;

            while (itr.hasNext() && itr2.hasNext()) {
                AttrTicketEntity currentTicket = itr.next();
                Integer currentNumber = itr2.next();
                table.addCell("No." + i + " Ticket Name & Type");
                table.addCell(currentTicket.getAttrTicketName() + "," + currentTicket.getAttrTicketType());
                table.addCell("Number of Tickets");
                table.addCell(Integer.toString(currentNumber));
//                table.addCell("Ticket Date"); 
//                //table.addCell(tpe.getAttrTicketBookDate().toString()); 
//                table.addCell(dateString); 
//                table.addCell("Ticket Price"); 
//                table.addCell(Double.toString(tpe.getAttrTicketFee())); 
                i++;
                System.out.println("EmailSessionBean: a ticket has been added!" + i);
            }

            table.addCell("Ticket Date");
            table.addCell(dateString);
            table.addCell("Ticket Price");
            table.addCell(Double.toString(tpe.getAttrTicketFee()));



        }
        document.add(table);


        Long tpId = tpe.getTpId();
        String tpIdString = generateBarcodeSessionBean.makeToSevenDigit(String.valueOf(tpId));
        System.out.println("tpIdString: " + tpIdString);
        String ticketPath = part + "\\barcode" + tpIdString + ".jpg";
        //   String ticketPath = "C:\\Users\\Administrator\\Desktop\\IS3102\\Code\\IRMS\\IRMSCustomer-war\\web\\images\\attractionTicket\\" + tpIdString + ".jpg";
        System.out.println("ticketPath: " + ticketPath);

        Image barcode = Image.getInstance(ticketPath);
        document.add(barcode);

        document.close();

        return OUTPUTFILE;
    }

    private String createTicketCombo(AttrComboEntity combo) throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException {
        //get class path here
        String classPath = EmailSessionBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.err.println("classPath: " + classPath);
        String[] fileNameParts = classPath.split("IRMS");
        String part = fileNameParts[0];
        part = part.replaceAll("%20", " ");
        System.err.println("part: " + part);
        File result = new File(part + "IRMS\\IRMS-war\\web\\images\\ComboTicketConfirmation_");
        String resultName = part + "\\pdf\\ComboTicketConfirmation_";

        //Below generate a PDF file 
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        String OUTPUTFILE = resultName
                + combo.getAttrComboName() + " " + combo.getAttrComboId() + ".pdf";

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        document.open();

        //Below specify the font type 
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font tableFont;
        tableFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.DARK_GRAY);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);

        //Below specify contents 
        String imagePath = part + "IRMS\\IRMS-war\\web\\images\\coral_island_banner_customer.png";
//        String imagePath = "C:\\Users\\Diana Wang\\Documents\\NetBeansProjects\\coral_island_banner_customer.png";
        Image image = Image.getInstance(imagePath);
        document.add(image);

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your e-ticket is displayed as below: ", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);

        //Below add a table 
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});

        //Add table header 
        PdfPCell c1 = new PdfPCell(new Phrase("Ticketing Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        //Add Table Contents 

        return OUTPUTFILE;


    }

    public String createTicketExpress(ExpressPassPurchaseEntity eppe) throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException {
        //get class path here
        String classPath = EmailSessionBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.err.println("classPath: " + classPath);
        String[] fileNameParts = classPath.split("IRMS");
        String part = fileNameParts[0];
        part = part.replaceAll("%20", " ");
        System.err.println("part: " + part);
        File result = new File(part + "IRMS\\IRMS-war\\web\\images\\ExpressTicket_");
        String resultName = part + "\\pdf\\ExpressTicketConfirmation_";

        //Below generate a PDF file 
        Document document;
        document = new Document(PageSize.A4, 50, 50, 50, 50);
        String OUTPUTFILE = resultName
                + eppe.getEppId() + ".pdf";

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
        document.open();

        //Below specify the font type 
        Font catFont = new Font(Font.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.TIMES_ROMAN, 12,
                Font.NORMAL, Color.RED);
        Font subFont = new Font(Font.TIMES_ROMAN, 16,
                Font.BOLD);
        Font tableFont;
        tableFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.DARK_GRAY);
        Font smallItalic = new Font(Font.TIMES_ROMAN, 12,
                Font.BOLDITALIC);

        //Below specify contents 
        String imagePath = part + "IRMS\\IRMS-war\\web\\images\\coral_island_banner_customer.png";
//        String imagePath = "C:\\Users\\Administrator\\Desktop\\IS3102\\pdf\\coral_island_banner_customer.png";
        Image image = Image.getInstance(imagePath);
        document.add(image);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(eppe.getEpBookDate());

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Your e-ticket is displayed as below: ", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);

        //Below add a table 
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(30);
        table.setSpacingBefore(30);
        table.setWidths(new int[]{1, 3});

        //Add table header 
        PdfPCell c1 = new PdfPCell(new Phrase("Ticketing Info"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Details & Remarks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        //below add table contents
        table.addCell("Attraction");
        if (eppe.getAttrEPs().get(0).getAttr() == null) {
            System.out.println("ep for vip");
            table.addCell("All Attraction");
        } else {
            table.addCell(eppe.getAttrEPs().get(0).getAttr().getAttrName());
        }

        /*     if (!eppe.getAttrEPs().isEmpty()) {
         table.addCell(eppe.getAttrEPs().get(0).getAttr().getAttrName());
         }else if(eppe.getAttrEPs().get(0).getAttr()==null) {
         System.out.println("ep for vip");
         table.addCell("All Attraction");
         }
         else {
         table.addCell("Attraction Not Assigned Yet!");
         }*/

        if (!eppe.getAttrEPs().isEmpty()) {
            Iterator<AttrExpressPassEntity> itr = eppe.getAttrEPs().iterator();
            Iterator<Integer> itr2 = eppe.getEpQuantities().iterator();
            int i = 1;

            while (itr.hasNext() && itr2.hasNext()) {
                AttrExpressPassEntity currentAttrExpressPass = itr.next();
                Integer currentInteger = itr2.next();
                table.addCell("Express Pass");
                table.addCell(currentAttrExpressPass.getAttrEPName());
                table.addCell("Quantity");
                table.addCell(Integer.toString(currentInteger));

                System.out.println("EmailSessionBean: A Express Ticket has been added!" + i);
                i++;
            }

            table.addCell("Date");
            table.addCell(dateString);
            table.addCell("Price");
            table.addCell(Double.toString(eppe.getEpFee()));
        }
        document.add(table);

        Long eppId = eppe.getEppId();
        String eppIdString = generateBarcodeSessionBean.makeToSevenDigit(String.valueOf(eppId));
        System.out.println("eppIdString: " + eppIdString);
        String ticketPath = part + "\\barcode" + eppIdString + ".jpg";
        //   String ticketPath = "C:\\Users\\Administrator\\Desktop\\IS3102\\Code\\IRMS\\IRMSCustomer-war\\web\\images\\attractionTicket\\" + eppIdString + ".jpg";
        System.out.println("ticketPath: " + ticketPath);

        Image barcode = Image.getInstance(ticketPath);
        document.add(barcode);
        document.close();

        return OUTPUTFILE;
    }

    public void createFeedbackReply(FeedbackEntity feedback, String content) {
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
                    InternetAddress.parse(feedback.getFeedbackOwnerEmail()));
            message.setSubject("(no reply) Your Feedback Update");
            String textbody = content;
            message.setText(textbody);


            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(feedback.getFeedbackOwnerEmail(), "(no reply) Your Feedback Update", textbody, "notification", today);
            feedbackSessionBean.updateFeedbackStatus(feedback, "handled");
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendShowInvitation(ShowEntity show, MemberEntity superVIP) {
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
                    InternetAddress.parse(superVIP.getMemberEmail()));
            message.setSubject("You are invited to an exclusive show event");
            String textbody = "Dear " + superVIP.getMemberName() + "!"
                    + "\n Thank you for being supportive to us all the time, and you are valued as a precious asset to us"
                    + "\nThus we've invited you to an exclusive show, with details listed below"
                    + "\n Name: " + show.getShowName()
                    + "\n Language: " +show.getShowLanguage()
                    +"\n Duration: "+show.getShowDuration()
                    +"\n Description: " + show.getShowDescription()
                    +"\n Type: " + show.getShowType()
                    +"\n You may choose to come for any schedule! Thank you!"
                    + "\n Please login immediately and make the payment.\n\n\nBest Regards,\nThe Coral Island Management Team";
            message.setText(textbody);

            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(superVIP.getMemberEmail(), "Your Are invited to an exclusive show event ", textbody, "notification", today);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void cancelNotification(String toEmailAddress, ReservationEntity newReservation) {
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
                    InternetAddress.parse(toEmailAddress));
            message.setSubject("Your Reservation Has Been Cancelled");
            String textbody = "Greeting from Coral Island Resort!"
                    + "\nYou have successfully cancelled the following reservation: "
                    + "\nHere is your Reservation Id:" + newReservation.getReservationId()
                    + "\nBelow is your reservation detail:"
                    + "\nName: " + newReservation.getRcName()
                    + "\nEmail Address: " + newReservation.getRcEmail()
                    + "\nCheckIn Date: " + newReservation.getRcCheckInDate()
                    + "\nCheckOut Date: " + newReservation.getRcCheckOutDate()
                    + "\nRoom Count " + newReservation.getReservationRoomCount()
                    + "\nRoom Type" + newReservation.getReservationRoomType()
                    + "\n\n We would like you help improve our service by filling in the feed back servey.Thank you!"
                    + "\n\nIn case of any issues and inqueries, you may contact our corporate service manager "
                    + "\n@ 65-8180 1380"
                    + "\n\n\nBest Regards,\nThe Coral Island Management Team";
            message.setText(textbody);


            Transport.send(message);
            Date today = new Date();
            memberMessageSessionBean.createNewMessage(toEmailAddress, "Your initial password", textbody, "memberSummary", today);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
