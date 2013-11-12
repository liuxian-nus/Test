/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMSTesting;

import CRMS.entity.FeedbackEntity;
import CRMS.session.EvaluationSessionBeanRemote;
import CRMS.session.FeedbackSessionBeanRemote;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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
public class FeedbackTesting {
    
    FeedbackSessionBeanRemote FeedbackSessionBean = lookupFeedbackSessionBean();
    
    public FeedbackTesting() {
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
    public void testCreateFeedback()
    {
        System.out.println("testCreateFeedback");
        
        String feedbackContent = "test";
        String feedbackOwnerEmail = "test";
        String feedbackTitle = "test";
        String feedbackStatus = "new";
        String feedbackDepartment = "test";
        Integer rating = 3;
        Date feedbackSentDate = new Date();
        
        FeedbackEntity test= new FeedbackEntity();
        
        test.setFeedbackContent(feedbackContent);
        test.setFeedbackDepartment(feedbackDepartment);
        test.setFeedbackOwnerEmail(feedbackOwnerEmail);
        test.setFeedbackSentDate(feedbackSentDate);
        test.setFeedbackStatus(feedbackStatus);
        test.setFeedbackTitle(feedbackTitle);
        test.setRating(rating);
        
        FeedbackSessionBean.createFeedback(test);
        System.out.println("testCreateFeedback: completed!");
        
        System.out.println("testUpdateFeedback");
        FeedbackSessionBean.updateFeedback(test);
        System.out.println("testUpdateFeedback: completed!");
        
        System.out.println("testUpdateFeedbackStatus");
        FeedbackSessionBean.updateFeedbackStatus(test, feedbackStatus);
        System.out.println("testUpdateFeedbackStatus: completed!");
    }
    
    @Test
    public void testGetAllFeedbacks()
    {
        System.out.println("testGetAllFeedbacks");
        
        List<FeedbackEntity> test = FeedbackSessionBean.getAllFeedbacks();
        assertNotNull(test);
        assertSame(test.getClass(),Vector.class);
    }
    
    @Test
    public void testGetFeedbackById() throws ExistException
    {
        System.out.println("testGetAllFeedbacks");
        
        FeedbackEntity test = FeedbackSessionBean.getFeedbackById(Long.parseLong("1"));
        assertNotNull(test);
        assertSame(test.getClass(),FeedbackEntity.class);
    }
    
    @Test
    public void testGetFeedbackByEmail()
    {
       System.out.println("testGetFeedbackByEmail"); 
       
       String email = "xinqi_wang@yahoo.com";
       List<FeedbackEntity> test = FeedbackSessionBean.getFeedbackByEmail(email);
       
       assertNotNull(test);
    }
    
    @Test
    public void testCountFeedbackByDepartment()
    {
       System.out.println("testCountFeedbackByDepartment"); 
       
       double test = FeedbackSessionBean.countFeedbackByDepartment("hotel");
       assertNotNull(test);
       
    }
    
    @Test
    public void testCalculateAverageRatingByDepartment()
    {
        System.out.println("testCalculateAverageRatingByDepartment");
        
        double test = FeedbackSessionBean.calculateAverageRatingByDepartment("hotel");
        assertNotNull(test);
    }

    private FeedbackSessionBeanRemote lookupFeedbackSessionBean() {
       try {
            Context c = new InitialContext();
            return (FeedbackSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/FeedbackSessionBean!CRMS.session.FeedbackSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}