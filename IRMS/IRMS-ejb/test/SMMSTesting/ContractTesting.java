/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMSTesting;

import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.entity.MerchantEntity;
import SMMS.entity.OutletEntity;
import SMMS.session.ContractSessionBeanRemote;
import SMMS.session.MerchantSessionBeanRemote;
import SMMS.session.OutletSessionBeanRemote;
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
public class ContractTesting {
    
    ContractSessionBeanRemote ContractSessionBean = lookupContractSessionBean();
    OutletSessionBeanRemote OutletSessionBean = lookupOutletSessionBean();
    MerchantSessionBeanRemote MerchantSessionBean = lookupMerchantSessionBean();
    public ContractTesting() {
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
    public void testAddUpdateContract() throws ExistException
    {
        System.out.println("testAddContract");
        
        MerchantEntity merchant = MerchantSessionBean.getMerchantById("cookiewxy@hotmail.com");
        OutletEntity outlet = OutletSessionBean.getOutletById(516);
        ContractEntity test = new ContractEntity();
        
        test.setMerchant(merchant);
        test.setOutlet(outlet);
        ContractEntity tested = ContractSessionBean.addContract(test);
        assertNotNull(tested);
        
        
    }
    @Test
    public void testUpdateContract() throws ExistException
    {
      System.out.println("testUpdateContract");
      
      ContractEntity tested = ContractSessionBean.getContractById(Long.parseLong("30"));
      tested.setStatus("changed");
        boolean updated = ContractSessionBean.updateContract(tested);
        assertTrue(updated);  
    }
    @Test
    public void testAddContractevent() throws ExistException
    {
        System.out.println("testAddContractevent");
        
        Long contractId = Long.parseLong("28");
        Long cevent = Long.parseLong("35");
        
        ContracteventEntity test = ContractSessionBean.addContractevent(contractId, cevent);
        assertNotNull(test);
    }

    @Test
    public void testRemoveContract() throws ExistException
    {
        System.out.println("testRemoveContract");
        
        Long contractId = Long.parseLong("34");//insert test case before testing
        ContractEntity test = ContractSessionBean.getContractById(contractId);
        ContractSessionBean.removeContract(test);
        System.out.println("remove successfull!");
    }
    
    @Test
    public void testGetContractByMerchant()
    {
        System.out.println("testGetContractByMerchant");
     
        String merchantEmail = "cookiewxy@hotmail.com";
        List<ContractEntity> test = ContractSessionBean.getContractByMerchant(merchantEmail);
        assertNotNull(test);
    }
    
    @Test
    public void testGetContractByOutlet()
    {
        System.out.println("testGetContractByOutlet");
        
        int outletId = 503;
        ContractEntity test = ContractSessionBean.getContractByOutlet(outletId);
        assertNotNull(test);
    }
    
    @Test
    public void testGetContractById() throws ExistException
    {
        System.out.println("testGetContractById");
        
        Long contractId = Long.parseLong("28");
        ContractEntity test = ContractSessionBean.getContractById(contractId);
        assertNotNull(test);
        
        System.out.println("testGetSize");
        int testsize = ContractSessionBean.getSize(test);
        assertNotNull(testsize);
    }
    
    @Test
    public void testGetAllContracts()
    {
        System.out.println("testGetAllContracts");
        
        List<ContractEntity> test = ContractSessionBean.getAllContracts();
        assertNotNull(test);
    }
    
    @Test
    public void testGetNewRequestContract()
    {
       System.out.println("testGetNewRequestContract"); 
       
       List<ContractEntity> test = ContractSessionBean.getNewRequestContract();
       assertNotNull(test);
    }
    
    @Test
    public void testGetRenewRequestContract()
    {
        System.out.println("testGetNewRequestContract");
        
        List<ContractEntity> test = ContractSessionBean.getRenewRequestContract();
        assertNotNull(test);
    }
    
    @Test
    public void testGetEarlyTerminationRequestContract()
    {
        System.out.println("testGetEarlyTerminationRequestContract");
        
        List<ContractEntity> test = ContractSessionBean.getEarlyTerminationRequestContract();
        assertNotNull(test);
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

    private OutletSessionBeanRemote lookupOutletSessionBean() {
        try {
            Context c = new InitialContext();
            return (OutletSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/OutletSessionBean!SMMS.session.OutletSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MerchantSessionBeanRemote lookupMerchantSessionBean() {
        try {
            Context c = new InitialContext();
            return (MerchantSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/MerchantSessionBean!SMMS.session.MerchantSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}