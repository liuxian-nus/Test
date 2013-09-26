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
import java.math.BigDecimal;
//import ACMS.entity.RoomEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author liuxian
 */
@Stateless
public class OverbookingSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    public int suggestedQuota = 0;
    public OverbookingQuotaEntity overbooking = new OverbookingQuotaEntity();
    public double cs;
    public double ce;
    public double sl;
    
    public OverbookingSessionBean (){
        
    }
    
    public void initOverbooking(OverbookingQuotaEntity ob){
//        ob.setSuggestedQuota(calculateSuggestedQuota());
        em.persist(ob);
    }

    //compensation to be discussed, now assume as direct human input
    public int calculateSuggestedQuota() {

        //algorithm missing here.
        overbooking = em.find(OverbookingQuotaEntity.class,1);
        String roomType = overbooking.getRoomType();
        PriceEntity price = em.find(PriceEntity.class, roomType);
        double cs = price.getPrice();
//        double cs = 485.3;
//        ce = overbooking.getCompensation1();
        ce = 105;
        sl = cs / (cs + ce);
        double z = 0.0;
        double p = 0.5;
        double n = 0.0;
        double final_z;

        while (p < sl) {
            n = 1 / (Math.pow(2, 0.5) * Math.PI) * Math.exp(-0.5 * Math.pow(z, 2));
            p += n * 0.01;
            z += 0.01;
            System.out.println(n);
            System.out.println(p);
            System.out.println(z);
        }


        final_z = Math.floor(z);        
        //suggestedQuota = integer value of final_z
        //calculate quota given the probability sl
        suggestedQuota = new BigDecimal(final_z).intValueExact();
        System.err.println(suggestedQuota);
        overbooking.setSuggestedQuota(suggestedQuota);
        em.merge(overbooking);
        return suggestedQuota;
    }

    public int resetQuota(int quota) {
        overbooking.setQuota(quota);
        em.merge(overbooking);
        return overbooking.getQuota();
    }

    public OverbookingQuotaEntity getQuota() {
        int id = 1;
        OverbookingQuotaEntity overbooking = em.find(OverbookingQuotaEntity.class, id);
        return overbooking;
    }

    public void setQuota(int newQuota) {
        int id = 1;
        OverbookingQuotaEntity quota = em.find(OverbookingQuotaEntity.class, id);
        quota.setQuota(newQuota);
    }

    private double pow(int i, double z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
