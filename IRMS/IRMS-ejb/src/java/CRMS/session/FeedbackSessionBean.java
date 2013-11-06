/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.FeedbackEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jieqiong
 */
@Stateless
public class FeedbackSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    FeedbackEntity feedback;
    
    public FeedbackSessionBean(){
        feedback=new FeedbackEntity();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createFeedback(FeedbackEntity feedback){
        System.out.println("FeedbackSessionBean: addFeedback");
        em.persist(feedback);
        System.out.println("feedback added!");
    }

    //used for mobile app
    public void createNewFeedback(String email, String feedbackTitle, String feedbackSentDate, String feedbackContent, String feedbackDepartment, String rating) {
        //algorithm missing
        em.persist(feedback);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateFeedback(FeedbackEntity feedback){
        System.out.println("FeedbackSessionBean: updateFeedback");   
        em.merge(feedback);
        em.flush();
        System.out.println("feedback updated");
        return; 
    }
    
    public List<FeedbackEntity> getAllFeedbacks(){
        Query q=em.createQuery("SELECT f from FeedbackEntity f"); 
        return q.getResultList();
    }
    
    public FeedbackEntity getFeedbackById(Long feedbackId) throws ExistException{
        System.out.println("FeedbackSessionBean: getFeedbackById");
        feedback=em.find(FeedbackEntity.class, feedbackId);
        if(feedback==null){
            throw new ExistException("feedback does not exist!");
        }  
        else{
            System.out.println("feedback found");
            return feedback;
        }      
    }
    
    /* shouldn't implement this method
     * public void removeFeedback(Long feedbackId)throws ExistException {
        System.out.println("FeedbackSessionBean:removeFeedback");
        feedback= em.find(FeedbackEntity.class, feedbackId);
        if(feedback == null) {
            throw new ExistException("Feedback does not exist!");
        }
        em.remove(feedback);
        System.out.println("the feedback has been removed.");
        return;
    }*/

    public void persist(Object object) {
        em.persist(object);
    }

}
