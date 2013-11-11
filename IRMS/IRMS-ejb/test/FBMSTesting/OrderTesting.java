/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMSTesting;

import FBMS.entity.DishEntity;
import FBMS.entity.OrderEntity;
import FBMS.session.FBEmailSessionBeanRemote;
import FBMS.session.OrderSessionBeanRemote;
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
public class OrderTesting {
    
    OrderSessionBeanRemote OrderSessionBean = lookupOrderSessionBean();
    
    public OrderTesting() {
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
    public void testGetDish()
    {
        System.out.println("testGetDish: start!");
        
        Long dishId = Long.parseLong("100001");
        DishEntity test = OrderSessionBean.getDish(dishId);
        assertNotNull(test);
        
        Long dishId1 = Long.parseLong("000011");
        DishEntity test1 = OrderSessionBean.getDish(dishId1);
        assertNull(test1);
    }
    
    @Test
    public void testGetRequestedOrders()
    {
        System.out.println("testGetRequestedOrders: start!");
        
        List <OrderEntity> orders = OrderSessionBean.getRequestedOrders();
        assertNotNull(orders);
    }
    
    @Test
    public void testGetConfirmedOrders()
    {
        System.out.println("testGetConfirmedOrders: start!");
        
        List <OrderEntity> orders = OrderSessionBean.getConfirmedOrders();
        assertNotNull(orders);
    }
    
    @Test
    public void testUpdateOrderStatus()
    {
        System.out.println("testUpdateOrderStatus: start!");
        
        String status = "Confirmed";
        Long Id = Long.parseLong("67");
        OrderEntity test = OrderSessionBean.updateOrderStatus(status, Id);
        assertNotNull(test);
        
        System.out.println("testUpdateOrder: start!");
        boolean updated = OrderSessionBean.updateOrder(test);
        assertTrue(updated);
        
        System.out.println("testConfirmOrder: start!");
        boolean confirmed = OrderSessionBean.confirmOrder(test);
        assertTrue(confirmed);
        
        System.out.println("testViewOrder: start!");
        OrderEntity test1 = OrderSessionBean.viewOrder(Id);
        assertNotNull(test1);
    }
    
    
   
    private OrderSessionBeanRemote lookupOrderSessionBean() {
       try {
            Context c = new InitialContext();
            return (OrderSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/OrderSessionBean!FBMS.session.OrderSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}