/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMSTesting;

import ACMS.entity.RoomEntity;
import ACMS.session.RoomSessionBeanRemote;
import ERMS.session.EPasswordHashSessionBeanRemote;
import ERMS.session.EmailSessionBeanRemote;
import Exception.ExistException;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class EmailTesting {
    
    EmailSessionBeanRemote EmailSessionBean = lookupEmailSessionBean();
    RoomSessionBeanRemote RoomSessionBean = lookupRoomSessionBean();
    public EmailTesting() {
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
    public void testEmailInitialPassward()
    {
        System.out.println("testEmailInitialPassward");
        
        String toEmailAdress = "xinqi_wang@yahoo.com";
        String initialPassword = "TEST";
        
        EmailSessionBean.emailInitialPassward(toEmailAdress, initialPassword);
    }

    @Test
    public void testEmailGeneratedPassword()
    {
        System.out.println("testEmailGeneratedPassword");
        
        String toEmailAdress = "xinqi_wang@yahoo.com";
        String initialPassword = "TEST";
        
        EmailSessionBean.emailGeneratedPassword(toEmailAdress, initialPassword);
    }
    
    @Test
    public void testEmailCorporateBill() throws ExistException, IOException, FileNotFoundException, DocumentException
    {
        System.out.println("testEmailCorporateBill");
        
        String toEmailAdress = "xinqi_wang@yahoo.com";
        RoomEntity room = RoomSessionBean.getRoomById(1105);
        
        EmailSessionBean.emailCorporateBill(toEmailAdress, room);
    }
    
    
    
    private EmailSessionBeanRemote lookupEmailSessionBean() {
        try {
            Context c = new InitialContext();
            return (EmailSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/EmailSessionBean!ERMS.session.EmailSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RoomSessionBeanRemote lookupRoomSessionBean() {
        try {
            Context c = new InitialContext();
            return (RoomSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/RoomSessionBean!ACMS.session.RoomSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}