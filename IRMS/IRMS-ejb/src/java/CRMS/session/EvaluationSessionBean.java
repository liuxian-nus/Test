/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberTransactionEntity;
import java.util.ArrayList;
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
public class EvaluationSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    MemberTransactionEntity mte;
    

    public double calculateSizeOfWallet(String memberEmail,String mtDepartment)

    {
        double sizeOfWallet = 0.00;
        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List <MemberTransactionEntity> allTrans = q.getResultList();
        Iterator <MemberTransactionEntity> itr = allTrans.iterator();
        List <MemberTransactionEntity> resultList = new ArrayList();

        
        while(itr.hasNext())
        {
            MemberTransactionEntity current = itr.next();
            if(current.getMemberEmail().equalsIgnoreCase(memberEmail)&& current.getMtDepartment().equalsIgnoreCase(mtDepartment))
             resultList.add(current);
        }
        
        Iterator <MemberTransactionEntity> itr2 = resultList.iterator();
        Double memberTotal = 0.00;
        
        if(!resultList.isEmpty())
        {
            while(itr2.hasNext())
            {
                MemberTransactionEntity current2 = itr2.next();
                memberTotal+=current2.getMtAmount();
            }
        }
        sizeOfWallet = memberTotal;
        return sizeOfWallet;
    }
    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    

}
