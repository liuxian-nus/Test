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
 * @author Jieqiong
 */
@Entity
public class CouponTypeEntity implements Serializable {
    /*This should be static entity created by manager
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long couponTypeId;
    private String couponName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date cpStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date cpEndDate; //we can also not set date. assume the coupon is always valid
    private String couponTypeRemarks;
    private double discount; //less than one, for example, 0.9

    public Long getCouponTypeId() {
        return couponTypeId;
    }

    public void setCouponTypeId(Long couponTypeId) {
        this.couponTypeId = couponTypeId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
    
    

    public Date getCpStartDate() {
        return cpStartDate;
    }

    public void setCpStartDate(Date cpStartDate) {
        this.cpStartDate = cpStartDate;
    }

    public Date getCpEndDate() {
        return cpEndDate;
    }

    public void setCpEndDate(Date cpEndDate) {
        this.cpEndDate = cpEndDate;
    }

    public String getCouponTypeRemarks() {
        return couponTypeRemarks;
    }

    public void setCouponTypeRemarks(String couponTypeRemarks) {
        this.couponTypeRemarks = couponTypeRemarks;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (couponTypeId != null ? couponTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CouponTypeEntity)) {
            return false;
        }
        CouponTypeEntity other = (CouponTypeEntity) object;
        if ((this.couponTypeId == null && other.couponTypeId != null) || (this.couponTypeId != null && !this.couponTypeId.equals(other.couponTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.entity.CouponTypeEntity[ id=" + couponTypeId + " ]";
    }
    
}
