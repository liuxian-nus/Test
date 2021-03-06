/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.OverbookingQuotaEntity;
import ACMS.session.OverbookingSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class OverbookingManagedBean implements Serializable {

    @EJB
    private OverbookingSessionBean obSessionBean;
    private OverbookingQuotaEntity ob;
    private int newQuota;
    private int suggestedQuota;
    private int demandMean;
    private int demandSD;
    private double ce;

    /**
     * Creates a new instance of OverbookingManagedBean
     */
    public OverbookingManagedBean() {
    }
    
    public OverbookingSessionBean getObSessionBean() {
        return obSessionBean;
    }
    
    public void setObSessionBean(OverbookingSessionBean obSessionBean) {
        this.obSessionBean = obSessionBean;
    }
    
    public OverbookingQuotaEntity getOb() {
        return ob;
    }
    
    public void setOb(OverbookingQuotaEntity ob) {
        this.ob = ob;
    }
    
    public int getNewQuota() {
        return newQuota;
    }
    
    public void setNewQuota(int newQuota) {
        this.newQuota = newQuota;
    }
    
    public int getSuggestedQuota() {
        return suggestedQuota;
    }
    
    public void setSuggestedQuota(int suggestedQuota) {
        this.suggestedQuota = suggestedQuota;
    }
    
    public void doUpdate(ActionEvent event) throws IOException, ExistException {
        
        try {
            obSessionBean.setQuota(newQuota);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when saving new quota", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Overbooking Quota Saved.", ""));
    }

    public void doCalculate(ActionEvent event) throws IOException, ExistException {
        
        try {
            ob = new OverbookingQuotaEntity();
            suggestedQuota = obSessionBean.calculateSuggestedQuota(demandMean, demandSD, ce);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when calculate quota", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "System Calculated Overbooking Quota.", ""));
    }
    
    public int getDemandMean() {
        return demandMean;
    }
    
    public void setDemandMean(int demandMean) {
        this.demandMean = demandMean;
    }
    
    public int getDemandSD() {
        return demandSD;
    }
    
    public void setDemandSD(int demandSD) {
        this.demandSD = demandSD;
    }
    
    public double getCe() {
        return ce;
    }
    
    public void setCe(double ce) {
        this.ce = ce;
    }
}
