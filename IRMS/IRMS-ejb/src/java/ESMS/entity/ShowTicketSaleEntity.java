/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

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
 * @author Ser3na
 */
@Entity
public class ShowTicketSaleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showTicketSaleId;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private ShowEntity show;
    
    //this is to make sure the member with this ticket is already entered, 防假票！！
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date showStartDateTime;
    private String showTicketType;
    private boolean showTicketStatus;
    private int showTicketQuantity;
    private double showTicketPrice;
    private String memberEmail;

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }
    
    
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
    
    public boolean isShowTicketStatus() {
        return showTicketStatus;
    }

    public void setShowTicketStatus(boolean showTicketStatus) {
        this.showTicketStatus = showTicketStatus;
    }

    public int getShowTicketQuantity() {
        return showTicketQuantity;
    }

    public void setShowTicketQuantity(int showTicketQuantity) {
        this.showTicketQuantity = showTicketQuantity;
    }

    public Date getShowStartDateTime() {
        return showStartDateTime;
    }

    public void setShowStartDateTime(Date showStartDateTime) {
        this.showStartDateTime = showStartDateTime;
    }

    public String getShowTicketType() {
        return showTicketType;
    }

    public void setShowTicketType(String showTicketType) {
        this.showTicketType = showTicketType;
    }

    public double getShowTicketPrice() {
        return showTicketPrice;
    }

    public void setShowTicketPrice(double showTicketPrice) {
        this.showTicketPrice = showTicketPrice;
    }
}
