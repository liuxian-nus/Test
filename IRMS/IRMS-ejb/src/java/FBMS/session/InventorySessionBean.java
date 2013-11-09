/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import Exception.ExistException;
import FBMS.entity.CourseEntity;
import FBMS.entity.DishEntity;
import FBMS.entity.MenuEntity;
import FBMS.entity.OrderEntity;
import java.util.ArrayList;
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
public class InventorySessionBean implements InventorySessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    DishEntity de;
    OrderEntity oe;

    public InventorySessionBean() {
    }

    @Override
    public DishEntity addDish(DishEntity dish) {
        em.persist(dish);
        return dish;
    }

    @Override
    public boolean deleteDish(Long dishId) {
        de = em.find(DishEntity.class, dishId);
        if (de != null) {
            em.remove(de);
            System.out.println("InventorySessionBean: The dish has been found and removed!" + dishId);
            return true;
        } else {
            System.out.println("InventorySessionBean: The dish does not exist!" + dishId);
            return false;
        }
    }

    @Override
    public DishEntity updateDish(DishEntity dish) {
        em.merge(dish);
        System.out.println("InventorySessionBean: The dish has been updated successfully!" + dish.getDishName() + dish.getDishCost() + dish.getDishQuantity());
        return dish;
    }

    @Override
    public OrderEntity issueGoods(Long orderId) {
        oe = em.find(OrderEntity.class, orderId);
        if (oe != null) {
            MenuEntity menu = oe.getMenu();
            Set<CourseEntity> courses = menu.getCourses();
            System.out.println("InventorySessionBean:issueGoods: Both Menu and courses have been found! MenuId: "
                    + menu.getMenuId());
            int courseNumber = courses.size();
            int i = 1;
            CourseEntity course = new CourseEntity();
            Iterator<CourseEntity> itr = courses.iterator();

            while (itr.hasNext()) {
                course = itr.next();
                course.getDish().setDishQuantity(oe.getMenu().getNumberOrder() + course.getDish().getDishQuantity());
                System.out.println("InventorySessionBean:issueGoods: course" + i + ": inventory has been deducted by " + oe.getMenu().getNumberOrder());
                em.merge(course);
                i++;
            }

            Double orderCost = this.assignCost(orderId);
            System.out.println("InventorySessionBean: issueGoods: The order associated cost has been assigned " + orderCost);


            oe.setStatus("goods issued");
            em.merge(oe);
            return oe;
        } else {
            System.out.println("InventorySessionBean:issueGoods: The dish does not exist!" + orderId);
            return null;
        }
    }

    @Override
    public Double assignCost(Long orderId) {
        Double currentCost = 0.00;
        oe = em.find(OrderEntity.class, orderId);
        if (oe != null) {
            MenuEntity menu = oe.getMenu();
            Set<CourseEntity> courses = menu.getCourses();
            Iterator<CourseEntity> itr = courses.iterator();
            CourseEntity course;

            while (itr.hasNext()) {
                course = itr.next();
                Double unitCost = course.getDish().getDishCost();
                System.out.println("InventorySessionBean:assignCost: The course " + course.getDish().getDishName()
                        + " and its cost is " + unitCost);
                Integer unit = menu.getNumberOrder();
                currentCost = currentCost + unit * unitCost;
                System.out.println("InventorySessionBean:assignCost: The currentCost is " + currentCost);
            }
            //Assign cost to the order
            oe.setCost(currentCost);

            //Assign salePrice to the order
            Integer numberPeople = oe.getMenu().getNumberOrder();
            Double uc = oe.getMenu().getCourses().size() * 1.50;
            Double salePrice = numberPeople * uc;
            oe.setSalePrice(salePrice);

            em.merge(oe);
            System.out.println("InventorySessionBean:assignCost: The cost assigned is " + oe.getCost());
            return currentCost;
        } else {
            System.out.println("InventorySessionBean:assignCost: The order does not exist!" + orderId);
            return null;
        }
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<DishEntity> listDishes() {
        Query q = em.createQuery("SELECT d FROM DishEntity d");

        List dishList = new ArrayList<DishEntity>();
        for (Object o : q.getResultList()) {
            DishEntity dish = (DishEntity) o;
            dishList.add(dish);
            System.out.println("InventorySessionBean: listDishes: new dish has been added");
        }

        return dishList;
    }
    
    @Override
    public DishEntity getDishById(Long dishId) throws ExistException
    {
         de = em.find(DishEntity.class, dishId);
        if (de == null) {
            throw new ExistException("Dish does not exist!");
        }
        return de;
    }
}
