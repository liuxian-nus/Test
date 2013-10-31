/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttrExpressPassEntity;
import ATMS.entity.ExpressPassPurchaseEntity;
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
public class ExpressPassPurchaseSessionBean {
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

}
