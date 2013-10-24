/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.PromotionEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
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
public class MarketingCampaignSessionBean implements MarketingCampaignSessionRemote {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    
    PromotionEntity mc = new PromotionEntity();
    MemberEntity member = new MemberEntity();
    
    @Override
    public String endMarketingCampaign(Date endDate){
        Timer timer = new Timer();
        timer.schedule(new EndTask(), endDate);
        timer.cancel();
        return "The marketing compaign has been ended by endMarketingCampaign";
    }
    class EndTask extends TimerTask{
        
        @Override
        public void run(){
            System.out.println("The marketing campaign has reached the end ");
            Set<Long> respondCustomers = getCustomers();
            for(Object o: respondCustomers){
                MemberTransactionEntity thismt = em.find(MemberTransactionEntity.class, o);
                System.out.println("This transaction is consumed by "+thismt.getMember().getMemberName());
                System.out.println("At "+thismt.getMtDate()+ " and the consumption amount is "+thismt.getMtAmount());
         
            }
        }
        
        public Set<Long> getCustomers(){
            Query q = em.createNamedQuery("SELECT mt.mtId FROM MemberTransactionEntity mt WHERE mt.mtPromotion = :mtPromotion");//this sentence got big problem
            Set stateSet = new HashSet<Long>();
            for (Object o: q.getResultList()) {
            Long m = (Long) o;
            stateSet.add(m);
        }
            return stateSet;
        }
    }
    @Override
    public void addMarketingCampaign(PromotionEntity mc){
     // mc.create(startDate, endDate, remarks, memberTargets);
      System.out.println("MarketingCampaignSessionBean: reference Id is "+mc.getMcId());
      em.persist(mc);
      endMarketingCampaign(mc.getMcEndDate());
      System.out.println("MarketingCampaignSessionBean: Marketing campaign has been ended "+ mc.getMcId());
    }
 public MarketingCampaignSessionBean(){};

}
