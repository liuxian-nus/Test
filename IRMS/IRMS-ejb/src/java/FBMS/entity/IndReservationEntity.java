/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diana Wang
 */
@Entity
public class IndReservationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IndReservationId;

    public Long getId() {
        return IndReservationId;
    }

    public void setId(Long id) {
        this.IndReservationId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IndReservationId != null ? IndReservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndReservationEntity)) {
            return false;
        }
        IndReservationEntity other = (IndReservationEntity) object;
        if ((this.IndReservationId == null && other.IndReservationId != null) || (this.IndReservationId != null && !this.IndReservationId.equals(other.IndReservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FBMS.entity.ReservationEntity[ id=" + IndReservationId + " ]";
    }
    
}
