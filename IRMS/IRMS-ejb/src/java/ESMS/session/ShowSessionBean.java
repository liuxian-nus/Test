/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
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
public class ShowSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ShowEntity show;

    public ShowSessionBean() {
    }

    public ShowEntity addShow(ShowEntity show) {
        em.persist(show);
        return show;
    }

    public boolean deleteShow(Long showId) {
        show = em.find(ShowEntity.class, showId);
        if (show == null) {
            System.out.println("deleteShow: show does not exist!");
            return false;
        }
        em.remove(show);
        return true;
    }

    public boolean updateShow(ShowEntity show) {
        em.merge(show);
        System.out.println("ShowSessionBean: show " + show.getShowName() + " is successfully updated");
        return true;
    }

    public List<ShowEntity> getAllShows() throws NoResultException {
        Query q = em.createQuery("SELECT m FROM ShowEntity m");
        return q.getResultList();
    }
    
    public void addShowSchedule(Long showId, ShowScheduleEntity showSchedule) {
        System.err.println("show session bean: add show schedule");
        show = em.find(ShowEntity.class, showId);
        show.addShowSchedule(showSchedule);
        em.merge(show);
    }

    public void addShowTicket(Long showId, ShowTicketEntity showTicket) {
        System.err.println("show session bean: add show ticket");
        show = em.find(ShowEntity.class, showId);
        show.addShowTicket(showTicket);
        em.merge(show);
    }
    
     public void uploadFile(Long showId, byte[] buffer) {
        show = em.find(ShowEntity.class, showId);
        show.setImage(buffer);
        em.persist(show);
        em.flush();
    }
}
