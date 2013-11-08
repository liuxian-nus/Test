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
import ACMS.entity.RoomPriceEntity;
import java.math.BigDecimal;
import javax.ejb.LocalBean;
//import ACMS.entity.RoomEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.math3.distribution.*;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class OverbookingSessionBean implements OverbookingSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    public int suggestedQuota = 0;
    public OverbookingQuotaEntity overbooking = new OverbookingQuotaEntity();
    public double cs;
    public double ce;
    public double sl;
    public int demandMean;
    public int demandSD;
    NormalDistribution n = new NormalDistribution();

    public OverbookingSessionBean() {
    }

    @Override
    public void initOverbooking(OverbookingQuotaEntity ob) {
        em.persist(ob);

    }

    //compensation to be discussed, now assume as direct human input
    @Override
    public int calculateSuggestedQuota(int demandMean, int demandSD, double ce) {

        //algorithm missing here.
        overbooking = em.find(OverbookingQuotaEntity.class, 1);
        String roomType = overbooking.getRoomType();
        RoomPriceEntity price = em.find(RoomPriceEntity.class, roomType);
//        double cs = price.getPrice();
        cs = 485.3;
//        ce = overbooking.getCompensation1();
        sl = cs / (cs + ce);
        System.out.println("sl:" + sl);
        double x = n.inverseCumulativeProbability(sl);
        System.out.println("x = " + x);
        double q = demandMean + demandSD * x;
        double quota = q - demandMean;
        System.out.println("Q = " + q);
        System.out.println("quota = " + quota);
        int suggestedQuota = (int) quota;
        System.out.println("final result = " + suggestedQuota);
        overbooking.setSuggestedQuota(suggestedQuota);
        em.merge(overbooking);
        return suggestedQuota;
    }

    @Override
    public int resetQuota(int quota) {
        overbooking.setQuota(quota);
        em.merge(overbooking);
        return overbooking.getQuota();
    }

    @Override
    public OverbookingQuotaEntity getQuota() {
        int id = 1;
        OverbookingQuotaEntity overbooking = em.find(OverbookingQuotaEntity.class, id);
        return overbooking;
    }

    @Override
    public void setQuota(int newQuota) {
        int id = 1;
        OverbookingQuotaEntity quota = em.find(OverbookingQuotaEntity.class, id);
        quota.setQuota(newQuota);
    }

    @Override
    public int getSuggestedQuota() {
        return suggestedQuota;
    }

    @Override
    public void setSuggestedQuota(int suggestedQuota) {
        this.suggestedQuota = suggestedQuota;
    }

    @Override
    public OverbookingQuotaEntity getOverbooking() {
        return overbooking;
    }

    @Override
    public void setOverbooking(OverbookingQuotaEntity overbooking) {
        this.overbooking = overbooking;
    }

    @Override
    public int getDemandMean() {
        return demandMean;
    }

    @Override
    public void setDemandMean(int demandMean) {
        this.demandMean = demandMean;
    }

    @Override
    public int getDemandSD() {
        return demandSD;
    }

    @Override
    public void setDemandSD(int demandSD) {
        this.demandSD = demandSD;
    }

    private double pow(int i, double z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
