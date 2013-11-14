/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.EventBookingEntity;
import CEMS.entity.EventEntity;
import CEMS.entity.EventServiceEntity;
import CEMS.entity.VenueEntity;
import CEMS.entity.VenueFunctionEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class EventSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    EventBookingEntity be;
    EventEntity ee;
    VenueEntity ve;
    EventServiceEntity se;
    private List<EventEntity> requests;
    private List<EventEntity> eventList;

    public EventSessionBean() {
    }

    public EventEntity makeReservation(String eventName, String eventType,
            String eventContact, String title, String name, String email, int eventScale, Date startDate, Date endDate) {
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
        System.out.println("EventSessionBean: makeReservation: event has been created!" + ee.getEventId() + ee.getEventName());

        ee.setStatus("PENDING");
        em.persist(ee);
        System.out.println("EventSessionBean: makeReservation: event status has been changed " + ee.getEventId() + ee.getStatus());

        return ee;
    }

    public EventEntity completeReservation(EventEntity event, String address, String phone, String countryOfResidence, Double estimatedBudget, String company, String industry, String preferredLanguage, boolean isPublic) {
        event.setAddress(address);
        event.setCountryOfResidence(countryOfResidence);
        event.setEstimatedBudget(estimatedBudget);
        event.setEventContact(phone);
        event.setEventCorporate(company);
        event.setEventIndustry(industry);
        event.setPreferLanguage(preferredLanguage);
        event.setIsPublic(isPublic);

        System.out.println("EventSessionBean:completeReservation: all event details have been updated!" + event.getEventId());
        em.merge(event);
        return event;
    }

    public EventEntity getReservation(Long eventId) {
        ee = em.find(EventEntity.class, eventId);
        if (ee != null) {
            System.out.println("EventSessionBean: getReservation: event has been found " + ee.getEventId() + ee.getStatus());
            return ee;
        } else {
            System.out.println("EventSessionBean: getReservation: event does not exist! " + eventId);
            return null;
        }
    }

    public boolean deleteReservation(Long eventId) {
        ee = em.find(EventEntity.class, eventId);
        if (ee != null) {
            System.out.println("EventSessionBean: getReservation: event has been found " + ee.getEventId() + ee.getStatus());
            em.remove(ee);
            return true;
        } else {
            System.out.println("EventSessionBean: getReservation: event does not exist! " + eventId);
            return false;
        }
    }

    public VenueEntity venueBooking(Long eventId, Long venueId, Date startDate, Date endDate) {
        System.out.println("EventSessionBean:venueBooking: start booking! " + eventId);
        ee = em.find(EventEntity.class, eventId);
        if (ee != null) {
            be = new EventBookingEntity();
            ve = em.find(VenueEntity.class, venueId);
            if (ve == null) {
                System.out.println("EventSessionBean:venueBooking: venue does not exist! " + venueId);
                return null;
            }

            System.out.println("EventSessionBean:venueBooking: venue has been found! " + venueId);
            be.setBookingDate(startDate);
//            be.setEndingDate(endDate);
            be.setEvent(ee);
            be.setVenue(ve);
            em.persist(be);
            System.out.println("EventSessionBean:venueBooking: booking has been persistd! " + be.getId());

            List<EventBookingEntity> bookings = new ArrayList<EventBookingEntity>();
            bookings = ee.getBookings();
            bookings.add(be);
            ee.setBookings(bookings);
            em.merge(ee);
            System.out.println("EventSessionBean:venueBooking: event has been modified and merged! " + ee.getEventId());

            return ve;
        } else {
            System.out.println("EventSessionBean:venueBooking: event does not exist! " + eventId);
            return null;
        }
    }

    public List<Date> checkVenueAvailability(Long venueId, Integer numberPeople) {
        ve = em.find(VenueEntity.class, venueId);
        if (ve != null) {
            if (ve.getVenueCapacity() < numberPeople) {
                System.out.println("EventSessionBean:CheckVenueAvailability: venue quota reached! ");
                return null;
            }
            Query q = em.createQuery("SELECT b FROM EventBookingEntity b WHERE b.venue.venueId ='" + venueId + "'");
            List<Date> unavailDates = new ArrayList<Date>();

            for (Object o : q.getResultList()) {
                EventBookingEntity booking = (EventBookingEntity) o;
                Date startDate = booking.getBookingDate();
//                Date endDate = booking.getEndingDate();

                Date unavailDate = startDate;
//                while (unavailDate.before(endDate)) {
                unavailDates.add(unavailDate);
                System.out.println("EventSessionBean:CheckVenueAvailability: date unavailable is "
                        + unavailDate);
                unavailDate.setTime(unavailDate.getTime() + 1 * 24 * 60 * 60 * 1000);
                System.out.println("EventSessionBean:CheckVenueAvailability: date has been incremented to "
                        + unavailDate);

//                }
                System.out.println("EventSessionBean:CheckVenueAvailability: date list has been partially retrieved "
                        + unavailDates.size());
            }
            System.out.println("EventSessionBean:CheckVenueAvailability: date list has been fully retrieved "
                    + unavailDates.size());
            return unavailDates;

        } else {
            System.out.println("EventSessionBean:checkVenueAvailability: The venue does not exist! " + venueId);
            return null;
        }

    }

    public boolean checkVenueCapacity(Long venueId, Integer numberPeople) {
        ve = em.find(VenueEntity.class, venueId);
        if (ve != null) {
            if (numberPeople > ve.getVenueCapacity()) {
                return false;
            } else {
                return true;
            }
        } else {
            System.out.println("EventSessionBean: checkVenueCapacity: venue does not exist! " + venueId);
            return false;
        }
    }

    public List<VenueEntity> searchVenue(Integer venueCapacity, String venueFunction) {
        Query q = em.createQuery("SELECT v FROM VenueEntity v WHERE v.venueCapacity >= '" + venueCapacity + "'");
        System.out.println("EventSessionBean:SearchVenue: qualified venues have been retrieved 1");
        List<VenueEntity> qualifedVenues = new ArrayList<VenueEntity>();
       
        
        Iterator <VenueEntity> itr = q.getResultList().iterator();
        
        while(itr.hasNext())
        {
            VenueEntity current = itr.next();
            List <VenueFunctionEntity> funcList = current.getVenueFunction();
            Iterator <VenueFunctionEntity> itr2 = funcList.iterator();
            
            while(itr2.hasNext())
            {
                VenueFunctionEntity currentFunction = itr2.next();
                if(currentFunction.getFunctionName().equalsIgnoreCase(venueFunction))
                {
                    System.out.println("A qualified venue has been returned!");
                    qualifedVenues.add(current);
                    break;
                }
            }
            System.out.println("VenueEntity: "+current.getVenueName());
        }

        
        System.out.println("EventSessionBean:SearchVenue: qualified venues have been retrieved 2");
        return qualifedVenues;
    }

    public List<EventEntity> listEvents() {
        Query q = em.createQuery("SELECT e FROM EventEntity e");
        List<EventEntity> events = new ArrayList<EventEntity>();

        for (Object o : q.getResultList()) {
            ee = (EventEntity) o;
            events.add(ee);
            System.out.println("EventSessionBean:listEvents: a new event has been added!");
        }
        System.out.println("EventSessionBean:listEvents: all events have been fully retrieved!" + events.size());
        return events;
    }

    public List<EventEntity> listConfirmedEvents() {
        List<EventEntity> confirmedEvents;
        String status = "In Progress: booking completed!";
        Query q = em.createQuery("SELECT e FROM EventEntity e WHERE e.status = '" + status + "'");

        confirmedEvents = q.getResultList();
        System.out.println("EventSessionBean: " + q.getResultList().isEmpty());
        return confirmedEvents;
    }

    public List<EventEntity> listPublicEvents() {
        Query q = em.createQuery("SELECT e FROM EventEntity e WHERE e.isPublic = 'true'");
        System.out.println("EventSessionBean: " + q.getResultList().isEmpty());
        return q.getResultList();
    }

    public List<EventEntity> listConfirmedPublicEvents() {
        Query q = em.createQuery("SELECT e FROM EventEntity e WHERE e.isPublic = 'true' AND e.status = "
                + "'In Progress: booking completed!'");

        List<EventEntity> eventList = q.getResultList();
        System.out.println("EventSessionBean: " + q.getResultList().isEmpty());
        return eventList;
    }

    public VenueEntity getVenue(Long venueId) {
        VenueEntity thisV = em.find(VenueEntity.class, venueId);
        if (thisV != null) {
            System.out.println("EventSessionBean:getVenue: The venue has been found!");
            return thisV;
        } else {
            System.out.println("EventSessionBean:getVenue: The venue cannot be found!");
            return null;
        }
    }

    public EventEntity addEvent(EventEntity event) {
        em.persist(event);
        return event;
    }

    public boolean updateEvent(EventEntity event) {
        em.merge(event);
        return true;
    }

    public boolean deleteEvent(Long eventId) {
        ee = em.find(EventEntity.class, eventId);
        if (ee == null) {
            return false;
        }
        em.remove(ee);
        return true;
    }

    public List<EventEntity> getAllEvents() {
        Query q = em.createQuery("SELECT m FROM EventEntity m");
        return q.getResultList();
    }

    public List<EventEntity> getPendingEvents() {
        Query q = em.createQuery("SELECT m FROM EventEntity m");
        requests = new ArrayList<EventEntity>();
        eventList = new ArrayList<EventEntity>();
        eventList = q.getResultList();

        Iterator<EventEntity> itr = eventList.iterator();
        while (itr.hasNext()) {
            ee = itr.next();
            if (ee.getStatus().equalsIgnoreCase("Pending")) {
                requests.add(ee);
            }
        }
        return requests;
    }

    public List<EventEntity> getReservedEvents() {
        Query q = em.createQuery("SELECT m FROM EventEntity m");
        requests = new ArrayList<EventEntity>();
        eventList = new ArrayList<EventEntity>();
        eventList = q.getResultList();

        Iterator<EventEntity> itr = eventList.iterator();
        while (itr.hasNext()) {
            ee = itr.next();
            if (ee.getStatus().equalsIgnoreCase("Reserved")) {
                requests.add(ee);
            }
        }
        return requests;
    }

    public List<EventEntity> getConfirmedEvents() {
        Query q = em.createQuery("SELECT m FROM EventEntity m");
        requests = new ArrayList<EventEntity>();
        eventList = new ArrayList<EventEntity>();
        eventList = q.getResultList();

        Iterator<EventEntity> itr = eventList.iterator();
        while (itr.hasNext()) {
            ee = itr.next();
            if (ee.getStatus().equalsIgnoreCase("Confirmed")) {
                requests.add(ee);
            }
        }
        return requests;
    }
    
    public List<EventEntity> getBillEvents() {
        Query q = em.createQuery("SELECT m FROM EventEntity m");
        requests = new ArrayList<EventEntity>();
        eventList = new ArrayList<EventEntity>();
        eventList = q.getResultList();

        Iterator<EventEntity> itr = eventList.iterator();
        while (itr.hasNext()) {
            ee = itr.next();
            if (ee.getStatus().equalsIgnoreCase("Confirmed")&&ee.isPaymentStatus()==false&&(!ee.getEventType().equalsIgnoreCase("show"))) {
                requests.add(ee);
            }
        }
        return requests;
    }

    public List<EventEntity> getCanceledEvents() {
        Query q = em.createQuery("SELECT m FROM EventEntity m");
        requests = new ArrayList<EventEntity>();
        eventList = new ArrayList<EventEntity>();
        eventList = q.getResultList();

        Iterator<EventEntity> itr = eventList.iterator();
        while (itr.hasNext()) {
            ee = itr.next();
            if (ee.getStatus().equalsIgnoreCase("Cancel")) {
                requests.add(ee);
            }
        }
        return requests;
    }

    // Request for Show
    public List<EventEntity> getRequests() {
        requests = new ArrayList<EventEntity>();
        eventList = new ArrayList<EventEntity>();
        Query q = em.createQuery("SELECT m FROM EventEntity m");
        eventList = q.getResultList();

        Iterator<EventEntity> itr = eventList.iterator();
        while (itr.hasNext()) {
            ee = itr.next();
            if (ee.getEventType().equalsIgnoreCase("show")) {
                requests.add(ee);
            }
        }
        return requests;
    }
}
