/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ser3na
 */
@Entity
public class EventServiceBookingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventBookingId;
    @ManyToOne(cascade={CascadeType.MERGE})
    private EventBookingEntity eventBooking;
    @OneToOne(cascade={CascadeType.MERGE})
    private EventServiceEntity eventService;
    
    private int eventServiceQuantity;

    public Long getEventBookingId() {
        return eventBookingId;
    }

    public void setEventBookingId(Long eventBookingId) {
        this.eventBookingId = eventBookingId;
    }

    public EventBookingEntity getEventBooking() {
        return eventBooking;
    }

    public void setEventBooking(EventBookingEntity eventBooking) {
        this.eventBooking = eventBooking;
    }

    public EventServiceEntity getEventService() {
        return eventService;
    }

    public void setEventService(EventServiceEntity eventService) {
        this.eventService = eventService;
    }

    public int getEventServiceQuantity() {
        return eventServiceQuantity;
    }

    public void setEventServiceQuantity(int eventServiceQuantity) {
        this.eventServiceQuantity = eventServiceQuantity;
    }
}
