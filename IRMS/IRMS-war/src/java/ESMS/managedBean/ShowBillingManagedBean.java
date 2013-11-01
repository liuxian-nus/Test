/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import ESMS.entity.ShowContractEntity;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowTicketSaleEntity;
import ESMS.session.ShowBillingSessionBean;
import ESMS.session.ShowContractSessionBean;
import ESMS.session.ShowSessionBean;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@RequestScoped
public class ShowBillingManagedBean {

    @EJB
    ShowBillingSessionBean showBillingSessionBean;
    @EJB
    ShowContractSessionBean showContractSessionBean;
    @EJB
    ShowSessionBean showSessionBean;
    private ShowEntity selectedShow;
    private ShowTicketSaleEntity selectedShowTicketSale;
    private ShowContractEntity selectedShowContract;
    private List<ShowEntity> shows;
    private List<ShowTicketSaleEntity> showTicketSales;
    private Long showId;
    private Double ticketRevenue=0.0;
    private Double ticketCommission;
    private Double bill;
    private Double rentalFee;

    //Constructor
    public ShowBillingManagedBean() {
        selectedShow = new ShowEntity();
        selectedShowTicketSale = new ShowTicketSaleEntity();
        selectedShowContract = new ShowContractEntity();
    }

    //Method
    public void showBill(ActionEvent event) {
        selectedShow = showSessionBean.getShowById(showId);
        System.err.println(selectedShow.getShowName());
        showTicketSales = showBillingSessionBean.getAllSelectedShowTicketSales(showId);
        Iterator<ShowTicketSaleEntity> itr = showTicketSales.iterator();
        while (itr.hasNext()) {
            selectedShowTicketSale = itr.next();
            ticketRevenue+=selectedShowTicketSale.getShowTicketPrice()*selectedShowTicketSale.getShowTicketQuantity();
        }
        ticketCommission = ticketRevenue * selectedShow.getShowContract().getShowTicketCommission();
        rentalFee = selectedShow.getShowContract().getShowVenueDuration() * selectedShow.getShowContract().getShowVenueRate();
        bill = ticketCommission+selectedShow.getShowContract().getShowDeposit()-rentalFee;
    }

    // Getters and Setters
    public ShowBillingSessionBean getShowBillingSessionBean() {
        return showBillingSessionBean;
    }

    public void setShowBillingSessionBean(ShowBillingSessionBean showBillingSessionBean) {
        this.showBillingSessionBean = showBillingSessionBean;
    }

    public ShowEntity getSelectedShow() {
        return selectedShow;
    }

    public void setSelectedShow(ShowEntity selectedShow) {
        this.selectedShow = selectedShow;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public ShowContractSessionBean getShowContractSessionBean() {
        return showContractSessionBean;
    }

    public void setShowContractSessionBean(ShowContractSessionBean showContractSessionBean) {
        this.showContractSessionBean = showContractSessionBean;
    }

    public ShowSessionBean getShowSessionBean() {
        return showSessionBean;
    }

    public void setShowSessionBean(ShowSessionBean showSessionBean) {
        this.showSessionBean = showSessionBean;
    }

    public ShowTicketSaleEntity getSelectedShowTicketSale() {
        return selectedShowTicketSale;
    }

    public void setSelectedShowTicketSale(ShowTicketSaleEntity selectedShowTicketSale) {
        this.selectedShowTicketSale = selectedShowTicketSale;
    }

    public ShowContractEntity getSelectedShowContract() {
        return selectedShowContract;
    }

    public void setSelectedShowContract(ShowContractEntity selectedShowContract) {
        this.selectedShowContract = selectedShowContract;
    }

    public List<ShowEntity> getShows() {
        return shows;
    }

    public void setShows(List<ShowEntity> shows) {
        this.shows = shows;
    }

    public Double getTicketRevenue() {
        return ticketRevenue;
    }

    public void setTicketRevenue(Double ticketRevenue) {
        this.ticketRevenue = ticketRevenue;
    }

    public List<ShowTicketSaleEntity> getShowTicketSales() {
        return showTicketSales;
    }

    public void setShowTicketSales(List<ShowTicketSaleEntity> showTicketSales) {
        this.showTicketSales = showTicketSales;
    }

    public Double getTicketCommission() {
        return ticketCommission;
    }

    public void setTicketCommission(Double ticketCommission) {
        this.ticketCommission = ticketCommission;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    public Double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(Double rentalFee) {
        this.rentalFee = rentalFee;
    }
}
