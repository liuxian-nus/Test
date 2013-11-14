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
import java.util.Date;
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
    private boolean editMode = false;
    private Date eventStartDate;
    private Date eventEndDate;
    private double eventMonthRate = 0.0;
    private double eventEarlyCharge = 0.0;
    private double eventCommissionRate = 0.0;

    /**
     * Creates a new instance of OperatorRequstContractManagedBean
     */
    public OperatorRequstContractManagedBean() {
        contract = new ContractEntity();
        cevent = new ContracteventEntity();
    }

    public void updateContract(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        System.out.println("in updating contract renew" + contract.getContractId());
        try {
//            contract = (ContractEntity) request.getSession().getAttribute("contractId");
            ContracteventEntity thisEvent = new ContracteventEntity();
            thisEvent = contract.getLast();
            thisEvent.setEventCommissionRate(eventCommissionRate);
            thisEvent.setEventEarlyCharge(eventEarlyCharge);
            thisEvent.setEventStartDate(eventStartDate);
            thisEvent.setEventEndDate(eventEndDate);
            thisEvent.setEventMonthRate(eventMonthRate);
            contracteventSessionBean.updateContractEvent(thisEvent);
            System.err.println("ahaha after updating contract event" + thisEvent.getEventStatus());
            System.out.println("in updating contract renew" + contract.getContractId());
            contractSessionBean.updateContract(contract);
            System.out.println("now the status is" + contract.getLast().getEventStatus());
//            cevent = contract.getLast();            
//            cevent.setEventContract(contract);
//            contracteventSessionBean.addContractevent(cevent);
//            System.out.println("after creating new contract event" + cevent.getContracteventId());
//
            FacesContext.getCurrentInstance().getExternalContext().redirect("operatorManageContract.xhtml");
//            contractSessionBean.addContractevent(contract.getContractId(), cevent.getContracteventId());// adding new event to contract, merge contract entity
//            System.out.println("after adding contract event" + cevent.getContracteventId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when updating new contract", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract has been updated.", ""));

    }

    public void viewContract(ActionEvent event) {
        System.out.println("No1:in displaying bean " + contract.getContractId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {


            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);
//            System.out.println("we are after setting parameter");
//            request.getSession().setAttribute("contractId", contract.getContractId());
            System.out.println("we are after setting contractId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("addRenewContract.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing contract", ""));
        }
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

    public void cancelContract(ActionEvent event) {
        System.out.println("in canceling contract" + contract.getContractId());
        cevent = contract.getLast();
        cevent.setEventStatus("canceled");
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

    public void renew(ActionEvent event) throws ExistException, IOException {
        System.out.println("in generating contract renew" + contract.getContractId());
        try {
            System.out.println("what is the commission now?" + eventCommissionRate);
            System.out.println("what is the commission now?" + eventEarlyCharge);
            System.out.println("what is the commission now?" + eventMonthRate);
            ContracteventEntity cevent2 = new ContracteventEntity();
            cevent2.setEventCommissionRate(eventCommissionRate);
            cevent2.setEventEarlyCharge(eventEarlyCharge);
            cevent2.setEventMonthRate(eventMonthRate);
            cevent2.setEventStartDate(eventStartDate);
            cevent2.setEventEndDate(eventEndDate);
            cevent2.setEventStatus("renew");
            cevent2.setEventContract(contract);
            contracteventSessionBean.addContractevent(cevent2);
            System.out.println("after creating new contract event" + cevent2.getContracteventId());


            contractSessionBean.addContractevent(contract.getContractId(), cevent2.getContracteventId());// adding new event to contract, merge contract entity
            System.out.println("after adding contract event" + cevent.getContracteventId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when renewing new contract", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract has been changed to renewed.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("operatorManageContract.xhtml");
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

    public boolean isEditMode() {
        System.out.println("edit more is " + editMode);
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
        System.out.println("edit more is " + editMode);

    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public double getEventMonthRate() {
        return eventMonthRate;
    }

    public void setEventMonthRate(double eventMonthRate) {
        this.eventMonthRate = eventMonthRate;
    }

    public double getEventEarlyCharge() {
        return eventEarlyCharge;
    }

    public void setEventEarlyCharge(double eventEarlyCharge) {
        this.eventEarlyCharge = eventEarlyCharge;
    }

    public double getEventCommissionRate() {
        return eventCommissionRate;
    }

    public void setEventCommissionRate(double eventCommissionRate) {
        this.eventCommissionRate = eventCommissionRate;
    }
}
