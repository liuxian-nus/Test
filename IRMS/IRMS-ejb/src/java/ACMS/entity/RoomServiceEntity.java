/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author liuxian
 */
@Entity
@Table(
        uniqueConstraints=
            @UniqueConstraint(columnNames={"roomServiceName"})
    )
public class RoomServiceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String roomServiceName;
    private String category; //free service, room service, room food catering
    private double roomServicePrice;
    

    public String getRoomServiceName() {
        return roomServiceName;
    }

    public void setRoomServiceName(String roomServiceName) {
        this.roomServiceName = roomServiceName;
    }

    public double getRoomServicePrice() {
        System.out.println("this room service price is: " + roomServicePrice);
        return roomServicePrice;
    }

    public void setRoomServicePrice(double roomServicePrice) {
        this.roomServicePrice = roomServicePrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomServiceName != null ? roomServiceName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomServiceEntity)) {
            return false;
        }
        RoomServiceEntity other = (RoomServiceEntity) object;
        if ((this.roomServiceName == null && other.roomServiceName != null) || (this.roomServiceName != null && !this.roomServiceName.equals(other.roomServiceName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ACMS.RoomServiceEntity[ id=" + roomServiceName + " ]";
    }
    
}
