/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.FeedbackEntity;
import CRMS.entity.MemberEntity;
import Exception.ExistException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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

    @EJB
    private MemberSessionBean memberSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    FeedbackEntity feedback;

    public FeedbackSessionBean() {
        feedback = new FeedbackEntity();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createFeedback(FeedbackEntity feedback) {
        System.out.println("FeedbackSessionBean: createFeedback");
        em.persist(feedback);
        System.out.println("feedback created!");
    }

    //for jsp page
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createFeedback(String content, String title, String email, String department, Date feedbackSentDate, int rating) {
        System.out.println("FeedbackSessionBean: createFeedback");
        System.out.println(feedback);
        feedback.setFeedbackContent(content);
        feedback.setFeedbackTitle(title);
        feedback.setFeedbackDepartment(department);
        feedback.setFeedbackOwnerEmail(email);
        feedback.setRating(rating);
        feedback.setFeedbackSentDate(feedbackSentDate);
        feedback.setFeedbackStatus("New");
        MemberEntity member = memberSessionBean.getMemberByEmail(email);
        if (member != null) {
            System.out.println("member found: " + member.getMemberName());
            feedback.setFeedbackOwner(member);
        }
        em.persist(feedback);
        System.out.println(feedback);
        System.out.println("feedback created!");
    }

    //used for mobile app
    public void createFeedback(String email, String feedbackTitle, String feedbackSentDate, String feedbackContent, String feedbackDepartment, String ratingString) throws ParseException {
        feedback.setFeedbackContent(feedbackContent);
        feedback.setFeedbackOwnerEmail(email);
        feedback.setFeedbackStatus("new");
        feedback.setFeedbackTitle(feedbackTitle);
        feedback.setFeedbackDepartment(feedbackDepartment);
        if (ratingString != null) {
            int rating = Integer.parseInt(ratingString);
            feedback.setRating(rating);
        }
        if (feedbackSentDate != null) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(feedbackSentDate);
            feedback.setFeedbackSentDate(date);
        }else {
            Date date = new Date();
            feedback.setFeedbackSentDate(date);
        }
        em.persist(feedback);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateFeedback(FeedbackEntity feedback) {
        System.out.println("FeedbackSessionBean: updateFeedback");
        em.merge(feedback);
        em.flush();
        System.out.println("feedback updated");
        return;
    }
    
    public void updateFeedbackStatus(FeedbackEntity feedback, String status) {
        System.out.println("Feedback session bean: update status");
        feedback.setFeedbackStatus(status);
        em.merge(feedback);
        em.flush();
        System.out.println("feedback status updated");
    }
    
    public List<FeedbackEntity> getAllFeedbacks() {
        Query q = em.createQuery("SELECT f from FeedbackEntity f");
        return q.getResultList();
    }

    public FeedbackEntity getFeedbackById(Long feedbackId) throws ExistException {
        System.out.println("FeedbackSessionBean: getFeedbackById");
        feedback = em.find(FeedbackEntity.class, feedbackId);
        if (feedback == null) {
            throw new ExistException("feedback does not exist!");
        } else {
            System.out.println("feedback found");
            return feedback;
        }
    }

    public List<FeedbackEntity> getFeedbackByEmail(String email) {
        Query q = em.createQuery("SELECT f from FeedbackEntity f WHERE f.feedbackOwnerEmail = '" + email + "'");
        return q.getResultList();
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
