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
    private int overbookingId = 1;
    private int quota;

    public int getQuota() {
        return quota;
    }

    public void setQuota( int quota) {
        this.quota = quota;
    }

    
    public int getOverbookingId() {
        return overbookingId;
    }

    public void setOverbookingId(int overbookingId) {
        this.overbookingId = overbookingId;
    }


    @Override
    public String toString() {
        return "ACMS.entity.OverbookingQuotaEntity[ id=" + overbookingId + " ]";
    }
    
}
