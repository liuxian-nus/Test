/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Cookie
 */
@Entity
public class BillEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date billDate;
    private double billAmount;
    private String billType; //commission, month rate,early terminate,deposit
    private String billMerchant;
    private String billStatus;
    @ManyToOne
    private OutletEntity billOutlet = new OutletEntity();

    public String getBillMerchant() {
        return billMerchant;
    }

    public void setBillMerchant(String billMerchant) {
        this.billMerchant = billMerchant;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public OutletEntity getBillOutlet() {
        return billOutlet;
    }

    public void setBillOutlet(OutletEntity billOutlet) {
        this.billOutlet = billOutlet;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public Long getId() {
        return billId;
    }

    public void setId(Long billId) {
        this.billId = billId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billId != null ? billId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillEntity)) {
            return false;
        }
        BillEntity other = (BillEntity) object;
        if ((this.billId == null && other.billId != null) || (this.billId != null && !this.billId.equals(other.billId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SMMS.entity.BillEntity[ id=" + billId + " ]";
    }
}
