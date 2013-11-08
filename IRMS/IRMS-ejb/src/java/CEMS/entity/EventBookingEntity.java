/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author lionetdd
 */
@Entity
public class EventBookingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bookingDate;
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date endingDate;
    private Integer numberNightRoom;
    
    @OneToMany(cascade={CascadeType.ALL})
    private List<EventServiceBookingEntity> serviceBookings;
    @ManyToOne(cascade={CascadeType.ALL})
    private EventEntity event;
    @OneToOne(cascade={CascadeType.ALL})
    private VenueEntity venue;

    public Integer getNumberNightRoom() {
        return numberNightRoom;
    }

    public void setNumberNightRoom(Integer numberNightRoom) {
        this.numberNightRoom = numberNightRoom;
    }

    public List<EventServiceBookingEntity> getServiceBookings() {
        return serviceBookings;
    }

    public void setServiceBookings(List<EventServiceBookingEntity> serviceBookings) {
        this.serviceBookings = serviceBookings;
    }

//    public Date getEndingDate() {
//        return endingDate;
//    }
//
//    public void setEndingDate(Date endingDate) {
//        this.endingDate = endingDate;
//    }
//    
    

    public EventBookingEntity(){}
    
    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public VenueEntity getVenue() {
        return venue;
    }

    public void setVenue(VenueEntity venue) {
        this.venue = venue;
    }
    
    public Long getId() {
        return bookingId;
    }

    public void setId(Long id) {
        this.bookingId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventBookingEntity)) {
            return false;
        }
        EventBookingEntity other = (EventBookingEntity) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CEMS.entity.BookingEntity[ id=" + bookingId + " ]";
    }
    
}
