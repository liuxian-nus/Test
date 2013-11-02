/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.OrderEntity;
import FBMS.entity.DishEntity;
import CRMS.entity.MemberEntity;
import Exception.ExistException;
import FBMS.entity.CourseEntity;
import FBMS.entity.MenuEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class OrderSessionBean implements OrderSessionBeanRemote {

    public OrderSessionBean() {
    }
    OrderEntity order;
    MenuEntity menu;
    CourseEntity thisCourse;
    MenuEntity thisMenu;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    /**
     *
     * @param dishId
     * @return
     */
    // ADD MENU
    public boolean setMenu(MenuEntity me) {
        em.persist(me);
        return true;
    }

    @Override
    public DishEntity getDish(Long dishId) {
        DishEntity de = em.find(DishEntity.class, dishId);
        return de;
    }

    @Override
    public MenuEntity getMenu(Long menuId) {
        MenuEntity m = em.find(MenuEntity.class, menuId);
        return m;
    }

    @Override
    public boolean setDish(DishEntity de) {
        em.persist(de);
        return true;
    }

    @Override //ADD COURSE
    public CourseEntity setCourse(CourseEntity ce) {
        em.persist(ce);
        return ce;
    }

    @Override
    public CourseEntity modifyCourse(CourseEntity ce) {
        em.merge(ce);
        return ce;
    }

    @Override
    public CourseEntity getCourse(Long courseId) {
        CourseEntity ce = em.find(CourseEntity.class, courseId);
        return ce;
    }

    /*E.2.1.2 Configure menu*/
    @Override
    public boolean configureMenu(Set<CourseEntity> courses) {

        System.out.println("OrderSessionBean: configure menu starts!");
        MenuEntity menu = new MenuEntity();
        menu.setCourses(courses);//a courseEntity list(with id)passed into a menu POJO 

        em.persist(menu);//menu entity persisted
        System.out.println("OrderSessionBean: configure menu is successful!");

        return true;
    }

    @Override // ADD MENU
    public boolean configureMenu(MenuEntity menu) {

        System.out.println("OrderSessionBean: configure menu starts!");
        em.persist(menu);
        System.out.println("OrderSessionBean: configure menu is successful!");

        return true;
    }

    /*E.2.1.3 place order*/
    @Override
    public boolean placeOrder(Date orderDateTime, MenuEntity menu, MemberEntity member) {
        System.out.println("OrderSessionBean: place order starts!");

        order = new OrderEntity();
        order.setOrderDateTime(orderDateTime);
        order.setMenu(menu);
        order.setMember(member);
        // order.setInvoice(invoice);

        em.persist(order);
        System.out.println("OrderSessionBean: place order is successful!");

        return true;
    }

    @Override // ADD ORDER
    public boolean placeOrder(OrderEntity order) {
        System.out.println("OrderSessionBean: place order starts!");
        em.persist(order);
        System.out.println("OrderSessionBean: place order is successful!");

        return true;
    }

    /*E.2.1.4*/
    @Override
    public boolean confirmOrder(OrderEntity order) {
        System.out.println("OrderSessionBean: confirm order starts!");
        order.setStatus("Confirmed");
        em.persist(order);
        System.out.println("OrderSessionBean: confirm order successfully!");
        return true;
    }

    @Override
    public OrderEntity viewOrder(Long orderId) {
        OrderEntity this_order = em.find(OrderEntity.class, orderId);
        System.out.println("OrderSessionBean: the order has been found!");
        return this_order;
    }

    @Override
    public boolean modifyOrder(Long orderId, Date orderDateTime, MenuEntity menu, MemberEntity member) {
        OrderEntity current = em.find(OrderEntity.class, orderId);

        if (member != null) {
            current.setMember(member);
        }

        if (menu != null) {
            current.setMenu(menu);
        }

        current.setOrderDateTime(orderDateTime);
        em.merge(current);
        System.out.println("OrderSessionBean: Order has been modified!");

        return true;
    }

    public boolean updateOrder(OrderEntity order) {
        em.merge(order);
        System.out.println("orderSessionBean: order " + order.getOrderId() + " is successfully updated");
        return true;
    }

    public boolean updateMenu(MenuEntity menu) {
        em.merge(menu);
        System.out.println("orderSessionBean: menu " + menu.getMenuId() + " is successfully updated");
        return true;
    }

    @Override
    public OrderEntity modifyOrder(Long orderId, String email, String mobile, String name, String notes, Date orderDateTime, String title, Integer numberOrder) {
        OrderEntity current = em.find(OrderEntity.class, orderId);
        if (current != null) {
            current.setEmail(email);
            current.setMobile(mobile);
            current.setName(name);
            current.setNotes(notes);
            current.setOrderDateTime(orderDateTime);
            current.setTitle(title);
            MenuEntity thisMenu = current.getMenu();
            thisMenu.setNumberOrder(numberOrder);

            Set<CourseEntity> thisCourses = thisMenu.getCourses();
            Iterator<CourseEntity> itr = thisCourses.iterator();
            while (itr.hasNext()) {
                CourseEntity ce = itr.next();
                // ce.setMenu(thisMenu);
                System.out.println("OrderSessionBean: CourseEntity's datafield menu has been set");
                ce.setQuantity(numberOrder);
                System.out.println("OrderSessionBean: CourseEntity's datafield numberPeople has been set");
                this.modifyCourse(ce);

            }

            em.merge(thisMenu);
            System.out.println("OrderSessionBean: the order menu has been modified and merged successfully");
            em.merge(current);
            System.out.println("OrderSessionBean: the order has been modified and merged successfully!");
            return current;
        } else {
            System.out.println("OrderSessionBean: the order cannot be found!");
            return null;
        }

    }

    // E.3.1.1 & E.3.1.2 update sales order 'in process','confirmed','pending','suspended','terminated','goods issued','completed'
    @Override
    public OrderEntity updateOrderStatus(String status, Long orderId) {
        order = em.find(OrderEntity.class, orderId);
        if (order != null) {
            order.setStatus(status);
            em.merge(order);
            System.out.println("OrderSessionBean:updateOrderStatus: The order has been updated to!" + status + " successfully!");
            return order;
        } else {
            System.out.println("OrderSessionBean:updateOrderStatus: The order does not exist!");
            return null;
        }
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    public CourseEntity addCourseToMenu(Long menuId, Long courseId) throws ExistException {
        thisMenu = em.find(MenuEntity.class, menuId);
        thisCourse = em.find(CourseEntity.class, courseId);
        if (thisCourse == null) {
            throw new ExistException("OrderSessionBean-->ExistException-->Invalid course !");
        }
        thisMenu.addCourse(thisCourse);
        em.merge(thisMenu);
        System.out.println("OrderSessionBean--> new menu " + thisMenu.getMenuId() + " new include new course " + thisCourse.getCourseId() + "size equal to" + thisMenu.getCourses().size());
        return thisCourse;
    }

    public List<OrderEntity> getRequestedOrders() {
        Query q = em.createQuery("SELECT m FROM OrderEntity m");
        List orderList = new ArrayList<OrderEntity>();
        for (Object o : q.getResultList()) {
            OrderEntity m = (OrderEntity) o;
            if (m.getStatus() == "Requested") {
                orderList.add(m);
            }
        }
        return orderList;
    }

    public List<OrderEntity> getConfirmedOrders() {
         Query q = em.createQuery("SELECT m FROM OrderEntity m");
        List orderList = new ArrayList<OrderEntity>();
        for (Object o : q.getResultList()) {
            OrderEntity m = (OrderEntity) o;
            if (m.getStatus() == "Confirmed") {
                orderList.add(m);
            }
        }
        return orderList;
    }
}
