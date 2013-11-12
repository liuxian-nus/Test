/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.FeedbackEntity;
import Exception.ExistException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface FeedbackSessionBeanRemote {

    double calculateAverageRatingByDepartment(String department);

    double countFeedbackByDepartment(String department);

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void createFeedback(FeedbackEntity feedback);

    //for jsp page
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void createFeedback(String content, String title, String email, String department, Date feedbackSentDate, int rating);

    void createFeedback(String email, String feedbackTitle, String feedbackSentDate, String feedbackContent, String feedbackDepartment, String ratingString) throws ParseException;

    List<FeedbackEntity> getAllFeedbacks();

    List<FeedbackEntity> getFeedbackByEmail(String email);

    FeedbackEntity getFeedbackById(Long feedbackId) throws ExistException;

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
    void persist(Object object);

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void updateFeedback(FeedbackEntity feedback);

    void updateFeedbackStatus(FeedbackEntity feedback, String status);
    
}
