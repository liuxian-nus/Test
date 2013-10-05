/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Cookie
 */
@Entity
public class PushingcartEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pushingcartId;
    private String pushingcartType;
    private int pushingcartLevel;
    private String pushingcartArea;
    private int pushingcartInventory;

    public Long getPushingcartId() {
        return pushingcartId;
    }

    public void setPushingcartId(Long pushingcartId) {
        this.pushingcartId = pushingcartId;
    }

    public String getPushingcartType() {
        return pushingcartType;
    }

    public void setPushingcartType(String pushingcartType) {
        this.pushingcartType = pushingcartType;
    }

    public int getPushingcartLevel() {
        return pushingcartLevel;
    }

    public void setPushingcartLevel(int pushingcartLevel) {
        this.pushingcartLevel = pushingcartLevel;
    }

    public String getPushingcartArea() {
        return pushingcartArea;
    }

    public void setPushingcartArea(String pushingcartArea) {
        this.pushingcartArea = pushingcartArea;
    }

    public int getPushingcartInventory() {
        return pushingcartInventory;
    }

    public void setPushingcartInventory(int pushingcartInventory) {
        this.pushingcartInventory = pushingcartInventory;
    }

    public Long getId() {
        return pushingcartId;
    }

    public void setId(Long pushingcartId) {
        this.pushingcartId = pushingcartId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pushingcartId != null ? pushingcartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PushingcartEntity)) {
            return false;
        }
        PushingcartEntity other = (PushingcartEntity) object;
        if ((this.pushingcartId == null && other.pushingcartId != null) || (this.pushingcartId != null && !this.pushingcartId.equals(other.pushingcartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SMMS.entity.PushingcartEntity[ id=" + pushingcartId + " ]";
    }
}
