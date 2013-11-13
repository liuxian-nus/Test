/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.CouponEntity;
import CRMS.entity.CouponTypeEntity;
import CRMS.entity.MemberEntity;
import Exception.ExistException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface CouponSessionBeanRemote {

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void addCoupon(CouponEntity c);

    boolean couponIsExpired(CouponEntity coupon, Date date);

    boolean couponIsUsed(CouponEntity coupon);

    boolean couponIsValid(CouponEntity coupon, Date date);

    CouponEntity generateCoupon(MemberEntity member, CouponTypeEntity type);

    List<CouponEntity> getAllCoupons();

    List<CouponEntity> getAllCouponsWithCouponType(CouponTypeEntity ct);

    CouponEntity getCouponById(Long id) throws ExistException;

    double getDiscountPrice(CouponEntity coupon, double price);


    void removeCoupon(Long id) throws ExistException;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void updateCoupon(CouponEntity c);

    void useCoupon(CouponEntity coupon, Date date, String department);
    
}
