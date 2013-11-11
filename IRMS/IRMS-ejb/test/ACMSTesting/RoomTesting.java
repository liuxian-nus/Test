/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMSTesting;

import ACMS.entity.RoomEntity;
import ACMS.session.RoomServiceSessionBeanRemote;
import ACMS.session.RoomSessionBeanRemote;
import Exception.ExistException;
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
    public void testGetAvailableRooms()
    {
        System.out.println("testGetAvailableRooms");
        
        
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