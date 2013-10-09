/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowTicketEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ser3na
 */
@Stateless
public class ShowTicketSessionBean {

    @PersistenceContext
    private EntityManager em;

    public ShowTicketEntity getFunctionality(Long funcId) throws ExistException {
        ShowTicketEntity showTicket = em.find(ShowTicketEntity.class, funcId);
        System.err.println("getShowTicket: " + showTicket.getShowTicketType());
        if (showTicket == null) {
            throw new ExistException("ShowTicketSessionBean-->ExistException-->Show Ticket doesn't exist!");
        }
        return showTicket;
    }

    public void addShowTicket(ShowTicketEntity showTicket) {
        em.persist(showTicket);
        em.flush();
    }

    public void updateShowTicketEntity(ShowTicketEntity showTicket) {
        em.merge(showTicket);
        em.flush();
    }

    public void removeShowTicketEntity(Long showTicketId) {
        ShowTicketEntity showTicket = em.find(ShowTicketEntity.class, showTicketId);
        em.remove(showTicket);
    }

    public List<ShowTicketEntity> getAllShowTickets() {
        Query query = em.createQuery("SELECT s1 FROM ShowTicketEntity s1");
        return query.getResultList();
    }
}
