/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author liuxian
 */
@Entity
public class OverbookingQuotaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long overbookingId;
    private int quota;

    public int getQuota() {
        return quota;
    }

    public void setQuota( int quota) {
        this.quota = quota;
    }

    
    public Long getOverbookingId() {
        return overbookingId;
    }

    public void setOverbookingId(Long overbookingId) {
        this.overbookingId = overbookingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (overbookingId != null ? overbookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OverbookingQuotaEntity)) {
            return false;
        }
        OverbookingQuotaEntity other = (OverbookingQuotaEntity) object;
        if ((this.overbookingId == null && other.overbookingId != null) || (this.overbookingId != null && !this.overbookingId.equals(other.overbookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ACMS.entity.OverbookingQuotaEntity[ id=" + overbookingId + " ]";
    }
    
}
