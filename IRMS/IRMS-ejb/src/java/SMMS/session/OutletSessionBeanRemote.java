/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.OutletEntity;
import SMMS.entity.OutletTransactionEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface OutletSessionBeanRemote {

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    OutletEntity addOutlet(OutletEntity outlet);

    OutletTransactionEntity addOutletTransaction(Long otransactionId, String outletId) throws ExistException;

    //    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    //    public void removeOutlet(int outletId) throws ExistException {
    //        outlet = em.find(OutletEntity.class, outletId);
    //        if (outlet == null) {
    //            throw new ExistException("Outlet doesn't exist!");
    //        }
    //        em.remove(outlet);
    //    }
    List<OutletEntity> getAllOutlets();

    List<OutletEntity> getAvailableOutlets();

    OutletEntity getOutletById(int id) throws ExistException;

    List<OutletEntity> getOutletsByMerchant(String merchantEmail);

    List<OutletEntity> getOutletsByType(String type);

    List<OutletTransactionEntity> getTransactions(int outletId);

    List<OutletEntity> getUnavailableOutlets();

    void persist(Object object);

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean updateOutlet(OutletEntity outlet);
    
}
