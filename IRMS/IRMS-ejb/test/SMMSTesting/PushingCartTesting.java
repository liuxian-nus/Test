/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMSTesting;

import Exception.ExistException;
import SMMS.entity.PushingcartEntity;
import SMMS.session.MerchantSessionBeanRemote;
import SMMS.session.PushingcartSessionBeanRemote;
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
public class PushingCartTesting {
    
    PushingcartSessionBeanRemote PushingcartSessionBean = lookupPushingcartSessionBean();
    public PushingCartTesting() {
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
    public void testAddUpdatePushingcart()
    {
        System.out.println("testAddPushingcart");
        
        String pushingcartType = "trolley";
        int pushingcartLevel =2;
        String pushingcartArea = "north";
        int pushingcartInventory = 28;
        
        PushingcartEntity test = new PushingcartEntity();
        test.setPushingcartArea(pushingcartArea);
        test.setPushingcartInventory(pushingcartInventory);
        test.setPushingcartLevel(pushingcartLevel);
        test.setPushingcartType(pushingcartType);
        
        PushingcartEntity tested = PushingcartSessionBean.addPushingcart(test);
        assertNotNull(tested);
        
        System.out.println("testUpdatePushingcart");
        
        tested.setPushingcartInventory(30);
        boolean updated = PushingcartSessionBean.updatePushingcart(tested);
        assertTrue(updated);
    }
    
    @Test
    public void testRemovePushingcart() throws ExistException
    {
        System.out.println("testRemovePushingcart");
        
        Long pushingcartId = Long.parseLong("88888");//Please insert test case before testing
        
        boolean removed = PushingcartSessionBean.removePushingcart(pushingcartId);
        assertTrue(removed);
    }
    
    @Test
    public void testGetAllPushingcarts()
    {
        System.out.println("testGetAllPushingcarts");
        
        List<PushingcartEntity> test = PushingcartSessionBean.getAllPushingcarts();
        assertNotNull(test);
    }
    

    private PushingcartSessionBeanRemote lookupPushingcartSessionBean() {
        try {
            Context c = new InitialContext();
            return (PushingcartSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/PushingcartSessionBean!SMMS.session.PushingcartSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}