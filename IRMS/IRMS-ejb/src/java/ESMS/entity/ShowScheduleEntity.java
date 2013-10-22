/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Ser3na
 */
@Entity
public class ShowScheduleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showScheduleId;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date duration;
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date showDate;
    @OneToMany(targetEntity = ShowTicketEntity.class, cascade = {CascadeType.MERGE})
    private List<ShowTicketEntity> showTickets = new ArrayList<ShowTicketEntity>();

    public Long getShowScheduleId() {
        return showScheduleId;
    }

    public void setShowScheduleId(Long showScheduleId) {
        this.showScheduleId = showScheduleId;
    }

//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

//    public Date getShowDate() {
//        return showDate;
//    }
//
//    public void setShowDate(Date showDate) {
//        this.showDate = showDate;
//    }

    public List<ShowTicketEntity> getShowTickets() {
        return showTickets;
    }

    public void setShowTickets(List<ShowTicketEntity> showTickets) {
        this.showTickets = showTickets;
    }
    
     public void addShowTicket(ShowTicketEntity showTicket) {
        this.showTickets.add(showTicket);
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
}
