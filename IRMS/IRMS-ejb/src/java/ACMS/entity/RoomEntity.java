/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import CRMS.entity.MemberEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    private String guestName;
    private String roomCreditCardNo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkInDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkOutDate;
    private double roomServiceCharge = 0;
    private String roomCorporate;
    
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<RoomServiceExeEntity> roomServiceExe = new ArrayList<RoomServiceExeEntity> ();
    @ManyToOne(cascade={CascadeType.PERSIST})
    private RoomPriceEntity roomPrice = new RoomPriceEntity();
    @ManyToOne(cascade={CascadeType.PERSIST})
    private ReservationEntity reservation = new ReservationEntity();
    @OneToOne(cascade={CascadeType.PERSIST})
    private MemberEntity roomMember = new MemberEntity();
    
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomHotel, int roomLevel, int roomNo) {
        this.roomId = roomHotel*1000 + roomLevel*100 + roomNo;
    }

    public RoomPriceEntity getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(RoomPriceEntity roomPrice) {
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

    public List<RoomServiceExeEntity> getRoomServiceExe() {
        return roomServiceExe;
    }

    public void setRoomServiceExe(List<RoomServiceExeEntity> roomServiceExe) {
        this.roomServiceExe = roomServiceExe;
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

    public void addRoomService(RoomServiceExeEntity newRoomServiceExe){       
       this.roomServiceExe.add(newRoomServiceExe);
       System.out.println("RoomEntity-->new service added:" + newRoomServiceExe.getRoomService().getRoomServiceName());
    }

    public double getRoomServiceCharge() {
        return roomServiceCharge;
    }
    
    public void addRoomServiceCharge(double newRoomServiceCharge) {
        this.roomServiceCharge += newRoomServiceCharge;
        System.out.println("RoomEntity-->new account receivable generated:" + newRoomServiceCharge);
    }
    
    public void setRoomServiceCharge(double setCharge) {
        this.roomServiceCharge = setCharge;
        System.out.println("RoomEntity-->update serviceCharge: " + setCharge);
    }

    public String getRoomCreditCardNo() {
        return roomCreditCardNo;
    }

    public void setRoomCreditCardNo(String roomCreditCardNo) {
        this.roomCreditCardNo = roomCreditCardNo;
    }

    public String getRoomCorporate() {
        return roomCorporate;
    }

    public void setRoomCorporate(String roomCorporate) {
        this.roomCorporate = roomCorporate;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
    }

    public MemberEntity getRoomMember() {
        return roomMember;
    }

    public void setRoomMember(MemberEntity roomMember) {
        this.roomMember = roomMember;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    @Override
    public String toString() {
        return "ACMS.RoomEntity[ id=" + roomId + " ]";
    }
    
}
