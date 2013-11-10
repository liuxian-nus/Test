/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.CouponTypeEntity;
import Exception.ExistException;
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
public class CouponTypeSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private CouponTypeEntity couponType;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public CouponTypeSessionBean(){
        couponType=new CouponTypeEntity();
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
