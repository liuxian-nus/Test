/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import CRMS.entity.MemberEntity;
import CRMS.session.MemberMessageSessionBean;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import SMMS.entity.ContractEntity;
import SMMS.entity.OutletEntity;
import SMMS.session.OutletSessionBean;
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
public class OutletPartnerManagedBean {
    @EJB
    private MemberSessionBean memberSessionBean;
    
    @EJB
    private OutletSessionBean outletSessionBean;
    private OutletEntity selected;

    

    /**
     * Creates a new instance of OutletPartnerManagedBean
     */
    public OutletPartnerManagedBean() {
        selected = new OutletEntity();
    }
    
     public List<OutletEntity> getOutlets() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String loginId = (String) request.getSession().getAttribute("userId");
        System.out.println(" in getting outlets"+loginId);
        return outletSessionBean.getOutletsByMerchant(loginId);
    }
     
      public void viewTransaction(ActionEvent event) throws IOException, ExistException {

        System.out.println("No1:in displaying transaction bean " + selected.getOutletId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selected = (OutletEntity) event.getComponent().getAttributes().get("selectedOutlet");
            System.out.println("N02: in displaying bean " + selected.getOutletId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisOutlet", selected);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewTransactionPartner.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing contract", ""));
            return;
        }
    }
      
      public OutletEntity getSelected() {
        return selected;
    }

    public void setSelected(OutletEntity selected) {
        this.selected = selected;
    }
    
    public List<MemberEntity> getMembers()
    {
        return memberSessionBean.getAllMembers();
    }

}
