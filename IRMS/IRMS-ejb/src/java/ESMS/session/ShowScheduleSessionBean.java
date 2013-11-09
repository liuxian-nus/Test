/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.session;

import ESMS.entity.ShowScheduleEntity;
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
public class ShowScheduleSessionBean implements ShowScheduleSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    ShowScheduleEntity showSchedule;

    public ShowScheduleSessionBean() {
    }

    @Override
    public ShowScheduleEntity addShowSchedule(ShowScheduleEntity showSchedule) {
        em.persist(showSchedule);
        return showSchedule;
    }

    @Override
    public void deleteShowSchedule(Long showScheduleId) {
        showSchedule = em.find(ShowScheduleEntity.class, showScheduleId);
        System.err.println("Show Schedule Id: " + showScheduleId);
        em.remove(showSchedule);
    }

    @Override
    public boolean updateShowSchedule(ShowScheduleEntity showSchedule) {
        em.merge(showSchedule);
//        System.out.println("ShowScheduleSessionBean: show " + showSchedule.getShowDate() + " is successfully updated");
        return true;
    }

    @Override
    public List<ShowScheduleEntity> getAllShowSchedules() throws NoResultException {
        Query q = em.createQuery("SELECT m FROM ShowScheduleEntity m");
        return q.getResultList();
    }

    @Override
    public ShowScheduleEntity getShowScheduleById(Long showScheduleId) {
        showSchedule = em.find(ShowScheduleEntity.class, showScheduleId);
        return showSchedule;
    }
}
