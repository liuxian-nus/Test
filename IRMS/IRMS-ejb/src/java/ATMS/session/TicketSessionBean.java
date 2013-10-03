/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import javax.ejb.Stateless;
import ATMS.entity.TicketEntity;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
        em.persist(ticket);
        return;
    }
    
    

    public void persist(Object object) {
        em.persist(object);
    }
    


}
