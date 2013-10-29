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
    private String attrId;
    private String attrName;
    @OneToOne (cascade ={CascadeType.ALL})  
    private QuotaEntity attrQuota=new QuotaEntity();
    
    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }


    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public QuotaEntity getAttrQuota() {
        return attrQuota;
    }

    public void setAttrQuota(QuotaEntity attrQuota) {
        this.attrQuota = attrQuota;
    } 

    
}
