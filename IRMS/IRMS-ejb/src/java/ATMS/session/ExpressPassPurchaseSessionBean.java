/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttrExpressPassEntity;
import ATMS.entity.ExpressPassPurchaseEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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
public class ExpressPassPurchaseSessionBean {
    @EJB
    private AttrExpressPassSessionBean attrExpressPassSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private ExpressPassPurchaseEntity epp;
    
    
    public ExpressPassPurchaseSessionBean(){
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long addEPPurchase(ExpressPassPurchaseEntity epp){
        em.persist(epp);
        System.out.println("eppId: "+epp.getEppId());
        return epp.getEppId();
    }
    
    public void updateEPPurchase(ExpressPassPurchaseEntity epp){
        em.merge(epp);
        em.flush();
        System.out.println("updated");
        return;
    }
    
    public List<ExpressPassPurchaseEntity> getAllEPPurchases(){
        //System.out.println("into AttrComboSessionBean: getAllAttrCombo");
        Query query = em.createQuery("SELECT epp ExpressPassPurchaseEntity epp");
        return query.getResultList();
    }
    
    public ExpressPassPurchaseEntity getEPPurchaseById(Long id){
        return em.find(ExpressPassPurchaseEntity.class, id);
    }
    
    public double calculateFee(AttrExpressPassEntity ep, int quantity){
        double ticketPrice=ep.getAttrEPPrice();
        return ticketPrice*quantity;
    }
    
    public ExpressPassPurchaseEntity prepareEPForVIP() throws ExistException{
        System.out.println("expressPassPurchaseSessionBean : prepareEPForVIP");
        epp=new ExpressPassPurchaseEntity();
        Date today=new Date();
        
        List<AttrExpressPassEntity> eps=epp.getAttrEPs();
        AttrExpressPassEntity thisEP=attrExpressPassSessionBean.getEPById(Long.valueOf(String.valueOf(20)));
        eps.add(thisEP);
        epp.setAttrEPs(eps);
        List<Integer> quantities=epp.getEpQuantities();
        quantities.add(1);
        epp.setEpQuantities(quantities);
        epp.setEpFee(0);
        epp.setEpBookDate(today);
        epp.setEppStatus("Purchased");
        epp.setEppRemarks("This express pass is for VIP only");
        
        addEPPurchase(epp);
        return epp;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ExpressPassPurchaseEntity getEpp() {
        return epp;
    }

    public void setEpp(ExpressPassPurchaseEntity epp) {
        this.epp = epp;
    }

    public void persist1(Object object) {
        em.persist(object);
    }

}
