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
public class PushingcartSessionBean implements PushingcartSessionBeanRemote {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    PushingcartEntity pushingcart = new PushingcartEntity();
    
    public PushingcartSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public PushingcartEntity addPushingcart(PushingcartEntity pushingcart) {
        em.persist(pushingcart);
        return pushingcart;
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public boolean updatePushingcart(PushingcartEntity pushingcart)
    {
        em.merge(pushingcart);
        System.out.println("PushingcartSessionBean: pushingcart " + pushingcart.getPushingcartId() + " is successfully updated");
        return true;
    }
    
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public boolean removePushingcart(Long pushingcartId) throws ExistException {
        pushingcart = em.find(PushingcartEntity.class, pushingcartId);
        if (pushingcart != null) {
            em.remove(pushingcart);
            System.out.println("PushingcartSessionBean: The cart has been found and removed!" + pushingcart.getPushingcartId());
            return true;
        } else {
            System.out.println("PushingcartSessionBean: The dish does not exist!");
            return false;
        }
    }
     
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void remove(PushingcartEntity pushingcart) throws ExistException {
        if(pushingcart==null) throw new ExistException ("Pushingcart doesn't exist!");
        em.remove(pushingcart);
    }
     
    @Override
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
    @Override
    public void remove() {
        System.out.println("PushingcartManagerBean: remove()");
    }
}
