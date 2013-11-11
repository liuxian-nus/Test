/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMSTesting;

import ACMS.entity.OverbookingQuotaEntity;
import ACMS.session.OverbookingSessionBeanRemote;
import ERMS.session.EmployeeSessionBeanRemote;
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
public class OverbookingTesting {
    
    OverbookingSessionBeanRemote OverbookingSessionBean = lookupOverbookingSessionBean();
    
    public OverbookingTesting() {
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
    public void testCalculateSuggestedQuota()
    {
        System.out.println("testCalculateSuggestedQuota");
        
        int demandMean = 100;
        int demandSD = 5;
        double ce = 100;
        
        int test = OverbookingSessionBean.calculateSuggestedQuota(demandMean, demandSD, ce);
        assertNotNull(test);
    }
    
    @Test
    public void testResetQuota()
    {
        System.out.println("testResetQuota");
        
        int quota = 200;
        int test = OverbookingSessionBean.resetQuota(quota);
        assertNotNull(test);
    }
    
    @Test
    public void testGetQuota()
    {
        OverbookingQuotaEntity overbooking = OverbookingSessionBean.getQuota();
        assertSame(overbooking.getClass(),OverbookingQuotaEntity.class);
    }

    
    private OverbookingSessionBeanRemote lookupOverbookingSessionBean() {
       try {
            Context c = new InitialContext();
            return (OverbookingSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/OverbookingSessionBean!ACMS.session.OverbookingSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}