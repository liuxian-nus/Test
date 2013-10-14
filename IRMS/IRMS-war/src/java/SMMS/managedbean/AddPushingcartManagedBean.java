/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import SMMS.entity.PushingcartEntity;
import SMMS.session.PushingcartSessionBean;
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
public class AddPushingcartManagedBean {

    @EJB
    PushingcartSessionBean pushingcartSessionBean;
    
    private PushingcartEntity pushingcart;
    
    public AddPushingcartManagedBean(){
        pushingcart = new PushingcartEntity();
    }
    
    public void saveNew(ActionEvent event) throws IOException {
        pushingcartSessionBean.addPushingcart(pushingcart);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New pushing cart saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("addPushingcart.xhtml");
    }

    public PushingcartSessionBean getPushingcartSessionBean() {
        return pushingcartSessionBean;
    }

    public void setPushingcartSessionBean(PushingcartSessionBean pushingcartSessionBean) {
        this.pushingcartSessionBean = pushingcartSessionBean;
    }

    public PushingcartEntity getPushingcart() {
        return pushingcart;
    }

    public void setPushingcart(PushingcartEntity pushingcart) {
        this.pushingcart = pushingcart;
    }
    
}
