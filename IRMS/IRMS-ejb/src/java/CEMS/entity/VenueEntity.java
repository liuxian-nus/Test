/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lionetdd
 */
@Entity
public class VenueEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long venueId;
    private String venueName;
   
    private int venueCapacity;
    private List<String> venueFunction = new ArrayList<String>();

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getVenueCapacity() {
        return venueCapacity;
    }

    public void setVenueCapacity(int venueCapacity) {
        this.venueCapacity = venueCapacity;
    }

    public List<String> getVenueFunction() {
        return venueFunction;
    }

    public void setVenueFunction(List<String> venueFunction) {
        this.venueFunction = venueFunction;
    }
   
    
    

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long id) {
        this.venueId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (venueId != null ? venueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenueEntity)) {
            return false;
        }
        VenueEntity other = (VenueEntity) object;
        if ((this.venueId == null && other.venueId != null) || (this.venueId != null && !this.venueId.equals(other.venueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CEMS.entity.VenueEntity[ id=" + venueId + " ]";
    }
    
}
