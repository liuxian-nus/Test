/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMSTesting;

import ESMS.entity.ShowContractEntity;
import ESMS.session.ShowContractSessionBeanRemote;
import ESMS.session.ShowSessionBeanRemote;
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
public class ShowContractTesting {
    
    ShowContractSessionBeanRemote scsb = lookupShowContractSessionBean();
    ShowSessionBeanRemote ssb = lookupShowSessionBean();
   
    public ShowContractTesting() {
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
    public void testDeleteShowContract()
    {
        System.out.println("testDeleteShowContract");
        
        Long Id = Long.parseLong("151");//Please insert test data before testng running
        boolean deleted = scsb.deleteShowContract(Id);
        assertTrue(deleted);
    }
    
    @Test
    public void testUpdateShowContract()
    {
        System.out.println("testUpdateShowContract");
        
        Long Id = Long.parseLong("1");
        ShowContractEntity current = scsb.thisShowContract(Id);
        boolean updated = scsb.updateShowContract(current);
        assertTrue(updated);
    }
    
    @Test
    public void testGetAllShowContracts()
    {
        System.out.println("testGetAllShowContracts");
        
        List <ShowContractEntity> shows = scsb.getAllShowContracts();
        assertNotNull(shows);
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

    private ShowSessionBeanRemote lookupShowSessionBean() {
        try {
            Context c = new InitialContext();
            return (ShowSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ShowSessionBean!ESMS.session.ShowSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}