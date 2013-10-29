/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author liuxian
 */
@Entity
public class RoomServiceExeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    private RoomEntity room = new RoomEntity();
    @ManyToOne(cascade = {CascadeType.ALL})
    private RoomServiceEntity roomService = new RoomServiceEntity();
    private int roomServiceQuantity = 0;

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public RoomServiceEntity getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomServiceEntity roomService) {
        this.roomService = roomService;
    }
    
     public int getRoomServiceQuantity() {
        return roomServiceQuantity;
    }

    public void setRoomServiceQuantity(int roomServiceQuantity) {
        this.roomServiceQuantity = roomServiceQuantity;
    }

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomServiceExeEntity)) {
            return false;
        }
        RoomServiceExeEntity other = (RoomServiceExeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ACMS.entity.RoomServiceExe[ id=" + id + " ]";
    }
}
