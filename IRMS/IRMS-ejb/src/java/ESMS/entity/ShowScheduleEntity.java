/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date startTime;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date duration;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date showDate;

    public Long getShowScheduleId() {
        return showScheduleId;
    }

    public void setShowScheduleId(Long showScheduleId) {
        this.showScheduleId = showScheduleId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }
}
