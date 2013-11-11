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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Ser3na
 */
@Entity
@Table(
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"showName"}))
public class ShowEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showId;
    private String showName;
    private String showDescription;
    private String showType;
    private byte[] image;
    private String imagePath;
    private boolean showStatus;
    private boolean showPaymentStatus;
    private String showLanguage;
    private int showDuration;   //actually show length
    @OneToMany(targetEntity = ShowScheduleEntity.class, cascade = {CascadeType.MERGE})
    private List<ShowScheduleEntity> showSchedules = new ArrayList<ShowScheduleEntity>();
    @OneToOne(cascade = {CascadeType.MERGE})
    private ShowContractEntity showContract;
    @OneToMany(targetEntity = ShowTicketSaleEntity.class, cascade = {CascadeType.MERGE})
    private List<ShowTicketSaleEntity> showTicketSale = new ArrayList<ShowTicketSaleEntity>();;

    public List<ShowScheduleEntity> getShowSchedules() {
        return showSchedules;
    }

    public void setShowSchedules(List<ShowScheduleEntity> showSchedules) {
        this.showSchedules = showSchedules;
    }

    public void addShowSchedule(ShowScheduleEntity showSchedule) {
        this.showSchedules.add(showSchedule);
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(String showDescription) {
        this.showDescription = showDescription;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ShowContractEntity getShowContract() {
        return showContract;
    }

    public void setShowContract(ShowContractEntity showContract) {
        this.showContract = showContract;
    }

    public List<ShowTicketSaleEntity> getShowTicketSale() {
        return showTicketSale;
    }

    public void setShowTicketSale(List<ShowTicketSaleEntity> showTicketSale) {
        this.showTicketSale = showTicketSale;
    }

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isShowPaymentStatus() {
        return showPaymentStatus;
    }

    public void setShowPaymentStatus(boolean showPaymentStatus) {
        this.showPaymentStatus = showPaymentStatus;
    }

    public String getShowLanguage() {
        return showLanguage;
    }

    public void setShowLanguage(String showLanguage) {
        this.showLanguage = showLanguage;
    }

    public int getShowDuration() {
        return showDuration;
    }

    public void setShowDuration(int showDuration) {
        this.showDuration = showDuration;
    }
}
