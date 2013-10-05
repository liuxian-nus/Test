/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomServiceEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
public class ContractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contractId;
    private String contractPeriod;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date contractStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date contractEndDate;
    private double contractDownPayment;
    private double contractMonthRate;
    private double contractEarlyCharge;
    private double contractCommissionRate;
    private String contractStatus;
    private double contractDeposit;
    private int contractOutlet;
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "contract")
    private List<ContracteventEntity> contractEvent = new ArrayList<ContracteventEntity>();
    @ManyToOne(cascade={CascadeType.PERSIST})
    private MerchantEntity contractMerchant = new MerchantEntity();

    public int getContractOutlet() {
        return contractOutlet;
    }

    public void setContractOutlet(int contractOutlet) {
        this.contractOutlet = contractOutlet;
    }

    public double getContractDeposit() {
        return contractDeposit;
    }

    public void setContractDeposit(double contractDeposit) {
        this.contractDeposit = contractDeposit;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(String contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public double getContractDownPayment() {
        return contractDownPayment;
    }

    public void setContractDownPayment(double contractDownPayment) {
        this.contractDownPayment = contractDownPayment;
    }

    public double getContractMonthRate() {
        return contractMonthRate;
    }

    public void setContractMonthRate(double contractMonthRate) {
        this.contractMonthRate = contractMonthRate;
    }

    public double getContractEarlyCharge() {
        return contractEarlyCharge;
    }

    public void setContractEarlyCharge(double contractEarlyCharge) {
        this.contractEarlyCharge = contractEarlyCharge;
    }

    public double getContractCommissionRate() {
        return contractCommissionRate;
    }

    public void setContractCommissionRate(double contractCommissionRate) {
        this.contractCommissionRate = contractCommissionRate;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public List<ContracteventEntity> getContractEvent() {
        return contractEvent;
    }

    public void setContractEvent(List<ContracteventEntity> contractEvent) {
        this.contractEvent = contractEvent;
    }

    public MerchantEntity getContractMerchant() {
        return contractMerchant;
    }

    public void setContractMerchant(MerchantEntity contractMerchant) {
        this.contractMerchant = contractMerchant;
    }

    public Long getId() {
        return contractId;
    }

    public void setId(Long contractId) {
        this.contractId = contractId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractId != null ? contractId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractEntity)) {
            return false;
        }
        ContractEntity other = (ContractEntity) object;
        if ((this.contractId == null && other.contractId != null) || (this.contractId != null && !this.contractId.equals(other.contractId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SMMS.entity.ContractEntity[ id=" + contractId + " ]";
    }
}
