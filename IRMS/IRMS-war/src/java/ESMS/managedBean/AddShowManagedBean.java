/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import ESMS.entity.ShowEntity;
import ESMS.session.ShowSessionBean;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ser3na
 */

@ManagedBean
@RequestScoped
public class AddShowManagedBean {

    @EJB
    ShowSessionBean showSessionBean;
    
    private ShowEntity show;
    
    public AddShowManagedBean() {
        show = new ShowEntity();
    }
    
    public void saveNewShow(ActionEvent event){
        System.err.println("Saving New Show...");
        showSessionBean.addShow(getShow());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Show saved.", ""));
    }
    
    public ShowEntity getShow() {
        return show;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }
    public void oneMore(ActionEvent event) throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("addShow.xhtml");
    }
}
