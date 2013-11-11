/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMSTesting;

import ACMS.session.RoomSessionBeanRemote;
import ERMS.session.EPasswordHashSessionBeanRemote;
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
public class EPasswordHashTesting {
    
    EPasswordHashSessionBeanRemote EPasswordHashSessionBean = lookupEPasswordHashSessionBean();
    
    public EPasswordHashTesting() {
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
    public void testHashPassword()
    {
        System.out.println("testHashPassword");
        
        String pwd = "test";
        
        String test = EPasswordHashSessionBean.hashPassword(pwd);
        assertNotNull(test);
        
    }
    

    private EPasswordHashSessionBeanRemote lookupEPasswordHashSessionBean() {
        try {
            Context c = new InitialContext();
            return (EPasswordHashSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/EPasswordHashSessionBean!ERMS.session.EPasswordHashSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}