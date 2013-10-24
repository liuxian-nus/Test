/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.PromotionEntity;
import java.util.Date;

/**
 *
 * @author Diana Wang
 */
public interface MarketingCampaignSessionRemote {

    void addMarketingCampaign(PromotionEntity mc);

    String endMarketingCampaign(Date endDate);

    void persist(Object object);
    
}
