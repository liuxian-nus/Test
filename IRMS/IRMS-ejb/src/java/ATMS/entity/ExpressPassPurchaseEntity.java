/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.entity;

import CRMS.entity.MemberEntity;
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
 * @author Jieqiong
 */
@Entity
public class ExpressPassPurchaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eppId;
    @OneToMany(cascade ={CascadeType.MERGE})
    private List<AttrExpressPassEntity> attrEPs=new ArrayList<AttrExpressPassEntity>();
    @ManyToOne
    private MemberEntity member;
    private List<Integer> epQuantities=new ArrayList<Integer>();
    private double epFee;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date epBookDate =new Date();
    private String eppStatus;

    public Long getEppId() {
        return eppId;
    }

    public void setEppId(Long eppId) {
        this.eppId = eppId;
    }

    public List<AttrExpressPassEntity> getAttrEPs() {
        return attrEPs;
    }

    public void setAttrEPs(List<AttrExpressPassEntity> attrEPs) {
        this.attrEPs = attrEPs;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public List<Integer> getEpQuantities() {
        return epQuantities;
    }

    public void setEpQuantities(List<Integer> epQuantities) {
        this.epQuantities = epQuantities;
    }

    public double getEpFee() {
        return epFee;
    }

    public void setEpFee(double epFee) {
        this.epFee = epFee;
    }

    public Date getEpBookDate() {
        return epBookDate;
    }

    public void setEpBookDate(Date epBookDate) {
        this.epBookDate = epBookDate;
    }

    public String getEppStatus() {
        return eppStatus;
    }

    public void setEppStatus(String eppStatus) {
        this.eppStatus = eppStatus;
    }
  
    
}
