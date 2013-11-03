/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.PromotionEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
public class PromotionSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    PromotionEntity promotion = new PromotionEntity();
    MemberEntity member = new MemberEntity();

    public String endMarketingCampaign(Date endDate) {
        Timer timer = new Timer();
        timer.schedule(new EndTask(), endDate);
        timer.cancel();
        return "The promotion has been ended by endMarketingCampaign";
    }

    public List<PromotionEntity> getPromotionByMemberEmail(String email) throws ExistException {
        System.out.println("promotion session bean: get promotion by email: " + email);
        MemberEntity thisMember = em.find(MemberEntity.class, email);
        if(thisMember == null) throw new ExistException("this member email is invalid");
        Query q = em.createQuery("SELECT p FROM PromotionEntity p");
        List promotions = new ArrayList<PromotionEntity>();
        for (Object o : q.getResultList()) {
            PromotionEntity p = (PromotionEntity) o;
            if(p.getMcMemberTargets().contains(thisMember)){
            promotions.add(p);
            }
        }
        return promotions;
    }

    class EndTask extends TimerTask {


        public void run() {
            System.out.println("The promotion has reached the end ");
            Set<Long> respondCustomers = getCustomers();
            for (Object o : respondCustomers) {
                MemberTransactionEntity thismt = em.find(MemberTransactionEntity.class, o);
                System.out.println("This transaction is consumed by " + thismt.getMemberEmail());
                System.out.println("At " + thismt.getMtDate() + " and the consumption amount is " + thismt.getMtAmount());

            }
        }

        public Set<Long> getCustomers() {
            Query q = em.createNamedQuery("SELECT mt.mtId FROM MemberTransactionEntity mt WHERE mt.mtPromotion = :mtPromotion");//this sentence got big problem
            Set stateSet = new HashSet<Long>();
            for (Object o : q.getResultList()) {
                Long m = (Long) o;
                stateSet.add(m);
            }
            return stateSet;
        }
    }

    public void addMarketingCampaign(PromotionEntity promotion) {
        // mc.create(startDate, endDate, remarks, memberTargets);
        System.out.println("MarketingCampaignSessionBean: reference Id is " + promotion.getPromotionId());
        em.persist(promotion);
        endMarketingCampaign(promotion.getPromotionEndDate());
        System.out.println("MarketingCampaignSessionBean: Marketing campaign has been ended " + promotion.getPromotionId());
    }

    public PromotionSessionBean() {
    }
;
}