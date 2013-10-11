/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import CRMS.entity.MemberEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ser3na
 */
@Entity
public class ShowTicketSaleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showTicketSaleId;
    @ManyToOne(targetEntity = ShowEntity.class)
    private ShowEntity show;
    @ManyToOne(targetEntity = ShowScheduleEntity.class)
    private ShowScheduleEntity showSchedule;
    @ManyToOne(targetEntity = ShowTicketEntity.class)
    private ShowTicketEntity showTicket;
    @ManyToOne(targetEntity = MemberEntity.class)
    private MemberEntity member;
    
    private int quantity;
    //this is to make sure the member with this ticket is already entered, 防假票！！
    private boolean status;
    
    public Long getShowTicketSaleId() {
        return showTicketSaleId;
    }

    public void setShowTicketSaleId(Long showTicketSaleId) {
        this.showTicketSaleId = showTicketSaleId;
    }

    public ShowEntity getShow() {
        return show;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }

    public ShowScheduleEntity getShowSchedule() {
        return showSchedule;
    }

    public void setShowSchedule(ShowScheduleEntity showSchedule) {
        this.showSchedule = showSchedule;
    }

    public ShowTicketEntity getShowTicket() {
        return showTicket;
    }

    public void setShowTicket(ShowTicketEntity showTicket) {
        this.showTicket = showTicket;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
