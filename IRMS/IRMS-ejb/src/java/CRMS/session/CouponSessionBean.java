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
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jieqiong
 */
@Stateless
@LocalBean
public class CouponSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    CouponEntity coupon;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public CouponSessionBean(){
        coupon=new CouponEntity();
    }
    
    public void generateCoupon(Date date, MemberEntity member, CouponTypeEntity type){
        System.out.println("CouponSessionBean : generateCoupon");
        coupon=new CouponEntity();
        coupon.setCouponIssueDate(date);
        coupon.setCouponOwner(member);
        coupon.setCouponType(type);
        coupon.setStatus("New");
        em.persist(coupon);
        System.out.println("coupon has been generated");
    }
    
    public void useCoupon(CouponEntity coupon, Date date,String department){
        System.out.println("CouponSessionBean : useCoupon");
        coupon.setDepartment(department);
        coupon.setStatus("Used");
        em.merge(coupon);
        em.flush();
        System.out.println("coupon has been used");
    }
    
    public boolean couponIsValid(CouponEntity coupon, Date date){
        System.out.println("CouponSessionBean : couponIsNotUsed");
        if((!couponIsUsed(coupon))&&(!couponIsExpired(coupon,date))){
            System.out.println("coupon is still valid");
            return true;
        }
        else return false;
        
    }
    
    public boolean couponIsUsed (CouponEntity coupon){
       System.out.println("CouponSessionBean : couponIsNotUsed");
       if(coupon.getStatus().equals("Used")){
           System.out.println("coupon has been used");
           return true;
       }
       else return false;        
   }
    
    public boolean couponIsExpired (CouponEntity coupon, Date date){
       System.out.println("CouponSessionBean : couponIsExpired");
       if(coupon.getCouponType().getCpEndDate().before(date)){
           System.out.print("coupon has expired");
           coupon.setStatus("Expired");
           updateCoupon(coupon);
           return true;
       }
       else return false;
               
   }
    
    public double getDiscountPrice(CouponEntity coupon, double price){
        System.out.println("CouponSessionBean : getDiscountPrice");
        CouponTypeEntity ct=coupon.getCouponType();
        double discount=ct.getDiscount();
        System.out.println("price after discount using coupon: "+price*discount);
        return price*discount;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCoupon(CouponEntity c){
        System.out.println("CouponSessionBean : addCoupon");
        em.persist(c);
        return;
    }
    
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCoupon(CouponEntity c){
        System.out.println("CouponTypeSessionBean : updateCoupon");
        em.merge(c);
        em.flush();
        return;
    }
   
   public List<CouponEntity> getAllCoupons(){
       System.out.println("CouponSessionBean : getAllCoupons");
       Query query = em.createQuery("SELECT c FROM CouponEntity c");
       return query.getResultList();
   }
   
   public CouponEntity getCouponById(Long id) throws ExistException{
       System.out.println("CouponSessionBean : getCouponById");
       coupon=em.find(CouponEntity.class, id);
       if(coupon==null) return null;
       else return coupon;
   }
   
   public void removeCoupon(Long id) throws ExistException{
       System.out.println("CouponSessionBean : removeCoupon");
       coupon=em.find(CouponEntity.class, id);
       if(coupon==null) throw new ExistException("coupon doesn't exist");
       else em.remove(coupon);
       System.out.println("the ticket has been removed.");
       return;
   }
   
    public void persist(Object object) {
        em.persist(object);
    }

}
