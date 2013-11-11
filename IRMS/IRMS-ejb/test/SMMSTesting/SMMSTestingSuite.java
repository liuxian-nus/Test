/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMSTesting;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Diana Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SMMSTesting.OutletTransactionTesting.class, SMMSTesting.MerchantBillingTesting.class, SMMSTesting.ContracteventTesting.class, SMMSTesting.PushingCartTesting.class, SMMSTesting.OutletTesting.class, SMMSTesting.MerchantTesting.class, SMMSTesting.ContractTesting.class})
public class SMMSTestingSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}