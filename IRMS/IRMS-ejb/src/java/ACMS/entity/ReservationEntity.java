/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author liudazhi
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
    //to be continued;

    public Long getId() {
        return reservationId;
    }

    public void setId(Long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
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
