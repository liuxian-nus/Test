/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowTicketEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ser3na
 */
@Stateless
public class ShowTicketSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    ShowTicketEntity showTicket;
    
    public ShowTicketSessionBean(){}

    public ShowTicketEntity addShowTicket(ShowTicketEntity showTicket) {
        em.persist(showTicket);
        return showTicket;
    }

    public boolean updateShowTicket(ShowTicketEntity showTicket) {
        em.merge(showTicket);
        return true;
    }

    public boolean deleteShowTicket(Long showTicketId) {
        showTicket = em.find(ShowTicketEntity.class, showTicketId);
        if (showTicket==null){
            System.out.println("deleteShowTicket: show does not exist!");
            return false;
        }
        em.remove(showTicket);
        return true;
    }

    public List<ShowTicketEntity> getAllShowTickets() throws NoResultException{
        Query q = em.createQuery("SELECT s1 FROM ShowTicketEntity s1");
        return q.getResultList();
    }
}
