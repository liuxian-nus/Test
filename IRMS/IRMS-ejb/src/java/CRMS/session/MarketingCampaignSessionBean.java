/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MarketingCampaignEntity;
import CRMS.entity.MemberEntity;
import java.util.Date;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class MarketingCampaignSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    MarketingCampaignEntity mc = new MarketingCampaignEntity();
    MemberEntity member = new MemberEntity();
    
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
            
        }
    }
    public void addMarketingCampaign(Date startDate,Date endDate,String remarks,Set<MemberEntity> memberTargets){
      mc.create(startDate, endDate, remarks, memberTargets);
      System.out.println("MarketingCampaignSessionBean: reference Id is "+mc.getMcId());
      em.persist(mc);
      endMarketingCampaign(endDate);
      System.out.println("MarketingCampaignSessionBean: Marketing campaign has been ended "+ mc.getMcId());
    }
 public MarketingCampaignSessionBean(){};

}
