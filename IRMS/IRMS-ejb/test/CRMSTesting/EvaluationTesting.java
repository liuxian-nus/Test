/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMSTesting;

import CRMS.session.EvaluationSessionBeanRemote;
import ERMS.session.EmployeeSessionBeanRemote;
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
public class EvaluationTesting {
    
    
    EvaluationSessionBeanRemote EvaluationSessionBean = lookupEvaluationSessionBean();
    public EvaluationTesting() {
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
    public void testCalculateRFMValue() throws ExistException
    {
        System.out.println("testCalculateRFMValue");
        
        String memberEmail = "xinqi_wang@yahoo.com";
        int ModelNumber =1;
        
        int RFMValue = EvaluationSessionBean.calculateRFMValue(memberEmail, ModelNumber);
        System.out.println("testCalculateRFMValue: "+RFMValue);
        assertNotNull(RFMValue);
    }

    private EvaluationSessionBeanRemote lookupEvaluationSessionBean() {
       try {
            Context c = new InitialContext();
            return (EvaluationSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/EvaluationSessionBean!CRMS.session.EvaluationSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}