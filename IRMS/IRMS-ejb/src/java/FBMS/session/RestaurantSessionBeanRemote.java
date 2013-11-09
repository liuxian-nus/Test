/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import Exception.ExistException;
import FBMS.entity.RestaurantEntity;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
@Remote
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
    
    public List<RestaurantEntity> getAllRestaurants() throws NoResultException;

    public RestaurantEntity getRestaurantById(Long restId) throws ExistException;

    public boolean updateRestaurant(RestaurantEntity restaurant);

    public boolean removeRestaurant(Long restId) throws ExistException;
}
