/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.EventBookingEntity;
import CEMS.entity.EventServiceBookingEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ser3na
 */
@Stateless
@LocalBean
public class EventServiceBookingSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    EventServiceBookingEntity eventServiceBooking;
    EventBookingEntity eventBooking;
    List<EventServiceBookingEntity> eventServiceBookingList = new ArrayList<EventServiceBookingEntity>();
    List<EventServiceBookingEntity> temp = new ArrayList<EventServiceBookingEntity>();

    public EventServiceBookingSessionBean() {
    }

    public List<EventServiceBookingEntity> getBookingById(Long id) {
        Query q = em.createQuery("SELECT m FROM EventServiceBookingEntity m");
        temp = new ArrayList<EventServiceBookingEntity>();
        temp = q.getResultList();
        eventServiceBookingList = new ArrayList<EventServiceBookingEntity>();
        eventServiceBooking = new EventServiceBookingEntity();
        Iterator<EventServiceBookingEntity> itr = temp.iterator();
        while (itr.hasNext()) {
            eventServiceBooking = itr.next();
            System.err.println("1. "+id);
            System.err.println("2. "+eventServiceBooking.getEventService().getId());
            System.err.println("3. "+eventServiceBooking.getEventBookingId());
            System.err.println("4. "+eventServiceBooking.getEventBooking().getId());
            if (eventServiceBooking.getEventBooking().getId().equals(id)) {
                eventServiceBookingList.add(eventServiceBooking);
            }
        }
        return eventServiceBookingList;
    }
    
    public EventServiceBookingEntity addEventServiceBooking(EventServiceBookingEntity eventServiceBooking) {
        em.persist(eventServiceBooking);
        return eventServiceBooking;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EventServiceBookingEntity getEventServiceBooking() {
        return eventServiceBooking;
    }

    public void setEventServiceBooking(EventServiceBookingEntity eventServiceBooking) {
        this.eventServiceBooking = eventServiceBooking;
    }

    public EventBookingEntity getEventBooking() {
        return eventBooking;
    }

    public void setEventBooking(EventBookingEntity eventBooking) {
        this.eventBooking = eventBooking;
    }

    public List<EventServiceBookingEntity> getEventServiceBookingList() {
        return eventServiceBookingList;
    }

    public void setEventServiceBookingList(List<EventServiceBookingEntity> eventServiceBookingList) {
        this.eventServiceBookingList = eventServiceBookingList;
    }

    public List<EventServiceBookingEntity> getTemp() {
        return temp;
    }

    public void setTemp(List<EventServiceBookingEntity> temp) {
        this.temp = temp;
    }
}
