/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import java.util.ArrayList;
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
    private ShowScheduleEntity showSchedule;
    private List<ShowEntity> shows;

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

    public List<ShowScheduleEntity> getAllShowSchedules(Long showId) throws NoResultException {
        show = em.find(ShowEntity.class, showId);
        List q = show.getShowSchedules();
//        Query q = em.createQuery("SELECT m FROM ShowScheduleEntity m");
//        return q.getResultList();
        return q;
    }

    public void addShowSchedule(Long showId, ShowScheduleEntity showSchedule) {
        System.err.println("show session bean: add show schedule");
        show = em.find(ShowEntity.class, showId);
        show.addShowSchedule(showSchedule);
        em.merge(show);
    }

    public void addShowTicket(Long showScheduleId, ShowTicketEntity showTicket) {
        System.err.println("show session bean: add show ticket");
//        show = em.find(ShowEntity.class, showId);
        showSchedule = em.find(ShowScheduleEntity.class, showScheduleId);
        showSchedule.addShowTicket(showTicket);
        em.merge(showSchedule);
    }

    public void uploadFile(Long showId, byte[] buffer) {
        show = em.find(ShowEntity.class, showId);
        show.setImage(buffer);
        em.persist(show);
        em.flush();
    }

    public void uploadImage(Long showId, String fileName) {
        show = em.find(ShowEntity.class, showId);
        show.setImagePath(fileName);
        em.persist(show);
        em.flush();
    }

    public ShowEntity getShowById(Long showId) {
        return em.find(ShowEntity.class, showId);
    }

    public List<ShowEntity> getShowByName(String searchName) {
        Query query = em.createQuery("SELECT r FROM ShowEntity r WHERE r.showName ='" + searchName + "'");
        System.err.println("getShowByName: " + searchName);
        shows = new ArrayList<ShowEntity>();
        shows = query.getResultList();
        
        for(ShowEntity s:shows)
            s.getShowSchedules().size();
        
        return shows;
    }
}
