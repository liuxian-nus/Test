/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.entity;

import CRMS.entity.MemberEntity;
import java.io.Serializable;
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
public class TicketPurchaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tpId;
    @OneToMany(cascade ={CascadeType.PERSIST})
    private List<AttrTicketEntity> attrTickets;
    @ManyToOne
    private MemberEntity member;
    private List<Integer> attrTicketQuantities;
    private double attrTicketFee;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date attrTicketBookDate =new Date();
    private String attrTPStatus;

    public TicketPurchaseEntity() {
        attrTPStatus = "in process";
    }
    

    public Long getTpId() {
        return tpId;
    }

    public void setTpId(Long tpId) {
        this.tpId = tpId;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public List<AttrTicketEntity> getAttrTickets() {
        return attrTickets;
    }

    public void setAttrTickets(List<AttrTicketEntity> attrTickets) {
        this.attrTickets = attrTickets;
    }

    public List<Integer> getAttrTicketQuantities() {
        return attrTicketQuantities;
    }

    public void setAttrTicketQuantities(List<Integer> attrTicketQuantities) {
        this.attrTicketQuantities = attrTicketQuantities;
    }

    public double getAttrTicketFee() {
        return attrTicketFee;
    }

    public void setAttrTicketFee(double attrTicketFee) {
        this.attrTicketFee = attrTicketFee;
    }

    public Date getAttrTicketBookDate() {
        return attrTicketBookDate;
    }

    public void setAttrTicketBookDate(Date attrTicketBookDate) {
        this.attrTicketBookDate = attrTicketBookDate;
    }

    public String getAttrTPStatus() {
        return attrTPStatus;
    }

    public void setAttrTPStatus(String attrTPStatus) {
        this.attrTPStatus = attrTPStatus;
    }
      
}
