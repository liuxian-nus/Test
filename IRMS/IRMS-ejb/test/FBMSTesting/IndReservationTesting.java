/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMSTesting;

import FBMS.entity.IndReservationEntity;
import FBMS.entity.RestaurantEntity;
import FBMS.session.FBEmailSessionBeanRemote;
import FBMS.session.IndReservationSessionBeanRemote;
import java.util.Date;
import java.util.Set;
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
public class IndReservationTesting {
    
    FBEmailSessionBeanRemote FBEmailSessionBean = lookupFBEmailSessionBean();
    IndReservationSessionBeanRemote indReservationSessionBean = lookupIndReservationSessionBean();
   
    public IndReservationTesting() {
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
    public void testSendConfirmation()
    {
        System.out.println("testSendConfirmation: start");
        
        String toEmailAddress = "xinqi_wang@yahoo.com";
        Long Id = Long.parseLong("1");
        IndReservationEntity ire = indReservationSessionBean.viewReservation(Id);//Please insert data before testing
        
        boolean sent = FBEmailSessionBean.sendConfirmation(toEmailAddress, ire);
        assertTrue(sent);
        
        
    }
    
    @Test
    public void testGetRestaurantEntity()
    {
       System.out.println("testGetRestaurantEntity: start"); 
       
       Long restId = Long.parseLong("1");
       RestaurantEntity test = indReservationSessionBean.getRestaurantEntity(restId);
       assertNotNull(test);
       System.out.println(test);
       
    }
    
    @Test
    public void testCreateRestaurantEntity()
    {
        System.out.println("testCreateRestaurantEntity: start");
        
        String restNeighbourhood = "North";
        String restTypeOfPlace = "restaurant";
        String restCuisine = "Chinese";
        String keyword = "Hibernate";
        
        RestaurantEntity test = indReservationSessionBean.createRestaurantEntity(restNeighbourhood, restTypeOfPlace, restCuisine, keyword);
        assertNotNull(test);
    }
    
    @Test
    public void testSearchRestaurant()
    {
        System.out.println("testSearchRestaurant: start");
        
        String restCuisine = "Chinese";
        String rest = "";
        RestaurantEntity re= indReservationSessionBean.createRestaurantEntity(rest, rest, restCuisine, rest);
        Set <RestaurantEntity> tests = indReservationSessionBean.searchRestaurant(re);
        assertNotNull(tests);
    }
    
    
    @Test
    public void testViewRestaurantDetails()
    {
       System.out.println("testViewRestaurantDetails: start"); 
       
       Long restId = Long.parseLong("1");
       Set <RestaurantEntity> tests = indReservationSessionBean.viewRestaurantDetails(restId);
       assertNotNull(tests);
      
    }
    
    @Test
    public void testCheckAvailability()
    {
        System.out.println("testCheckAvailability: start"); 
        
        Long restId = Long.parseLong("1");
        int numberPeople = 10;
        Date date = new Date();
        RestaurantEntity re = indReservationSessionBean.getRestaurantEntity(restId);
        
        boolean test = indReservationSessionBean.checkAvailability(re, numberPeople, date);
        assertTrue(test);
        
    }
    
    @Test
    public void testMakeReservation()
    {
        System.out.println("testMakeReservation: start");
        
        Date indReservationDateTime = new Date();
        Long restId = Long.parseLong("1");
        Integer number = 10;
        String title = "Ms.";
        String name = "WANG XINQI";
        String email = "xinqi_wang@yahoo.com";
        String mobile = "92728760";
        String notes = "N.A";
        
        IndReservationEntity ire = indReservationSessionBean.makeReservation(indReservationDateTime, restId, number, title, name, email, mobile, notes);
        assertNotNull(ire);
        
        System.out.println("testConfirmReservation: start");
        boolean confirmed =  indReservationSessionBean.confirmReservation(ire);
        assertTrue (confirmed);
        
       
    }
    
    @Test
    public void testViewReservation()
    {
       System.out.println("testViewReservation: start");
       
       Long Id = Long.parseLong("1");
       IndReservationEntity ire = indReservationSessionBean.viewReservation(Id);
       assertNotNull(ire);
    }
    
    @Test
    public void testModifyReservation()
    {
       System.out.println("testModifyReservation: start");
       
       Long IndId = Long.parseLong("1");
//       IndReservationEntity test = indReservationSessionBean.viewReservation(IndId);
       
       Date indReservationDateTime = new Date();
        Long restId = Long.parseLong("1");
        Integer number = 5;
        String title = "Ms.";
        String name = "WANG XINQI";
        String email = "xinqi_wang@yahoo.com";
        String mobile = "92728760";
        String notes = "N.A";
        
        IndReservationEntity test = indReservationSessionBean.modifyReservation("Status", restId, indReservationDateTime, IndId, number, title, name, email, mobile, notes);
        assertNotNull(test);
    }
    
    
    private FBEmailSessionBeanRemote lookupFBEmailSessionBean() {
        try {
            Context c = new InitialContext();
            return (FBEmailSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/FBEmailSessionBean!FBMS.session.FBEmailSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private IndReservationSessionBeanRemote lookupIndReservationSessionBean() {
       try {
            Context c = new InitialContext();
            return (IndReservationSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/IndReservationSessionBean!FBMS.session.IndReservationSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}