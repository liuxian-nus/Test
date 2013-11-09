/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.EventBookingEntity;
import CEMS.entity.VenueEntity;
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
public class EventBookingSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    List<EventBookingEntity> eventBookings = new ArrayList<EventBookingEntity>();
    List<EventBookingEntity> bookingList;
    EventBookingEntity eventBooking;

    public EventBookingSessionBean() {
    }

    public List<EventBookingEntity> getEventBookings(VenueEntity venue) {
        Query q = em.createQuery("SELECT e FROM EventBookingEntity e");
        bookingList = new ArrayList<EventBookingEntity>();
        bookingList = q.getResultList();
        Iterator<EventBookingEntity> itr = bookingList.iterator();
        eventBooking = new EventBookingEntity();
        eventBookings = new ArrayList<EventBookingEntity>();
        while (itr.hasNext()) {
            eventBooking = itr.next();
            System.err.println("Booking List Size: "+bookingList.size());
            System.err.println("event booking name: "+eventBooking.getEvent().getEventName());
            System.err.println("venue id:"+venue.getVenueId());
            if (eventBooking.getVenue().getVenueId().equals(venue.getVenueId())) {
                eventBookings.add(eventBooking);
                System.err.println(eventBooking+" added to eventBookings list");
            }
        }
        return eventBookings;
    }
}
