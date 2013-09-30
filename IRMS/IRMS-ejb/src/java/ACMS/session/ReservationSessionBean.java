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
    
    public ReservationEntity getReservationById(Long reservationId) throws ExistException {
        reservation = em.find(ReservationEntity.class, reservationId);
//        if(reservation == null)throw new ExistException("Reservation does not exist!");
        return reservation;
        }
    
    public void addReservation(ReservationEntity newReservation) {
    /*
     * Date rcCheckInDate = null;
        rcCheckInDate.setYear(reservation.getRcCheckInDate().getYear()-1900); 
        rcCheckInDate.setMonth(reservation.getRcCheckInDate().getMonth()-1);
        rcCheckInDate.setDate(reservation.getRcCheckInDate().getDate());
        room.setCheckInDate(rcCheckInDate);
        Date rcCheckOutDate = null;
        rcCheckOutDate.setYear(reservation.getRcCheckOutDate().getYear()-1900); 
        rcCheckOutDate.setMonth(reservation.getRcCheckOutDate().getMonth()-1);
        rcCheckOutDate.setDate(reservation.getRcCheckOutDate().getDate());
        room.setCheckOutDate(rcCheckOutDate);
     */
        ReservationEntity thisReservation = newReservation;
        thisReservation.getRcCheckInDate().setYear(newReservation.getRcCheckInDate().getYear()-1900);
        thisReservation.getRcCheckInDate().setMonth(newReservation.getRcCheckInDate().getMonth()-1);
        thisReservation.getRcCheckOutDate().setYear(newReservation.getRcCheckOutDate().getYear()-1900);
        thisReservation.getRcCheckOutDate().setMonth(newReservation.getRcCheckOutDate().getMonth()-1);
        em.persist(thisReservation);
    }

    public ReservationEntity getReservationById(String searchId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
