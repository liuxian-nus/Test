/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diana Wang
 */
@Entity
public class EquipmentEntity extends ServiceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long equipmentId;
    private Integer equipQuota;

    public Integer getEquipQuota() {
        return equipQuota;
    }

    public void setEquipQuota(Integer equipQuota) {
        this.equipQuota = equipQuota;
    }
    

    @Override
    public Long getId() {
        return equipmentId;
    }

    @Override
    public void setId(Long id) {
        this.equipmentId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipmentId != null ? equipmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipmentEntity)) {
            return false;
        }
        EquipmentEntity other = (EquipmentEntity) object;
        if ((this.equipmentId == null && other.equipmentId != null) || (this.equipmentId != null && !this.equipmentId.equals(other.equipmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CEMS.entity.EquipmentEntity[ id=" + equipmentId + " ]";
    }
    
}
