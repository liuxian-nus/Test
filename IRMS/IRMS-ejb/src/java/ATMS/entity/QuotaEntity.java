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
    private Long quotaId;
    private int maxQuota;
    private int restQuota;
    
    public QuotaEntity(){
    }

    public Long getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
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
    
}
