/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import ESMS.entity.ShowContractEntity;
import ESMS.session.ShowContractSessionBean;
import Exception.ExistException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ViewShowContractManagedBean {
    @EJB
    private ShowContractSessionBean showContractSessionBean;
    private ShowContractEntity showContract;
    private Long id;
            
    public ViewShowContractManagedBean() {
        showContract = new ShowContractEntity();
    }
    
    public List<ShowContractEntity> getShowContracts() throws ExistException {
        return showContractSessionBean.getAllShowContracts();
    }
    
    public void deleteShowContract(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code1"));
        showContractSessionBean.deleteShowContract(getId());
    }

    public ShowContractSessionBean getShowContractSessionBean() {
        return showContractSessionBean;
    }

    public void setShowContractSessionBean(ShowContractSessionBean showContractSessionBean) {
        this.showContractSessionBean = showContractSessionBean;
    }

    public ShowContractEntity getShowContract() {
        return showContract;
    }

    public void setShowContract(ShowContractEntity showContract) {
        this.showContract = showContract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
