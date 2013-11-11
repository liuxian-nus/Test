/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMSTesting;

import Exception.ExistException;
import FBMS.entity.DishEntity;
import FBMS.entity.OrderEntity;
import FBMS.session.InventorySessionBeanRemote;
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
public class InventoryTesting {
    
    InventorySessionBeanRemote InventorySessionBean = lookupInventorySessionBean();
    
    public InventoryTesting() {
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
    public void testAddDish()
    {
        System.out.println("testAddDish");
        
        String dishName = "shanzhapian";
        Integer dishquant = 1000;
        Double dishCost = 3.00;
        
        DishEntity test = new DishEntity();
        test.setDishCost(dishCost);
        test.setDishName(dishName);
        test.setDishQuantity(dishquant);
        
        test = InventorySessionBean.addDish(test);
        assertNotNull(test);
    }
    
    @Test
    public void testDeleteDish()
    {
        System.out.println("testDeleteDish");
        
        Long dishId = Long.parseLong("88888");//Please insert test case before testing
        boolean test = InventorySessionBean.deleteDish(dishId);
        
        assertTrue(test);
    }
    
    @Test
    public void testUpdateDishGetDishById() throws ExistException
    {
        System.out.println("testGetDishById");
        
        Long dishId = Long.parseLong("100001");
        DishEntity test = InventorySessionBean.getDishById(dishId);
        assertNotNull(test);
        
        System.out.println("testUpdateDish");
        DishEntity test2 = InventorySessionBean.updateDish(test);
        assertNotNull(test2);
    }
    
    @Test
    public void testListDishes()
    {
        System.out.println("testListDishes");
        
        List <DishEntity> dishes = InventorySessionBean.listDishes();
        assertNotNull(dishes);
    }
    
    @Test
    public void testIssueGoods()
    {
        System.out.println("testIssueGoods");
        
        Long orderId = Long.parseLong("67");
        OrderEntity test = InventorySessionBean.issueGoods(orderId);
        System.out.println(test.getStatus());
        assertNotNull(test);
    }
    
    @Test
    public void testAssignCost()
    {
        System.out.println("testAssignCost");
        
        Long orderId = Long.parseLong("67");
        Double cost = InventorySessionBean.assignCost(orderId);
        assertNotNull(cost);
        System.out.println("the order cost is "+ cost);
    }

    private InventorySessionBeanRemote lookupInventorySessionBean() {
        try {
            Context c = new InitialContext();
            return (InventorySessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/InventorySessionBean!FBMS.session.InventorySessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}