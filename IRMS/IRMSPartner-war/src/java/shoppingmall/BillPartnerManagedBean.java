/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.BillItemEntity;
import SMMS.entity.ContractEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.MerchantBillSessionBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author liudazhi
 */
@ManagedBean
@ViewScoped
public class BillPartnerManagedBean {

    @EJB
    private ContractSessionBean contractSessionBean;
    @EJB
    private MerchantBillSessionBean merchantBillSessionBean;
    private List<BillEntity> availableBills;
    private List<BillEntity> unpaidBills;
    private List<BillEntity> overdueBills;
    private BillEntity bill;
    private List<BillItemEntity> bills;

    /**
     * Creates a new instance of BillPartnerManagedBean
     */
    public BillPartnerManagedBean() {
        bill = new BillEntity();
        unpaidBills = new ArrayList<BillEntity>();
        overdueBills = new ArrayList<BillEntity>();
        bills = new ArrayList<BillItemEntity>();
    }

//    @PostConstruct
//    public void init() {
//        availableBills = merchantBillSessionBean.getAvailableBills();
//        unpaidBills = merchantBillSessionBean.getUnpaidBills();
//        overdueBills = merchantBillSessionBean.getOverdueBills();
//    }
    public List<BillEntity> getMerchantUnpaidBills() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String loginId = (String) request.getSession().getAttribute("userId");
        return merchantBillSessionBean.getUnpaidBillsByMerchant(loginId);
    }

    public List<BillEntity> getMerchantPaidBills() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String loginId = (String) request.getSession().getAttribute("userId");
        return merchantBillSessionBean.getPaidBillsByMerchant(loginId);
    }

    public void makePayament(ActionEvent event) throws ExistException {
        System.out.println("in making payment" + bill.getBillId());
        bill.setBillStatus("paid");
        merchantBillSessionBean.updateBill(bill);
        System.out.println("after updating bills" + bill.getBillStatus());
        if ("newApproved".equals(bill.getContract().getStatus())) {
            bill.getContract().setStatus("newActive");
            contractSessionBean.updateContract(bill.getContract());
        }
        if ("renewApproved".equals(bill.getContract().getStatus())) {
            bill.getContract().setStatus("renewActive");
            contractSessionBean.updateContract(bill.getContract());
            System.err.println("here in renew what is the status now???" + bill.getContract().getStatus());
        }

        if ("earlyTerminationApproved".equals(bill.getContract().getStatus())) {
            bill.getContract().setStatus("earlyTerminated");
            contractSessionBean.updateContract(bill.getContract());
            System.err.println("here in renew what is the status now???" + bill.getContract().getStatus());

        }

    }

    public void viewBill(ActionEvent event) {
        System.out.println("No1:in displaying bean " + bill.getBillId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {

            bills = bill.getBillItem();
            System.out.println(" getting bilss" + bills.size());
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisBills", bills);
            System.out.println("we are after setting parameter");
            request.getSession().setAttribute("thisBills", bills);
            System.out.println("we are after setting contractId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewBillPartner.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing bill detial", ""));
            return;
        }
    }

    public List<BillEntity> getAvailableBills() {
        return availableBills;
    }

    public void setAvailableBills(List<BillEntity> availableBills) {
        this.availableBills = availableBills;
    }

    public List<BillEntity> getUnpaidBills() {
        return unpaidBills;
    }

    public List<BillItemEntity> getBills() {
        return bills;
    }

    public void setItemBills(List<BillItemEntity> bills) {
        this.bills = bills;
    }

    public void setUnpaidBills(List<BillEntity> unpaidBills) {
        this.unpaidBills = unpaidBills;
    }

    public List<BillEntity> getOverdueBills() {
        return overdueBills;
    }

    public void setOverdueBills(List<BillEntity> overdueBills) {
        this.overdueBills = overdueBills;
    }

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }
}
