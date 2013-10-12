/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Ser3na
 */
@Entity
public class ShowMerchantEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String showMerchantEmail;
    
    private String showMerchantName;
    private String showMerchantAddress;
    private String showMerchantContact;
    
    @OneToMany(targetEntity=ShowEntity.class, cascade = {CascadeType.MERGE})
    private List<ShowEntity> shows = new ArrayList<ShowEntity>();

    public String getShowMerchantEmail() {
        return showMerchantEmail;
    }

    public void setShowMerchantEmail(String showMerchantEmail) {
        this.showMerchantEmail = showMerchantEmail;
    }

    public String getShowMerchantName() {
        return showMerchantName;
    }

    public void setShowMerchantName(String showMerchantName) {
        this.showMerchantName = showMerchantName;
    }

    public String getShowMerchantAddress() {
        return showMerchantAddress;
    }

    public void setShowMerchantAddress(String showMerchantAddress) {
        this.showMerchantAddress = showMerchantAddress;
    }

    public String getShowMerchantContact() {
        return showMerchantContact;
    }

    public void setShowMerchantContact(String showMerchantContact) {
        this.showMerchantContact = showMerchantContact;
    }

    public List<ShowEntity> getShows() {
        return shows;
    }

    public void setShows(List<ShowEntity> shows) {
        this.shows = shows;
    }
}
