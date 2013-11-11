/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMSTesting;

import ESMS.entity.ShowTicketEntity;
import ESMS.session.ShowTicketSaleSessionBeanRemote;
import ESMS.session.ShowTicketSessionBeanRemote;
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
public class ShowTicketTesting {
    
    ShowTicketSessionBeanRemote stsb = lookupShowTicketSessionBean();
    
    public ShowTicketTesting() {
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
    public void testAddUpdateShowTicket()
    {
        System.out.println("testAddShowTicket: start");
        
        ShowTicketEntity ste = new ShowTicketEntity();
        ste.setShowTicketPrice(30.00);
        ste.setShowTicketQuantity(100);
        ste.setShowTicketQuota(50);
        ste.setShowTicketType("Cat1");
        
        ShowTicketEntity test = stsb.addShowTicket(ste);
        assertNotNull(test);
        
        System.out.println("testUpdateShowTicket: start");
        
        test.setShowTicketQuantity(400);
        boolean t = stsb.updateShowTicket(test);
        assertTrue(t);
        
    }
    
    @Test
    public void testDeleteShowTicket()
    {
       System.out.println("testDeleteShowTicket: start"); 
       
       Long Id = Long.parseLong("88888");//Please insert this test case before testing
       boolean t = stsb.deleteShowTicket(Id);
       assertTrue(t);
       
    }
    
    @Test
    public void testGetAllShowTickets()
    {
        System.out.println("testGetAllShowTickets: start");
        
        List<ShowTicketEntity> testList = stsb.getAllShowTickets();
        assertNotNull(testList);
    }
    
    @Test
    public void testGetShowTicketById()
    {
        System.out.println("testGetShowTicketById: start");
        
        Long Id = Long.parseLong("70007");
        ShowTicketEntity ste = stsb.getShowTicketById(Id);
        assertNotNull(ste);
    }
    
    @Test
    public void testUpdateQuantity()
    {
        System.out.println("testUpdateQuantity: start");
        Long Id = Long.parseLong("70008");
        stsb.updateQuantity(Id, 10);
        System.out.println(stsb.getShowTicketById(Id).getShowTicketQuantity());
    }
    private ShowTicketSessionBeanRemote lookupShowTicketSessionBean() {
        try {
            Context c = new InitialContext();
            return (ShowTicketSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ShowTicketSessionBean!ESMS.session.ShowTicketSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
}
}