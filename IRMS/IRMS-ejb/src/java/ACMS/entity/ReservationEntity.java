/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import CRMS.entity.MemberEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author liuxian
 */
@Entity
public class ReservationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    private String rcName; //reservation customer name
    private String rcEmail;
    private String rcHP;
    private String rcCreditCardNo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rcCheckInDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rcCheckOutDate;
    private int reservationHotelNo;
    private int reservationRoomCount;
    private int reservationGuestCount;
    private String reservationRoomType;
    private String reservationCorporate;
    private double reservationTotal;
    private String reservationStatus;//confirmed, guaranteed, cancelled, outdated, checkedIn
    //to be continued;
     @ManyToOne(cascade={CascadeType.MERGE})
     private MemberEntity rcMember;

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getRcName() {
        return rcName;
    }

    public void setRcName(String rcName) {
        this.rcName = rcName;
    }

    public String getRcEmail() {
        return rcEmail;
    }

    public void setRcEmail(String rcEmail) {
        this.rcEmail = rcEmail;
    }

    public String getRcHP() {
        return rcHP;
    }

    public void setRcHP(String rcHP) {
        this.rcHP = rcHP;
    }

    public String getRcCreditCardNo() {
        return rcCreditCardNo;
    }

    public void setRcCreditCardNo(String rcCreditCardNo) {
        this.rcCreditCardNo = rcCreditCardNo;
    }

    public Date getRcCheckInDate() {
        return rcCheckInDate;
    }

    public void setRcCheckInDate(Date rcCheckInDate) {
        this.rcCheckInDate = rcCheckInDate;
    }

    public Date getRcCheckOutDate() {
        return rcCheckOutDate;
    }

    public void setRcCheckOutDate(Date rcCheckOutDate) {
        this.rcCheckOutDate = rcCheckOutDate;
    }

    public int getReservationHotelNo() {
        return reservationHotelNo;
    }

    public void setReservationHotelNo(int reservationHotelNo) {
        this.reservationHotelNo = reservationHotelNo;
    }

    public int getReservationRoomCount() {
        return reservationRoomCount;
    }

    public void setReservationRoomCount(int reservationRoomCount) {
        this.reservationRoomCount = reservationRoomCount;
    }

    public int getReservationGuestCount() {
        return reservationGuestCount;
    }

    public void setReservationGuestCount(int reservationGuestCount) {
        this.reservationGuestCount = reservationGuestCount;
    }

    public MemberEntity getRcMember() {
        return rcMember;
    }

    public void setRcMember(MemberEntity rcMember) {
        this.rcMember = rcMember;
    }

    public String getReservationCorporate() {
        return reservationCorporate;
    }

    public void setReservationCorporate(String reservationCorporate) {
        this.reservationCorporate = reservationCorporate;
    }

    public double getReservationTotal() {
        return reservationTotal;
    }

    public void setReservationTotal(double reservationTotal) {
        this.reservationTotal = reservationTotal;
    }

    public String getReservationRoomType() {
        return reservationRoomType;
    }

    public void setReservationRoomType(String reservationRoomType) {
        this.reservationRoomType = reservationRoomType;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the reservationId fields are not set
        if (!(object instanceof ReservationEntity)) {
            return false;
        }
        ReservationEntity other = (ReservationEntity) object;
        if ((this.reservationId == null && other.reservationId != null) || (this.reservationId != null && !this.reservationId.equals(other.reservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ACMS.entity.ReservationEntity[ reservationId=" + reservationId + " ]";
    }

}
