/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMSTesting;

import Exception.ExistException;
import SMMS.entity.ContracteventEntity;
import SMMS.session.ContractSessionBeanRemote;
import SMMS.session.ContracteventSessionBeanRemote;
import SMMS.session.MerchantSessionBeanRemote;
import java.util.Date;
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
public class ContracteventTesting {
    
    ContracteventSessionBeanRemote ContracteventSessionBean = lookupContracteventSessionBean();
    ContractSessionBeanRemote ContractSessionBean = lookupContractSessionBean();
    public ContracteventTesting() {
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
    public void testAddContractevent() throws ExistException
    {
        System.out.println("testAddContractevent");
        
        Date eventStartDate = new Date(2013,12,1); 
        Date eventEndDate = new Date(2014,12,1);
        Date eventTime = new Date();
        double eventMonthRate = 1000.00;
        double eventEarlyCharge = 3000.00;
        double eventCommissionRate = 0.10;
        String eventStatus = "newApproved";
        double eventDeposit = 50000.00;
        Long Id = Long.parseLong("28");
        
        ContracteventEntity test = new ContracteventEntity();
        test.setEventCommissionRate(eventCommissionRate);
        test.setEventDeposit(eventDeposit);
        test.setEventEarlyCharge(eventEarlyCharge);
        test.setEventEndDate(eventEndDate);
        test.setEventMonthRate(eventMonthRate);
        test.setEventStartDate(eventStartDate);
        test.setEventTime(eventTime);
        test.setEventStatus(eventStatus);
        test.setEventContract(ContractSessionBean.getContractById(Id));
        
        ContracteventEntity tested = ContracteventSessionBean.addContractevent(test);
        assertNotNull(tested);
        
        System.out.println("testUpdateContractEvent");
        boolean current = ContracteventSessionBean.updateContractEvent(tested);
        assertTrue(current);
    }

    @Test
    public void testUpdateContractEvent()
    {
        
       
       
    }
    private ContracteventSessionBeanRemote lookupContracteventSessionBean() {
       try {
            Context c = new InitialContext();
            return (ContracteventSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ContracteventSessionBean!SMMS.session.ContracteventSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ContractSessionBeanRemote lookupContractSessionBean() {
       try {
            Context c = new InitialContext();
            return (ContractSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ContractSessionBean!SMMS.session.ContractSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}