/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.managedbean;

import Exception.ExistException;
import SMMS.entity.OutletEntity;
import SMMS.entity.PushingcartEntity;
import SMMS.session.OutletSessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class OutletManagedBean implements Serializable {

    @EJB
    private OutletSessionBean outletSessionBean;
    private OutletEntity outlet = new OutletEntity();
    private int outletId;

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public OutletSessionBean getOutletSessionBean() {
        return outletSessionBean;
    }

    public void setOutletSessionBean(OutletSessionBean outletSessionBean) {
        this.outletSessionBean = outletSessionBean;
    }

    public OutletEntity getOutlet() {
        System.err.println("we in in getting outlet" + outlet.getOutletId());
        return outlet;
    }

    public void setOutlet(OutletEntity outlet) {
        this.outlet = outlet;
    }

    /**
     * Creates a new instance of OutletManagedBean
     */
    public OutletManagedBean() {
    }

    public List<OutletEntity> getAllOutlets() throws ExistException, IOException {
        System.err.println("No:1 in get all outlets");
        return outletSessionBean.getAllOutlets();

    }

    public void updateOutlet(ActionEvent event) throws ExistException {

        try {
            System.out.println("we are in update outlet in 4" + outletId);
//            setOutletId((int) event.getComponent().getAttributes().get("lala"));
//            System.out.println("we are in update outlet after setting lalala managedbean" + outletId);
            outletSessionBean.updateOutlet(outlet);
            System.out.println("we are after updating outlet in managedbean");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when updating new outlet", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Outlet has been updated.", ""));
    }
}
