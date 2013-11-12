/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.CouponEntity;
import CRMS.entity.CouponTypeEntity;
import Exception.ExistException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
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
public class CouponTypeSessionBean {
    @EJB
    private CouponSessionBean couponSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private CouponTypeEntity couponType;
    private CouponEntity coupon;
    @Resource
    private SessionContext ctx;
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public CouponTypeSessionBean(){
        couponType=new CouponTypeEntity();
    }
    
    public void createExpireTimers(Date endDate) {
        System.out.println("CouponSessionBean : createExpireTimers");
        TimerService timerService = ctx.getTimerService();
        TimerConfig config = new TimerConfig("setExpired", true);
        Timer timer = (Timer) timerService.createSingleActionTimer(endDate, config);
        System.out.println("in session bean test" + timer.getInfo().toString());
    }
    
    @Timeout
    public void handleTimeout(Timer timer) throws ExistException {

        System.out.println("in handle timeout test");
        if (timer.getInfo().toString().equals("setExpired")) {
            List<CouponEntity> coupons=couponSessionBean.getAllCouponsWithCouponType(couponType);
            for(int i=0;i<coupons.size();i++){
                coupon=coupons.get(i);
                coupon.setStatus("Expired");
                couponSessionBean.updateCoupon(coupon);
                System.out.println("coupon status set: "+coupon.getStatus());
            }
        }
    }
    
    
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCouponType(CouponTypeEntity ct){
        System.out.println("CouponTypeSessionBean : addCouponType");
        System.out.println("ct id: "+ct.getCouponTypeId());
        em.persist(ct);
        return;
    }
    
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCouponType(CouponTypeEntity ct){
        System.out.println("CouponTypeSessionBean : updateCouponType");
        em.merge(ct);
        em.flush();
        return;
    }
   
   public void setCouponType(CouponTypeEntity ct){
       System.out.println("CouponTypeSessionBean : setCouponType");
       couponType=ct;
   }
   
   public List<CouponTypeEntity> getAllCouponTypes(){
       System.out.println("CouponTypeSessionBean : getAllCouponTypes");
        Query query = em.createQuery("SELECT ct FROM CouponTypeEntity ct");
        return query.getResultList();
    }
    
    public CouponTypeEntity getCouponTypeById(Long id) throws ExistException{
        System.out.println("CouponTypeSessionBean : getCouponTypeById");
        couponType=em.find(CouponTypeEntity.class, id);
        if(couponType==null) throw new ExistException("Coupon type doesn't exist");
        else return couponType;
    }
    
    public void removeCouponType(Long id)throws ExistException {
        System.out.println("CouponTypeSessionBean :removeCouponType");
        couponType=em.find(CouponTypeEntity.class, id);
        if(couponType==null) throw new ExistException("Coupon type doesn't exist");
        em.remove(couponType);
        System.out.println("the ticket has been removed.");
        return;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    

}
