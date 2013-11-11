/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMSTesting;

import ESMS.entity.ShowContractEntity;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.session.ShowContractSessionBeanRemote;
import ESMS.session.ShowScheduleSessionBeanRemote;
import ESMS.session.ShowSessionBeanRemote;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diana Wang
 */
public class ShowShowScheduleTesting {

    ShowSessionBeanRemote ssb = lookupShowSessionBean();
    ShowContractSessionBeanRemote scsb = lookupShowContractSessionBean();
    ShowScheduleSessionBeanRemote sssb = lookupShowScheduleSessionBean();
    
    
    public ShowShowScheduleTesting() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void testAddUpdateShow() {
        
        System.out.println("testAddShow: Start");
        String showName = "testShow";
        String showName1 = null;
        String showDescription = "This is a test";
        String showDescription1 = null;
        String showType = "Internal";
        String showType1 = null;
        boolean showStatus = true;
        boolean showPaymentStatus = false;

        //Test first add case
        ShowEntity se1 = new ShowEntity();
        se1.setShowDescription(showDescription);
        se1.setShowName(showName);
        se1.setShowType(showType);
        se1.setShowStatus(showStatus);
        se1.setShowPaymentStatus(showPaymentStatus);
        ssb.addShow(se1);
        assertNotNull(se1);
        
        System.out.println("testUpdateShow1");
        se1.setShowName("changed");
        assertTrue(ssb.updateShow(se1));

        //Test second add case
        ShowEntity se2 = new ShowEntity();
        se2.setShowDescription(showDescription1);
        se2.setShowName(showName1);
        se2.setShowPaymentStatus(showPaymentStatus);
        se2.setShowStatus(showStatus);
        se2.setShowType(showType1);
        ssb.addShow(se2);
        assertNotNull(se2);

    }

    @Test
    public void testAddContract() {
        System.out.println("testAddContract: start!");

        String showMerchantName = "TEST";
        String showMerchantName1 = null;
        String showMerchantEmail = "Test";
        String showMerchantEmail1 = null;
        String showMerchantContact = "test";
        String showMerchantContact1 = null;
        String showMerchantAddress = "test";
        String showMerchantAddress1 = null;
        Double showVenueRate = Double.parseDouble("-10");
        Double showVenueRate1 = Double.parseDouble("10");
        Double showTicketCommission = Double.parseDouble("-10");
        Double showTicketCommission1 = Double.parseDouble("0.01");
        int showVenueDuration = 3;
        int showVenueDuration1 = -1;

        ShowContractEntity sce = new ShowContractEntity();
        ShowContractEntity sce1 = new ShowContractEntity();

        sce.setShow(ssb.getShowById(Long.parseLong("6")));
        sce.setShowMerchantAddress(showMerchantAddress);
        sce.setShowMerchantContact(showMerchantContact);
        sce.setShowMerchantEmail(showMerchantEmail);
        sce.setShowMerchantName(showMerchantName);
        sce.setShowVenueRate(showVenueRate1);
        sce.setShowTicketCommission(showTicketCommission1);
        sce.setShowVenueDuration(showVenueDuration);


        sce1.setShow(ssb.getShowById(Long.parseLong("6")));
        sce1.setShowMerchantAddress(showMerchantAddress1);
        sce1.setShowMerchantContact(showMerchantContact1);
        sce1.setShowMerchantEmail(showMerchantEmail1);
        sce1.setShowMerchantName(showMerchantName1);
        sce1.setShowTicketCommission(showTicketCommission);
        sce1.setShowVenueDuration(showVenueDuration1);
        sce1.setShowVenueRate(showVenueRate);

        scsb.addShowContract(sce);
        scsb.addShowContract(sce1);

        assertNotNull(sce);
        assertNotNull(sce1);

    }
    
    @Test
    public void testGetAllShows()
    {
        List <ShowEntity> ses = ssb.getAllShows();
        assertNotNull(ses);
    }
    
    @Test
    public void testGetAllShowSchedules()
    {
        List <ShowScheduleEntity> sss = ssb.getAllShowSchedules(Long.parseLong("5"));
        assertNotNull(sss);
    }
    
    @Test
    public void testAddShowSchedule()
    {
        System.out.println("testAddShowSchedule");
        Long showId = Long.parseLong("70002");
        Long showScheduleId = Long.parseLong("70006");
        
        ShowScheduleEntity sse = sssb.getShowScheduleById(showScheduleId);
        
        ssb.addShowSchedule(showId, sse);
        assertNotNull(sse);
        assertNotNull(ssb.getShowById(showId).getShowSchedules());
        
    }
    
    @Test
    public void testDeleteShowSchedule()
    {
        System.out.println("testDeleteShowSchedule");
        sssb.deleteShowSchedule(Long.parseLong("88888"));//Please insert this test case before testing
        assertNull(sssb.getShowScheduleById(Long.parseLong("88888")));
    }
    
    @Test
    public void testUpdateShowSchedule()
    {
        System.out.println("testUpdateShowSchedule");
        Long Id = Long.parseLong("70003");
        ShowScheduleEntity sse = sssb.getShowScheduleById(Id);
        assertNotNull(sse);
        sse.setStartDateTime(sse.getStartDateTime());
        
        
        assertTrue(sssb.updateShowSchedule(sse));
    }
    
    @Test
    public void testGetShowScheduleById()
    {
        System.out.println("testGetShowScheduleById");
        
        Long Id = Long.parseLong("70006");
        assertNotNull(sssb.getShowScheduleById(Id));
    }

    private ShowSessionBeanRemote lookupShowSessionBean() {
        try {
            Context c = new InitialContext();
            return (ShowSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ShowSessionBean!ESMS.session.ShowSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ShowContractSessionBeanRemote lookupShowContractSessionBean() {
        try {
            Context c = new InitialContext();
            return (ShowContractSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ShowContractSessionBean!ESMS.session.ShowContractSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }

    }

    private ShowScheduleSessionBeanRemote lookupShowScheduleSessionBean() {
        try {
            Context c = new InitialContext();
            return (ShowScheduleSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ShowScheduleSessionBean!ESMS.session.ShowScheduleSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}




