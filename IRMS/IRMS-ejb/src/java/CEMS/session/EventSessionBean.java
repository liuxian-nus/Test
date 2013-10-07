/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.BookingEntity;
import CEMS.entity.EventEntity;
import CEMS.entity.ServiceEntity;
import CEMS.entity.VenueEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class EventSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    BookingEntity be;
    EventEntity ee;
    VenueEntity ve;
    ServiceEntity se;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public EventSessionBean(){}
    
    public EventEntity makeReservation(String eventName,String eventType,
            String eventContact,String title,String name, String email,int eventScale,Date startDate,Date endDate)
    {
        ee = new EventEntity();
        
            ee.setEmail(email);
            ee.setEndDate(endDate);
            ee.setEventContact(eventContact);
            ee.setEventName(eventName);
            ee.setEventScale(eventScale);
            ee.setEventType(eventType);
            ee.setName(name);
            ee.setStartDate(startDate);
            ee.setTitle(title);
            System.out.println("EventSessionBean: makeReservation: event has been created!"+ee.getEventId()+ee.getEventName());
            
            ee.setStatus("In Progress: move to booking");
            em.persist(ee);
            System.out.println("EventSessionBean: makeReservation: event status has been changed "+ee.getEventId()+ee.getStatus());

        return ee;
    }
    
    public EventEntity getReservation(Long eventId)
    {
        ee = em.find(EventEntity.class,eventId);
        if(ee!=null)
        {
            System.out.println("EventSessionBean: getReservation: event has been found "+ee.getEventId()+ee.getStatus());
            return ee;
        }
        else
        {
            System.out.println("EventSessionBean: getReservation: event does not exist! "+eventId);
            return null;
        }
    }
    
    public boolean deleteReservation(Long eventId)
    {
        ee = em.find(EventEntity.class,eventId);
        if(ee!=null)
        {
           System.out.println("EventSessionBean: getReservation: event has been found "+ee.getEventId()+ee.getStatus());
           em.remove(ee);
           return true;
        }
        else
        {
            System.out.println("EventSessionBean: getReservation: event does not exist! "+eventId);
            return false;
        }
    }

    public VenueEntity venueBooking(Long eventId, Long venueId,Date startDate,Date endDate)
    {
        System.out.println("EventSessionBean:venueBooking: start booking! "+eventId);
        ee = em.find(EventEntity.class, eventId);
        if(ee!=null)
        {
            be = new BookingEntity();
            ve = em.find(VenueEntity.class, venueId);
            if(ve==null)
            {
               System.out.println("EventSessionBean:venueBooking: venue does not exist! "+venueId); 
               return null;
            }
            
            System.out.println("EventSessionBean:venueBooking: venue has been found! "+venueId);
               be.setBookingDate(startDate);
               be.setEndingDate(endDate);
               be.setEvent(ee);
               be.setVenue(ve);
               em.persist(be);
            System.out.println("EventSessionBean:venueBooking: booking has been persistd! "+be.getId());
            
            List <BookingEntity> bookings = new ArrayList<BookingEntity> ();
                bookings = ee.getBookings();
                bookings.add(be);
                ee.setBookings(bookings);
                em.merge(ee);
            System.out.println("EventSessionBean:venueBooking: event has been modified and merged! "+ee.getEventId());
        
            return ve;
        }
        else
        {
            System.out.println("EventSessionBean:venueBooking: event does not exist! "+eventId);
            return null;
        }
    }
   

}
