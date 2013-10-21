/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Ser3na
 */
@Entity
public class ShowContractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showContractId;
    private String showMerchantName;
    private String showMerchantEmail;
    private String showMerchantContact;
    private String showMerchantAddress;
    private Double showVenueRate;
    private Double showTicketCommission;
    private int showVenueDuration;
    
    @OneToOne(cascade = {CascadeType.MERGE})
    private ShowEntity show;

    public Long getShowContractId() {
        return showContractId;
    }

    public void setShowContractId(Long showContractId) {
        this.showContractId = showContractId;
    }

    public String getShowMerchantName() {
        return showMerchantName;
    }

    public void setShowMerchantName(String showMerchantName) {
        this.showMerchantName = showMerchantName;
    }

    public String getShowMerchantEmail() {
        return showMerchantEmail;
    }

    public void setShowMerchantEmail(String showMerchantEmail) {
        this.showMerchantEmail = showMerchantEmail;
    }

    public String getShowMerchantContact() {
        return showMerchantContact;
    }

    public void setShowMerchantContact(String showMerchantContact) {
        this.showMerchantContact = showMerchantContact;
    }

    public String getShowMerchantAddress() {
        return showMerchantAddress;
    }

    public void setShowMerchantAddress(String showMerchantAddress) {
        this.showMerchantAddress = showMerchantAddress;
    }

    public Double getShowVenueRate() {
        return showVenueRate;
    }

    public void setShowVenueRate(Double showVenueRate) {
        this.showVenueRate = showVenueRate;
    }

    public Double getShowTicketCommission() {
        return showTicketCommission;
    }

    public void setShowTicketCommission(Double showTicketCommission) {
        this.showTicketCommission = showTicketCommission;
    }

    public int getShowVenueDuration() {
        return showVenueDuration;
    }

    public void setShowVenueDuration(int showVenueDuration) {
        this.showVenueDuration = showVenueDuration;
    }

    public ShowEntity getShow() {
        return show;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }
}
