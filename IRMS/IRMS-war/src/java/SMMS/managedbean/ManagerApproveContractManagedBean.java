/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.ContracteventSessionBean;
import java.io.IOException;
import java.io.Serializable;
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
    private ContracteventSessionBean contracteventSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;
    private ContractEntity selected;
    private ContractEntity contract;

    
    /**
     * Creates a new instance of ManagerApproveContractManagedBean
     */
    public ManagerApproveContractManagedBean() {
        contract = new ContractEntity();
        selected = new ContractEntity();
    }
    
    public void managerViewContract(ActionEvent event) throws IOException, ExistException {

        System.out.println("No1:in displaying bean " + selected.getContractId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selected = (ContractEntity) event.getComponent().getAttributes().get("managerView");
            System.out.println("N02: in displaying bean " + selected.getContractId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("managerSelect", selected);
            System.out.println("we are after setting parameter");
            request.getSession().setAttribute("contractId", selected.getContractId());
            System.out.println("we are after setting contractId session attribute");
            FacesContext.getCurrentInstance().getExternalContext().redirect("managerViewContract.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing contract", ""));
            return;
        }
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
}
