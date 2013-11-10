/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import SMMS.entity.BillEntity;
import SMMS.entity.BillItemEntity;
import SMMS.entity.ContractEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.MerchantBillSessionBean;
import java.util.ArrayList;
import java.util.List;
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
    private MerchantBillSessionBean merchantBillSessionBean;

    @EJB
    private ContractSessionBean contractSessionBean;
    private ContractEntity selectedContract;
    private List<BillItemEntity> selectedBills;

    public List<BillItemEntity> getSelectedBills() {
        return selectedBills;
    }

    public void setSelectedBills(List<BillItemEntity> selectedBills) {
        this.selectedBills = selectedBills;
    }

    
    /**
     * Creates a new instance of ContractResultPartnerManagedBean
     */
    public ContractResultPartnerManagedBean() {
        selectedContract = new ContractEntity();
        selectedBills = new ArrayList<BillItemEntity>();
    }

    public void initViewSelect(PhaseEvent event) {

        selectedContract = (ContractEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisContract");
        System.out.println("In init view select event size" + selectedContract.getContractEvent().size() + "select contract ID" + selectedContract.getContractId());
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }

     public void initViewSelect2(PhaseEvent event) {

        selectedBills = (List<BillItemEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisBills");
    
        //        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }

    public ContractEntity getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(ContractEntity selectedContract) {
        this.selectedContract = selectedContract;
    }
    
    

}
