/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import Exception.ExistException;
import FBMS.entity.RestaurantEntity;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
public interface RestaurantSessionBeanRemote {

    /*E.4.2.1 Create restaurant*/
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    RestaurantEntity addRestaurant(RestaurantEntity restaurant);

    void persist(Object object);

    /*E.4.2.2 Update restaurant information*/
    /**
     *
     * @param restId
     * @param restNeighbourhood
     * @param restTypeOfPlace
     * @param restCuisine
     * @param restName
     * @return
     * @throws ExistException
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    RestaurantEntity updateRestaurant(Long restId, String restNeighbourhood, String restTypeOfPlace, String restCuisine, String restName) throws ExistException;
    
}
