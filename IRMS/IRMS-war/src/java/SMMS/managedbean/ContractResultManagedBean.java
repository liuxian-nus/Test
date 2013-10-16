/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import SMMS.entity.ContractEntity;
import SMMS.session.ContractSessionBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class ContractResultManagedBean {

    @EJB
    private ContractSessionBean contractSessionBean;
    private ContractEntity selectedContract;

    public ContractResultManagedBean() {
    }

    public ContractEntity getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(ContractEntity selectedContract) {
        this.selectedContract = selectedContract;
    }
    
    public void initViewSelect(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        selectedContract = (ContractEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisContract");
        System.out.println("In init view select event size"+ selectedContract.getContractEvent().size()+"select contract ID"+selectedContract.getContractId());
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }
    
    
    
    /**
     * Creates a new instance of ContractResultManagedBean
     */
}
