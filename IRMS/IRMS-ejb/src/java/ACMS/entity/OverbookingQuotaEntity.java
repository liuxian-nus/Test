/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author liuxian
 */
@Entity
public class OverbookingQuotaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int overbookingId = 1;
    private String roomType;
    private double compensation1;
    private double compensation2;
    private int quota;
    private int suggestedQuota;

    public int getQuota() {
        return quota;
    }

    public void setQuota( int quota) {
        this.quota = quota;
    }

    
    public int getOverbookingId() {
        return overbookingId;
    }

    public void setOverbookingId(int overbookingId) {
        this.overbookingId = overbookingId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getCompensation1() {
        return compensation1;
    }

    public void setCompensation1(double compensation1) {
        this.compensation1 = compensation1;
    }

    public double getCompensation2() {
        return compensation2;
    }

    public void setCompensation2(double compensation2) {
        this.compensation2 = compensation2;
    }

    public int getSuggestedQuota() {
        return suggestedQuota;
    }

    public void setSuggestedQuota(int suggestedQuota) {
        this.suggestedQuota = suggestedQuota;
    }

    @Override
    public String toString() {
        return "ACMS.entity.OverbookingQuotaEntity[ id=" + overbookingId + " ]";
    }
    
}
