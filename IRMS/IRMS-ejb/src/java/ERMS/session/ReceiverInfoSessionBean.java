/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ERMS.entity.MessageEntity;
import ERMS.entity.ReceiverInfoEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class ReceiverInfoSessionBean {

     @PersistenceContext
    private EntityManager em;
    
    private MessageSessionBean mm;
    
    public ReceiverInfoEntity getReceiverInfoById(Long Id)throws ExistException
    {
        ReceiverInfoEntity info = em.find(ReceiverInfoEntity.class, Id);
        if(info == null) {
            throw new ExistException("ReceiverInfo does not exist!");
        }
        return info;
    }
    
    
    public void addReceiverInfo(ReceiverInfoEntity info)
    {
        System.out.println("ReceiverInfoSessionBean--> add ReceiverInfo");
        em.persist(info);
    }
    
    public void editEmployee(ReceiverInfoEntity info)
    {
        System.out.println("ReceiverInfoSessionBean--> edit ReceiverInfo");
        em.merge(info);
    }
    

    public void removeReceiverInfo(Long infoId) throws ExistException
    {
        ReceiverInfoEntity receiverInfo = em.find(ReceiverInfoEntity.class, infoId);
        if(receiverInfo == null) {
            throw new ExistException("ReceiverInfo does not exist!");
        }
         System.out.println("ReceiverInfoSessionBean--> remove ReceiverInfo");
        em.remove(receiverInfo);
    }
    
    public List<ReceiverInfoEntity> getAllReceiverInfo(){
        Query query = em.createQuery("SELECT s1 FROM ReceiverInfoEntity s1");
        return query.getResultList();
    }
    
    public List<ReceiverInfoEntity> getRecInfoBySender(String id){
        Query query = em.createQuery("SELECT s1 FROM ReceiverInfoEntity s1 WHERE s1.senderId = :inRecId");
        query.setParameter("inRecId", id);
        List<ReceiverInfoEntity> recInfo = null;
        
        try{
            recInfo = query.getResultList();
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        
        return recInfo;
        
    }
    
    public List<MessageEntity> getMessageBySender(String id){
        List<ReceiverInfoEntity> recInfo = getRecInfoBySender(id);
        int i = 0;
        List<MessageEntity> msg = new ArrayList<MessageEntity>();
        while (recInfo.get(i) != null){
            msg.add(recInfo.get(i).getMessage());
        }
        
        return msg;
    }

    public List<ReceiverInfoEntity> getRecInfoByReceiver(String id){
        Query query = em.createQuery("SELECT s1 FROM ReceiverInfoEntity s1 WHERE s1.receiverId = :inRecId");
        query.setParameter("inRecId", id);
        List<ReceiverInfoEntity> recInfo = null;
        
        try{
            recInfo = query.getResultList();
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        
        return recInfo;
    }
    
    public List<MessageEntity> getMessageByReceiver(String id){
        List<ReceiverInfoEntity> recInfo = getRecInfoByReceiver(id);
        List<MessageEntity> msg = new ArrayList<MessageEntity>();
        for (int i=0;i<recInfo.size();i++) {
            msg.add(recInfo.get(i).getMessage());
        }
        
        return msg;
    }

    public MessageSessionBean getMm() {
        return mm;
    }

    public void setMm(MessageSessionBean mm) {
        this.mm = mm;
    }
    

}
