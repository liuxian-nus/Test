/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttrExpressPassEntity;
import ATMS.session.AttrExpressPassSessionBean;
import Exception.ExistException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@ViewScoped
public class manageExpressPassManagedBean {
    @EJB
    private AttrExpressPassSessionBean attrExpressPassSessionBean;
    private AttrExpressPassEntity ep;
    private Long epId;
    private boolean editMode;
    

    /**
     * Creates a new instance of manageExpressPassManagedBean
     */
    public manageExpressPassManagedBean() {
        ep=new AttrExpressPassEntity();
    }
    
    public List<AttrExpressPassEntity> getAllEPs(){
        return attrExpressPassSessionBean.getAllEPs();
    }
    
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        System.out.println("into ManageEPManagedBean");
        attrExpressPassSessionBean.updateEP(ep);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }
    
    public void deleteEP(ActionEvent event) throws ExistException {
        setEpId((Long)event.getComponent().getAttributes().get("code1"));
        System.out.println(("epId ")+getEpId());
        attrExpressPassSessionBean.removeEP(getEpId());
    }

    public AttrExpressPassSessionBean getAttrExpressPassSessionBean() {
        return attrExpressPassSessionBean;
    }

    public void setAttrExpressPassSessionBean(AttrExpressPassSessionBean attrExpressPassSessionBean) {
        this.attrExpressPassSessionBean = attrExpressPassSessionBean;
    }

    public AttrExpressPassEntity getEp() {
        System.out.println("epname: "+ep.getAttrEPName());
        return ep;
    }

    public void setEp(AttrExpressPassEntity ep) {
        this.ep = ep;
    }

    public Long getEpId() {
        return epId;
    }

    public void setEpId(Long epId) {
        this.epId = epId;
    }

    public boolean isEditMode() {
        System.out.println("editMode: "+editMode);
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
    
    
    
    
    
}
