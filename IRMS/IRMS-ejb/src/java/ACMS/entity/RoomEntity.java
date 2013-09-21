/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    private String roomType;
    private String roomStatus = "available"; //reserved, occupied or available
    private int roomHotel;
    private int roomLevel;
    private int roomNo;
    private boolean hasBreakfast = false;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkInDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkOutDate;
    private double roomServiceCharge = 0;
    @ManyToMany(cascade={CascadeType.PERSIST})
    private Set<RoomServiceEntity> roomService = new HashSet<RoomServiceEntity> ();
    @ManyToOne(cascade={CascadeType.PERSIST})
    private PriceEntity roomPrice = new PriceEntity();
    private double overbookingLoss = 0;
   
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomHotel, int roomLevel, int roomNo) {
        this.roomId = roomHotel*1000 + roomLevel*100 + roomNo;
    }

    public PriceEntity getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(PriceEntity roomPrice) {
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
       System.out.println("RoomEntity-->new service added:" + newRoomService.getRoomServiceName());
    }

    public double getRoomServiceCharge() {
        return roomServiceCharge;
    }
    
    public void addRoomServiceCharge(double newRoomServiceCharge) {
        this.roomServiceCharge += newRoomServiceCharge;
        System.out.println("RoomEntity-->new account receivable generated:" + newRoomServiceCharge);
    }

    public double getOverbookingLoss() {
        return overbookingLoss;
    }

    public void setOverbookingLoss(double overbookingLoss) {
        this.overbookingLoss = overbookingLoss;
    }
    
    @Override
    public String toString() {
        return "ACMS.RoomEntity[ id=" + roomId + " ]";
    }
    
}
