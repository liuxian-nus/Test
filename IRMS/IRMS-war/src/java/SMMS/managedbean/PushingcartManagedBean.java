/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import Exception.ExistException;
import SMMS.entity.PushingcartEntity;
import SMMS.session.PushingcartSessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class PushingcartManagedBean implements Serializable {

    @EJB
    private PushingcartSessionBean pushingcartSessionBean;
    private PushingcartEntity pushingcart;
    private boolean editMode;
    private Long cartId;

    public PushingcartSessionBean getPushingcartSessionBean() {
        return pushingcartSessionBean;
    }

    public void setPushingcartSessionBean(PushingcartSessionBean pushingcartSessionBean) {
        this.pushingcartSessionBean = pushingcartSessionBean;
    }

    public List<PushingcartEntity> getAllPushingcarts() throws ExistException, IOException {
        System.err.println("No:1 in get all carts");
        return pushingcartSessionBean.getAllPushingcarts();

    }

    public void deletePushingcart(ActionEvent event) throws ExistException {

        try {
            System.out.println("No4: we are in DELETE pushing cart in managedbean" + pushingcart.getPushingcartId());
            pushingcartSessionBean.removePushingcart(pushingcart.getPushingcartId());
            System.out.println("we are after deleting pushing cart in managedbean");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new pushing cart", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pushing cart has been deleted.", ""));
    }

    public void addPushingcart(ActionEvent event) throws IOException {

        try {
            System.out.println("we are in add pushing cart in managedbean" + pushingcart.getPushingcartId());
            pushingcartSessionBean.addPushingcart(pushingcart);
            System.out.println("we are after add pushing cart in managedbean");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new employee", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Pushing cart added.", ""));
//            emailSessionBean.emailInitialPassward(employee.getPersonalEmail(), initialPwd); //send email
        pushingcart = new PushingcartEntity();
    }

    public void updatePushingcart(ActionEvent event) throws ExistException {

        try {
            System.out.println("we are in update pushing cart in managedbean" + pushingcart.getPushingcartId());
            pushingcartSessionBean.updatePushingcart(pushingcart);
            System.out.println("we are after updating pushing cart in managedbean");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new employee", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pushing cart has been updated.", ""));
    }

    public PushingcartEntity getPushingcart() {
        System.err.println("No2 Im here in getting pushing cart" + pushingcart.getPushingcartId());
        return pushingcart;
    }

    public void setPushingcart(PushingcartEntity pushingcart) {
        System.err.println("No4 Im here in getting pushing cart" + pushingcart.getPushingcartId());
        this.pushingcart = pushingcart;
    }

    public boolean isEditMode() {
        System.err.println("1Edit mode: " + editMode);
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Long getCartId() {
        System.err.println("No3: we are in getting cart ID" + cartId);
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
        System.err.println("No4: we are in setting cart ID" + cartId);

    }

    /**
     * Creates a new instance of PushingcartManagedBean
     */
    public PushingcartManagedBean() {
        pushingcart = new PushingcartEntity();
    }
}
