/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Diana Wang
 */
@Entity
public class PaypalTransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//paypaltransactionentity id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ptDate;
    private double ptAmount;
    private String ptDepartment;//6 AAUs
    private boolean ptMode; //either payment by cash or by card, false is cash, true is card
    private boolean paymentStatus;//paid or not

    public Date getPtDate() {
        return ptDate;
    }

    public void setPtDate(Date ptDate) {
        this.ptDate = ptDate;
    }

    public double getPtAmount() {
        return ptAmount;
    }

    public void setPtAmount(double ptAmount) {
        this.ptAmount = ptAmount;
    }

    public String getPtDepartment() {
        return ptDepartment;
    }

    public void setPtDepartment(String ptDepartment) {
        this.ptDepartment = ptDepartment;
    }

    public boolean isPtMode() {
        return ptMode;
    }

    public void setPtMode(boolean ptMode) {
        this.ptMode = ptMode;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof PaypalTransactionEntity)) {
            return false;
        }
        PaypalTransactionEntity other = (PaypalTransactionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.entity.PaypalTransactionEntity[ id=" + id + " ]";
    }
    
}
