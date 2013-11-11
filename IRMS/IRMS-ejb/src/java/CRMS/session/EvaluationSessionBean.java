/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberTransactionEntity;
import CRMS.entity.RFMModelEntity;
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
    

    public double calculateSizeOfWallet(String memberEmail)

    {
        System.out.println("calculateSizeOfWallet");
        double sizeOfWallet;
        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List <MemberTransactionEntity> allTrans = q.getResultList();
        Iterator <MemberTransactionEntity> itr = allTrans.iterator();
        List <MemberTransactionEntity> resultList = new ArrayList();

        
        while(itr.hasNext())
        {
            MemberTransactionEntity current = itr.next();
            if(current.getMemberEmail().equalsIgnoreCase(memberEmail))
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
    
    public double calculateShareOfWallet(String memberEmail,String mtDepartment)
    {
       double shareOfWallet=0.00;
       System.out.println("calculateShareOfWallet");
        
        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List <MemberTransactionEntity> allTrans = q.getResultList(); 
        Iterator <MemberTransactionEntity> itr = allTrans.iterator();
        List <MemberTransactionEntity> resultList = new ArrayList();
        Double memberTotal = 0.00;
        Double dpmtMemberTotal = 0.00;
        
        while(itr.hasNext())
        {
           MemberTransactionEntity current = itr.next();
           if(current.getMemberEmail().equals(memberEmail))
           {
               memberTotal+=current.getMtAmount();
               if(current.getMtDepartment().equalsIgnoreCase(mtDepartment))
               {
                   dpmtMemberTotal+=current.getMtAmount();
               }
           }
        }
        if(memberTotal!=0)
        shareOfWallet = dpmtMemberTotal/memberTotal;
       return shareOfWallet;
    }
    
    //Model number currently is 1 since only one model is available
    public boolean setRFMParameter(Double Recency, Double Frequency, Double Monetary,int ModelNumber)
    {
        boolean completed = false;
        RFMModelEntity current = em.find(RFMModelEntity.class,ModelNumber );
        if(current!=null)
        {
            current.setFrequency(Frequency);
            current.setMonetary(Monetary);
            current.setRecency(Recency);
            System.out.println("set completed!");
            completed = true;
        }
        return completed;
    }
    
    public Integer calculateRFMValue(String memberEmail)
    {
        Integer RFMValue = 0;
        System.out.println("calculateRFMValue");
        
        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List <MemberTransactionEntity> allTrans = q.getResultList(); 
        Iterator <MemberTransactionEntity> itr = allTrans.iterator();
        
        while(itr.hasNext())
        {
            MemberTransactionEntity current = itr.next();
            
        }
        return RFMValue;
    }
    
    
    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    

}
