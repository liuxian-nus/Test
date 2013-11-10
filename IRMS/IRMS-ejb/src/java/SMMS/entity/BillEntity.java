/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private Date billDate; // current date
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dueDate;
    private double billAmount;
    private String billType; //commission, month rate,early terminate,deposit
    private String billStatus; // unpaid, paid, overdue,available(to send out)
    @ManyToOne
    private ContractEntity contract = new ContractEntity();
    @OneToMany(mappedBy = "bill", cascade = (CascadeType.MERGE))
    private List<BillItemEntity> billItem = new ArrayList<BillItemEntity>();

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public List<BillItemEntity> getBillItem() {
        return billItem;
    }

    public void setBillItem(List<BillItemEntity> billItem) {
        this.billItem = billItem;
    }

    public void addBillItem(BillItemEntity billItem) {
        this.billItem.add(billItem);
        System.out.println("adding deltails to bill already" + billItem.getId());
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
