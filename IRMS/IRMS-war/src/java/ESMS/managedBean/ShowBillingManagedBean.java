/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import ERMS.session.EmailSessionBean;
import ESMS.entity.ShowContractEntity;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowTicketSaleEntity;
import ESMS.session.ShowBillingSessionBean;
import ESMS.session.ShowContractSessionBean;
import ESMS.session.ShowSessionBean;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

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
    @EJB
    EmailSessionBean emailSessionBean;
    private ShowEntity selectedShow;
    private ShowTicketSaleEntity selectedShowTicketSale;
    private ShowContractEntity selectedShowContract;
    private List<ShowEntity> shows;
    private List<ShowTicketSaleEntity> showTicketSales;
    private Long showId;
    private Double ticketRevenue = 0.0;
    private Double ticketCommission = 0.0;
    private Double bill = 0.0;
    private Double rentalFee = 0.0;
    private double deposit = 0.0;

    //Constructor
    public ShowBillingManagedBean() {
        selectedShow = new ShowEntity();
        selectedShowTicketSale = new ShowTicketSaleEntity();
        selectedShowContract = new ShowContractEntity();
    }

    //Method
    public void showBill(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        selectedShow = showSessionBean.getShowById(showId);
        System.err.println(selectedShow.getShowName());
        showTicketSales = showBillingSessionBean.getAllSelectedShowTicketSales(showId);
        Iterator<ShowTicketSaleEntity> itr = showTicketSales.iterator();
        while (itr.hasNext()) {
            selectedShowTicketSale = itr.next();
            ticketRevenue += selectedShowTicketSale.getShowTicketPrice() * selectedShowTicketSale.getShowTicketQuantity();
        }
        ticketCommission = ticketRevenue * selectedShow.getShowContract().getShowTicketCommission();
        rentalFee = selectedShow.getShowContract().getShowVenueDuration() * selectedShow.getShowContract().getShowVenueRate();
        if (selectedShow.getShowContract().getShowDeposit() != null) {
            deposit = selectedShow.getShowContract().getShowDeposit();
        }
        bill = ticketCommission + deposit - rentalFee;
        request.getSession().setAttribute("billedShow", selectedShow);
        request.getSession().setAttribute("ticketCommission", ticketCommission);
        request.getSession().setAttribute("rentalFee", rentalFee);
        request.getSession().setAttribute("ticketRevenue", ticketRevenue);
        request.getSession().setAttribute("showBill", bill);
    }

    public void oneMore(ActionEvent event) throws IOException, BadElementException, MalformedURLException, DocumentException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        selectedShow = (ShowEntity) request.getSession().getAttribute("billedShow");
        ticketCommission = (Double) request.getSession().getAttribute("ticketCommission");
        rentalFee = (Double) request.getSession().getAttribute("rentalFee");
        ticketRevenue = (Double) request.getSession().getAttribute("ticketRevenue");
        bill = (Double) request.getSession().getAttribute("showBill");
        selectedShowContract = selectedShow.getShowContract();
        System.err.println("contract: " + selectedShowContract.getShowMerchantEmail());
        emailSessionBean.sendShowContractBill(rentalFee, bill, ticketCommission, ticketRevenue, selectedShowContract);
        selectedShow.setShowPaymentStatus(true);
        System.err.println("Status: " + selectedShow.isShowPaymentStatus());
        showSessionBean.updateShow(selectedShow);
        FacesContext.getCurrentInstance().getExternalContext().redirect("showBilling.xhtml");

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
