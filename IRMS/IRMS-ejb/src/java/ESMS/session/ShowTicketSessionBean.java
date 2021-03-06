/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowTicketEntity;
import java.util.List;
import javax.ejb.LocalBean;
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
@LocalBean
public class ShowTicketSessionBean implements ShowTicketSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    ShowTicketEntity showTicket;

    public ShowTicketSessionBean() {
    }

    @Override
    public ShowTicketEntity addShowTicket(ShowTicketEntity showTicket) {
        em.persist(showTicket);
        return showTicket;
    }

    @Override
    public boolean updateShowTicket(ShowTicketEntity showTicket) {
        em.merge(showTicket);
        return true;
    }

    @Override
    public boolean deleteShowTicket(Long showTicketId) {
        showTicket = em.find(ShowTicketEntity.class, showTicketId);
        if (showTicket == null) {
            System.out.println("deleteShowTicket: show does not exist!");
            return false;
        }
        em.remove(showTicket);
        return true;
    }

    @Override
    public List<ShowTicketEntity> getAllShowTickets() throws NoResultException {
        Query q = em.createQuery("SELECT s1 FROM ShowTicketEntity s1");
        return q.getResultList();
    }

    @Override
    public ShowTicketEntity getShowTicketById(Long showTicketId) {
        showTicket = em.find(ShowTicketEntity.class, showTicketId);
        return showTicket;
    }

    @Override
    public void updateQuantity(Long showTicketId, int showTicketQuota) {
        showTicket = em.find(ShowTicketEntity.class, showTicketId);
        showTicket.setShowTicketQuota(showTicket.getShowTicketQuota()-showTicketQuota);
    }
}
