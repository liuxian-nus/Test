/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttractionEntity;
import javax.ejb.Stateless;
import ATMS.entity.TicketEntity;
import Exception.ExistException;
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
    
    private TicketEntity ticket;
    
    public TicketSessionBean(){     
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addTicket(TicketEntity ticket){
        ticket=mapAttractionId(ticket);
        em.persist(ticket);
        return;
    }
    
    public List<TicketEntity> getAllTickets(){
        Query query = em.createQuery("SELECT t FROM TicketEntity t");
        return query.getResultList();
    }
    
    public void updateTicket(TicketEntity ticket){
        System.out.println("into TicketSessionBean: update Attraction");   
        ticket=mapAttractionId(ticket);
        em.merge(ticket);
        em.flush();
        System.out.println("updated");
        return; 
    }
    
    public boolean removeTicket(Long ticketId)throws ExistException {
        System.out.println("into removeTicket");
        ticket = em.find(TicketEntity.class, ticketId);
        if(ticket == null) {
            throw new ExistException("Ticket does not exist!");
        }
        em.remove(ticket);
        System.out.println("the ticket has been removed.");
        return true;
    } 
    
    public TicketEntity mapAttractionId(TicketEntity ticket){
        String name=ticket.getAttrName();
        
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
    }
    

    public void persist(Object object) {
        em.persist(object);
    }
    


}
