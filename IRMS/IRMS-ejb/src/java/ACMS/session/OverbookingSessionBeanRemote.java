/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.OverbookingQuotaEntity;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface OverbookingSessionBeanRemote {

    //compensation to be discussed, now assume as direct human input
    int calculateSuggestedQuota(int demandMean, int demandSD, double ce);

    int getDemandMean();

    int getDemandSD();

    OverbookingQuotaEntity getOverbooking();

    OverbookingQuotaEntity getQuota();

    int getSuggestedQuota();

    void initOverbooking(OverbookingQuotaEntity ob);

    int resetQuota(int quota);

    void setDemandMean(int demandMean);

    void setDemandSD(int demandSD);

    void setOverbooking(OverbookingQuotaEntity overbooking);

    void setQuota(int newQuota);

    void setSuggestedQuota(int suggestedQuota);
    
}
