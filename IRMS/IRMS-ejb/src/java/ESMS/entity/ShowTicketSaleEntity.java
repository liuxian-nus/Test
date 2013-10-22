/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import CRMS.entity.MemberEntity;
import java.io.Serializable;
import javax.persistence.CascadeType;
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
    @ManyToOne(cascade = {CascadeType.ALL})
    private ShowEntity show;
    
    //this is to make sure the member with this ticket is already entered, 防假票！！
    private boolean showTicketStatus;
    private int showTicketQuantity;
    
    
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
}
