/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMSTesting;

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
@Suite.SuiteClasses({RestaurantTesting.class, InventoryTesting.class, FBMSTesting.class, IndReservationTesting.class, FBMSTestingSuite.class, BillingTesting.class, OrderTesting.class})
public class FBMSTestingSuiteUpdated {

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