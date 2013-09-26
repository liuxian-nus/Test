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
 * @author liuxian
 */
@ManagedBean
@RequestScoped
public class RoomSearchResultManagedBean implements Serializable{

    private RoomEntity thisRoom;
    private List<RoomEntity> rooms;
    private int roomId;
    
    public RoomSearchResultManagedBean() {
    }
    
    public List<RoomEntity> getRooms() {
        return rooms;
    }
    
    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }
    
    public RoomEntity getThisRoom() {
        return thisRoom;
    }
    
    public void setThisRoom(RoomEntity thisRoom) {
        this.thisRoom = thisRoom;
    }
    
    public void initViewSelect(PhaseEvent event) {
        thisRoom = (RoomEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisRoom");
    }
    
    public void intiViewAll(PhaseEvent event) {
        rooms = (List<RoomEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("Rooms");
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    
}
