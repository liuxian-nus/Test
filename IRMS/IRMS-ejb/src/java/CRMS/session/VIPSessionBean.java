/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class VIPSessionBean {
    @EJB
    private MemberSessionBean memberSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public boolean upgradeToVIP(String emailAddress) throws ExistException
    {
        boolean upgraded = false;
        
        MemberEntity current = memberSessionBean.getMemberByEmail(emailAddress);
        if(current==null)
            throw new ExistException();
        
        current.setIsVIP(true);
        em.merge(current);
        return upgraded;
    }
    
    public boolean upgradeToSuperVIP()
    {
        boolean upgraded = false;
        
        return upgraded;
    }
       
    public List<MemberEntity> getVIPs() {
        Query q = em.createQuery("SELECT m FROM MemberEntity m WHERE m.isVIP =" + true);
        return q.getResultList();
    }
    
    public void assignContactEmployee(MemberEntity member, String contactEmployeeId) {
        member.setContactEmployee(contactEmployeeId);
        em.merge(member);
    }
    
    public List<MemberEntity> resetSuperVIPs() {
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            m.setContactEmployee(null);
            em.merge(m);
        }
        return this.getSuperVIPs();
    }
    
    //Percentile is preset to be 90%
    public List<MemberEntity> getSuperVIPs()
    {
        List<MemberEntity> allMembers = memberSessionBean.getAllMembers();
        Iterator<MemberEntity> itr = allMembers.iterator();
        double allMemberTotal = 0.00;
        double currentMemberTotal = 0.00;
         //get a description statistics
        DescriptiveStatistics stats = new DescriptiveStatistics();
        
        while(itr.hasNext())
        {
            MemberEntity current = itr.next();
            List <MemberTransactionEntity> allTrans = new ArrayList();
            allTrans.addAll(current.getMemberTransactions());
            Iterator<MemberTransactionEntity> itr2 = allTrans.iterator();
            
            while(itr2.hasNext())
            {
                MemberTransactionEntity mte = itr2.next();
                allMemberTotal+=mte.getMtAmount();
                stats.addValue(mte.getMtAmount());
            }
        }
        
        System.out.println("allMemberTotal "+allMemberTotal);
        
        double baseline = stats.getPercentile(90);//can be manipulated based on demand
        
        List <MemberEntity> baseList = memberSessionBean.getAllMembers();
        List <MemberEntity> resultList = new ArrayList();
        
        Iterator <MemberEntity> itr3 = baseList.iterator();
        while(itr3.hasNext())
        {
            MemberEntity current = itr3.next();
            List <MemberTransactionEntity> allTrans2 = new ArrayList();
            allTrans2.addAll(current.getMemberTransactions());
            Iterator<MemberTransactionEntity> itr4 = allTrans2.iterator();
            
            while(itr4.hasNext())
            {
                MemberTransactionEntity mte = itr4.next();
                if((mte.getMtAmount()>=baseline)&&(!resultList.contains(current)))
                    resultList.add(current);
            }
        }
        
        return resultList;
    }
    
    public List<MemberEntity> getSuperVIPsByPercentile(int percentile)
    {
       List<MemberEntity> allMembers = memberSessionBean.getAllMembers();
        Iterator<MemberEntity> itr = allMembers.iterator();
        double allMemberTotal = 0.00;
        double currentMemberTotal = 0.00;
         //get a description statistics
        DescriptiveStatistics stats = new DescriptiveStatistics();
        
        while(itr.hasNext())
        {
            MemberEntity current = itr.next();
            List <MemberTransactionEntity> allTrans = new ArrayList();
            allTrans.addAll(current.getMemberTransactions());
            Iterator<MemberTransactionEntity> itr2 = allTrans.iterator();
            
            while(itr2.hasNext())
            {
                MemberTransactionEntity mte = itr2.next();
                allMemberTotal+=mte.getMtAmount();
                stats.addValue(mte.getMtAmount());
            }
        }
        
        System.out.println("allMemberTotal "+allMemberTotal);
        
        double baseline = stats.getPercentile(percentile);//can be manipulated based on demand
        
        List <MemberEntity> baseList = memberSessionBean.getAllMembers();
        List <MemberEntity> resultList = new ArrayList();
        
        Iterator <MemberEntity> itr3 = baseList.iterator();
        while(itr3.hasNext())
        {
            MemberEntity current = itr3.next();
            List <MemberTransactionEntity> allTrans2 = new ArrayList();
            allTrans2.addAll(current.getMemberTransactions());
            Iterator<MemberTransactionEntity> itr4 = allTrans2.iterator();
            
            while(itr4.hasNext())
            {
                MemberTransactionEntity mte = itr4.next();
                if((mte.getMtAmount()>=baseline)&&(!resultList.contains(current)))
                    resultList.add(current);
            }
        }
        
        return resultList; 
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
