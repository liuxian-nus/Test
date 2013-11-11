/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMSTesting;

import ACMS.entity.RoomPriceEntity;
import ACMS.session.ReservationSessionBeanRemote;
import ACMS.session.RoomPriceSessionBeanRemote;
import java.util.ArrayList;
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
public class RoomPriceTesting {
    
    RoomPriceSessionBeanRemote RoomPriceSessionBean = lookupRoomPriceSessionBean();
    public RoomPriceTesting() {
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
    public void testCreatePrice()
    {
        System.out.println("testCreatePrice");
        
        String priceType = "test";
        double price = 100.00;
        RoomPriceEntity test = new RoomPriceEntity();
        
        test.setPrice(price);
        test.setPriceType(priceType);
        
        RoomPriceSessionBean.createPrice(test);
        System.out.println("testCreatePrice completed!");
        
        System.out.println("testUpdatePrice");
        RoomPriceSessionBean.updatePrice(test);
        System.out.println("testUpdatePrice completed!");
    }

    @Test
    public void testGetAllRoomPrices()
    {
        System.out.println("testGetAllRoomPrices");
        
        List<RoomPriceEntity> test = RoomPriceSessionBean.getAllRoomPrices();
        assertNotNull(test);
        assertSame(test.getClass(),ArrayList.class);
        
    }
    private RoomPriceSessionBeanRemote lookupRoomPriceSessionBean() {
       try {
            Context c = new InitialContext();
            return (RoomPriceSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/RoomPriceSessionBean!ACMS.session.RoomPriceSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}