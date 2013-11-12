/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.PaypalTransactionEntity;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class PaypalTransactionSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public PaypalTransactionEntity getPaypalTranByToken(String token)
    {
        Query q = em.createQuery("SELECT PTE FROM PAYPALTRANSACTIONENTITY PTE");
        List <PaypalTransactionEntity> list = q.getResultList();
        Iterator <PaypalTransactionEntity> itr = list.iterator();
        PaypalTransactionEntity result = new PaypalTransactionEntity();
        
        while(itr.hasNext())
        {
            PaypalTransactionEntity current = itr.next();
            if(current.getToken().equalsIgnoreCase(token))
            {
                result = current;
                break;
            }
        }
        
        return result;
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
