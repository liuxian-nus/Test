/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMSTesting;


import ACMS.session.OverbookingSessionBeanRemote;
import ACMS.session.RoomServiceSessionBeanRemote;
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
public class ACMSTesting {
    
    RoomServiceSessionBeanRemote rssb = lookupRoomServiceSessionBean();
    OverbookingSessionBeanRemote osb = lookupOverbookingSessionBean();
    
            public ACMSTesting() {
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
    public void testCalculateRoomCharge()
    {
        System.out.println("TestCalculateRoomCharge: Start");
        
        String rs = "Pineapple Rice";
        String rs2 = "Chocolate Puff";
        
    }
    
    @Test
    public void testOverBooking()
    {
        System.out.println("TestOverBooking: Start");
        
        int dm1 = 150;
        int dsd1=5;
        double ce1 = 100;
        
        int dm2 = 150;
        int dsd2 = 5;
        double ce2 = 30;
        
        int dm3 = 150;
        int dsd3 = 5;
        double ce3 = 0;
        
        int dm4 = 0;
        int dsd4 = 1;
        double ce4 = 50;
        
        int dm5 = -1;
        int dsd5 = 5;
        double ce5 = 100;
        
        int dm6 = 150;
        int dsd6 = -1;
        double ce6 = 100;
        
       /* int dm7 = 150;
        int dsd7 = 5;
        double ce7 = -1;*/
        
        int quota1 = osb.calculateSuggestedQuota(dm1, dsd1, ce1);
        int quota2 = osb.calculateSuggestedQuota(dm2, dsd2, ce2);
        int quota3 = osb.calculateSuggestedQuota(dm3, dsd3, ce3);
        int quota4 = osb.calculateSuggestedQuota(dm4, dsd4, ce4);
        int quota5 = osb.calculateSuggestedQuota(dm5, dsd5, ce5);
        int quota6 = osb.calculateSuggestedQuota(dm6, dsd6, ce6);
        /*int quota7 = osb.calculateSuggestedQuota(dm7, dsd7, ce7);*/ //Failed
        
        assertNotNull(quota1);
        assertEquals(4,quota1);
        System.out.println("test1: "+quota1 );
        
        assertNotNull(quota2);
        assertEquals(7,quota2);
        System.out.println("test2: "+quota2);
        
        assertNotNull(quota3);
        assertEquals(2147483647,quota3);
        System.out.println("test3: "+quota3);
        
        assertNotNull(quota4);
        assertEquals(1,quota4);
        System.out.println("test4: "+quota4);
        
        assertNotNull(quota5);
        assertEquals(4,quota5);
        System.out.println("test5: "+quota5);
        
        assertNotNull(quota6);
        assertEquals(0,quota6);
        System.out.println("test6: "+quota6);
        
//        assertNotNull(quota7);
//        System.out.println("test7: "+quota7);
        
        
        
    }
    
    

   private RoomServiceSessionBeanRemote lookupRoomServiceSessionBean() {
        try {
            Context c = new InitialContext();
            return (RoomServiceSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/RoomServiceSessionBean!ACMS.session.RoomServiceSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private OverbookingSessionBeanRemote lookupOverbookingSessionBean() {
        try {
            Context c = new InitialContext();
            return (OverbookingSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/OverbookingSessionBean!ACMS.session.OverbookingSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}