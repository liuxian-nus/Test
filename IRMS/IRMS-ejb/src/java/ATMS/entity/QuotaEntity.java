/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import ATMS.entity.TicketEntity;

/**
 *
 * @author Jieqiong
 */
@Entity
public class QuotaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int maxQuota;
    private int restQuota;
    
    public QuotaEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaxQuota() {
        return maxQuota;
    }

   
    public void setMaxQuota(int maxQuota) {
        this.maxQuota = maxQuota;
    }
    
     public int getRestQuota() {
        return restQuota;
    }

    public void setRestQuota(int restQuota) {
        this.restQuota = restQuota;
    }
    
    

    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuotaEntity)) {
            return false;
        }
        QuotaEntity other = (QuotaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ATMS.entity.QuotaEntity[ id=" + id + " ]";
    }
    
}
