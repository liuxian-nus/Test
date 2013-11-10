/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.PushingcartEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface PushingcartSessionBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    PushingcartEntity addPushingcart(PushingcartEntity pushingcart);

    List<PushingcartEntity> getAllPushingcarts();

    void persist(Object object);

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void remove(PushingcartEntity pushingcart) throws ExistException;

    @Remove
    void remove();

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean removePushingcart(Long pushingcartId) throws ExistException;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    boolean updatePushingcart(PushingcartEntity pushingcart);
    
}
