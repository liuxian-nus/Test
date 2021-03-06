/*

 */
package ACMS.session;

import ACMS.entity.RoomPriceEntity;
import ACMS.entity.ReservationEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.MemberTransactionSessionBean;
import CRMS.session.PromotionSessionBean;
import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.joda.time.DateMidnight;
import org.joda.time.Days;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class ReservationSessionBean implements ReservationSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    ReservationEntity reservation = new ReservationEntity();
    List<ReservationEntity> reservations;
    private Date currentDate = new Date();
    @EJB
    private MemberTransactionSessionBean memberTransactionSessionBean;
    @EJB
    private PromotionSessionBean promotionSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;

    public ReservationSessionBean() {
    }

    public void updateReservation(ReservationEntity reservation) {
        em.merge(reservation);
        System.out.println("hahaha after updating");
    }

    @Override
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

    @Override
    public List<ReservationEntity> getTodayReservations() {
        Query q = em.createQuery("SELECT re FROM ReservationEntity re");
        List reservationList = new ArrayList<ReservationEntity>();
        for (Object o : q.getResultList()) {
            ReservationEntity re = (ReservationEntity) o;
            System.out.println("current" + currentDate.getDate());
//            System.out.println("checkinDate" + re.getRcCheckInDate().getDate());

            if ((re.getRcCheckInDate() != null) && (re.getRcCheckInDate().getDate() == currentDate.getDate())) {
                reservationList.add(re);
            }
        }
        System.out.println("session bean return a full list of all reservations");
        return reservationList;
    }

    @Override
    public List<ReservationEntity> getBeforeReservations() {
        Query q = em.createQuery("SELECT re FROM ReservationEntity re");
        List reservationList = new ArrayList<ReservationEntity>();
        for (Object o : q.getResultList()) {
            ReservationEntity re = (ReservationEntity) o;
            if ((re.getRcCheckOutDate() != null) && (re.getRcCheckOutDate().before(currentDate))) {
                reservationList.add(re);
            }
        }
        System.out.println("session bean return a full list of all reservations");
        return reservationList;
    }

    @Override
    public ReservationEntity getReservationById(Long reservationId) throws ExistException {
        reservation = em.find(ReservationEntity.class, reservationId);
//        if(reservation == null)throw new ExistException("Reservation does not exist!");
        return reservation;
    }

    @Override
    public List<ReservationEntity> getReservationByName(String rcName) {
        Query query = em.createQuery("SELECT r FROM ReservationEntity r WHERE r.rcName ='" + rcName + "'");
        System.err.println("getReservationByName: " + rcName);
        //query.setParameter("employeeName", employeeName);
        reservations = new ArrayList<ReservationEntity>();
        reservations = query.getResultList();
        return reservations;
    }

    @Override
    public List<ReservationEntity> getReservationByEmail(String rcEmail) {
        Query query = em.createQuery("SELECT r FROM ReservationEntity r WHERE r.rcEmail ='" + rcEmail + "'");
        System.err.println("getReservationByEmail: " + rcEmail);
        //query.setParameter("employeeName", employeeName);
        reservations = new ArrayList<ReservationEntity>();
        reservations = query.getResultList();
        return reservations;
    }

    @Override
    public List<ReservationEntity> getReservationByDate(Date today) {
        Query query = em.createQuery("SELECT r FROM ReservationEntity r WHERE r.rcCheckInDate ='" + today + "'");
        System.err.println("getReservationByDate: " + today);
        //query.setParameter("employeeName", employeeName);
        reservations = new ArrayList<ReservationEntity>();
        reservations = query.getResultList();
        return reservations;
    }

    public double calculateReservationTotal(ReservationEntity thisReservation) {
        RoomPriceEntity thisPrice = em.find(RoomPriceEntity.class, thisReservation.getReservationRoomType().toLowerCase());

        DateMidnight start = new DateMidnight(thisReservation.getRcCheckInDate());
        DateMidnight end = new DateMidnight(thisReservation.getRcCheckOutDate());
        int days = Days.daysBetween(start, end).getDays();

        return thisPrice.getPrice() * thisReservation.getReservationRoomCount() * days;
    }

    public double calculateReservationTotalWithPromotion(ReservationEntity thisReservation, PromotionEntity thisPromotion) {
        RoomPriceEntity thisPrice = em.find(RoomPriceEntity.class, thisReservation.getReservationRoomType().toLowerCase());
        System.out.println("Calculate reservatino total price with promotion " + thisPromotion.getPromotionCode() + " " + thisPromotion.getPromotionTitle());
        DateMidnight start = new DateMidnight(thisReservation.getRcCheckInDate());
        DateMidnight end = new DateMidnight(thisReservation.getRcCheckOutDate());
        int days = Days.daysBetween(start, end).getDays();

        return thisPrice.getPrice() * thisReservation.getReservationRoomCount() * days * (1 - thisPromotion.getDiscount());
    }
//for jsp reservation

    public void addReservation(ReservationEntity newReservation, double totalPrice) throws IOException, FileNotFoundException, DocumentException {
        Date today = new Date();
        System.out.println("in reservation session bean: add reservation");
        ReservationEntity thisReservation = newReservation;
//        thisReservation.getRcCheckInDate().setYear(newReservation.getRcCheckInDate().getYear() - 1900);
//        thisReservation.getRcCheckInDate().setMonth(newReservation.getRcCheckInDate().getMonth() - 1);
//        thisReservation.getRcCheckOutDate().setYear(newReservation.getRcCheckOutDate().getYear() - 1900);
//        thisReservation.getRcCheckOutDate().setMonth(newReservation.getRcCheckOutDate().getMonth() - 1);
//        RoomPriceEntity thisPrice = em.find(RoomPriceEntity.class, thisReservation.getReservationRoomType());
//
//        DateMidnight start = new DateMidnight(thisReservation.getRcCheckInDate());
//        DateMidnight end = new DateMidnight(thisReservation.getRcCheckOutDate());
//        int days = Days.daysBetween(start, end).getDays();

//        thisReservation.setReservationTotal(this.calculateReservationTotal(thisReservation));//5 should be days between
        if (thisReservation.getRcNationality().equals("129")) {
            thisReservation.setRcNationality("Malaysian");
        }
        thisReservation.setReservationTotal(totalPrice);
        thisReservation.setReservationStatus("guarantee"); //haven't implement yet
        em.persist(thisReservation);
        String description = "Hotel Reservation from " + thisReservation.getRcCheckInDate() + " to " + thisReservation.getRcCheckOutDate() + " with a total room fee: " + thisReservation.getReservationTotal();
        if (thisReservation.getRcMember() != null) {
            memberTransactionSessionBean.addMemberTransaction(thisReservation.getRcMember(), thisReservation.getReservationTotal(), today, "Hotel", null, description, false);
        }
        emailSessionBean.emailReservationConfirmation(thisReservation.getRcEmail(), thisReservation);
        System.err.println("successfully added reservation: " + newReservation.getReservationId());
    }

    @Override
    public void addReservation(ReservationEntity newReservation) {
        Date today = new Date();
        System.out.println("in reservation session bean: add reservation");
        ReservationEntity thisReservation = newReservation;
//        thisReservation.getRcCheckInDate().setYear(newReservation.getRcCheckInDate().getYear() - 1900);
//        thisReservation.getRcCheckInDate().setMonth(newReservation.getRcCheckInDate().getMonth() - 1);
//        thisReservation.getRcCheckOutDate().setYear(newReservation.getRcCheckOutDate().getYear() - 1900);
//        thisReservation.getRcCheckOutDate().setMonth(newReservation.getRcCheckOutDate().getMonth() - 1);
//        RoomPriceEntity thisPrice = em.find(RoomPriceEntity.class, thisReservation.getReservationRoomType());
//
//        DateMidnight start = new DateMidnight(thisReservation.getRcCheckInDate());
//        DateMidnight end = new DateMidnight(thisReservation.getRcCheckOutDate());
//        int days = Days.daysBetween(start, end).getDays();
        if (((thisReservation.getRcNationality() != null)) && (thisReservation.getRcNationality().equals("129"))) {
            thisReservation.setRcNationality("Malaysian");
        }

        thisReservation.setReservationTotal(this.calculateReservationTotal(thisReservation));//5 should be days between
        thisReservation.setReservationStatus("confirmed");
        em.persist(thisReservation);
        String description = "Hotel Reservation from " + thisReservation.getRcCheckInDate() + " to " + thisReservation.getRcCheckOutDate() + " with a total room fee: " + thisReservation.getReservationTotal();
        if (newReservation.getRcMember() != null) {
            memberTransactionSessionBean.addMemberTransaction(thisReservation.getRcMember(), thisReservation.getReservationTotal(), today, "Hotel", null, description, false);
        }
        try {
            emailSessionBean.emailReservationConfirmation(thisReservation.getRcEmail(), thisReservation);
        } catch (IOException ex) {
            Logger.getLogger(ReservationSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReservationSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("successfully added reservation: " + newReservation.getReservationId());
    }

    public void addReservationByCoin(ReservationEntity newReservation, MemberEntity member) throws IOException, FileNotFoundException, DocumentException {
        Date today = new Date();
        System.out.println("in reservation session bean: add reservation");
        ReservationEntity thisReservation = newReservation;
//        thisReservation.getRcCheckInDate().setYear(newReservation.getRcCheckInDate().getYear() - 1900);
//        thisReservation.getRcCheckInDate().setMonth(newReservation.getRcCheckInDate().getMonth() - 1);
//        thisReservation.getRcCheckOutDate().setYear(newReservation.getRcCheckOutDate().getYear() - 1900);
//        thisReservation.getRcCheckOutDate().setMonth(newReservation.getRcCheckOutDate().getMonth() - 1);
        RoomPriceEntity thisPrice = em.find(RoomPriceEntity.class, thisReservation.getReservationRoomType().toLowerCase());

        DateMidnight start = new DateMidnight(thisReservation.getRcCheckInDate());
        DateMidnight end = new DateMidnight(thisReservation.getRcCheckOutDate());
        int days = Days.daysBetween(start, end).getDays();
        if (thisReservation.getRcNationality().equals("129")) {
            thisReservation.setRcNationality("Malaysian");
        }

        thisReservation.setReservationTotal(thisPrice.getPrice() * thisReservation.getReservationRoomCount() * days);//5 should be days between
        thisReservation.setReservationStatus("complementary");
        thisReservation.setRcMember(member);
        em.persist(thisReservation);
        emailSessionBean.emailReservationConfirmation(thisReservation.getRcEmail(), thisReservation);
        String description = "Hotel Reservation from " + thisReservation.getRcCheckInDate() + " to " + thisReservation.getRcCheckOutDate() + " with a total room fee: " + thisReservation.getReservationTotal();
        memberTransactionSessionBean.addMemberTransaction(thisReservation.getRcMember(), thisReservation.getReservationTotal(), today, "Hotel", null, description, true);
        System.err.println("successfully added reservation: " + newReservation.getReservationId());
    }

    public int calculateNights(ReservationEntity newReservation) {
        DateMidnight start = new DateMidnight(newReservation.getRcCheckInDate());
        DateMidnight end = new DateMidnight(newReservation.getRcCheckOutDate());
        return Days.daysBetween(start, end).getDays();
    }

    public void addReservationWithPromotion(ReservationEntity newReservation, String promotionCode) throws IOException, FileNotFoundException, DocumentException {
        Date today = new Date();
        System.out.println("in reservation session bean: add reservation with promotion");
        ReservationEntity thisReservation = newReservation;
        PromotionEntity thisPromotion = promotionSessionBean.getPromotionByCode(promotionCode);
        thisReservation.setReservationTotal(this.calculateReservationTotalWithPromotion(thisReservation, thisPromotion));//5 should be days between
        thisReservation.setReservationStatus("guarantee");
        if (thisReservation.getRcNationality().equals("129")) {
            thisReservation.setRcNationality("Malaysian");
        }

        em.persist(thisReservation);
        emailSessionBean.emailReservationConfirmation(thisReservation.getRcEmail(), thisReservation);
        String description = "Hotel Reservation from " + thisReservation.getRcCheckInDate() + " to " + thisReservation.getRcCheckOutDate() + " with a total room fee: " + thisReservation.getReservationTotal();
        if (newReservation.getRcMember() != null) {
            memberTransactionSessionBean.addMemberTransaction(thisReservation.getRcMember(), thisReservation.getReservationTotal(), today, "Hotel", promotionCode, description, false);
        }
        System.err.println("successfully added reservation: " + newReservation.getReservationId());
    }

    @Override
    public void cancelReservation(Long reservationId) {
        reservation = em.find(ReservationEntity.class, reservationId);
        reservation.setReservationStatus("cancelled");
        em.merge(reservation);
        emailSessionBean.cancelNotification(reservation.getRcEmail(),reservation);

    }

    public void cancelReservation(ReservationEntity reservation) {
        reservation.setReservationStatus("cancelled");
        em.merge(reservation);
        emailSessionBean.cancelNotification(reservation.getRcEmail(),reservation);

    }

    public void payRoomFee(ReservationEntity reservation) {
        reservation.setReservationStatus("guarantee");
        em.merge(reservation);
    }

    public double calculateTotalPrice(ReservationEntity reservation) {
        System.out.println("calculateTotalPrice");
        System.out.println("room type: " + reservation.getReservationRoomType());
        RoomPriceEntity thisPrice = em.find(RoomPriceEntity.class, reservation.getReservationRoomType().toLowerCase());
        System.out.println("thisPrice: " + thisPrice.getPrice());
        System.out.println("room count: " + reservation.getReservationRoomCount());

        DateMidnight start = new DateMidnight(reservation.getRcCheckInDate());
        DateMidnight end = new DateMidnight(reservation.getRcCheckOutDate());
        int days = Days.daysBetween(start, end).getDays();

        double totalPrice = thisPrice.getPrice() * reservation.getReservationRoomCount() * days;
        System.out.println("totalPrice: " + totalPrice);
        return totalPrice;
    }

    @Override
    public ReservationEntity getReservationById(String searchId) {
        Long id = Long.valueOf(searchId);
        ReservationEntity thisReservation = em.find(ReservationEntity.class, id);
        return thisReservation;
    }

    @Override
    public Date getCurrentDate() {
        return currentDate;
    }

    @Override
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
