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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private OutletEntity selected;

   

    public void setOutletLevel(int outletLevel) {
        this.outletLevel = outletLevel;
    }

    public int getOutletNo() {
        return outletNo;
    }

    public void setOutletNo(int outletNo) {
        this.outletNo = outletNo;
    }
    private int outletLevel;
    private int outletNo;

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
        selected = new OutletEntity();
    }

    public List<OutletEntity> getAllOutlets() throws ExistException, IOException {
        System.err.println("No:1 in get all outlets");
        return outletSessionBean.getAllOutlets();

    }

    public List<OutletEntity> getAvailableOutlets() throws ExistException, IOException {
        System.err.println("No:1 in get all outlets");
        return outletSessionBean.getAvailableOutlets();
    }

    public List<OutletEntity> getUnavailableOutlets() throws ExistException, IOException {
        System.err.println("No:1 in get all outlets");
        return outletSessionBean.getUnavailableOutlets();
    }

    public void viewTransaction(ActionEvent event) throws IOException, ExistException {

        System.out.println("No1:in displaying transaction bean " + selected.getOutletId());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            selected = (OutletEntity) event.getComponent().getAttributes().get("selectedOutlet");
            System.out.println("N02: in displaying bean " + selected.getOutletId());

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisOutlet", selected);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("operatorViewTransaction.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when viewing contract", ""));
            return;
        }
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

    public void addOutlet(ActionEvent event) throws IOException, ExistException {
        System.out.println("add new outlet: " + outletLevel+outletNo);
        outlet.setOutletId(outletLevel, outletNo);
        System.out.println("after setting id"+outlet.getOutletId());
        
        if (outletSessionBean.getOutletById(outlet.getOutletId()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Outlet id already exists, please try again", ""));
            return;
        }
        outletSessionBean.addOutlet(outlet);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New outlet saved.", ""));
        outlet = new OutletEntity();
        FacesContext.getCurrentInstance().getExternalContext().redirect("outletManagement.xhtml");
    }
    
     public OutletEntity getSelected() {
        return selected;
    }

    public void setSelected(OutletEntity selected) {
        this.selected = selected;
    }

    public int getOutletLevel() {
        return outletLevel;
    }
}
