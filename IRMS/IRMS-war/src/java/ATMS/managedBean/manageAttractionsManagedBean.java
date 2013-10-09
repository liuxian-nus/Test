/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttractionEntity;
import ATMS.session.AttractionSessionBean;
import Exception.ExistException;
import FBMS.entity.RestaurantEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@ViewScoped
public class manageAttractionsManagedBean {
    @EJB
    private AttractionSessionBean attractionSessionBean;
    private AttractionEntity attr;
    private String attrId;
    private boolean editMode;
   
    /**
     * Creates a new instance of manageAttractionsManagedBean
     */
    public manageAttractionsManagedBean() {
        attr=new AttractionEntity();
    }
    
    public List<AttractionEntity> getAllAttractions(){
        return attractionSessionBean.getAllAttractions();
    }
    
    public AttractionEntity getSelectedAttraction(){
        return attr;
    }
    
    public boolean isEditMode(){
        return editMode;
    }
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        System.out.println("into ManageAttractionManagedBean");
        attractionSessionBean.updateAttraction(getSelectedAttraction());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }
    
    public void deleteAttraction(ActionEvent event) throws ExistException {
        setAttrId((String)event.getComponent().getAttributes().get("code1"));
        System.out.println(("attrId ")+getAttrId());
        attractionSessionBean.removeAttraction(getAttrId());
    }
    
    

    public AttractionSessionBean getAttractionSessionBean() {
        return attractionSessionBean;
    }

    public void setAttractionSessionBean(AttractionSessionBean attractionSessionBean) {
        this.attractionSessionBean = attractionSessionBean;
    }

    public AttractionEntity getAttr() {
        System.out.println("attraction name: "+attr.getAttrName());
        return attr;
    }

    public void setAttr(AttractionEntity attr) {
        this.attr = attr;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
    
    
    
    
    
    
}
