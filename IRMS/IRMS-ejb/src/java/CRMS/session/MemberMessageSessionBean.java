/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberMessageEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
public class MemberMessageSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    MemberEntity member = new MemberEntity();
    MemberMessageEntity memberMessage = new MemberMessageEntity();
    List<MemberMessageEntity> messages;

    public MemberMessageSessionBean() {
    }

    public List<MemberMessageEntity> getAllMessages() {
        Query query = em.createQuery("SELECT mm FROM MemberMessageEntity mm");
        messages = new ArrayList<MemberMessageEntity>();
        messages = query.getResultList();
        return messages;
    }

    public List<MemberMessageEntity> getBookingSummaryByMemberEmail(String memberEmail) {
        System.out.println("in member message session bean: get message by email " + memberEmail);
        //       MemberEntity thisMember = em.find(MemberEntity.class, memberEmail);
        //       if (thisMember == null) {
        //           throw new ExistException("Member email is invalid!");
        //       }
        //      System.out.println("member is found! name is: " + thisMember.getMemberName());
        Query query = em.createQuery("SELECT mm FROM MemberMessageEntity mm WHERE mm. messageCategory = 'bookingSummary'");
        //mm.memberReceiver.memberEmail ='" + memberEmail + "' 
        messages = new ArrayList<MemberMessageEntity>();
        for (Object o : query.getResultList()) {
            MemberMessageEntity thisMessage = (MemberMessageEntity) o;
            if (thisMessage.getMemberReceiver().equals(memberEmail)) {
                messages.add(thisMessage);
            }
        }
        return messages;
    }

    public List<MemberMessageEntity> getMemberSummaryByMemberEmail(String memberEmail) {
        System.out.println("in member message session bean: get message by email " + memberEmail);
        /*        MemberEntity thisMember = em.find(MemberEntity.class, memberEmail);
         if (thisMember == null) {
         throw new ExistException("Member email is invalid!");
         }
         System.out.println("member is found! name is: " + thisMember.getMemberName());*/
        Query query = em.createQuery("SELECT mm FROM MemberMessageEntity mm WHERE mm. messageCategory = 'memberSummary'");
        //mm.memberReceiver.memberEmail ='" + memberEmail + "' 
        messages = new ArrayList<MemberMessageEntity>();
        for (Object o : query.getResultList()) {
            MemberMessageEntity thisMessage = (MemberMessageEntity) o;
            if (thisMessage.getMemberReceiver().equals(memberEmail)) {
                messages.add(thisMessage);
            }
        }
        return messages;
    }

    public List<MemberMessageEntity> getNotificationByMemberEmail(String memberEmail) {
        System.out.println("in member message session bean: get message by email " + memberEmail);
        /*     MemberEntity thisMember = em.find(MemberEntity.class, memberEmail);
         if (thisMember == null) {
         throw new ExistException("Member email is invalid!");
         }
         System.out.println("member is found! name is: " + thisMember.getMemberName());*/
        Query query = em.createQuery("SELECT mm FROM MemberMessageEntity mm WHERE mm. messageCategory = 'notification'");
        //mm.memberReceiver.memberEmail ='" + memberEmail + "' 
        messages = new ArrayList<MemberMessageEntity>();
        for (Object o : query.getResultList()) {
            MemberMessageEntity thisMessage = (MemberMessageEntity) o;
            if (thisMessage.getMemberReceiver().equals(memberEmail)) {
                messages.add(thisMessage);
            }
        }
        return messages;
    }

    public MemberMessageEntity getMessageById(Long messageId) throws ExistException {
        MemberMessageEntity thisMessage = em.find(MemberMessageEntity.class, messageId);
        if (thisMessage == null) {
            throw new ExistException("This message does not exist");
        }
        System.out.println("Message found: " + thisMessage.getMessageTitle());
        return thisMessage;
    }

    public void createMessage(MemberMessageEntity newMessage) {
        newMessage.setMessageStatus("unread");
        em.persist(newMessage);
    }

    public boolean deleteMessage(MemberMessageEntity thisMessage) {
        thisMessage.setMessageStatus("deleted");
        em.merge(thisMessage);
        System.out.println("this message is successfully deleted");
        return true;
    }

    public boolean updateMessage(MemberMessageEntity thisMessage) {
        thisMessage.setMessageStatus("read");
        em.merge(thisMessage);
        System.out.println("this message is successfully updated to read");
        return true;
    }

    public void createNewMessage(MemberEntity member, String title, String content, String category, Date sentDate) {
        MemberMessageEntity newMessage = new MemberMessageEntity();
        newMessage.setMemberReceiver(member.getMemberEmail());
        newMessage.setMessageTitle(title);
        newMessage.setMessageContent(content);
        newMessage.setMessageCategory(category);
        newMessage.setMessageSentDate(sentDate);
        newMessage.setMessageStatus("unread");
        em.persist(newMessage);
    }
    
    public void createNewMessage(String toEmailAddress, String title, String content, String category, Date sentDate) {
        MemberMessageEntity newMessage = new MemberMessageEntity();
        newMessage.setMemberReceiver(toEmailAddress);
        newMessage.setMessageTitle(title);
        newMessage.setMessageContent(content);
        newMessage.setMessageCategory(category);
        newMessage.setMessageSentDate(sentDate);
        newMessage.setMessageStatus("unread");
        em.persist(newMessage);
    }
}
