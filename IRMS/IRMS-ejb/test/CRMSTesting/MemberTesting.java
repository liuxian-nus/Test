/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMSTesting;

import CRMS.entity.MemberEntity;
import CRMS.session.FeedbackSessionBeanRemote;
import CRMS.session.MemberSessionBeanRemote;
import java.util.ArrayList;
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

public class MemberTesting {
    
    MemberSessionBeanRemote MemberSessionBean = lookupMemberSessionBean();
    public MemberTesting() {
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
    public void testGetMemberByEmail()
    {
        System.out.println("testGetMemberByEmail");
        
        String email = "xinqi_wang@yahoo.com";
        MemberEntity test = MemberSessionBean.getMemberByEmail(email);
        assertNotNull(test);
    }
    
    @Test
    public void testGetMemberByBirthMonth()
    {
        System.out.println("testGetMemberByBirthMonth");
        
        List<MemberEntity> test = MemberSessionBean.getMemberByBirthMonth(3);
        assertSame(test.getClass(),ArrayList.class);
    }

    private MemberSessionBeanRemote lookupMemberSessionBean() {
       try {
            Context c = new InitialContext();
            return (MemberSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/MemberSessionBean!CRMS.session.MemberSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}