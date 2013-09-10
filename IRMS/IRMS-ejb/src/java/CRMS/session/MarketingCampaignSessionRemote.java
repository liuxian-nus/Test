/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MarketingCampaignEntity;
import java.util.Date;

/**
 *
 * @author Diana Wang
 */
public interface MarketingCampaignSessionRemote {

    void addMarketingCampaign(MarketingCampaignEntity mc);

    String endMarketingCampaign(Date endDate);

    void persist(Object object);
    
}
