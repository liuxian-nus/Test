/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

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
 * @author liudazhi
 */
@ManagedBean
@ViewScoped
public class ContractResultPartnerManagedBean {

    @EJB
    private ContractSessionBean contractSessionBean;
    private ContractEntity selectedContract;

    /**
     * Creates a new instance of ContractResultPartnerManagedBean
     */
    public ContractResultPartnerManagedBean() {
        selectedContract = new ContractEntity();
    }

    public void initViewSelect(PhaseEvent event) {

        selectedContract = (ContractEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisContract");
        System.out.println("In init view select event size" + selectedContract.getContractEvent().size() + "select contract ID" + selectedContract.getContractId());
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }

    public ContractEntity getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(ContractEntity selectedContract) {
        this.selectedContract = selectedContract;
    }
}
