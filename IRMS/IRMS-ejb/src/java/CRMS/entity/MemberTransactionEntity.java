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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author liuxian
 */
@Entity
@XmlRootElement
@XmlType(name="memberTransactionEntity")
public class MemberTransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mtId;//memberTransaction Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mtDate;
    private double mtAmount;
    private String mtDepartment; //6 AAUs
    private boolean mtMode; //either payment by cash or by card, false is cash, true is card
    private boolean paymentStatus;
    private String mtPromotion;
    private String memberEmail;
    private String mtDescription;
    

    public String getMtPromotion() {
        return mtPromotion;
    }

    public void setMtPromotion(String mtPromotion) {
        this.mtPromotion = mtPromotion;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }
   
     
    public MemberTransactionEntity(){
        setMtId (System.nanoTime());
    }
    
   /* public void create(Date mtDate,double mtAmount,String mtDepartment,boolean mtMode) {
        this.setMtDate(mtDate);
        this.setMtAmount(mtAmount);
        this.setMtDepartment(mtDepartment);
        this.setMtMode(mtMode);
    }*/

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

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getMtDescription() {
        return mtDescription;
    }

    public void setMtDescription(String mtDescription) {
        this.mtDescription = mtDescription;
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
