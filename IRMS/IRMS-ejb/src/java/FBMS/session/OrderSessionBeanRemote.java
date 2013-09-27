/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import CRMS.entity.MemberEntity;
import FBMS.entity.DishEntity;
import FBMS.entity.InvoiceEntity;
import FBMS.entity.MenuEntity;
import FBMS.entity.OrderEntity;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Jieqiong
 */
public interface OrderSessionBeanRemote {

    /*E.2.1.2 Configure menu*/
    boolean configureMenu(Set<DishEntity> dishes);

    boolean configureMenu(MenuEntity menu);

    /*E.2.1.4*/
    boolean confirmOrder(OrderEntity order);

    void persist(Object object);

    /*E.2.1.3 place order*/
    boolean placeOrder(Date orderDateTime, MenuEntity menu, MemberEntity member);

    boolean placeOrder(OrderEntity order);
    
    OrderEntity viewOrder(Long orderId);
    
    boolean modifyOrder (Long orderId,Date orderDateTime, MenuEntity menu, MemberEntity member);
    
     /**
     *
     * @param dishId
     * @return
     */
    DishEntity getDish (Long dishId);
    
    MenuEntity getMenu(Long menuId);
    
    boolean setDish(DishEntity de);
    
}
