/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.ItemTransactionEntity;
import SMMS.entity.SMItemEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xinyu
 */
@Stateless
@LocalBean
public class SMItemSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private SMItemEntity item = new SMItemEntity();

    public void persist(Object object) {
        em.persist(object);
    }

    public SMItemEntity addItem(SMItemEntity item) {
        em.persist(item);
        System.out.println("OutletSessionBean: outlet " + item.getId() + " is successfully added");
        return item;
    }
    
    
    public ItemTransactionEntity addItemTransaction(ItemTransactionEntity itransaction) {
        em.persist(itransaction);
        System.out.println("OutletSessionBean: outlet " + item.getId() + " is successfully added");
        return itransaction;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public SMItemEntity updateItem(SMItemEntity item) {
        em.merge(item);
        System.out.println("OutletSessionBean: outlet " + item.getId() + " is successfully added");
        return item;
    }
    
    public ItemTransactionEntity updateTransactionItem(ItemTransactionEntity itransaction) {
        em.merge(itransaction);
        System.out.println("OutletSessionBean: outlet " + item.getId() + " is successfully added");
        return itransaction;
    }

    public SMItemEntity getItemById(Long id) throws ExistException {
        System.err.println("in get outlet by id sessionbean");
        SMItemEntity thisOutlet = em.find(SMItemEntity.class, id);
//        if (thisRoom == null) {
//            throw new ExistException("RoomSessionBean-->ExistException-->Room doesn't exist!");
//        }
        return thisOutlet;
    }

    public List<SMItemEntity> getAllItems() {
        Query q = em.createQuery("SELECT m FROM SMItemEntity m");
        List OutletList = new ArrayList<SMItemEntity>();
        for (Object o : q.getResultList()) {
            SMItemEntity m = (SMItemEntity) o;
            OutletList.add(m);
        }
        return OutletList;
    }
}
