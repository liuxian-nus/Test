/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.IndReservationEntity;
import FBMS.entity.RestaurantEntity;
import java.util.Date;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Administrator
 */
@Remote
public interface IndReservationSessionBeanRemote {

    
    RestaurantEntity getRestaurantEntity(Long restId);
    /*E.1.1.3 Check Availability*/
    boolean checkAvailability(RestaurantEntity restaurant, int numberPeople, Date date);

    boolean confirmReservation(IndReservationEntity ire);
    
    IndReservationEntity makeReservation(Date indReservationDateTime, Long restId,Integer numberPeople, String title, String name, String email,String mobile, String notes );
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    RestaurantEntity createRestaurantEntity(String restNeighbourhood, String restTypeOfPlace, String restCuisine, String keyword);

   
    void persist(Object object);

    Set<RestaurantEntity> searchRestaurant(RestaurantEntity r);

    /*E.1.1.2 View restaurant details*/
    Set<RestaurantEntity> viewRestaurantDetails(Long restId);
    
    IndReservationEntity viewReservation (Long indReservationId);
    
    IndReservationEntity modifyReservation (String status,Long restId, Date indReservationDateTime, Long indReservationId,Integer numberPeople, String title, String name, String email,String mobile, String notes);
    
    
}
