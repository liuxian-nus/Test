/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.OutletEntity;
import SMMS.entity.OutletTransactionEntity;
import java.util.ArrayList;
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
 * @author Cookie
 */
@Stateless
@LocalBean
public class OutletSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    OutletEntity outlet = new OutletEntity();
    OutletEntity thisOutlet = new OutletEntity();
    OutletTransactionEntity otransaction = new OutletTransactionEntity();

    public OutletSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean updateOutlet(OutletEntity outlet) {
        em.merge(outlet);
        System.out.println("OutletSessionBean: outlet " + outlet.getOutletName() + " is successfully updated");
        return true;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public OutletEntity addOutlet(OutletEntity outlet) {
        em.persist(outlet);
        System.out.println("OutletSessionBean: outlet " + outlet.getOutletId() + " is successfully added");
        return outlet;
    }
    
    public OutletTransactionEntity addOutletTransaction(Long otransactionId, String outletId) throws ExistException {
        otransaction = em.find(OutletTransactionEntity.class, otransactionId);
        thisOutlet = em.find(OutletEntity.class, outletId);
        if (otransaction == null) {
            throw new ExistException("OutletSessionBean-->ExistException-->Invalid outlet transaction!");
        }
        thisOutlet.addTransaction(otransaction);
        em.merge(thisOutlet);
        System.out.println("OutletSessionBean--> Outlet" + thisOutlet.getOutletId() + " include new transaction " + otransaction.getId());
        return otransaction;
    }

//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public void removeOutlet(int outletId) throws ExistException {
//        outlet = em.find(OutletEntity.class, outletId);
//        if (outlet == null) {
//            throw new ExistException("Outlet doesn't exist!");
//        }
//        em.remove(outlet);
//    }

    public List<OutletEntity> getAllOutlets() {
        Query q = em.createQuery("SELECT m FROM OutletEntity m");
        List OutletList = new ArrayList<OutletEntity>();
        for (Object o : q.getResultList()) {
            OutletEntity m = (OutletEntity) o;
            OutletList.add(m);
        }
        return OutletList;
    }

    public List<OutletEntity> getAvailableOutlets() {
        System.err.println("in getAvailableoutlet session bean");
        Query q = em.createQuery("SELECT m FROM OutletEntity m");
        List OutletList = new ArrayList<OutletEntity>();
        for (Object o : q.getResultList()) {
            OutletEntity m = (OutletEntity) o;
            if (m.getOutletStatus() == "available") {
                OutletList.add(m);
            }
        }
        System.err.println("in get available outlets sessionbean: outlet list size=" + OutletList.size());
        return OutletList;
    }
    
    
     public List<OutletEntity> getUnavailableOutlets() {
        System.err.println("in getAvailableoutlet session bean");
        Query q = em.createQuery("SELECT m FROM OutletEntity m");
        List OutletList = new ArrayList<OutletEntity>();
        for (Object o : q.getResultList()) {
            OutletEntity m = (OutletEntity) o;
            if (m.getOutletStatus() == "unavailable") {
                OutletList.add(m);
            }
        }
        System.err.println("in get available outlets sessionbean: outlet list size=" + OutletList.size());
        return OutletList;
    }
    
    public OutletEntity getOutletById(int id) throws ExistException {
        System.err.println("in get outlet by id sessionbean");
        OutletEntity thisOutlet = em.find(OutletEntity.class, id);
//        if (thisRoom == null) {
//            throw new ExistException("RoomSessionBean-->ExistException-->Room doesn't exist!");
//        }
        return thisOutlet;
    }
    
    public List<OutletEntity> getOutletsByMerchant(String merchantEmail) {
        System.err.println("in get outlet by merchant session bean");
        Query q = em.createQuery("SELECT m FROM OutletEntity m");
        List OutletList = new ArrayList<OutletEntity>();
        for (Object o : q.getResultList()) {
            OutletEntity m = (OutletEntity) o;
            if (m.getContract().getMerchant().getMerchantEmail()== merchantEmail) {
                OutletList.add(m);
            }
        }
        System.err.println("in get outlets by merchant sessionbean: outlet list size=" + OutletList.size());
        return OutletList;
    }
    
    public List<OutletTransactionEntity> getTransactions(int outletId) {
        System.err.println("in get transactions session bean");
        Query q = em.createQuery("SELECT m FROM OutletTransactionEntity m");
        List OutletList = new ArrayList<OutletEntity>();
        for (Object o : q.getResultList()) {
            OutletTransactionEntity m = (OutletTransactionEntity) o;
            if (m.getTransactionOutlet().getOutletId()==outletId) {
                OutletList.add(m);
            }
        }
        System.err.println("in get transactions sessionbean: outlet list size=" + OutletList.size());
        return OutletList;
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
