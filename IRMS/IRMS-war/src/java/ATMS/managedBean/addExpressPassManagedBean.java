/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttrExpressPassEntity;
import ATMS.entity.AttractionEntity;
import ATMS.session.AttrExpressPassSessionBean;
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
public class addExpressPassManagedBean {
    @EJB
    private AttractionSessionBean attractionSessionBean;
    @EJB
    private AttrExpressPassSessionBean attrExpressPassSessionBean;
    
    
    private AttrExpressPassEntity ep;
    private Long epId;
    private AttractionEntity attr;
    private String attrId;
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    /**
     * Creates a new instance of addExpressPassManagedBean
     */
    public addExpressPassManagedBean() {
        ep=new AttrExpressPassEntity();
    }
    
    public void saveNewExpressPass(ActionEvent event) throws IOException{
        try{
            System.out.println("into addEPManagedBean: saveNewEP"); 
            attr=attractionSessionBean.getAttrById(attrId);
            System.out.println("attr: "+attr.getAttrName());
            ep.setAttr(attr);
            attrExpressPassSessionBean.addEP(ep);
            System.out.println("new EP added");
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding a new express pass", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Express Pass Saved.", ""));
        ep=new AttrExpressPassEntity();
    }
    
    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addAttrExpressPass.xhtml");
    }

    public AttractionSessionBean getAttractionSessionBean() {
        return attractionSessionBean;
    }

    public void setAttractionSessionBean(AttractionSessionBean attractionSessionBean) {
        this.attractionSessionBean = attractionSessionBean;
    }

    public AttrExpressPassSessionBean getAttrExpressPassSessionBean() {
        return attrExpressPassSessionBean;
    }

    public void setAttrExpressPassSessionBean(AttrExpressPassSessionBean attrExpressPassSessionBean) {
        this.attrExpressPassSessionBean = attrExpressPassSessionBean;
    }

    public AttrExpressPassEntity getEp() {
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

    public AttractionEntity getAttr() {
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

    
}
