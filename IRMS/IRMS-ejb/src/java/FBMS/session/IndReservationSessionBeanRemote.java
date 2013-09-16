/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.IndReservationEntity;
import FBMS.entity.RestaurantEntity;
import javax.ejb.Remote;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Administrator
 */
@Remote
public interface IndReservationSessionBeanRemote {

    /*E.1.1.3 Check Availability*/
    boolean checkAvailability(RestaurantEntity restaurant, int numberPeople, Date date);

    boolean confirmReservation(IndReservationEntity ire);
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    RestaurantEntity createRestaurantEntity(String restNeighbourhood, String restTypeOfPlace, String restCuisine, String keyword);

    boolean makeReservation(Date indReservationDateTime, Long restId, Integer numberPeople, String title, String name, String email, String mobile, String notes);

    void persist(Object object);

    Set<RestaurantEntity> searchRestaurant(RestaurantEntity r);

    /*E.1.1.2 View restaurant details*/
    Set<RestaurantEntity> viewRestaurantDetails(Long restId);
    
}