/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.ReservationEntity;
import Exception.ExistException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface ReservationSessionBeanRemote {

    void addReservation(ReservationEntity newReservation);

    void cancelReservation(Long reservationId);

    List<ReservationEntity> getAllReservations();

    List<ReservationEntity> getBeforeReservations();

    Date getCurrentDate();

    List<ReservationEntity> getReservationByDate(Date today);

    List<ReservationEntity> getReservationByEmail(String rcEmail);

    ReservationEntity getReservationById(Long reservationId) throws ExistException;

    ReservationEntity getReservationById(String searchId);

    List<ReservationEntity> getReservationByName(String rcName);

    List<ReservationEntity> getTodayReservations();

    void setCurrentDate(Date currentDate);
    
}
