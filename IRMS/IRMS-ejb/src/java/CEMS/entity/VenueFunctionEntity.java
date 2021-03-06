/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Diana Wang
 */
@Entity
public class VenueFunctionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long venueFunctionId;
    
    private String functionName;
    @ManyToMany(mappedBy = "venueFunction")
    private List<VenueEntity> venueEntitys;
    
    public VenueFunctionEntity(){}

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    
    public Long getId() {
        return venueFunctionId;
    }

    public void setId(Long id) {
        this.venueFunctionId = id;
    }

    public Long getVenueFunctionId() {
        return venueFunctionId;
    }

    public void setVenueFunctionId(Long venueFunctionId) {
        this.venueFunctionId = venueFunctionId;
    }

    public List<VenueEntity> getVenueEntitys() {
        return venueEntitys;
    }

    public void setVenueEntitys(List<VenueEntity> venueEntitys) {
        this.venueEntitys = venueEntitys;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (venueFunctionId != null ? venueFunctionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenueFunctionEntity)) {
            return false;
        }
        VenueFunctionEntity other = (VenueFunctionEntity) object;
        if ((this.venueFunctionId == null && other.venueFunctionId != null) || (this.venueFunctionId != null && !this.venueFunctionId.equals(other.venueFunctionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CEMS.entity.VenueFunctionEntity[ id=" + venueFunctionId + " ]";
    }
    
}
