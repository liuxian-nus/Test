/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMSTesting;

import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import SMMS.session.ContractSessionBeanRemote;
import SMMS.session.MerchantBillSessionBeanRemote;
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
public class MerchantBillingTesting {
    
    MerchantBillSessionBeanRemote MerchantBillSessionBean = lookupMerchantBillSessionBean();
    ContractSessionBeanRemote ContractSessionBean = lookupContractSessionBean();
    
    public MerchantBillingTesting() {
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
    public void testCreateTimers()
    {
        System.out.println("testCreateTimers");
        System.out.println("createOverDueTimers");
        
        Date overdue =  new Date (2013,11,11);
        MerchantBillSessionBean.createOverDueTimers(overdue);
        
        System.out.println("createActiveTimers");
        
        Date startDate = new Date(2014,1,1);
        MerchantBillSessionBean.createActiveTimers(startDate);
        
        System.out.println("testCreateTimers Completed!");
    }

    @Test
    public void testAddBill() throws ExistException
    {
        System.out.println("testAddBill");
        
        Date billDate = new Date();
        Date dueDate = new Date(2014,10,12);
        double billAmount = 1000.00;
        String billType = "commission";
        String billStatus = "paid";
        ContractEntity contract = ContractSessionBean.getContractById(Long.parseLong("28"));
        
        BillEntity test = new BillEntity();
        test.setBillAmount(billAmount);
        test.setBillDate(billDate);
        test.setBillStatus(billStatus);
        test.setDueDate(dueDate);
        test.setBillType(billType);
        test.setContract(contract);
        
        BillEntity tested = MerchantBillSessionBean.addBill(test);
        assertNotNull(tested);
    }
    
    @Test
    public void testRemoveBill() throws ExistException
    {
        System.out.println("testAddBill");
        
        BillEntity removed = MerchantBillSessionBean.getBillById(Long.parseLong("88888"));
        //Insert case b4 testing
        assertNotNull(removed);
    }
    
    @Test
    public void testUpdateBill() throws ExistException
    {
        System.out.println("testUpdateBill");
        
        BillEntity test = MerchantBillSessionBean.getBillById(Long.parseLong("99999"));
        //Insert case b4 testing
        BillEntity tested = MerchantBillSessionBean.updateBill(test);
        assertNotNull(tested);
    }
    
    @Test
    public void testGetBillByContract()
    {
        System.out.println("testGetBillByContract");
        //Need to insert more test cases for testing
        Long contractId = Long.parseLong("88888");
        List<BillEntity> tested = MerchantBillSessionBean.getBillByContract(contractId);
        assertNotNull(tested);
    }
    
    @Test
    public void testGetBillByMerchant()
    {
        System.out.println("testGetBillByMerchant");//Please insert test case
        
        String merchantId = "cookiewxy@hotmail.com";
        List<BillEntity> test = MerchantBillSessionBean.getBillByMerchant(merchantId);
        assertNotNull(test);
    }
    
    @Test
    public void testGetAvailableBills()
    {
        System.out.println("testGetAvailableBills");
        
        List<BillEntity> test = MerchantBillSessionBean.getAllBills();
        assertNotNull(test);
    }
    
    @Test
    public void testGetUnpaidBills()
    {
        System.out.println("testGetUnpaidBills");
        
        List<BillEntity> test = MerchantBillSessionBean.getUnpaidBills();
        assertNotNull(test); 
    }
    
    @Test
    public void testGetOverdueBills()
    {
        System.out.println("testGetOverdueBills");
        
        List<BillEntity> test = MerchantBillSessionBean.getOverdueBills();
        assertNotNull(test);
        
    }
    
    @Test
    public void testGetUnpaidBillsByMerchant()
    {
        System.out.println("testGetUnpaidBillsByMerchant");
        
        String merchantId = "cookiewxy@hotmail.com";
        List<BillEntity> test = MerchantBillSessionBean.getUnpaidBillsByMerchant(merchantId);
        assertNotNull(test);
    }
    
    @Test
    public void testGetPaidBillsByMerchant()
    {
        System.out.println("testGetPaidBillsByMerchant");
        
        String merchantId = "cookiewxy@hotmail.com";
        List<BillEntity> test = MerchantBillSessionBean.getPaidBillsByMerchant(merchantId);
        assertNotNull(test);
    }
    
    @Test
    public void testGetAllBills()
    {
        System.out.println("testGetAllBills");
        
        List<BillEntity> test = MerchantBillSessionBean.getAllBills();
        assertNotNull(test);
    }
            
    
    private MerchantBillSessionBeanRemote lookupMerchantBillSessionBean() {
       try {
            Context c = new InitialContext();
            return (MerchantBillSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/MerchantBillSessionBean!SMMS.session.MerchantBillSessionBeanRemote");
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