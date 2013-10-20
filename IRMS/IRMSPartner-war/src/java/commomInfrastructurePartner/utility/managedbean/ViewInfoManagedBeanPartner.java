/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commomInfrastructurePartner.utility.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import Exception.ExistException;
import SMMS.entity.MerchantEntity;
import SMMS.session.MerchantSessionBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ViewInfoManagedBeanPartner {

    @EJB
    private MerchantSessionBean em;
    private MerchantEntity selectedMerchant;
    private boolean editMode;
    private String merchantId;

    /**
     * Creates a new instance of ViewInfoManagedBeanPartner
     */
    public ViewInfoManagedBeanPartner() {
        selectedMerchant = new MerchantEntity();
    }

    public boolean isEditMode() {
        return editMode;
    }

    public MerchantEntity getMerchants() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String loginId = (String) request.getSession().getAttribute("userId");

        return em.getMerchantById(loginId);
    }

    public void saveChanges(ActionEvent event) throws ExistException {
        em.updateMerchant(selectedMerchant);
        addMessage("Changes saved.");
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public MerchantEntity getSelectedMerchant() {
        return selectedMerchant;
    }

    public void setSelectedMerchant(MerchantEntity selectedMerchant) {
        this.selectedMerchant = selectedMerchant;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
