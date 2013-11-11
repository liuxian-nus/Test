/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMSTesting;

import ACMS.entity.RoomEntity;
import ACMS.entity.RoomServiceEntity;
import ACMS.session.RoomServiceSessionBeanRemote;
import ACMS.session.RoomSessionBeanRemote;
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
public class RoomTesting {
    
    RoomSessionBeanRemote RoomSessionBean = lookupRoomSessionBean();
    public RoomTesting() {
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
    public void testGetRoomById() throws ExistException
    {
        System.out.println("testGetRoomById");
        
        int Id = 1105;
        RoomEntity test = RoomSessionBean.getRoomById(Id);
        assertNotNull(test);
        assertSame(test.getClass(),RoomEntity.class);
    }
    
    @Test
    public void testGetAvailableRooms() throws ExistException
    {
        System.out.println("testGetAvailableRooms");
        
        List<RoomEntity> test = RoomSessionBean.getAvailableRooms();
        assertNotNull(test);
        assertSame(test.getClass(),ArrayList.class);
    }
    
    @Test
    public void testGetAllRooms() throws ExistException
    {
        System.out.println("testGetAllRooms");
        
        List<RoomEntity> test = RoomSessionBean.getAllRooms();
        assertNotNull(test);
        assertSame(test.getClass(),ArrayList.class);
    }
    
    @Test
    public void testGetOccupiedRooms() throws ExistException
    {
        System.out.println("testGetOccupiedRooms");
        
        List<RoomEntity> test = RoomSessionBean.getOccupiedRooms();
        assertNotNull(test);
        assertSame(test.getClass(),ArrayList.class);
    }
    
    @Test
    public void testGetCheckInRooms() throws ExistException
    {
        System.out.println("testGetCheckInRooms");
        
        Long reservationId = Long.parseLong("7");//to be modified
        List<RoomEntity> test = RoomSessionBean.getCheckInRooms(reservationId);
        assertNotNull(test);
        assertSame(test.getClass(),ArrayList.class);
        
    }
    
    @Test
    public void testAddRoomService() throws ExistException
    {
        System.out.println("testAddRoomService");
        
        int roomId = 1105;
        String roomServiceName = "Thai Pineapple Rice";
        int quantity = 2;
        
        RoomServiceEntity test = RoomSessionBean.addRoomService(roomId, roomServiceName, quantity);
        assertNotNull(test);
        assertSame(test.getClass(),RoomServiceEntity.class);
        
    }

    @Test
    public void testClearServiceCharge() throws ExistException
    {
       System.out.println("testClearServiceCharge"); 
       
       int roomId = 1105;
       double test = RoomSessionBean.clearServiceCharge(roomId);
       assertNotNull(test);
    }
    
    @Test
    public void testCalculateRoomFee()
    {
       System.out.println("testCalculateRoomFee");
    }
    private RoomSessionBeanRemote lookupRoomSessionBean() {
        try {
            Context c = new InitialContext();
            return (RoomSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/RoomSessionBean!ACMS.session.RoomSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
