/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import SMMS.entity.MerchantEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.MerchantBillSessionBean;
import SMMS.session.MerchantSessionBean;
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
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class merchantResultManagedBean {
    @EJB
    private MerchantBillSessionBean merchantBillSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;
    @EJB
    private MerchantSessionBean merchantSessionBean;
    private MerchantEntity selectedMerchant;

    

    
    /**
     * Creates a new instance of merchantResultManagedBean
     */
    public merchantResultManagedBean() {
        selectedMerchant = new MerchantEntity();
    }
    
     public void initViewSelect(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        selectedMerchant = (MerchantEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisMerchant");
        System.out.println("In init view select merchant"+selectedMerchant.getMerchantEmail());
//        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }
     
    public List<ContractEntity> getContracts()
    {
        System.out.println("in getting all contracts by merchant managed bean merchant mail" + selectedMerchant.getMerchantEmail());
        return contractSessionBean.getContractByMerchant(selectedMerchant.getMerchantEmail());
    }
    
    public List<BillEntity> getBills()
    {
        System.out.println("in getting all bills by merchant managed bean merchant mail" + selectedMerchant.getMerchantEmail());
        return merchantBillSessionBean.getBillByMerchant(selectedMerchant.getMerchantEmail());
    }
     
     public MerchantEntity getSelectedMerchant() {
        return selectedMerchant;
    }

    public void setSelectedMerchant(MerchantEntity selectedMerchant) {
        this.selectedMerchant = selectedMerchant;
    }
    
}
