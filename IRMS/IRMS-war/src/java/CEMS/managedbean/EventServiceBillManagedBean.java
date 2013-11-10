/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.EventBookingEntity;
import CEMS.entity.EventEntity;
import CEMS.entity.EventServiceBookingEntity;
import CEMS.session.EventBookingSessionBean;
import CEMS.session.EventServiceBookingSessionBean;
import CEMS.session.EventSessionBean;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class EventServiceBillManagedBean {

    @EJB
    EventSessionBean eventSessionBean;
    @EJB
    EventServiceBookingSessionBean eventServiceBookingSessionBean;
    @EJB
    EventBookingSessionBean eventBookingSessionBean;
    private EventEntity selectedEvent;
    private EventServiceBookingEntity selectedEventServiceBooking;
    private EventBookingEntity selectedEventBooking;
    private List<EventServiceBookingEntity> eventServiceBooking;
    private List<EventBookingEntity> eventBooking;
    private double serviceTotalCost=0.0;
    private double venueRate=0.0;
    private double finalBill=0.0;
    private Long id;

    /**
     * Creates a new instance of EventServiceBillManagedBean
     */
    public EventServiceBillManagedBean() {
        selectedEvent = new EventEntity();
        selectedEventServiceBooking = new EventServiceBookingEntity();
        selectedEventBooking = new EventBookingEntity();
    }

    public void showBill(ActionEvent event) {
        selectedEvent = eventSessionBean.getReservation(id);
        eventServiceBooking = eventServiceBookingSessionBean.getServiceByEventId(id);
        Iterator<EventServiceBookingEntity> itr = eventServiceBooking.iterator();
        while (itr.hasNext()) {
            selectedEventServiceBooking = itr.next();
            serviceTotalCost += selectedEventServiceBooking.getEventServiceQuantity() * selectedEventServiceBooking.getEventService().getServiceCost();
        }
        eventBooking = eventBookingSessionBean.getEventBookingByEventId(id);
        Iterator<EventBookingEntity> itr2 = eventBooking.iterator();
        while (itr2.hasNext()) {
            selectedEventBooking = itr2.next();
            venueRate += selectedEventBooking.getVenueRate();
        }
        finalBill = selectedEvent.getDeposit()-serviceTotalCost-venueRate;
    }
    
     public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("eventServiceBill.xhtml");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventSessionBean getEventSessionBean() {
        return eventSessionBean;
    }

    public void setEventSessionBean(EventSessionBean eventSessionBean) {
        this.eventSessionBean = eventSessionBean;
    }

    public EventEntity getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(EventEntity selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public EventServiceBookingSessionBean getEventServiceBookingSessionBean() {
        return eventServiceBookingSessionBean;
    }

    public void setEventServiceBookingSessionBean(EventServiceBookingSessionBean eventServiceBookingSessionBean) {
        this.eventServiceBookingSessionBean = eventServiceBookingSessionBean;
    }

    public List<EventServiceBookingEntity> getEventServiceBooking() {
        return eventServiceBooking;
    }

    public void setEventServiceBooking(List<EventServiceBookingEntity> eventServiceBooking) {
        this.eventServiceBooking = eventServiceBooking;
    }

    public EventServiceBookingEntity getSelectedEventServiceBooking() {
        return selectedEventServiceBooking;
    }

    public void setSelectedEventServiceBooking(EventServiceBookingEntity selectedEventServiceBooking) {
        this.selectedEventServiceBooking = selectedEventServiceBooking;
    }

    public double getServiceTotalCost() {
        return serviceTotalCost;
    }

    public void setServiceTotalCost(double serviceTotalCost) {
        this.serviceTotalCost = serviceTotalCost;
    }

    public double getVenueRate() {
        return venueRate;
    }

    public void setVenueRate(double venueRate) {
        this.venueRate = venueRate;
    }

    public List<EventBookingEntity> getEventBooking() {
        return eventBooking;
    }

    public void setEventBooking(List<EventBookingEntity> eventBooking) {
        this.eventBooking = eventBooking;
    }

    public EventBookingSessionBean getEventBookingSessionBean() {
        return eventBookingSessionBean;
    }

    public void setEventBookingSessionBean(EventBookingSessionBean eventBookingSessionBean) {
        this.eventBookingSessionBean = eventBookingSessionBean;
    }

    public EventBookingEntity getSelectedEventBooking() {
        return selectedEventBooking;
    }

    public void setSelectedEventBooking(EventBookingEntity selectedEventBooking) {
        this.selectedEventBooking = selectedEventBooking;
    }

    public double getFinalBill() {
        return finalBill;
    }

    public void setFinalBill(double finalBill) {
        this.finalBill = finalBill;
    }
}
