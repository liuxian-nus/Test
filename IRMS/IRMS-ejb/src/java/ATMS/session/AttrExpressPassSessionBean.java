/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttrExpressPassEntity;
import ATMS.entity.AttrTicketEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
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

public class AttrExpressPassSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    private AttrExpressPassEntity ep;
    
    public AttrExpressPassSessionBean(){
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addEP(AttrExpressPassEntity ep){
        em.persist(ep);
        return;
    }
    
    public List<AttrExpressPassEntity> getAllEPs(){
        Query query = em.createQuery("SELECT ep FROM AttrExpressPassEntity ep");
        return query.getResultList();
    }
    
    public void updateEP(AttrExpressPassEntity ep){
        System.out.println("into TicketSessionBean: update Attraction");   
        em.merge(ep);
        em.flush();
        System.out.println("updated");
        return; 
    }
    
    public boolean removeEP(Long EPId)throws ExistException {
        System.out.println("into removeTicket");
        ep = em.find(AttrExpressPassEntity.class, EPId);
        if(ep == null) {
            throw new ExistException("EP does not exist!");
        }
        em.remove(ep);
        System.out.println("the ep has been removed.");
        return true;
    } 
    
    public AttrExpressPassEntity getEPById(Long EPId) throws ExistException{
        System.out.println("TicketSessionBean : getTicketById");
        //System.out.println("ticketId passed in :"+ticketId);
        ep=em.find(AttrExpressPassEntity.class, EPId);
        if(ep==null){
            throw new ExistException("ticket does not exist!");
        }  
        else{
            System.out.println("ticket found");
            return ep;
        }
        
    }
    
    
    public void persist(Object object) {
        em.persist(object);
    }
    


}
