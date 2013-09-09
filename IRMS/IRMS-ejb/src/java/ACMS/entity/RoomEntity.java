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
public class RoomEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int roomId;
    private double roomPrice;
    private String roomType;
    private String roomStatus;
    private int roomHotel;
    private int roomLevel;
    private int roomNo;
    private boolean hasBreakfast;

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }    

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomHotel, int roomLevel, int roomNo) {
        this.roomId = roomHotel*1000 + roomLevel*100 + roomNo;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public int getRoomHotel() {
        return roomHotel;
    }

    public void setRoomHotel(int roomHotel) {
        this.roomHotel = roomHotel;
    }

    public int getRoomLevel() {
        return roomLevel;
    }

    public void setRoomLevel(int roomLevel) {
        this.roomLevel = roomLevel;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "ACMS.RoomEntity[ id=" + roomId + " ]";
    }
    
}
