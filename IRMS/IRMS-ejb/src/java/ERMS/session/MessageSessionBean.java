/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;


import ERMS.entity.EmployeeEntity;
import ERMS.entity.MessageEntity;
import ERMS.entity.ReceiverInfoEntity;
import Exception.ExistException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class MessageSessionBean {

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    ReceiverInfoSessionBean infoManager = new ReceiverInfoSessionBean();
    
    public MessageEntity getMessageById(Long messageId) throws ExistException{
        System.out.println("MessageSessionBean--> get message with id" + messageId);
        MessageEntity message = em.find(MessageEntity.class, messageId);
        if(message == null) {
            throw new ExistException("Message does not exist!");
        }
        return message;
    }
    
    public void addPrivateMessage(String senderId,String receiverId,String title,String msg,String type)
    {
        System.out.println("MessageSessionBean-->creating a new message....");
        MessageEntity message = new MessageEntity();
        
        ReceiverInfoEntity receiver = new ReceiverInfoEntity();
        receiver.setDeleted(false);
        receiver.setOpened(false);
        receiver.setReceiverId(receiverId);
        receiver.setSenderId(senderId);
        receiver.setMessage(message);
        //retrieve senderName from employee table
        EmployeeEntity sender = em.find(EmployeeEntity.class,senderId);
        message.setSenderName(sender.getEmployeeName());
        
        List<ReceiverInfoEntity> infoList = new ArrayList<ReceiverInfoEntity>();
        infoList.add(receiver);
        
        message.setRecInfo(infoList);
        message.setTitle(title);
        message.setContent(msg);
        message.setType(type);
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 
        try {
            
            String sendTime = dateFormat.format(calendar.getTime());
            message.setSendTime(sendTime);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("MessageSessionBean--> one new message sent: "+ msg + "--from person "+senderId);
        em.persist(message);
    }
    
    public void addSystemMessage(String senderId,List<String> idList,String title,String msg,String type)
    {
        System.out.println("MessageSessionBean-->generating a new system message....");
        MessageEntity message = new MessageEntity();
        
        List<ReceiverInfoEntity> infoList = new ArrayList<ReceiverInfoEntity>();
        for (int i=0;i<idList.size();i++){
            ReceiverInfoEntity receiver = new ReceiverInfoEntity();
            receiver.setDeleted(false);
            receiver.setOpened(false);
            receiver.setReceiverId(idList.get(i));
            receiver.setSenderId(senderId);
            receiver.setMessage(message);
            
            infoList.add(receiver);
        }
        
        
        message.setRecInfo(infoList);
        message.setTitle(title);
        message.setContent(msg);
        message.setType(type);
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 
        try {
            
            String sendTime = dateFormat.format(calendar.getTime());
            message.setSendTime(sendTime);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("MessageSessionBean--> one new system message sent: "+ msg);
        em.persist(message);
    }
    
    
    public void editMessage(MessageEntity message)
    {
        em.merge(message);
        System.out.println("MessageSessionBean--> message updated as " + message.getContent());
        em.flush();
    }
    

    public void removeMessage(Long messageId, String receiverId)throws ExistException
    {
        MessageEntity message = em.find(MessageEntity.class, messageId);
//        List<ReceiverInfo> infoList = message.getRecInfo();
        if(message == null) {
            throw new ExistException("Message does not exist!");
        }
        for (int i=0; i<message.getRecInfo().size(); i++) {
            if (receiverId.equals(message.getRecInfo().get(i).getReceiverId())){
                Long infoId = message.getRecInfo().get(i).getReceiveMsgId();
                
                message.getRecInfo().remove(i);
                editMessage(message);
                
                infoManager.removeReceiverInfo(infoId);

                break;
            } 
        }
        
        
        if (message.getRecInfo().size()>0){
            return;
        }
        else{
            em.remove(message);
        }        
        
    }
    
    public List<MessageEntity> getAllMessages(){
        Query query = em.createQuery("SELECT s1 FROM MessageEntity s1");
        return query.getResultList();
    }
    
}
