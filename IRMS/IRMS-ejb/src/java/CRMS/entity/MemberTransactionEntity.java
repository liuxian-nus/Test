/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Temporal;

/**
 *
 * @author liuxian
 */
@Entity
public class MemberTransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mtId;//memberTransaction Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mtDate;
    private double mtAmount;
    private String mtDepartment; //6 AAUs
    private boolean mtMode; //either payment by cash or by card, false is cash, true is card
    
    public MemberTransactionEntity(){
        setMtId (System.nanoTime());
    }
    
    public void create(Date mtDate,double mtAmount,String mtDepartment,boolean mtMode) {
        this.setMtDate(mtDate);
        this.setMtAmount(mtAmount);
        this.setMtDepartment(mtDepartment);
        this.setMtMode(mtMode);
    }

    public Long getMtId() {
        return mtId;
    }

    public void setMtId(Long mtId) {
        this.mtId = mtId;
    }

    public Date getMtDate() {
        return mtDate;
    }

    public void setMtDate(Date mtDate) {
        this.mtDate = mtDate;
    }

    public double getMtAmount() {
        return mtAmount;
    }

    public void setMtAmount(double mtAmount) {
        this.mtAmount = mtAmount;
    }

    public String getMtDepartment() {
        return mtDepartment;
    }

    public void setMtDepartment(String mtDepartment) {
        this.mtDepartment = mtDepartment;
    }

    public boolean isMtMode() {
        return mtMode;
    }

    public void setMtMode(boolean mtMode) {
        this.mtMode = mtMode;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mtId != null ? mtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberTransactionEntity)) {
            return false;
        }
        MemberTransactionEntity other = (MemberTransactionEntity) object;
        if ((this.mtId == null && other.mtId != null) || (this.mtId != null && !this.mtId.equals(other.mtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.entity.MemberTransactionEntity[ id=" + mtId + " ]";
    }
    
}
