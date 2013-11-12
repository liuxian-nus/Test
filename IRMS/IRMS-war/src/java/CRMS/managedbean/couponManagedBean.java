/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.CouponEntity;
import CRMS.session.CouponSessionBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@ViewScoped
public class couponManagedBean {
    @EJB
    private CouponSessionBean couponSessionBean;
    private CouponEntity coupon;

    /**
     * Creates a new instance of manageCouponManagedBean
     */
    public couponManagedBean() {
        coupon=new CouponEntity();
    }

    public CouponSessionBean getCouponSessionBean() {
        return couponSessionBean;
    }

    public void setCouponSessionBean(CouponSessionBean couponSessionBean) {
        this.couponSessionBean = couponSessionBean;
    }

    public CouponEntity getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
    }
    
    

}
