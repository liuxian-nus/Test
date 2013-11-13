/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMSTesting;

import Exception.ExistException;
import SMMS.entity.OutletEntity;
import SMMS.entity.OutletTransactionEntity;
import SMMS.session.MerchantSessionBeanRemote;
import SMMS.session.OutletSessionBeanRemote;
import SMMS.session.OutletTransactionSessionBeanRemote;
import java.util.Date;
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
public class OutletTransactionTesting {
    
    OutletTransactionSessionBeanRemote OutletTransactionSessionBean = lookupOutletTransactionSessionBean();
    OutletSessionBeanRemote  OutletSessionBean = lookupOutletSessionBean();
    public OutletTransactionTesting() {
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
    public void testAddTransaction() throws ExistException
    {
        System.out.println("testAddTransaction");
        
        Date transactionDate = new Date();
        double transactionAmount = 10.00;
        int outletId = 210;
        OutletEntity oe = OutletSessionBean.getOutletById(outletId);
        
        OutletTransactionEntity test = new OutletTransactionEntity();
        test.setTransactionAmount(transactionAmount);
        test.setTransactionDate(transactionDate);
//        test.setTransactionOutlet(oe);
        
        OutletTransactionEntity added = OutletTransactionSessionBean.addTransaction(test);
        assertNotNull(added);
    }
    
    @Test
    public void testRemoveTransaction() throws ExistException
    {
        System.out.println("testRemoveTransaction");
        
        Long Id = Long.parseLong("39");//please insert
        OutletTransactionEntity test = OutletTransactionSessionBean.getTransaction(Id);
        OutletTransactionSessionBean.removeTransaction(test);
        System.out.println("Transaction successfully removed!");
    }
    
    @Test
    public void testGetTransactionByOutlet()
    {
        System.out.println("testGetTransactionByOutlet");
        
        int outletId = 217;
        List<OutletTransactionEntity> test = OutletTransactionSessionBean.getTransactionByOutlet(outletId);
        assertNotNull(test);
    }

    private OutletTransactionSessionBeanRemote lookupOutletTransactionSessionBean() {
        try {
            Context c = new InitialContext();
            return (OutletTransactionSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/OutletTransactionSessionBean!SMMS.session.OutletTransactionSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private OutletSessionBeanRemote lookupOutletSessionBean() {
      try {
            Context c = new InitialContext();
            return (OutletSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/OutletSessionBean!SMMS.session.OutletSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}