/*

 */
package ACMS.session;

import ACMS.entity.RoomPriceEntity;
import ACMS.entity.ReservationEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Date;
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

    @PersistenceContext(unitName="IRMS-ejbPU")
    private EntityManager em;
    ReservationEntity reservation = new ReservationEntity();
    List<ReservationEntity> reservations;
    private Date currentDate = new Date();

    
    public ReservationSessionBean() {
    }

    public List<ReservationEntity> getAllReservations() {
        Query q = em.createQuery("SELECT re FROM ReservationEntity re");
        List reservationList = new ArrayList<ReservationEntity>();
        for (Object o : q.getResultList()) {
            ReservationEntity re = (ReservationEntity) o;
            reservationList.add(re);
        }
        System.out.println("session bean return a full list of all reservations");
        return reservationList;
    }
    
    public List<ReservationEntity> getTodayReservations() {
        Query q = em.createQuery("SELECT re FROM ReservationEntity re");
        List reservationList = new ArrayList<ReservationEntity>();
        for (Object o : q.getResultList()) {
            ReservationEntity re = (ReservationEntity) o;
            System.out.println("current"+currentDate.getDate());
            System.out.println("checkinDate"+re.getRcCheckInDate().getDate());
            if (re.getRcCheckInDate().getDate()==currentDate.getDate()){
            reservationList.add(re);}
        }
        System.out.println("session bean return a full list of all reservations");
        return reservationList;
    }

    public List<ReservationEntity> getBeforeReservations() {
        Query q = em.createQuery("SELECT re FROM ReservationEntity re");
        List reservationList = new ArrayList<ReservationEntity>();
        for (Object o : q.getResultList()) {
            ReservationEntity re = (ReservationEntity) o;
            if (re.getRcCheckOutDate().before(currentDate)){
            reservationList.add(re);}
        }
        System.out.println("session bean return a full list of all reservations");
        return reservationList;
    }
    public ReservationEntity getReservationById(Long reservationId) throws ExistException {
        reservation = em.find(ReservationEntity.class, reservationId);
//        if(reservation == null)throw new ExistException("Reservation does not exist!");
        return reservation;
    }

    public List<ReservationEntity> getReservationByName(String rcName) {
        Query query = em.createQuery("SELECT r FROM ReservationEntity r WHERE r.rcName ='" + rcName + "'");
        System.err.println("getReservationByName: " + rcName);
        //query.setParameter("employeeName", employeeName);
        reservations = new ArrayList<ReservationEntity>();
        reservations = query.getResultList();
        return reservations;
    }

    public List<ReservationEntity> getReservationByEmail(String rcEmail) {
        Query query = em.createQuery("SELECT r FROM ReservationEntity r WHERE r.rcEmail ='" + rcEmail + "'");
        System.err.println("getReservationByEmail: " + rcEmail);
        //query.setParameter("employeeName", employeeName);
        reservations = new ArrayList<ReservationEntity>();
        reservations = query.getResultList();
        return reservations;
    }

    public List<ReservationEntity> getReservationByDate(Date today) {
        Query query = em.createQuery("SELECT r FROM ReservationEntity r WHERE r.rcCheckInDate ='" + today + "'");
        System.err.println("getReservationByDate: " + today);
        //query.setParameter("employeeName", employeeName);
        reservations = new ArrayList<ReservationEntity>();
        reservations = query.getResultList();
        return reservations;
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
        thisReservation.getRcCheckInDate().setYear(newReservation.getRcCheckInDate().getYear() - 1900);
        thisReservation.getRcCheckInDate().setMonth(newReservation.getRcCheckInDate().getMonth() - 1);
        thisReservation.getRcCheckOutDate().setYear(newReservation.getRcCheckOutDate().getYear() - 1900);
        thisReservation.getRcCheckOutDate().setMonth(newReservation.getRcCheckOutDate().getMonth() - 1);
        RoomPriceEntity thisPrice = em.find(RoomPriceEntity.class, thisReservation.getReservationRoomType());
//        Query query = em.createQuery("SELECT rp FROM RoomPriceEntity rp WHERE rp.hotelId ='" + newReservation.getReservationHotelNo() + "' AND rp.priceType ='" + newReservation.getReservationRoomType() + "'");
//        RoomPriceEntity thisPrice = (RoomPriceEntity) query.getResultList().get(0);
        thisReservation.setReservationTotal(thisPrice.getPrice() * thisReservation.getReservationRoomCount() * 5);//5 should be days between
        thisReservation.setReservationStatus("guarantee"); //haven't implement yet
        em.persist(thisReservation);
    }

    public void cancelReservation(Long reservationId) {
        reservation = em.find(ReservationEntity.class, reservationId);
        reservation.setReservationStatus("cancelled");
    }

    public ReservationEntity getReservationById(String searchId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
