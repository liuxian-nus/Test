/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.MerchantEntity;
import SMMS.session.ContractSessionBean;
import SMMS.session.MerchantSessionBean;
import java.io.IOException;
import java.util.List;
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
 * @author liudazhi
 */
@ManagedBean
@ViewScoped
public class ContractPartnerManagedBean {

    @EJB
    private MerchantSessionBean merchantSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;
    private MerchantEntity merchant;
    private ContractEntity selected;

    /**
     * Creates a new instance of ContractPartnerManagedBean
     */
    public ContractPartnerManagedBean() {
        merchant = new MerchantEntity();
        selected = new ContractEntity();
    }

    public List<ContractEntity> getContracts() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String loginId = (String) request.getSession().getAttribute("userId");
        return contractSessionBean.getContractByMerchant(loginId);
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
           
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewContractPartner.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing contract", ""));
            return;
        }
    }
    
    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
    }
    
     public ContractEntity getSelected() {
        return selected;
    }

    public void setSelected(ContractEntity merchant) {
        this.selected = merchant;
    }
}
