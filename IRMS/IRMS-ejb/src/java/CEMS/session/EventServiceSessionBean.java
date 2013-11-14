/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.EventServiceEntity;
import java.util.ArrayList;
import java.util.Iterator;
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
public class EventServiceSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    public EventServiceSessionBean() {
    }
    
    public List<EventServiceEntity> getAllEventServices() throws NoResultException  {
        Query q = em.createQuery("SELECT es FROM EventServiceEntity es");
        return q.getResultList();
    }
    
    public List<EventServiceEntity> getEventServiceByCategory(String category) throws NoResultException {
//        Query q = em.createQuery("SELECT es FROM EventServiceEntity es WHERE es.serviceCategory = '" + category + "'");
//        System.out.println("EventSerSessionBean"+q.getResultList().isEmpty());
        System.out.println("In getEventServiceByCategory");
        Query q2 = em.createQuery("SELECT M FROM EventServiceEntity M");
        
        Iterator<EventServiceEntity> itr = q2.getResultList().iterator();
        List <EventServiceEntity> resultList = new ArrayList();
        while (itr.hasNext())
        {
            EventServiceEntity current = itr.next();
            if(current.getServiceCategory().equalsIgnoreCase(category))
            {
                resultList.add(current);
                System.out.println("a service has been got!");
            }
        }
        System.out.println(resultList.size());
        return resultList;
//        return q.getResultList();
    }

    public EventServiceEntity getEventServiceById(Long serviceId) {
        EventServiceEntity eventService = em.find(EventServiceEntity.class, serviceId);
        return eventService;
    }
    
     public EventServiceEntity addEventService(EventServiceEntity eventService) {
        em.persist(eventService);
        return eventService;
    }
     
     public boolean updateService(EventServiceEntity eventService) {
        em.merge(eventService);
        return true;
    }

     public boolean deleteService(Long id) {
        EventServiceEntity eventService = em.find(EventServiceEntity.class, id);
        if (eventService == null) {
            return false;
        }
        em.remove(eventService);
        return true;
    }
}
