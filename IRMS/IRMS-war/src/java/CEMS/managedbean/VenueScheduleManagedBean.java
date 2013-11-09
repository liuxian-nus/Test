/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.EventBookingEntity;
import CEMS.entity.EventEntity;
import CEMS.entity.VenueEntity;
import CEMS.session.EventBookingSessionBean;
import CEMS.session.EventSessionBean;
import CEMS.session.VenueSessionBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class VenueScheduleManagedBean {

    @EJB
    EventBookingSessionBean eventBookingSessionBean;
    @EJB
    EventSessionBean eventSessionBean;
    @EJB
    VenueSessionBean venueSessionBean;
    
    VenueEntity venue;
    EventBookingEntity eventBooking;
    EventEntity eventEntity;
    
    private Long eventId;
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    List<EventBookingEntity> eventBookings;

    /**
     * Creates a new instance of VenueScheduleManagedBean
     */
    public VenueScheduleManagedBean() {
        eventBookings = new ArrayList<EventBookingEntity>();
        venue = new VenueEntity();
        eventEntity = new EventEntity();
        eventBooking = new EventBookingEntity();
        eventModel = new DefaultScheduleModel();
    }

    public void init(PhaseEvent event) {
        venue = new VenueEntity();
        venue = (VenueEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("venue");
        doThis();
    }

    public void doThis() {
        eventBookings = new ArrayList<EventBookingEntity>();
        System.err.println("get flash venue: " + venue.getVenueId());
        eventBookings = (List<EventBookingEntity>) eventBookingSessionBean.getEventBookings(venue);
        System.err.println("doThis size: " + eventBookings.size());
        Iterator<EventBookingEntity> itr = eventBookings.iterator();
        while (itr.hasNext()) {
            eventBooking = itr.next();
            eventModel.addEvent(new DefaultScheduleEvent(eventBooking.getEvent().getEventName(), eventBooking.getBookingDate(), eventBooking.getBookingDate()));
        }
    }

    public void addEvent(ActionEvent actionEvent) {
        System.err.println("add eventBooking: " + eventId);
        if (event.getId() == null) {
            eventModel.addEvent(event);
            eventEntity = eventSessionBean.getReservation(eventId);
            System.err.println(eventEntity.getEventId());
            
            eventBooking = new EventBookingEntity();
            eventBooking.setEvent(eventEntity);
            eventBooking.setVenue(venue);
            eventBooking.setBookingDate(event.getStartDate());
            eventBookingSessionBean.addEventBooking(eventBooking);
            System.err.println("New event booking..."+ eventBooking.getId());
           
        } else {
            eventModel.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        System.err.println("onDateSelect...");
    }

    public EventBookingSessionBean getEventBookingSessionBean() {
        return eventBookingSessionBean;
    }

    public void setEventBookingSessionBean(EventBookingSessionBean eventBookingSessionBean) {
        this.eventBookingSessionBean = eventBookingSessionBean;
    }

    public List<EventBookingEntity> getEventBookings() {
        return eventBookings;
    }

    public void setEventBookings(List<EventBookingEntity> eventBookings) {
        this.eventBookings = eventBookings;
    }

    public VenueEntity getVenue() {
        return venue;
    }

    public void setVenue(VenueEntity venue) {
        this.venue = venue;
    }

    public EventBookingEntity getEventBooking() {
        return eventBooking;
    }

    public void setEventBooking(EventBookingEntity eventBooking) {
        this.eventBooking = eventBooking;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
