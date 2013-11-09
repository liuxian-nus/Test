/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import CRMS.entity.MemberEntity;
import Exception.ExistException;
import FBMS.entity.CourseEntity;
import FBMS.entity.DishEntity;
import FBMS.entity.MenuEntity;
import FBMS.entity.OrderEntity;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface OrderSessionBeanRemote {

    CourseEntity addCourseToMenu(Long menuId, Long courseId) throws ExistException;

    /*E.2.1.2 Configure menu*/
    boolean configureMenu(Set<CourseEntity> courses);

    // ADD MENU
    boolean configureMenu(MenuEntity menu);

    /*E.2.1.4*/
    boolean confirmOrder(OrderEntity order);

    List<OrderEntity> getConfirmedOrders();

    CourseEntity getCourse(Long courseId);

    DishEntity getDish(Long dishId);

    MenuEntity getMenu(Long menuId);

    List<OrderEntity> getRequestedOrders();

    CourseEntity modifyCourse(CourseEntity ce);

    boolean modifyOrder(Long orderId, Date orderDateTime, MenuEntity menu, MemberEntity member);

    OrderEntity modifyOrder(Long orderId, String email, String mobile, String name, String notes, Date orderDateTime, String title, Integer numberOrder);

    void persist(Object object);

    /*E.2.1.3 place order*/
    boolean placeOrder(Date orderDateTime, MenuEntity menu, MemberEntity member);

    // ADD ORDER
    boolean placeOrder(OrderEntity order);

    //ADD COURSE
    CourseEntity setCourse(CourseEntity ce);

    boolean setDish(DishEntity de);

    /**
     *
     * @param dishId
     * @return
     */
    // ADD MENU
    boolean setMenu(MenuEntity me);

    boolean updateMenu(MenuEntity menu);

    boolean updateOrder(OrderEntity order);

    // E.3.1.1 & E.3.1.2 update sales order 'in process','confirmed','pending','suspended','terminated','goods issued','completed'
    OrderEntity updateOrderStatus(String status, Long orderId);

    OrderEntity viewOrder(Long orderId);
    
}
