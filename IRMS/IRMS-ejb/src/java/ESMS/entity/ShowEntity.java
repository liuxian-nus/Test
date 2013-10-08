/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Ser3na
 */
@Entity
public class ShowEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showId;
    private String showName;
    private String showDescription;
    private int showCapacity;
    
    @OneToMany(targetEntity = ShowTicketEntity.class, cascade = {CascadeType.MERGE})
    private List<ShowTicketEntity> showTickets = new ArrayList<ShowTicketEntity>();
    @OneToMany(targetEntity = ShowScheduleEntity.class, cascade = {CascadeType.MERGE})
    private List<ShowScheduleEntity> showSchedules = new ArrayList<ShowScheduleEntity>();

    public List<ShowScheduleEntity> getShowSchedules() {
        return showSchedules;
    }

    public void setShowSchedules(List<ShowScheduleEntity> showSchedules) {
        this.showSchedules = showSchedules;
    }
    
    public void addShowSchedule(ShowScheduleEntity showSchedule){
        this.showSchedules.add(showSchedule);
    }
    
    public List<ShowTicketEntity> getShowTickets() {
        return showTickets;
    }

    public void setShowTickets(List<ShowTicketEntity> showTickets) {
        this.showTickets = showTickets;
    }

    public void addShowTicket(ShowTicketEntity showTicket) {
        this.showTickets.add(showTicket);
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(String showDescription) {
        this.showDescription = showDescription;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }
    
    public int getShowCapacity() {
        return showCapacity;
    }

    public void setShowCapacity(int showCapacity) {
        this.showCapacity = showCapacity;
    }
}
