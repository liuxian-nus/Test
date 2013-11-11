/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMSTesting;

import ESMS.entity.ShowTicketSaleEntity;
import ESMS.session.ShowBillingSessionBeanRemote;
import ESMS.session.ShowTicketSaleSessionBeanRemote;
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
public class ShowTicketSaleTesting {
    
    ShowTicketSaleSessionBeanRemote stssb = lookupShowTicketSaleSessionBean();
    
    public ShowTicketSaleTesting() {
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
    public void testGetAllShowTicketSales()
    {
        System.out.println("testGetAllShowTicketSales: start");
        
        List <ShowTicketSaleEntity> testList = stssb.getAllShowTicketSales();
        assertNotNull(testList);
    }
    
    @Test
    public void testAddUpdateShowTicketSale()
    {
        System.out.println("testAddShowTicketSale: start");
        
        ShowTicketSaleEntity stse = new ShowTicketSaleEntity();
        stse.setShowStartDateTime(new Date());
        stse.setShowTicketPrice(0.00);
        stse.setShowTicketQuantity(100);
        stse.setShowTicketStatus(true);
        stse.setShowTicketType("cat1");
        
        ShowTicketSaleEntity test = stssb.addShowTicketSale(stse);
        assertNotNull(test);
        
        System.out.println("testUpdateShowTicketSale: start");
        
        stse.setShowTicketQuantity(200);
        boolean updated = stssb.updateShowTicketSale(stse);
        assertTrue(updated);
    }
    
    @Test
    public void testDeleteShowTicketSale()
    {
        System.out.println("testDeleteShowTicketSale: start");
        
        Long Id = Long.parseLong("88888");//Please insert this test case before testing
        boolean test = stssb.deleteShowTicketSale(Id);
        
        assertTrue(test);
      
    }
    
  

    private ShowTicketSaleSessionBeanRemote lookupShowTicketSaleSessionBean() {
        try {
            Context c = new InitialContext();
            return (ShowTicketSaleSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ShowTicketSaleSessionBean!ESMS.session.ShowTicketSaleSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}