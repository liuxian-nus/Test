/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.ContracteventEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.ContracteventSessionBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class OperatorRequstContractManagedBean {

    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private ContracteventSessionBean contracteventSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;
    private ContractEntity contract;
    private Long contractId;
    private ContracteventEntity cevent;

    /**
     * Creates a new instance of OperatorRequstContractManagedBean
     */
    public OperatorRequstContractManagedBean() {
        contract = new ContractEntity();
        cevent = new ContracteventEntity();
    }

    public void requestNew(ActionEvent event) {
        System.out.println("in getting new request" + contract.getContractId());
        cevent = contract.getLast();
        if ("new".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("newRequest");
        }
        if ("renew".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("renewRequest");
        }
        if ("earlyTermination".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("earlyTerminationRequest");
        }
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting new request" + contract.getLast().getEventStatus());
        emailSessionBean.emailRequest("cookiewxy@hotmail.com", contract); // send email to manager for information
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);

    }

    public void withdraw(ActionEvent event) {
        System.out.println("in withdraw request" + contract.getContractId());
        cevent = contract.getLast();
        if ("newRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("new");
        }
        if ("renewRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("renew");
        }
        if ("earlyTerminationRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("earlyTermination");
        }
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting new request" + contract.getLast().getEventStatus());
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);

    }

    //SO FAR NO USE
    public void requestRenew(ActionEvent event) {
        System.out.println("in getting renew request" + contract.getContractId());
        cevent = contract.getLast();
        cevent.setEventStatus("renewRequest");
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting renew request" + contract.getLast().getEventStatus());
        emailSessionBean.emailRequest("cookiewxy@hotmail.com", contract);
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);


    }

    public void requestEarlyTermination(ActionEvent event) {
        System.out.println("in getting renew request" + contract.getContractId());
        cevent = contract.getLast();
        cevent.setEventStatus("earlyTerminationRequest");
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting renew request" + contract.getLast().getEventStatus());
        emailSessionBean.emailRequest("cookiewxy@hotmail.com", contract);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);

    }

    public void renew(ActionEvent event) throws ExistException {
        System.out.println("in generating contract renew" + contract.getContractId());
       try{
        cevent.setEventStatus("renew");
        cevent.setEventContract(contract);
        contracteventSessionBean.addContractevent(cevent);
        System.out.println("after creating new contract event" + cevent.getContracteventId());
       

        contractSessionBean.addContractevent(contract.getContractId(), cevent.getContracteventId());// adding new event to contract, merge contract entity
        System.out.println("after adding contract event" + cevent.getContracteventId());
       }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when renewing new contract", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract has been changed to renewed.", ""));
    }

    public void terminate(ActionEvent event) {
        System.out.println("in getting renew request" + contract.getContractId());
        cevent = contract.getLast();
        cevent.setEventStatus("terminated");
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting renew request" + contract.getLast().getEventStatus());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);

    }

    public void delete(ActionEvent event) throws ExistException, IOException {
        System.out.println("In deleting contract" + contract.getContractId());
        try {
            contract.getOutlet().setOutletStatus("available");
            contractSessionBean.removeContract(contract);
            FacesContext.getCurrentInstance().getExternalContext().redirect("operatorManageContract.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing contract", ""));
            return;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract has been deleted successfully", ""));

    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public ContracteventEntity getCevent() {
        return cevent;
    }

    public void setCevent(ContracteventEntity cevent) {
        this.cevent = cevent;
    }
}
