/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.session;

import ATMS.entity.AttractionEntity;
import ATMS.entity.QuotaEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jieqiong
 */
@Stateless
@LocalBean
public class AttractionSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    AttractionEntity attr;
    QuotaEntity quota=new QuotaEntity();
    
    public AttractionSessionBean(){
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addAttraction(AttractionEntity attr){
        em.persist(attr);
        return;
    }
    
    public List<AttractionEntity> getAllAttractions()throws NoResultException{
        Query query = em.createQuery("SELECT a FROM AttractionEntity a");
        return query.getResultList();
    }
    
    public AttractionEntity getAttrById(String id){
        return em.find(AttractionEntity.class,id);
    }
    
    public void updateAttraction(AttractionEntity attr){
        System.out.println("into AttractoinSessionBean: update Attraction");
        quota=attr.getAttrQuota();
        quota.setRestQuota(quota.getMaxQuota());
        attr.setAttrQuota(quota);      
        em.merge(attr);
        em.flush();
        System.out.println("updated");
        return; 
    }
    
    public boolean removeAttraction(String attrId)throws ExistException {
        System.out.println("into removeAttraction");
        attr = em.find(AttractionEntity.class, attrId);
        if(attr == null) {
            throw new ExistException("Attraction does not exist!");
        }
        em.remove(attr);
        System.out.println("the attraction has been removed.");
        return true;
    } 

    public void persist(Object object) {
        em.persist(object);
    }
    



}
