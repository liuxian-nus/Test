/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.FunctionalityEntity;
import ERMS.entity.RoleEntity;
import ERMS.session.FunctionalitySessionBean;
import ERMS.session.RoleSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
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
public class ManageRoleManagedBean {
    
    @EJB
    private RoleSessionBean rm;
    @EJB
    private FunctionalitySessionBean fm;
    private RoleEntity selectedRole;
    private boolean editMode;
    private int id;
    private List<String> selectedFunc = new ArrayList<String>();

    /** Creates a new instance of ManageEmployeeManagedBean */
    public ManageRoleManagedBean() {
        selectedRole = new RoleEntity();
    }
    
    public boolean isEditMode() {  
        return editMode;  
    }
    
    public List<RoleEntity> getRoles() {
        return rm.getAllRoles();
    }

    public void deleteRole(ActionEvent event) {
        setId((Integer)event.getComponent().getAttributes().get("code1"));
        rm.removeRole(getId());
    }
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        selectedRole.setFunctionalities(new ArrayList<FunctionalityEntity>());
        pushToFunc(selectedRole);
        rm.updateRole(getSelectedRole());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }

    /**
     * @return the selectedRole
     */
    public RoleEntity getSelectedRole() {
        return selectedRole;
    }

    /**
     * @param selectedRole the selectedRole to set
     */
    public void setSelectedRole(RoleEntity selectedRole) {
        this.selectedRole = selectedRole;
    }

    /**
     * @param editMode the editMode to set
     */
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public String toSentence(List<FunctionalityEntity> func){
        String output = null;
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < func.size()){
            sb.append(func.get(i).getFuncName());
            sb.append(";");
            System.out.println(func.get(i).getFuncName());
            i++;
        }
        output = sb.toString();
        return output;
    }

    public List<String> getSelectedFunc() {
        return selectedFunc;
    }

    public void setSelectedFunc(List<String> selectedFunc) {
        this.selectedFunc = selectedFunc;
    }

    public FunctionalitySessionBean getFm() {
        return fm;
    }

    public void setFm(FunctionalitySessionBean fm) {
        this.fm = fm;
    }
    
    public void pushToFunc(RoleEntity selectedRole) throws ExistException{
        int i = 0;
        Long id;
        id = Long.valueOf(selectedFunc.get(i));
        selectedRole.getFunctionalities().add(fm.getFunctionality(id));
        System.out.println(selectedFunc.get(i));
        
        while(i < (selectedFunc.size()-1)){
            i++;
            id = Long.valueOf(selectedFunc.get(i));
            selectedRole.getFunctionalities().add(fm.getFunctionality(id));
            System.out.println(selectedFunc.get(i));
        }
        
    } 
}
