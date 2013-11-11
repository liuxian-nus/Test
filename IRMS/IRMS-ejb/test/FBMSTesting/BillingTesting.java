/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMSTesting;

import FBMS.entity.AccountEntity;
import FBMS.entity.InvoiceEntity;
import FBMS.entity.ReceiptEntity;
import FBMS.session.BillingSessionBeanRemote;
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
public class BillingTesting {
    
    BillingSessionBeanRemote BillingSessionBean = lookupBillingSessionBean();
    
    public BillingTesting() {
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
    public void testCreateInvoice()
    {
        System.out.println("testCreateInvoice");
        
        Long orderId = Long.parseLong("67");
        Date currentDate = new Date();
        
        InvoiceEntity test = BillingSessionBean.createInvoice(orderId, currentDate);
        assertNotNull(test);
    }
    
    @Test
    public void testCreateReceipt()
    {
        System.out.println("testCreateReceipt");
        
        Long invoiceId = Long.parseLong("1");
        Date currentDate = new Date();
        
        ReceiptEntity test = BillingSessionBean.createReceipt(invoiceId, currentDate);
        assertNotNull(test);
    }
    
    @Test
    public void testCreateAccount()
    {
        System.out.println("testCreateReceipt");
        
        String accountName = "FB Account Receivable";
        Double amount = 10.00;
        
        boolean created = BillingSessionBean.createAccount(accountName, amount);
        assertTrue(created);
    }

    @Test
    public void testViewAccount()
    {
       System.out.println("testViewAccount"); 
       
       Long Id = Long.parseLong("88888");//Please insert test case before testing
       AccountEntity test = BillingSessionBean.viewAccount(Id);
       assertNotNull(test);
    }
    
    @Test
    public void testReceivePayment()
    {
        System.out.println("testReceivePayment");
        
        Double amount = 100.00;
        Long Id = Long.parseLong("88888");//Please insert test case before testing
        Double test = BillingSessionBean.receivePayment(amount, Id);
        assertNotNull(test);
        System.out.println("the received amount is "+test);
    }
    
    @Test
    public void testPostPayment()
    {
        System.out.println("testPostPayment");
        
        Double amount = 100.00;
        Long Id = Long.parseLong("88888");
        Double test = BillingSessionBean.postPayment(amount, Id);
        assertNotNull(test);
        System.out.println("the posted amount is "+test);
    }
    private BillingSessionBeanRemote lookupBillingSessionBean() {
       try {
            Context c = new InitialContext();
            return (BillingSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/BillingSessionBean!FBMS.session.BillingSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}