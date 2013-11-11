/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMSTesting;

import ACMS.entity.RoomServiceEntity;
import ACMS.session.RoomPriceSessionBeanRemote;
import ACMS.session.RoomServiceSessionBeanRemote;
import Exception.ExistException;
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
public class RoomServiceTesting {
    
    
    RoomServiceSessionBeanRemote RoomServiceSessionBean = lookupRoomServiceSessionBean();
    
    public RoomServiceTesting() {
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
    public void testAddRoomService()
    {
        System.out.println("testAddRoomService");
        
        String roomServiceName = "test";
        String category="test";
        double roomServicePrice= 10.00;
        
        RoomServiceEntity test = new RoomServiceEntity();
        test.setCategory(category);
        test.setRoomServiceName(roomServiceName);
        test.setRoomServicePrice(roomServicePrice);
        
        RoomServiceSessionBean.addRoomService(test);
        System.out.println("testAddRoomService: completed!");
    }
    
    @Test
    public void testRemoveRoomService() throws ExistException
    {
        System.out.println("testRemoveRoomService");
        
        String rsnm = "Laundry";
        boolean removed = RoomServiceSessionBean.removeRoomService(rsnm);
        assertTrue(removed);
    }
    
    @Test
    public void testUpdateRoomService() throws ExistException
    {
        System.out.println("testUpdateRoomService");
        
        String roomServiceName = "TV Channel Subscription 1";
        String category = "free service";
        double roomServicePrice = 0.0;
        
        boolean updated = RoomServiceSessionBean.updateRoomService(roomServiceName, category, roomServicePrice);
        assertTrue(updated);
    }
    
    @Test
    public void testGetServiceByName() throws ExistException
    {
        System.out.println("testGetServiceByName");
        
        String roomServiceName ="Chocolate Puff";
        RoomServiceEntity test = RoomServiceSessionBean.getServiceByName(roomServiceName);
        assertNotNull(test);
        assertSame(test.getClass(),RoomServiceEntity.class);
    }
    
    @Test
    public void testGetAllRoomServices() throws ExistException
    {
        System.out.println("testGetAllRoomServices");
        
        List<RoomServiceEntity> test = RoomServiceSessionBean.getAllRoomServices();
        assertNotNull(test);
        assertSame(test.getClass(),ArrayList.class);
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
}