/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.CouponTypeEntity;
import CRMS.session.CouponTypeSessionBean;
import Exception.ExistException;
import java.io.IOException;
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
public class couponTypesManagedBean {
    @EJB
    private CouponTypeSessionBean couponTypeSessionBean;
    private CouponTypeEntity ct;
    private Long ctId;
    private boolean editMode;
    
    /**
     * Creates a new instance of couponTypesManagedBean
     */
    public couponTypesManagedBean() {
        ct=new CouponTypeEntity();
    }
    
    public List<CouponTypeEntity> getAllCouponTypes(){
        System.out.println("get all coupon types");
        return couponTypeSessionBean.getAllCouponTypes();
    }
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        System.out.println("couponTypesManagedBean : saveChanges");
        System.out.println("ct name: "+ct.getCouponName());
        System.out.println("editMode: "+editMode);   
        System.out.println("editMode: "+ct.getCpStartDate());    
        System.out.println("editMode: "+ct.getCpEndDate());        
        couponTypeSessionBean.updateCouponType(getCt());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }
    
    public void deleteCouponType(ActionEvent event) throws ExistException {
        setCtId((Long)event.getComponent().getAttributes().get("code1"));
        couponTypeSessionBean.removeCouponType(getCtId());
    }
    
    public void listCoupons(ActionEvent event) throws IOException {
        System.out.println("list coupons");
        FacesContext.getCurrentInstance().getExternalContext().redirect("listCoupons.xhtml");
    }

    public CouponTypeSessionBean getCouponTypeSessionBean() {
        return couponTypeSessionBean;
    }

    public void setCouponTypeSessionBean(CouponTypeSessionBean couponTypeSessionBean) {
        this.couponTypeSessionBean = couponTypeSessionBean;
    }

    public CouponTypeEntity getCt() {
        return ct;
    }

    public void setCt(CouponTypeEntity ct) {
        this.ct = ct;
    }

    public Long getCtId() {
        return ctId;
    }

    public void setCtId(Long ctId) {
        this.ctId = ctId;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
    
    
}
