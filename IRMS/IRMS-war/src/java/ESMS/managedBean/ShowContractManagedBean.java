/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import ESMS.entity.ShowContractEntity;
import ESMS.session.ShowContractSessionBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ShowContractManagedBean {
    @EJB
    private ShowContractSessionBean showContractSessionBean;
    private ShowContractEntity showContract;
    
    public ShowContractManagedBean(){
        showContract = new ShowContractEntity();
    }
    
    public void saveNew(ActionEvent event) throws IOException {
        showContractSessionBean.addShowContract(showContract);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New show contract saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("showContract.xhtml");
    }
    
    public void cancel(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("showContract.xhtml");
    }

    public ShowContractSessionBean getShowContractSessionBean() {
        return showContractSessionBean;
    }

    public void setShowContractSessionBean(ShowContractSessionBean showContractSessionBean) {
        this.showContractSessionBean = showContractSessionBean;
    }

    public ShowContractEntity getShowContract() {
        return showContract;
    }

    public void setShowContract(ShowContractEntity showContract) {
        this.showContract = showContract;
    }
    
    
}
