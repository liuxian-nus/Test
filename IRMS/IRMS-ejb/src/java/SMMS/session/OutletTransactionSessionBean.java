/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.OutletEntity;
import SMMS.entity.OutletTransactionEntity;
import SMMS.entity.PushingcartEntity;
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
public class OutletTransactionSessionBean implements OutletTransactionSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    OutletTransactionEntity transaction = new OutletTransactionEntity();

    public OutletTransactionSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public OutletTransactionEntity addTransaction(OutletTransactionEntity transaction) {
//        OutletTransactionEntity at = transaction;
//        at.getTransactionDate().setYear(transaction.getTransactionDate().getYear()-1990);
//        at.getTransactionDate().setMonth(transaction.getTransactionDate().getMonth()-1);
        em.persist(transaction);
        return transaction;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void removeTransaction(OutletTransactionEntity transaction)throws ExistException {
        if(transaction==null) throw new ExistException ("Transaction doesn't exist!");
        em.remove(transaction);
    }
    
    @Override
    public List<OutletTransactionEntity> getTransactionByOutlet(int outletId) {
        System.err.println("in get transaction by outlet session bean");
        Query q = em.createQuery("SELECT m FROM OutletTransactionEntity m");
        List TransactionList = new ArrayList<OutletTransactionEntity>();
        for (Object o : q.getResultList()) {
            OutletTransactionEntity m = (OutletTransactionEntity) o;
            if (m.getTransactionOutlet().getOutletId()== outletId) {
                TransactionList.add(m);
            }
        }
        System.err.println("in get TransactionList by outlet sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }
    
    @Override
    public double calculateCommission(int month, int outletId) {
        System.err.println("in calculate transaction commission session bean");
        Query q = em.createQuery("SELECT m FROM OutletTransactionEntity m");
        List TransactionList = new ArrayList<OutletTransactionEntity>();
        double amount = 0.00;
        for (Object o : q.getResultList()) {
            OutletTransactionEntity m = (OutletTransactionEntity) o;
            if (m.getTransactionOutlet().getId()== outletId) {
                TransactionList.add(m);
                amount= amount+m.getTransactionAmount();
            }
        }
        System.err.println("in get TransactionList by outlet sessionbean: Transaction List size=" + TransactionList.size()+amount);
        return amount;
    }
    
    public OutletTransactionEntity getTransaction(Long transactionId)
    {
        System.out.println("OutletTransactionSessionBean: in GetTransaction");
        
        OutletTransactionEntity current = em.find(OutletTransactionEntity.class, transactionId);
        return current;
    }
    
}
