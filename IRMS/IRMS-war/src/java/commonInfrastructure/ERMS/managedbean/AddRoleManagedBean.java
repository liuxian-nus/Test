/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.RoleEntity;
import ERMS.session.FunctionalitySessionBean;
import ERMS.session.RoleSessionBean;
import Exception.ExistException;
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
 * @author liuxian
 */
@ManagedBean
@RequestScoped
public class AddRoleManagedBean {

   
    /** Creates a new instance of AddRoleManagedBean */
    @EJB
    RoleSessionBean rm;
    @EJB
    private FunctionalitySessionBean fm;
    
    private RoleEntity role;
    private List<String> selectedFunc;
    
    public AddRoleManagedBean() {
        role = new RoleEntity();
    }
    
    public void saveNewRole(ActionEvent event) throws ExistException
    {
        pushToFunc();
        rm.addRole(getRole());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Role saved.", ""));
    }
    
    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
    public void oneMore(ActionEvent event) throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddRole.xhtml");
    }

    public List<String> getSelectedFunc() {
        return selectedFunc;
    }

    public void setSelectedFunc(List<String> selectedFunc) {
        this.selectedFunc = selectedFunc;
    }
    
     public void pushToFunc() throws ExistException{
        int i = 0;
        Long id;
        System.out.println("haha");
        id = Long.valueOf(selectedFunc.get(i));
        role.getFunctionalities().add(getFm().getFunctionality(id));
        System.out.println(selectedFunc.get(i));
        
        while(i < (selectedFunc.size()-1)){
            i++;
            id = Long.valueOf(selectedFunc.get(i));
            role.getFunctionalities().add(getFm().getFunctionality(id));
            System.out.println(selectedFunc.get(i));
        }
        
    }

    public FunctionalitySessionBean getFm() {
        return fm;
    }

    public void setFm(FunctionalitySessionBean fm) {
        this.fm = fm;
    }
}
