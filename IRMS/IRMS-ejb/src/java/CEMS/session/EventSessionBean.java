/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.BookingEntity;
import CEMS.entity.EventEntity;
import CEMS.entity.ServiceEntity;
import CEMS.entity.VenueEntity;
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
public class EventSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    BookingEntity be;
    EventEntity ee;
    VenueEntity ve;
    ServiceEntity se;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public EventSessionBean(){}
    
    public BookingEntity makeReservation()
    {
        
        return be;
    }

   

}
