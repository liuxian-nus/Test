/*

 */
package ACMS.session;

import ACMS.entity.ReservationEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
public class ReservationSessionBean {

    @PersistenceContext
    private EntityManager em;
    ReservationEntity reservation = new ReservationEntity();

    public ReservationSessionBean() {
    }

    public List<ReservationEntity> getAllReservations() throws ExistException {
        Query q = em.createQuery("SELECT re FROM ReservationEntity re");
        List reservationList = new ArrayList<ReservationEntity>();
        for (Object o : q.getResultList()) {
            ReservationEntity re = (ReservationEntity) o;
            reservationList.add(re);
         }
        if(reservationList.isEmpty())  throw new ExistException("Reservation database is empty!");
        return reservationList;
        }
    
    public ReservationEntity getReservationById(String reservationId) throws ExistException {
        reservation = em.find(ReservationEntity.class, reservationId);
        if(reservation == null)throw new ExistException("Reservation does not exist!");
        return reservation;
        }
    
    public void addReservation(ReservationEntity newReservation) {
        em.persist(newReservation);
    }
    }
