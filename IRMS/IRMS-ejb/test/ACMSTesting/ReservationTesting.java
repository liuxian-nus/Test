/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMSTesting;

import ACMS.entity.ReservationEntity;
import ACMS.session.OverbookingSessionBeanRemote;
import ACMS.session.ReservationSessionBeanRemote;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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
public class ReservationTesting {
    
    
    ReservationSessionBeanRemote ReservationSessionBean = lookupReservationSessionBean();
    
    public ReservationTesting() {
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
    public void testGetAllReservations()
    {
        System.out.println("testGetAllReservations");
        
        List<ReservationEntity> test = ReservationSessionBean.getAllReservations();
        assertNotNull(test);
        assertSame(test.getClass(),ArrayList.class);
    }
    
    @Test
    public void testGetTodayReservations()
    {
       System.out.println("testGetTodayReservations"); 
       
       List<ReservationEntity> test = ReservationSessionBean.getTodayReservations();
       assertNotNull(test);
    }
    
    @Test
    public void testGetBeforeReservations()
    {
        System.out.println("testGetBeforeReservations");
        
        List<ReservationEntity> test = ReservationSessionBean.getBeforeReservations();
        assertNotNull(test);
    }
    
    @Test
    public void testGetReservationById() throws ExistException
    {
       System.out.println("testGetReservationById"); 
       
       Long Id = Long.parseLong("7");
       ReservationEntity test = ReservationSessionBean.getReservationById(Id);
       assertNotNull(test);
    }
    
    @Test
    public void testGetReservationByName()
    {
        System.out.println("testGetReservationByName");
        
        String rcname = "Diana";
        List<ReservationEntity> test = ReservationSessionBean.getReservationByName(rcname);
        assertNotNull(test);
        assertSame(test.getClass(),Vector.class);
    }
    
    @Test
    public void testGetReservationByEmail()
    {
        System.out.println("testGetReservationByEmail");
        
        String email = "diana-wang@yahoo.com";
        List<ReservationEntity> test = ReservationSessionBean.getReservationByEmail(email);
        assertNotNull(test);
        assertSame(test.getClass(),Vector.class);
    }
    
    @Test
    public void testGetReservationByDate()
    {
        System.out.println("testGetReservationByDate");
        
        Date today = new Date(2014,11,1);
        List<ReservationEntity> test = ReservationSessionBean.getReservationByDate(today);
        assertNotNull(test);
//        assertSame(test.getClass(),ArrayList.class);
    }
    
    @Test
    public void testCancelReservation()
    {
        System.out.println("testCancelReservation");
        
        Long Id = Long.parseLong("99999");
        ReservationSessionBean.cancelReservation(Id);
    }
    

    private ReservationSessionBeanRemote lookupReservationSessionBean() {
        try {
            Context c = new InitialContext();
            return (ReservationSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ReservationSessionBean!ACMS.session.ReservationSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}