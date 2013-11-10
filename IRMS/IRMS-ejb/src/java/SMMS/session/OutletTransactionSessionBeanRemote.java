/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
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
public interface OutletTransactionSessionBeanRemote {

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    OutletTransactionEntity addTransaction(OutletTransactionEntity transaction);

    double calculateCommission(int month, int outletId);

    List<OutletTransactionEntity> getTransactionByOutlet(int outletId);

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    void persist(Object object);

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void removeTransaction(OutletTransactionEntity transaction) throws ExistException;
    
    public OutletTransactionEntity getTransaction(Long transactionId);
    
}
