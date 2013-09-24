/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.RoomEntity;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@RequestScoped
public class RoomResultManagedBean implements Serializable {
private RoomEntity room;
private List<RoomEntity> allRooms;
private List<RoomEntity> availableRooms;

    public List<RoomEntity> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(List<RoomEntity> availableRooms) {
        this.availableRooms = availableRooms;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public List<RoomEntity> getAllRooms() {
        return allRooms;
    }

    public void setAllRooms(List<RoomEntity> allRooms) {
        this.allRooms = allRooms;
    }

    /**
     * Creates a new instance of RoomResultManagedBean
     */
    public RoomResultManagedBean() {
    }
    
     public void initViewAll(PhaseEvent event) {
        allRooms = (List<RoomEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("AllRooms");
    }

    public void initViewAvailable(PhaseEvent event) {
        availableRooms = (List<RoomEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("AllRooms");
    }
    
}
