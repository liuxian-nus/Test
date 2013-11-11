/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMSTesting;

import Exception.ExistException;
import FBMS.entity.RestaurantEntity;
import FBMS.session.FBEmailSessionBeanRemote;
import FBMS.session.RestaurantSessionBeanRemote;
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
public class RestaurantTesting {
    
    RestaurantSessionBeanRemote RestaurantSessionBean = lookupRestaurantSessionBean();
    
    public RestaurantTesting() {
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
    public void testAddRestaurant()
    {
        System.out.println("testAddRestaurant: Start!");
        
        String restName = "Test";
        String restNei = "South";
        String restType = "restaurant";
        String restCui = "Chinese";
        Integer restQuota = 100;
        
        RestaurantEntity test = new RestaurantEntity();
        test.setRestCuisine(restCui);
        test.setRestName(restName);
        test.setRestNeighbourhood(restNei);
        test.setRestQuota(restQuota);
        test.setRestTypeOfPlace(restType);
        
        assertNotNull(RestaurantSessionBean.addRestaurant(test));
        
        System.out.println("testUpdateRestaurant: Start!");
        test.setRestName("changed");
        assertTrue(RestaurantSessionBean.updateRestaurant(test));
    }
    
    @Test
    public void testGetAllRestaurants()
    {
        System.out.println("testGetAllRestaurants: Start!");
        
        List<RestaurantEntity> resultList = RestaurantSessionBean.getAllRestaurants();
        assertNotNull(resultList);
        
    }
    
    @Test
    public void testGetRestaurantById() throws ExistException
    {
       System.out.println("testGetRestaurantById: Start!"); 
       
       Long restId = Long.parseLong("1");
       RestaurantEntity test = RestaurantSessionBean.getRestaurantById(restId);
       assertNotNull(test);
    }
    
    @Test
    public void testRemoveRestaurant() throws ExistException
    {
        System.out.println("testRemoveRestaurant: Start!");
        
        Long restId = Long.parseLong("88888");//Please insert test case before testing
        boolean removed = RestaurantSessionBean.removeRestaurant(restId);
        assertTrue(removed);
    }

    private RestaurantSessionBeanRemote lookupRestaurantSessionBean() {
       try {
            Context c = new InitialContext();
            return (RestaurantSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/RestaurantSessionBean!FBMS.session.RestaurantSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}