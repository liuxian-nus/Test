/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMSTesting;

import Exception.ExistException;
import SMMS.entity.OutletEntity;
import SMMS.entity.OutletTransactionEntity;
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
public class OutletTesting {
    
    OutletSessionBeanRemote OutletSessionBean = lookupOutletSessionBean();
    public OutletTesting() {
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
    public void testGetAllOutlets()
    {
        System.out.println("testGetAllOutlets");
        
        List<OutletEntity> test = OutletSessionBean.getAllOutlets();
        
        assertNotNull(test);
    }
    
    @Test
    public void testGetAvailableOutlets()
    {
        System.out.println("testGetAvailableOutlets");
        
        List<OutletEntity> test = OutletSessionBean.getAvailableOutlets();
        assertNotNull(test);
        System.out.println(test);
    }
    
    @Test
    public void testGetUnavailableOutlets()
    {
        System.out.println("testGetUnavailableOutlets");
        
        List<OutletEntity> test = OutletSessionBean.getUnavailableOutlets();
        assertNotNull(test);
        System.out.println(test);
    }
    
    @Test
    public void testGetOutletByType()
    {
        System.out.println("testGetOutletByTyoe");
        
        String type = "Cafes";
        List<OutletEntity> test = OutletSessionBean.getOutletsByType(type);
        assertNotNull(test);
        System.out.println(test);
    }
    
    @Test
    public void testGetOutletById() throws ExistException
    {
        System.out.println("testGetOutletById");
        
        int id = 217;
        OutletEntity test = OutletSessionBean.getOutletById(id);
        assertNotNull(test);
        System.out.println(test);
    }
    
    @Test
    public void testGetOutletsByMerchant()
    {
        System.out.println("testGetOutletsByMerchant");
        
        String memberEmail = "cookiewxy@hotmail.com";
        List<OutletEntity> test = OutletSessionBean.getOutletsByMerchant(memberEmail);
        assertNotNull(test);
        System.out.println(test);
    }
    
    @Test
    public void testGetTransactions()
    {
        System.out.println("testGetTransactions");
        
        int id = 217;
        
        List<OutletTransactionEntity> test = OutletSessionBean.getTransactions(id);
        assertNotNull(test);
        System.out.println(test);
    }
    
//    @Test
//    public void testAddUpdateOutlet()
//    {
//       System.out.println("testAddOutlet"); 
//       
//       int outletLevel = 2;
//       int outletNo = 201;
//       double outletArea = 50.00;
//       
//       OutletEntity test = new OutletEntity();
//       test.setOutletArea(outletArea);
//       test.setOutletLevel(outletLevel);
//       test.setOutletNo(outletNo);
//       
//       test = OutletSessionBean.addOutlet(test);
//       assertNotNull(test);
//       System.out.println(test);
//       
////       System.out.println("testUpdateOutlet");
////       test.setOutletArea(51.00);
////       boolean updated = OutletSessionBean.updateOutlet(test);
////       assertTrue(updated);
////       System.out.println(test);
//    }
    
    

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