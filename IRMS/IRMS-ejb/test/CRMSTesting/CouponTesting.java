/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMSTesting;

import CRMS.session.CouponSessionBeanRemote;
import CRMS.session.EvaluationSessionBeanRemote;
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
public class CouponTesting {
    
    CouponSessionBeanRemote CouponSessionBean = lookupCouponSessionBean();
    
    public CouponTesting() {
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
    

    private CouponSessionBeanRemote lookupCouponSessionBean() {
         try {
            Context c = new InitialContext();
            return (CouponSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/CouponSessionBean!CRMS.session.CouponSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}