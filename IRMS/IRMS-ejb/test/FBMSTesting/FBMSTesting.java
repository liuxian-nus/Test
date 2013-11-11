/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMSTesting;

import FBMS.entity.IndReservationEntity;
import FBMS.entity.RestaurantEntity;
import FBMS.session.FBEmailSessionBeanRemote;
import FBMS.session.IndReservationSessionBeanRemote;
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
public class FBMSTesting {

    FBEmailSessionBeanRemote fBEmailSessionBeanRemote = lookupFBEmailSessionBeanBean();
    IndReservationSessionBeanRemote indReservationSessionBean = lookupIndReservationSessionBeanLocal();

    public FBMSTesting() {
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

    private IndReservationSessionBeanRemote lookupIndReservationSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (IndReservationSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/IndReservationSessionBean!FBMS.session.IndReservationSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    @Test
    public void testGetRestaurantEntity() {
        System.out.println("testGetRestaurantEntity");

        Long restId = Long.parseLong("1");
        RestaurantEntity re = indReservationSessionBean.getRestaurantEntity(restId);
        assertNotNull(re);
    }

   @Test
    public void testSearchRestaurant() {
        System.out.println("testSearchRestaurant");

        String kw1 = "Royal China";
        String kw2 = "";
        String cui1 = "Chinese";
        String Type2 = "";
        String Nei = "";
        String cui = "";

        RestaurantEntity re1 = indReservationSessionBean.createRestaurantEntity(Nei, Type2, cui1, kw1);
        RestaurantEntity re2 = indReservationSessionBean.createRestaurantEntity(Nei, Type2, cui, kw2);

        Set<RestaurantEntity> rests = indReservationSessionBean.searchRestaurant(re1);
        Set<RestaurantEntity> rests1 = indReservationSessionBean.searchRestaurant(re2);
        System.out.println(rests.isEmpty());

        assertFalse(rests.isEmpty());
        assertEquals(1, rests.size());

        assertFalse(rests1.isEmpty());
        assertEquals(14, rests1.size());

    }

    @Test
    public void testTableReservation() {
        System.out.println("testTableReservation");

        Long reId1 = Long.parseLong("1");
        System.out.println(reId1);
        Long reId2 = Long.parseLong("99");
        Long reId3 = Long.parseLong("0");

        IndReservationEntity ire1 = indReservationSessionBean.viewReservation(reId1);
        IndReservationEntity ire2 = indReservationSessionBean.viewReservation(reId2);
        IndReservationEntity ire3 = indReservationSessionBean.viewReservation(reId3);

        assertNotNull(ire1);
        assertNull(ire2);
        assertNull(ire3);

    }

    @Test
    public void testSendEmail() {
        System.out.println("testSendEmail");
        fBEmailSessionBeanRemote.toString();
    }

    private FBEmailSessionBeanRemote lookupFBEmailSessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (FBEmailSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/FBEmailSessionBean!FBMS.session.FBEmailSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}