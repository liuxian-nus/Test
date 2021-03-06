/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttractionEntity;
import javax.ejb.Stateless;
import ATMS.entity.AttrTicketEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
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
public class TicketSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    private AttrTicketEntity ticket;
    
    public TicketSessionBean(){     
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addTicket(AttrTicketEntity ticket){
  //      ticket=mapAttractionId(ticket);
        em.persist(ticket);
        return;
    }
    
    public List<AttrTicketEntity> getAllTickets(){
        Query query = em.createQuery("SELECT at FROM AttrTicketEntity at");
        return query.getResultList();
    }
    
    public void updateTicket(AttrTicketEntity ticket){
        System.out.println("into TicketSessionBean: update Attraction");   
     //   ticket=mapAttractionId(ticket);
        em.merge(ticket);
        em.flush();
        System.out.println("updated");
        return; 
    }
    
    public boolean removeTicket(Long ticketId)throws ExistException {
        System.out.println("into removeTicket");
        ticket = em.find(AttrTicketEntity.class, ticketId);
        if(ticket == null) {
            throw new ExistException("Ticket does not exist!");
        }
        em.remove(ticket);
        System.out.println("the ticket has been removed.");
        return true;
    } 
    
    public AttrTicketEntity getTicketById(Long ticketId) throws ExistException{
        System.out.println("TicketSessionBean : getTicketById");
        System.out.println("ticketId passed in :"+ticketId);
        ticket=em.find(AttrTicketEntity.class, ticketId);
        if(ticket==null){
            throw new ExistException("ticket does not exist!");
        }  
        else{
            System.out.println("ticket found");
            return ticket;
        }
        
    }
    
    /*public AttrTicketEntity mapAttractionId(AttrTicketEntity ticket){
        String name=ticket.getAttrTicketName();
        
        if(name.equals("Aquarium"))
            ticket.setAttrId("AQ");
        else if(name.equals("Horror House"))
            ticket.setAttrId("IT");
        else if(name.equals("Culture Musuem"))
            ticket.setAttrId("MU");
        else if(name.equals("Adventure World"))
            ticket.setAttrId("OT");
        else
            System.out.println("wrong attraction name");
        
        return ticket; 
    }*/
    
    public List<AttrTicketEntity> selectTicketForOneAttraction(AttractionEntity attr){
        System.out.println("TicketSessionBean : selectTicketForOneAttraction");
        List<AttrTicketEntity> tkts=getAllTickets();
        System.out.println("tkts size: "+tkts.size());
        List<AttrTicketEntity> selectedTickets=new ArrayList<AttrTicketEntity>();
        for(int i=0;i<tkts.size();i++){
            System.out.println("i:"+i);
            if(tkts.get(i).getAttr().equals(attr)){
                System.out.println("correct ticket");
                selectedTickets.add(tkts.get(i));
            }      
        }
        return selectedTickets;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    


}
