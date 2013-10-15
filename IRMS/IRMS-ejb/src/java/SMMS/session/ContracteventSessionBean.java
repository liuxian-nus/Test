/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import SMMS.entity.ContracteventEntity;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cookie
 */
@Stateless
@LocalBean
public class ContracteventSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     ContracteventEntity newevent = new ContracteventEntity();
     
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ContracteventEntity addContractevent(ContracteventEntity contractevent) {
        System.out.println("ContractEvent Session bean: add contractevent called");
        em.persist(contractevent);
        return contractevent;
    }
}
