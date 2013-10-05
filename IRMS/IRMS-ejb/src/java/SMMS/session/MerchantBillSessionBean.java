/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.BillEntity;
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
public class MerchantBillSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    public MerchantBillSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }
    BillEntity bill = new BillEntity();

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public BillEntity addBill(BillEntity bill) {
        em.persist(bill);
        return bill;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeBill(BillEntity bill) throws ExistException {
        if (bill == null) {
            throw new ExistException("bill doesn't exist!");
        }
        if (bill.getBillStatus() == "paid") {
            throw new ExistException("bill cannot be removed because it has been paid!");
        }
        em.remove(bill);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public BillEntity updateBill(BillEntity bill) throws ExistException {
        if (bill.getBillStatus() == "paid") {
            throw new ExistException("bill cannot be updated because it has been paid!");
        }
        em.merge(bill);
        return bill;
    }
    
    
    public List<BillEntity> getBillByOutlet(int outletId) {
        System.err.println("in get bill by outlet session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getBillOutlet().getOutletId()== outletId) {
                TransactionList.add(m);
            }
        }
        System.err.println("in get bill by outlet sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }
    
    public List<BillEntity> getBillByMerchant(String merchantId) {
        System.err.println("in get bill by merchant session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getBillMerchant()== merchantId) {
                TransactionList.add(m);
            }
        }
        System.err.println("in get bill by outlet sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }
}
