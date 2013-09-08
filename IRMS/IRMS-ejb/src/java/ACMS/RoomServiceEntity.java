/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author liuxian
 */
@Entity
public class RoomServiceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long roomServiceId;
    private String roomServiceType;
    private double roomServicePrice;

    public Long getRoomServiceId() {
        return roomServiceId;
    }

    public void setRoomServiceId(Long roomServiceId) {
        this.roomServiceId = roomServiceId;
    }

    public String getRoomServiceType() {
        return roomServiceType;
    }

    public void setRoomServiceType(String roomServiceType) {
        this.roomServiceType = roomServiceType;
    }

    public double getRoomServicePrice() {
        return roomServicePrice;
    }

    public void setRoomServicePrice(double roomServicePrice) {
        this.roomServicePrice = roomServicePrice;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomServiceId != null ? roomServiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomServiceEntity)) {
            return false;
        }
        RoomServiceEntity other = (RoomServiceEntity) object;
        if ((this.roomServiceId == null && other.roomServiceId != null) || (this.roomServiceId != null && !this.roomServiceId.equals(other.roomServiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ACMS.RoomServiceEntity[ id=" + roomServiceId + " ]";
    }
    
}
