/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.OverbookingQuotaEntity;
import ACMS.entity.RoomEntity;
import java.util.List;
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
    
    public int calculateSeggestedQuota(List<RoomEntity> room){
    //algorithm missing here.
    return suggestedQuota;
    }
    
    public int resetQuota(int quota){
        overbooking.setQuota(quota);
        em.merge(overbooking);
        return overbooking.getQuota();
    }
}
