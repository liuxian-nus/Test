/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import Exception.ExistException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

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
    private String roomStatus; //reserved, occupied or available
    private int roomHotel;
    private int roomLevel;
    private int roomNo;
    private boolean hasBreakfast;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkInDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkOutDate;
    @ManyToMany(cascade={CascadeType.PERSIST})
    public Set<RoomServiceEntity> roomService = new HashSet<RoomServiceEntity> ();

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
    
    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }    
    
    public Set<RoomServiceEntity> getRoomService(){
        return roomService;
    }
    
    public void setRoomService(Set<RoomServiceEntity> roomService){
        this.roomService = roomService;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void addRoomService(RoomServiceEntity newRoomService){
       this.roomService.add(newRoomService);
       System.out.println("new service added:" + newRoomService.getRoomServiceName());
       System.out.println("new account receivable" + newRoomService.getRoomServicePrice());
    }
    
    @Override
    public String toString() {
        return "ACMS.RoomEntity[ id=" + roomId + " ]";
    }
    
}
