/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.FunctionalityEntity;
import ERMS.session.FunctionalitySessionBean;
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
public class ManageFunctionalityManagedBean {
    
    @EJB
    private FunctionalitySessionBean fm;
    private FunctionalityEntity selectedFunctionality;
    private boolean editMode;
    private Long id;

    /** Creates a new instance of ManageEmployeeManagedBean */
    public ManageFunctionalityManagedBean() {
        selectedFunctionality = new FunctionalityEntity();
    }
    
    public boolean isEditMode() {  
        return editMode;  
    }
    
    public List<FunctionalityEntity> getFunctionalities() {
        return fm.getAllFunctionalities();
    }

    public void deleteFunctionality(ActionEvent event) {
        setId((Long)event.getComponent().getAttributes().get("code1"));
        fm.removeFunctionality(getId());
    }
    
    public void saveChanges(ActionEvent event)
    {
        fm.updateFunctionality(getSelectedFunctionality());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }

    /**
     * @return the selectedFunctionality
     */
    public FunctionalityEntity getSelectedFunctionality() {
        return selectedFunctionality;
    }

    /**
     * @param selectedFunctionality the selectedFunctionality to set
     */
    public void setSelectedFunctionality(FunctionalityEntity selectedFunctionality) {
        this.selectedFunctionality = selectedFunctionality;
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
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    } 
}
