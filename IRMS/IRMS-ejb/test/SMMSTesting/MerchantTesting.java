/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMSTesting;

import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.MerchantEntity;
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
public class MerchantTesting {
    
    MerchantSessionBeanRemote MerchantSessionBean = lookupMerchantSessionBean();
    
    public MerchantTesting() {
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
    public void testAddMerchant()
    {
        System.out.println("testAddMerchant");
        
        String merchantEmail = "test";
        String merchantName = "test";
        String merchantPassword = "test";
        String merchantHP="test";
        String merchantAddress="test";
        String securityQuestion="test";
        String answer="test";
        
        MerchantEntity test = new MerchantEntity();
        test.setAnswer(answer);
        test.setMerchantAddress(merchantAddress);
        test.setMerchantEmail(merchantEmail);
        test.setMerchantHP(merchantHP);
        test.setMerchantName(merchantName);
        test.setMerchantPassword(merchantPassword);
        test.setSecurityQuestion(securityQuestion);
        
        MerchantEntity tested = MerchantSessionBean.addMerchant(test);
        assertNotNull(tested);
        
        System.out.println("testUpdateMerchant"); 
        
        tested.setAnswer("changed");
        boolean updated = MerchantSessionBean.updateMerchant(tested);
        assertTrue(updated);
    }
    
    @Test
    public void testGetAllMerchants() throws ExistException
    {
       System.out.println("testGetAllMerchants");
       
       List<MerchantEntity> test = MerchantSessionBean.getAllMerchants();
       assertNotNull(test);       
    }
    
    @Test
    public void testGetMerchantById() throws ExistException
    {
        System.out.println("testGetMerchantById");
        
        String merchantId = "cookiewxy@hotmail.com";
        MerchantEntity test = MerchantSessionBean.getMerchantById(merchantId);
        assertNotNull(test);
    }
    
    @Test
    public void testAddContractInMerchant() throws ExistException
    {
        System.out.println("testAddContractInMerchant");
        
        String merchantId = "cookiewxy@hotmail.com";
        Long contractId = Long.parseLong("88888");
        
        ContractEntity test = MerchantSessionBean.addContractInMerchant(contractId, merchantId);
        assertNotNull(test);
    }

    @Test
    public void testCreateTimers()
    {
        System.out.println("testCreateTimers");
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