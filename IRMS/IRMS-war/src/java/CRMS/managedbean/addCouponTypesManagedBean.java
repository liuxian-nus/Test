/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.CouponTypeEntity;
import CRMS.session.CouponTypeSessionBean;
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
public class addCouponTypesManagedBean {
    @EJB
    private CouponTypeSessionBean couponTypeSessionBean;
    private CouponTypeEntity ct;
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    /**
     * Creates a new instance of addCouponTypeManagedBean
     */
    public addCouponTypesManagedBean() {
        ct=new CouponTypeEntity();
    }
    
    public void saveNewCouponType(ActionEvent event) throws IOException{
        try{
            System.out.println("addCouponTypeManagedBean : saveNewCouponType"); 
            couponTypeSessionBean.addCouponType(ct);
            System.out.println("new coupon type added");    
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding a new coupon type", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Coupon Type Saved.", ""));
        ct=new CouponTypeEntity();
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
    
    
}
