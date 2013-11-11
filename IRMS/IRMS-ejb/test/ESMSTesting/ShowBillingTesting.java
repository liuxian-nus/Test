/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMSTesting;

import ESMS.entity.ShowEntity;
import ESMS.entity.ShowTicketSaleEntity;
import ESMS.session.ShowBillingSessionBeanRemote;
import ESMS.session.ShowContractSessionBeanRemote;
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
public class ShowBillingTesting {
    
    ShowBillingSessionBeanRemote sbsb = lookupShowBillingSessionBean();
    
    
    public ShowBillingTesting() {
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
    public void testGetAllSelectedShows()
    {
        System.out.println("testGetAllSelectedShows: start");
        
        List <ShowEntity> testList = sbsb.getAllSelectedShows();
        assertNotNull(testList);
    }
    
    @Test
    public void testGetAllSelectedShowTicketSales()
    {
        System.out.println("testGetAllSelectedShowTicketSales");
        
        Long Id = Long.parseLong("70002");
        List <ShowTicketSaleEntity> testList = sbsb.getAllSelectedShowTicketSales(Id);
        assertNotNull(testList);            
    }
    

    private ShowBillingSessionBeanRemote lookupShowBillingSessionBean() {
        try {
            Context c = new InitialContext();
            return (ShowBillingSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ShowBillingSessionBean!ESMS.session.ShowBillingSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}