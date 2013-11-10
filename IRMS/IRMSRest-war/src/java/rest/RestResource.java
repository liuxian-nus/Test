/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import CRMS.entity.FeedbackEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberMessageEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.FeedbackSessionBean;
import CRMS.session.MemberMessageSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import CRMS.session.PromotionSessionBean;
import Exception.ExistException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author liuxian
 */
@Stateless
@Path("rest")
public class RestResource {

    @EJB
    private MemberMessageSessionBean memberMessageSessionBean;
    @EJB
    MemberSessionBean memberSessionBean;
    @EJB
    PromotionSessionBean promotionSessionBean;
    @EJB
    FeedbackSessionBean feedbackSessionBean;
    @EJB
    MemberTransactionSessionBean transactionSessionBean;
    @Context
    private UriInfo context;

    public RestResource() {
    }

    //LoginViewController
    @GET
    @Path("member/login")
    @Produces(MediaType.APPLICATION_JSON)
    public MemberEntity login(@QueryParam("email") String email, @QueryParam("password") String password) {
        System.out.println("Email is: " + email);
        System.out.println("Password is: " + password);
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        MemberEntity member = memberSessionBean.checkLogIn(email, password);
        if (member != null) {
            System.err.println("member name is: " + member.getMemberName());
            return member;
        } else {
            throw new WebApplicationException(404);
        }
    }

    //ProfileViewController
    @GET
    @Path("member")
    @Produces(MediaType.APPLICATION_JSON)
    public MemberEntity getMember(@QueryParam("email") String email) {
        System.err.println("Email is: " + email);
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        MemberEntity member = memberSessionBean.getMemberByEmail(email);
        if (member != null) {
            System.err.println("member name is: " + member.getMemberName());
            return member;
        } else {
            throw new WebApplicationException(404);
        }
    }

    //ProfileViewController
    @PUT
    @Path("member/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateMember(@PathParam("email") String email,
            @FormParam("name") String name,
            @FormParam("hp") String hp,
            @FormParam("dob") String dob,
            @FormParam("gender") String gender,
            @FormParam("maritalStatus") String maritalStatus,
            @FormParam("subscription") String subscription) throws ExistException, ParseException {
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        System.err.println("email is" + email);
        System.err.println("name is " + name);
        memberSessionBean.updateMember(email, name, hp, dob, maritalStatus, gender, subscription);
    }

    //RegisterViewController
    @PUT
    @Path("member/register/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public MemberEntity createNewMember(@PathParam("email") String email,
            @FormParam("password") String password,
            @FormParam("password2") String password2,
            @FormParam("name") String name,
            @FormParam("hp") String hp,
            @FormParam("dob") String dob,
            @FormParam("gender") String gender,
            @FormParam("maritalStatus") String maritalStatus,
            @FormParam("nationality") String nationality,
            @FormParam("securityQuestion") String securityQuestion,
            @FormParam("answer") String answer) throws ExistException, ParseException {
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        System.err.println("email is" + email);
        System.err.println("name is " + name);
        MemberEntity newMember = memberSessionBean.createNewMember(email, password, password2, name, hp, dob, gender, maritalStatus, nationality, securityQuestion, answer);
        return newMember;
    }

    //updatePassword: haven't implemented
    @PUT
    @Path("member/password/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePassword(@QueryParam("email") String email, @QueryParam("password") String password) {
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        System.err.println("email is: " + email);
        System.err.println("member Password is " + password);
        memberSessionBean.updatePassword(email, password);
    }

    //TransactionViewController
    @GET
    @Path("member/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MemberTransactionEntity> getMemberTransaction(@QueryParam("email") String email) {
        if (transactionSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        List<MemberTransactionEntity> mtlist = transactionSessionBean.getTransactionsByMemberEmail(email);
        if (mtlist != null) {
            return mtlist;
        } else {
            throw new WebApplicationException(404);
        }
    }

    //MessageViewController
    @GET
    @Path("member/bookingSummary")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MemberMessageEntity> getBookingSummary(@QueryParam("email") String email) {
        if (memberMessageSessionBean == null) {
            System.err.println("memberMessageSessionBean is null");
        }
        List<MemberMessageEntity> messageList = memberMessageSessionBean.getBookingSummaryByMemberEmail(email);
        if (messageList != null) {
            return messageList;
        } else {
            throw new WebApplicationException(404);
        }
    }

    //MessageViewController
    @GET
    @Path("member/memberSummary")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MemberMessageEntity> getMemberSummary(@QueryParam("email") String email) {
        if (memberMessageSessionBean == null) {
            System.err.println("memberMessageSessionBean is null");
        }
        List<MemberMessageEntity> messageList = memberMessageSessionBean.getMemberSummaryByMemberEmail(email);
        if (messageList != null) {
            return messageList;
        } else {
            throw new WebApplicationException(404);
        }
    }

    //MessageViewController
    @GET
    @Path("member/notification")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MemberMessageEntity> getNotification(@QueryParam("email") String email) {
        if (memberMessageSessionBean == null) {
            System.err.println("memberMessageSessionBean is null");
        }
        List<MemberMessageEntity> messageList = memberMessageSessionBean.getNotificationByMemberEmail(email);
        if (messageList != null) {
            return messageList;
        } else {
            throw new WebApplicationException(404);
        }
    }

    //MessageDetailViewController
    @PUT
    @Path("member/messages/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changeMessageStatus(@PathParam("messageId") String id) throws ExistException {
        if (memberMessageSessionBean == null) {
            System.err.println("memberMessageSessionBean is null");
        }
        System.out.println("message id is: " + id);
        long messageId = Long.parseLong(id);
        MemberMessageEntity thisMessage = memberMessageSessionBean.getMessageById(messageId);
        if (thisMessage != null) {
            System.out.println("message found: " + thisMessage.getMessageTitle());
            memberMessageSessionBean.updateMessage(thisMessage);
            System.out.println("now this message has a status of : " + thisMessage.getMessageStatus());
        } else {
            throw new WebApplicationException(404);
        }
    }

    @GET
    @Path("member/messages")
    @Produces(MediaType.APPLICATION_JSON)
    public MemberMessageEntity getMessageById(@QueryParam("messageId") String id) throws ExistException {
        if (memberMessageSessionBean == null) {
            System.err.println("memberMessageSessionBean is null");
        }
        System.out.println("message id is: " + id);
        long messageId = Long.parseLong(id);
        MemberMessageEntity thisMessage = memberMessageSessionBean.getMessageById(messageId);
         if (thisMessage != null) {
            return thisMessage;
        } else {
            throw new WebApplicationException(404);
        }
    }

    //MessageDetailViewController
    @PUT//????
    @Path("member/deleteMessages/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteMemberMessage(@PathParam("messageId") String id) throws ExistException {
        if (memberMessageSessionBean == null) {
            System.err.println("memberMessageSessionBean is null");
        }
        long messageId = Long.parseLong(id);
        MemberMessageEntity thisMessage = memberMessageSessionBean.getMessageById(messageId);
        memberMessageSessionBean.deleteMessage(thisMessage);
    }

    //PromotionViewController
    @GET
    @Path("member/promotions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PromotionEntity> getMemberPromotion(@QueryParam("email") String email) throws ExistException {
        if (promotionSessionBean == null) {
            System.err.println("promotionSessionBean is null");
        }
        List<PromotionEntity> promotionList = promotionSessionBean.getPromotionByMemberEmail(email);
        if (promotionList != null) {
            return promotionList;
        } else {
            throw new WebApplicationException(404);
        }
    }

    //FeedbackViewController
    @PUT
    @Path("member/feedback/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean sendNewFeedback(@PathParam("email") String email,
            @FormParam("feedbackTitle") String feedbackTitle,
            @FormParam("feedbackDate") String feedbackSentDate,
            @FormParam("feedbackContent") String feedbackContent,
            @FormParam("feedbackDepartment") String feedbackDepartment,
            @FormParam("rating") String rating) throws ExistException, ParseException {
        if (feedbackSessionBean == null) {
            System.err.println("feedbackSessionBean is null");
        }
        System.out.println("from user: " + email);
        feedbackSessionBean.createFeedback(email, feedbackTitle, feedbackSentDate, feedbackContent, feedbackDepartment, rating);
        return true;
    }
    
    @GET
    @Path("member/feedbacks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FeedbackEntity> getFeedbacks(@QueryParam("email") String email) {
         if (feedbackSessionBean == null) {
            System.err.println("feedbackSessionBean is null");
        }
        System.out.println("get feedbacks from user: " + email);
        List<FeedbackEntity> feedbackList = new ArrayList<FeedbackEntity>();
        feedbackList = feedbackSessionBean.getFeedbackByEmail(email);
        return feedbackList;
    }
    private MemberSessionBean lookupMemberSessionBeanBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (MemberSessionBean) c.lookup("java:global/IRMS/IRMS-ejb/MemberSessionBean!CRMS.session.MemberSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
