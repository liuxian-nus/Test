/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.PushingcartEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cookie
 */
@Stateless
@LocalBean
public class PushingcartSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    PushingcartEntity pushingcart = new PushingcartEntity();
    
    public PushingcartSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public PushingcartEntity addPushingcart(PushingcartEntity pushingcart) {
        em.persist(pushingcart);
        return pushingcart;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean updatePushingcart(PushingcartEntity pushingcart)
    {
        em.merge(pushingcart);
        System.out.println("PushingcartSessionBean: pushingcart " + pushingcart.getPushingcartId() + " is successfully updated");
        return true;
    }
    
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removePushingcart(Long pushingcartId) throws ExistException {
        pushingcart = em.find(PushingcartEntity.class, pushingcartId);
        if(pushingcart==null) throw new ExistException ("Pushingcart doesn't exist!");
        em.remove(pushingcart);
    }
     
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(PushingcartEntity pushingcart) throws ExistException {
        if(pushingcart==null) throw new ExistException ("Pushingcart doesn't exist!");
        em.remove(pushingcart);
    }
     
    public List<PushingcartEntity> getAllPushingcarts(){
        Query q = em.createQuery("SELECT m FROM PushingcartEntity m");
        List PushingcartList = new ArrayList<PushingcartEntity>();
        for (Object o: q.getResultList()) {
            PushingcartEntity m = (PushingcartEntity) o;
            PushingcartList.add(m);
        }
        return PushingcartList;
    }
     
     
     @Remove
    public void remove() {
        System.out.println("PushingcartManagerBean: remove()");
    }
}
