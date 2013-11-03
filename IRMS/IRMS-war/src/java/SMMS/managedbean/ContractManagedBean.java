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
    private String searchId;
    private List<ContractEntity> contracts;
    private String outletType;
    private String outletName;

    public ContractManagedBean() {

        contract = new ContractEntity();
        newevent = new ContracteventEntity();
        selected = new ContractEntity();

    }

    public String getOutletType() {
        return outletType;
    }

    public void setOutletType(String outletType) {
        this.outletType = outletType;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
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

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public List<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractEntity> contracts) {
        this.contracts = contracts;
    }

    /**
     * Creates a new instance of ContractManagedBean
     */
    public void addContract(ActionEvent event) throws ExistException, IOException {
        System.out.println("NO1: in adding contract" + merchantId + "and outlet ID:" + outletId);
        System.out.println("outlet name and type" + outletType + outletName);
        try {

            merchant = merchantSessionBean.getMerchantById(merchantId);
            outlet = outletSessionBean.getOutletById(outletId);
            System.out.println("NO2: contract outlet id getting outlet" + outlet.getOutletId());

            contract.setMerchant(merchant); //adding new merchant
            contract.setOutlet(outlet); //adding new outlet
            System.out.println("NO3: contract outlet id setting done" + contract.getOutlet().getOutletId());
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
            outlet.setOutletName(outletName);
            outlet.setOutletType(outletType);
            outletSessionBean.updateOutlet(outlet);//merge outlet
            System.out.println("after adding contract to outlet" + outlet.getOutletId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new contract", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract has been added.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("operatorManageContract.xhtml");
    }

    // USEFUL GETTINGS
    public List<String> completeMerchants() throws ExistException {
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

    public List<String> completeContracts() throws ExistException {
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

    //USEFUL THERE
    public List<Integer> completeOutlets() throws ExistException {
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

    public void searchById() {
        System.out.println("No1:in searching contract by Id bean " + searchId);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selected = contractSessionBean.getContractById(Long.valueOf(searchId));
            System.out.println("N02: in searching by id bean " + selected.getContractId());

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

    public void searchByOutlet() {
        System.out.println("No1:in searching contract by Id bean " + outletId);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selected = contractSessionBean.getContractByOutlet(outletId);
            System.out.println("N02: in searching by id bean " + selected.getContractId());

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

    public void searchByMerchant() {
        System.out.println("No1:in searching contract by merchant bean " + merchantId);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            merchant = merchantSessionBean.getMerchantById(merchantId);
            System.out.println("N02: in searching by merchant bean " + merchant.getMerchantEmail());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisMerchant", merchant);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewMerchant.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing merchant", ""));
            return;
        }
    }
}
