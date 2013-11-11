/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import SMMS.entity.ContracteventEntity;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ContracteventSessionBeanRemote {

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    ContracteventEntity addContractevent(ContracteventEntity contractevent);

    void persist(Object object);

    boolean updateContractEvent(ContracteventEntity cevent);
    
}
