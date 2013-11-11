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
import java.util.Calendar;
import java.util.Date;
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
    private Date currentDate = new Date();

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
        if ("newRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("newApproved");
        }
        if ("renewRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("renewApproved");
        }
        if ("earlyTerminationRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("earlyTerminationApproved");
        }
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting new request" + contract.getLast().getEventStatus());
        emailSessionBean.emailApprovalAction("a0077969@nus.edu.sg", contract);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);

        if ("newApproved".equals(cevent.getEventStatus())) {
            System.err.println("in new");
            addDepositBill(contract);
            merchantBillSessionBean.createActiveTimers(contract.getLast().getEventStartDate());
            emailSessionBean.emailMerchantBill(contract.getMerchant().getMerchantEmail(), bill);
        }

        if ("renewApproved".equals(cevent.getEventStatus())) {
            
            System.err.println("in new approved");
            

        }

        if ("earlyTerminationApproved".equals(cevent.getEventStatus())) {
            //addFinalBill(contract);
            merchantBillSessionBean.createActiveTimers(contract.getLast().getEventStartDate());
            emailSessionBean.emailMerchantBill(contract.getMerchant().getMerchantEmail(), bill);
        }
   }

    public void rejectNew(ActionEvent event) {
        System.out.println("NO1: in getting new REJECT" + contract.getContractId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        contract = (ContractEntity) event.getComponent().getAttributes().get("managerViewSelect");
        System.out.println("NO2: in getting new approve" + contract.getContractId());
        cevent = contract.getLast();
        if ("newRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("newRejected");
        }
        if ("renewRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("renewRejected");
        }
        if ("earlyTerminationRequest".equals(cevent.getEventStatus())) {
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
        if ("newRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("newPending");
        }
        if ("renewRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("renewPending");
        }
        if ("earlyTerminationRequest".equals(cevent.getEventStatus())) {
            cevent.setEventStatus("earlyTerminationPending");
        }
        contracteventSessionBean.updateContractEvent(cevent);
        System.out.println("after setting new request" + contract.getLast().getEventStatus());
        emailSessionBean.emailApprovalAction("a0077969@nus.edu.sg", contract);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisContract", contract);

    }

    public void addDepositBill(ContractEntity contract) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 2);  //here expire after 2 minutes
        Date dueDate = cal.getTime();
        System.out.println("in setting due date" + dueDate);

        bill.setBillAmount(contract.getLast().getEventDeposit());
        bill.setBillStatus("unpaid");
        bill.setBillType("deposit");
        bill.setBillDate(currentDate);
        bill.setContract(contract);
        bill.setDueDate(dueDate);
        merchantBillSessionBean.addBill(bill);
        merchantBillSessionBean.setBill(bill);
        System.err.println("before creating timer" + dueDate);
        merchantBillSessionBean.createOverDueTimers(dueDate);

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

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
