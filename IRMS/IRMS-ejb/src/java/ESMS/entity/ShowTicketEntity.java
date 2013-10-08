/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ser3na
 */
@Entity
public class ShowTicketEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showTicketId;
    private String showTicketType;
    private String showTicketPrice;
    private String showTicketQuantity;

    public Long getShowTicketId() {
        return showTicketId;
    }

    public void setShowTicketId(Long showTicketId) {
        this.showTicketId = showTicketId;
    }

    public String getShowTicketType() {
        return showTicketType;
    }

    public void setShowTicketType(String showTicketType) {
        this.showTicketType = showTicketType;
    }

    public String getShowTicketPrice() {
        return showTicketPrice;
    }

    public void setShowTicketPrice(String showTicketPrice) {
        this.showTicketPrice = showTicketPrice;
    }

    public String getShowTicketQuantity() {
        return showTicketQuantity;
    }

    public void setShowTicketQuantity(String showTicketQuantity) {
        this.showTicketQuantity = showTicketQuantity;
    }
    
    @Override
    public String toString() {
        return "ESMS.entity.ShowTicketEntity[ id=" + showTicketId + " ]";
    }
    
}
