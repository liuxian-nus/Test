/*
 * Assumption!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 1.overbooking only apply to 4-star hotel, superior roomType and 5-star hotel deluxy roomType, all the suits are not applicable
 * 2.noShow loss is the loss if the customer doesn't show-up
 * 3.compensation loss is the loss of upgrade/transfer
 * -------For upgrade: compensation loss = upgraded roomPrice - original roomPrice
 * -------For transfer: customers are relocated to other hotels with the same roomType, compensation equals the roomPrice
 * 4.since the compensation loss is the weighted average of historical data,with not enough data, we assume this is given
 * as it can be retrieved from other hotel experiences or experiences of CIR elsewhere.
 * 5. THere should be a page for hotelAdmin to input data and calculate the suggested quota
 * 6. Assume customer demand is normal distribution
 */
package ACMS.session;

import ACMS.entity.OverbookingQuotaEntity;
import ACMS.entity.PriceEntity;
//import ACMS.entity.RoomEntity;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class OverbookingSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    public int suggestedQuota=0;
    public OverbookingQuotaEntity overbooking = new OverbookingQuotaEntity();
    public double cs;
    public double ce;
    public double sl;
    
    //compensation to be discussed, now assume as direct human input
    public int calculateSeggestedQuota(String priceType,double compensation){
        
        //algorithm missing here.
        PriceEntity price = em.find(PriceEntity.class, priceType);
        double cs = price.getPrice();
        ce = compensation;
        sl = cs/(cs + ce);
        return suggestedQuota;
    }
    
    public int resetQuota(int quota){
        overbooking.setQuota(quota);
        em.merge(overbooking);
        return overbooking.getQuota();
    }
}
