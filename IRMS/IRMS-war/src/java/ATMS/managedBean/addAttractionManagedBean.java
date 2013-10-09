/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttractionEntity;
import ATMS.entity.QuotaEntity;
import ATMS.session.AttractionSessionBean;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@RequestScoped
public class addAttractionManagedBean {
    @EJB
    private AttractionSessionBean attractionSessionBean;
    private AttractionEntity attr;
    private QuotaEntity quota=new QuotaEntity();
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public addAttractionManagedBean() {
        attr=new AttractionEntity();
    }

    public AttractionEntity getAttr() {
        return attr;
    }

    public void setAttr(AttractionEntity attr) {
        this.attr = attr;
    }

    public QuotaEntity getQuota() {
        return quota;
    }

    public void setQuota(QuotaEntity quota) {
        this.quota = quota;
    }
    
    public void saveNewAttraction(ActionEvent event) throws IOException{
        try{
            System.out.println("into addAttractionManagedBean: saveNewAttraction");
            quota=attr.getQuota();
            quota.setRestQuota(quota.getMaxQuota());
            attr.setQuota(quota);
            System.out.println("attr name: "+attr.getAttrName());
            attractionSessionBean.addAttraction(attr);
            System.out.println("new attraction added");
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding a new attraction", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Attraction Saved.", ""));
        attr=new AttractionEntity();
    }
    
    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addAttractions.xhtml");
    }

    
    
    
    
}
