/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.ContracteventSessionBean;
import SMMS.session.MerchantBillSessionBean;
import java.io.IOException;
import java.io.Serializable;
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
public class ManagerApproveContractManagedBean implements Serializable {
    @EJB
    private MerchantBillSessionBean merchantBillSessionBean;

    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private ContracteventSessionBean contracteventSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;
    private ContractEntity selected;
    private ContractEntity contract;
    private ContracteventEntity cevent;
    private BillEntity bill;

    /**
     * Creates a new instance of ManagerApproveContractManagedBean
     */
    public ManagerApproveContractManagedBean() {
        contract = new ContractEntity();
        selected = new ContractEntity();
        cevent = new ContracteventEntity();
        bill = new BillEntity();
    }


    public void managerViewContract(ActionEvent event) throws IOException, ExistException {

        System.out.println("No1:in displaying bean " + selected.getContractId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selected = (ContractEntity) event.getComponent().getAttributes().get("managerSelected");
            System.out.println("N02: in displaying bean " + selected.getContractId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("managerContract", selected);
            System.out.println("we are after setting parameter");
            request.getSession().setAttribute("managerContractId", selected.getContractId());
            System.out.println("we are after setting contractId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("managerViewContract.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing contract", ""));
            return;
        }
    }

    public void approveNew(ActionEvent event) {
        System.out.println("in getting new approve the contract is" + contract.getContractId());
        cevent = contract.getLast();
        if (cevent.getEventStatus().contains("new")) {
            cevent.setEventStatus("newApproved");
        }
        if (cevent.getEventStatus().contains("renew")) {
            cevent.setEventStatus("renewApproved");
        }
        if (cevent.getEventStatus().contains("earlyTermination")) {
            cevent.setEventStatus("earlyTerminationApproved");
        }
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting new request" + contract.getLast().getEventStatus());
        emailSessionBean.emailApprovalAction("a0077969@nus.edu.sg", contract);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);
        addDepositBill(contract);

    }

    public void rejectNew(ActionEvent event) {
        System.out.println("NO1: in getting new REJECT" + contract.getContractId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        contract = (ContractEntity) event.getComponent().getAttributes().get("managerViewSelect");
        System.out.println("NO2: in getting new approve" + contract.getContractId());
        cevent = contract.getLast();
        if (cevent.getEventStatus().contains("new")) {
            cevent.setEventStatus("newRejected");
        }
        if (cevent.getEventStatus().contains("renew")) {
            cevent.setEventStatus("renewRejected");
        }
        if (cevent.getEventStatus().contains("earlyTermination")) {
            cevent.setEventStatus("earlyTerminationRejected");
        }
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting new request" + contract.getLast().getEventStatus());
        emailSessionBean.emailApprovalAction("a0077969@nus.edu.sg", contract);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);

    }

    public void pendingNew(ActionEvent event) {
        System.out.println("in getting new approve" + contract.getContractId());
        cevent = contract.getLast();
        if (cevent.getEventStatus().contains("new")) {
            cevent.setEventStatus("newPending");
        }
        if (cevent.getEventStatus().contains("renew")) {
            cevent.setEventStatus("renewPending");
        }
        if (cevent.getEventStatus().contains("earlyTermination")) {
            cevent.setEventStatus("earlyTerminationPending");
        }
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting new request" + contract.getLast().getEventStatus());
        emailSessionBean.emailApprovalAction("a0077969@nus.edu.sg", contract);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);

    }

    public ContractEntity getSelected() {
   
        return selected;
    }

    public void setSelected(ContractEntity selected) {
        this.selected = selected;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
    
    public void addDepositBill(ContractEntity contract)
    {
        bill.setBillAmount(contract.getLast().getEventDeposit());
        bill.setBillStatus("unpaid");
        bill.setBillType("deposit");
        bill.setContract(contract);
        merchantBillSessionBean.addBill(bill);
    }
}
