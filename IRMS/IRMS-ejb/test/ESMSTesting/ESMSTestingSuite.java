/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMSTesting;

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
@Suite.SuiteClasses({ESMSTesting.ShowContractTesting.class, ESMSTesting.ShowTicketSaleTesting.class, ESMSTesting.ShowShowScheduleTesting.class, ESMSTesting.ShowBillingTesting.class, ESMSTesting.ShowTicketTesting.class})
public class ESMSTestingSuite {

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