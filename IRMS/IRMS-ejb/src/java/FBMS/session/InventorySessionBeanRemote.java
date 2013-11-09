/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import Exception.ExistException;
import FBMS.entity.DishEntity;
import FBMS.entity.OrderEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface InventorySessionBeanRemote {

    DishEntity addDish(DishEntity dish);

    Double assignCost(Long orderId);

    boolean deleteDish(Long dishId);

    DishEntity getDishById(Long dishId) throws ExistException;

    OrderEntity issueGoods(Long orderId);

    List<DishEntity> listDishes();

    void persist(Object object);

    DishEntity updateDish(DishEntity dish);
    
}
