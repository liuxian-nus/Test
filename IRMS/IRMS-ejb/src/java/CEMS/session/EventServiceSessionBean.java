/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.EventServiceEntity;
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
public class EventServiceSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    public EventServiceSessionBean() {
    }
    
    public List<EventServiceEntity> getAllEventServices() {
        Query q = em.createQuery("SELECT m FROM EventServiceEntity m");
        return q.getResultList();
    }

    public EventServiceEntity getEventServiceById(Long serviceId) {
        EventServiceEntity eventService = em.find(EventServiceEntity.class, serviceId);
        return eventService;
    }
    
     public EventServiceEntity addEventService(EventServiceEntity eventService) {
        em.persist(eventService);
        return eventService;
    }

}
