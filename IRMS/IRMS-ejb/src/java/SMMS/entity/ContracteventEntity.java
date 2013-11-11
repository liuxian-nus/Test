/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
public class ContracteventEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contracteventId;
    private String eventPeriod;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date eventStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date eventEndDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date eventTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date eventDownDate;//bu yao le
    private double eventDownPayment;//bu yao le
    private double eventMonthRate;
    private double eventEarlyCharge;
    private double eventCommissionRate;
    private String eventStatus;
    private double eventDeposit;
    @ManyToOne(cascade={CascadeType.MERGE}) ///not necessarily need this lah
    private ContractEntity contract = new ContractEntity();


    public Date getEventDownDate() {
        return eventDownDate;
    }

    public void setEventDownDate(Date eventDownDate) {
        this.eventDownDate = eventDownDate;
    }
    
    public double getEventDeposit() {
        return eventDeposit;
    }

    public void setEventDeposit(double eventDeposit) {
        this.eventDeposit = eventDeposit;
    }

    public ContractEntity getEventContract() {
        return contract;
    }

    public void setEventContract(ContractEntity eventContract) {
        this.contract = eventContract;
    }

    public Long getContracteventId() {
        return contracteventId;
    }

    public void setContracteventId(Long contracteventId) {
        this.contracteventId = contracteventId;
    }

    public String getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public double getEventDownPayment() {
        return eventDownPayment;
    }

    public void setEventDownPayment(double eventDownPayment) {
        this.eventDownPayment = eventDownPayment;
    }

    public double getEventMonthRate() {
        return eventMonthRate;
    }

    public void setEventMonthRate(double eventMonthRate) {
        this.eventMonthRate = eventMonthRate;
    }

    public double getEventEarlyCharge() {
        return eventEarlyCharge;
    }

    public void setEventEarlyCharge(double eventEarlyCharge) {
        this.eventEarlyCharge = eventEarlyCharge;
    }

    public double getEventCommissionRate() {
        return eventCommissionRate;
    }

    public void setEventCommissionRate(double eventCommissionRate) {
        this.eventCommissionRate = eventCommissionRate;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Long getId() {
        return contracteventId;
    }

    public void setId(Long contracteventId) {
        this.contracteventId = contracteventId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contracteventId != null ? contracteventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContracteventEntity)) {
            return false;
        }
        ContracteventEntity other = (ContracteventEntity) object;
        if ((this.contracteventId == null && other.contracteventId != null) || (this.contracteventId != null && !this.contracteventId.equals(other.contracteventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SMMS.entity.ContractstatusEntity[ id=" + contracteventId + " ]";
    }
}
