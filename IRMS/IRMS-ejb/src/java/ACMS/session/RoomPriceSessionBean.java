/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.RoomPriceEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class RoomPriceSessionBean implements RoomPriceSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    public RoomPriceSessionBean() {
    }

    @Override
    public void createPrice(RoomPriceEntity price) {
        em.persist(price);
    }

    @Override
    public void updatePrice(RoomPriceEntity price) {
        em.merge(price);
    }

    @Override
    public List<RoomPriceEntity> getAllRoomPrices() {
        Query q = em.createQuery("SELECT rp FROM RoomPriceEntity rp");
        List roomPriceList = new ArrayList<RoomPriceEntity>();
        for (Object o : q.getResultList()) {
            RoomPriceEntity rp = (RoomPriceEntity) o;
            roomPriceList.add(rp);
        }
        System.out.println("Session bean has returned a full list of all room prices");
        return roomPriceList;
    }

}
