/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Jieqiong
 */
@Entity
public class AttractionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String attrName;
    @OneToOne (cascade ={CascadeType.ALL})  
    private QuotaEntity quota=new QuotaEntity();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public QuotaEntity getQuota() {
        return quota;
    }

    public void setQuota(QuotaEntity quota) {
        this.quota = quota;
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
        if (!(object instanceof AttractionEntity)) {
            return false;
        }
        AttractionEntity other = (AttractionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ATMS.entity.AttractionEntity[ id=" + id + " ]";
    }
    
}
