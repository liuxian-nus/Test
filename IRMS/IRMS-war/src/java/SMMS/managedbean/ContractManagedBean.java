/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.entity.MerchantEntity;
import SMMS.entity.OutletEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.ContracteventSessionBean;
import SMMS.session.MerchantSessionBean;
import SMMS.session.OutletSessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class ContractManagedBean implements Serializable {

    @EJB
    private ContracteventSessionBean contracteventSessionBean;
    @EJB
    private OutletSessionBean outletSessionBean;
    @EJB
    private MerchantSessionBean merchantSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;
    private ContractEntity contract;
    private List<MerchantEntity> merchants;
    private MerchantEntity merchant;
    private OutletEntity outlet;
    private List<OutletEntity> outlets;
    private String merchantId; //merchant name unique
    private int outletId;
    private ContracteventEntity newevent;
    private Date currentDate = new Date();
    private ContractEntity selected;

    public ContractManagedBean() {

        contract = new ContractEntity();
        newevent = new ContracteventEntity();
        selected = new ContractEntity();

    }

    public ContractEntity getSelected() {
        return selected;
    }

    public void setSelected(ContractEntity selected) {
        this.selected = selected;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public ContracteventSessionBean getContracteventSessionBean() {
        return contracteventSessionBean;
    }

    public void setContracteventSessionBean(ContracteventSessionBean contracteventSessionBean) {
        this.contracteventSessionBean = contracteventSessionBean;
    }

    public OutletSessionBean getOutletSessionBean() {
        return outletSessionBean;
    }

    public void setOutletSessionBean(OutletSessionBean outletSessionBean) {
        this.outletSessionBean = outletSessionBean;
    }

    public MerchantSessionBean getMerchantSessionBean() {
        return merchantSessionBean;
    }

    public void setMerchantSessionBean(MerchantSessionBean merchantSessionBean) {
        this.merchantSessionBean = merchantSessionBean;
    }

    public ContractSessionBean getContractSessionBean() {
        return contractSessionBean;
    }

    public void setContractSessionBean(ContractSessionBean contractSessionBean) {
        this.contractSessionBean = contractSessionBean;
    }

    public List<MerchantEntity> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<MerchantEntity> merchants) {
        this.merchants = merchants;
    }

    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
    }

    public OutletEntity getOutlet() {
        return outlet;
    }

    public void setOutlet(OutletEntity outlet) {
        this.outlet = outlet;
    }

    public List<OutletEntity> getOutlets() {
        return outlets;
    }

    public void setOutlets(List<OutletEntity> outlets) {
        this.outlets = outlets;
    }

    public ContracteventEntity getNewevent() {
        return newevent;
    }

    public void setNewevent(ContracteventEntity newevent) {
        this.newevent = newevent;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    /**
     * Creates a new instance of ContractManagedBean
     */
    public void addContract(ActionEvent event) throws ExistException, IOException {
        System.out.println("in adding contract" + merchantId + "and outlet ID:" + outletId);
        try {

            merchant = merchantSessionBean.getMerchantById(merchantId);
            outlet = outletSessionBean.getOutletById(outletId);

            contract.setMerchant(merchant); //adding new merchant
            contract.setOutlet(outlet); //adding new outlet
            contractSessionBean.addContract(contract);//persist contract entity
            System.out.println("after persisting contract" + contract.getContractId());

            newevent.setEventStatus("new");
            newevent.setEventContract(contract);
            contracteventSessionBean.addContractevent(newevent);//persist event entity
            System.out.println("after creating new contract event" + newevent.getContracteventId());
            selected = new ContractEntity();

            contractSessionBean.addContractevent(contract.getContractId(), newevent.getContracteventId());// adding new event to contract, merge contract entity
            System.out.println("after adding contract event" + newevent.getContracteventId());

            merchantSessionBean.addContractInMerchant(contract.getContractId(), merchantId);//merge merchant
            System.out.println("after adding contract to merchant" + merchant.getMerchantEmail());

            outlet.setContract(contract);
            outlet.setOutletStatus("unavailable");
            outletSessionBean.updateOutlet(outlet);//merge outlet
            System.out.println("after adding contract to outlet" + outlet.getOutletId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new contract", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract has been added.", ""));
    }

    // NOT USEFUL GETTINGS
    public List<String> getAllMerchants() throws ExistException {
        System.out.println("NO4: we are in ALL merchants bean BEFORE");
        List<String> results = new ArrayList<String>();

        List<MerchantEntity> merchantList = merchantSessionBean.getAllMerchants();
        for (Object o : merchantList) {
            MerchantEntity rve = (MerchantEntity) o;
            results.add((rve.getMerchantEmail()).toString());
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }

    //NOT USEFUL THERE
    public List<Integer> getAvailableOutlets() throws ExistException {
        System.out.println("NO4: we are in ALL outlets bean BEFORE");
        List<Integer> results = new ArrayList<Integer>();

        List<OutletEntity> merchantList = outletSessionBean.getAvailableOutlets();
        for (Object o : merchantList) {
            OutletEntity rve = (OutletEntity) o;
            results.add((rve.getOutletId()));
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }

    public List<ContractEntity> getAllContracts() {
        System.out.println("in getting all contracts in session bean");
        return contractSessionBean.getAllContracts();
    }

    public List<ContractEntity> getNewRequestContracts() {
        System.out.println("in getting all new request contracts in session bean");
        return contractSessionBean.getNewRequestContract();
    }

    public List<ContractEntity> getRenewRequestContracts() {
        System.out.println("in getting all new request contracts in session bean");
        return contractSessionBean.getRenewRequestContract();
    }

    public List<ContractEntity> getEarlyTerminationRequestContracts() {
        System.out.println("in getting all new request contracts in session bean");
        return contractSessionBean.getEarlyTerminationRequestContract();
    }

    public void viewContract(ActionEvent event) throws IOException, ExistException {

        System.out.println("No1:in displaying bean " + selected.getContractId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selected = (ContractEntity) event.getComponent().getAttributes().get("selectedContract");
            System.out.println("N02: in displaying bean " + selected.getContractId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", selected);
            System.out.println("we are after setting parameter");
            request.getSession().setAttribute("contractId", selected.getContractId());
            System.out.println("we are after setting contractId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("operatorViewContract.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing contract", ""));
            return;
        }
    }
}
