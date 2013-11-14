/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import FBMS.session.BillingSessionBean;
import SMMS.entity.BillEntity;
import SMMS.entity.BillItemEntity;
import SMMS.entity.ContractEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.MerchantBillSessionBean;
import SMMS.session.MerchantSessionBean;
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
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class OperatorBillManagedBean {

    @EJB
    private ContractSessionBean contractSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private BillingSessionBean billingSessionBean;
    @EJB
    private MerchantBillSessionBean merchantBillSessionBean;
    @EJB
    private MerchantSessionBean merchantSessionBean;
    private List<BillEntity> availableBills;
    private List<BillEntity> unpaidBills;
    private List<BillEntity> overdueBills;
    private BillEntity bill;
    private String searchId;
    private BillEntity selected;
    private List<BillEntity> bills;
    private ContractEntity contract;

    /**
     * Creates a new instance of OperatorBillManagedBean
     */
    @PostConstruct
    public void init() {
        availableBills = merchantBillSessionBean.getAvailableBills();
        unpaidBills = merchantBillSessionBean.getUnpaidBills();
        overdueBills = merchantBillSessionBean.getOverdueBills();
    }

    public OperatorBillManagedBean() {
        bill = new BillEntity();
        selected = new BillEntity();
        bills = new ArrayList<BillEntity>();
        contract = new ContractEntity();

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

    public void setUnpaidBills(List<BillEntity> unpaidBills) {
        this.unpaidBills = unpaidBills;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public List<BillEntity> getOverdueBills() {
        return overdueBills;
    }

    public List<BillEntity> getBills() {
        return bills;
    }

    public void setBills(List<BillEntity> bills) {
        this.bills = bills;
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

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public BillEntity getSelected() {
        return selected;
    }

    public void setSelected(BillEntity selected) {
        this.selected = selected;
    }

    public void sendBill(ActionEvent event) throws ExistException { //for available
        System.out.println("in sending bill" + bill.getBillId());
        bill.setBillStatus("unpaid");
        merchantBillSessionBean.updateBill(bill);
        System.out.println("after setting" + bill.getBillStatus());
        emailSessionBean.emailMerchantBill(bill.getContract().getMerchant().getMerchantEmail(), bill);
        System.out.println("email sent");
    }

    public void sendReminder(ActionEvent event) { //for unpaid
        System.out.println("in sending bill" + bill.getBillId());
        emailSessionBean.emailMerchantBill(bill.getContract().getMerchant().getMerchantEmail(), bill);
        System.out.println("email sent");

    }

    public void sendYearlyBill(ActionEvent event) throws ExistException {
        merchantBillSessionBean.addYearlyBill();
    }

    public void sendTerminateBill(ActionEvent event) throws ExistException {

        System.out.println("what is the current contract now" + contract.getContractId());
        merchantBillSessionBean.addTerminationBill(contract);
    }

    public void viewBill(ActionEvent event) {
        System.out.println("No1:in displaying bean " + bill.getBillId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            List<BillItemEntity> items = new ArrayList<BillItemEntity>();
            items = bill.getBillItem();
            System.out.println(" getting bilss" + items.size());
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisBills", items);
            System.out.println("we are after setting parameter");
            request.getSession().setAttribute("thisBills", items);
            System.out.println("we are after setting contractId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewBillDetail.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing bill detial", ""));
        }
    }

    public void searchById(ActionEvent event) {
        System.out.println("No1:in searching bill by Id bean " + searchId);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selected = merchantBillSessionBean.getBillById(Long.valueOf(searchId));
            System.out.println("N02: in searching by id bean " + selected.getBillId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisBill", selected);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("operatorViewBill.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing Bill", ""));
            return;
        }
    }

//    public void searchByMerchant(ActionEvent event) {
//        System.out.println("No1:in searching bill by Id bean " + searchId);
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        try {
//            bills = merchantBillSessionBean.getBillByMerchant(searchId);
//            System.out.println("N02: in searching by id bean " + bills.size());
//
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("theseBills", bills);
//            System.out.println("we are after setting parameter");
//            FacesContext.getCurrentInstance().getExternalContext().redirect("viewMerchant.xhtml");
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing Bill", ""));
//            return;
//        }
//    }
    public void searchByContract(ActionEvent event) {
        System.out.println("No1:in searching bill by Id bean " + searchId);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            bills = merchantBillSessionBean.getBillByContract(Long.valueOf(searchId));
            System.out.println("N02: in searching by id bean " + bills.size());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisBills", bills);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("operatorViewContractBill.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing Bill", ""));
            return;
        }
    }

    public List<String> completeBillsContract() throws ExistException {
        System.out.println("NO4: we are in ALL contracts bean BEFORE");
        List<String> results = new ArrayList<String>();

        List<ContractEntity> merchantList = contractSessionBean.getAllContracts();
        for (Object o : merchantList) {
            ContractEntity rve = (ContractEntity) o;
            results.add((rve.getContractId()).toString());
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }

    public List<String> completeBillsId() throws ExistException {
        System.out.println("NO4: we are in ALL contracts bean BEFORE");
        List<String> results = new ArrayList<String>();

        List<BillEntity> merchantList = merchantBillSessionBean.getAllBills();
        for (Object o : merchantList) {
            BillEntity rve = (BillEntity) o;
            results.add((rve.getBillId()).toString());
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }
}
