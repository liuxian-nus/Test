/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.CourseEntity;
import FBMS.entity.DishEntity;
import FBMS.entity.MenuEntity;
import FBMS.entity.OrderEntity;
import java.util.HashSet;
import java.util.Iterator;
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
public class InventorySessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    DishEntity de;
    OrderEntity oe;

    
    public InventorySessionBean(){}
    
    public DishEntity addDish(String dishName,Integer dishQuantity,Double dishCost)
    {
        de = new DishEntity();
        de.setDishCost(dishCost);
        de.setDishName(dishName);
        de.setDishQuantity(dishQuantity);
        em.persist(de);
        return de;
    }
    
    public boolean deleteDish(Long dishId)
    {
        de = em.find(DishEntity.class, dishId);
        if(de !=null)
        {
            em.remove(de);
            System.out.println("InventorySessionBean: The dish has been found and removed!"+dishId);
            return true;
        }
        else
        {
            System.out.println("InventorySessionBean: The dish does not exist!"+dishId);
            return false;
        }
    }
    
    public DishEntity updateDish(Long dishId,String dishName,Integer dishQuantity,Double dishCost)
    {
        de = em.find(DishEntity.class, dishId);
        if(de!=null)
        {
            de.setDishCost(dishCost);
            de.setDishName(dishName);
            de.setDishQuantity(dishQuantity);
            em.merge(de);
            System.out.println("InventorySessionBean: The dish has been updated successfully!"+de.getDishName()+de.getDishCost()+de.getDishQuantity());
            return de;
        }
        else
        {
            System.out.println("InventorySessionBean: The dish does not exist!"+dishId);
            return null;
        }
    }
    
    public OrderEntity issueGoods(Long orderId)
    {
        oe = em.find(OrderEntity.class,orderId);
        if(oe!=null)
        {
           MenuEntity menu = oe.getMenu();
           Set <CourseEntity> courses = menu.getCourses();
           System.out.println("InventorySessionBean:issueGoods: Both Menu and courses have been found! MenuId: "
                   +menu.getMenuId());
           int courseNumber = courses.size();
           int i =1;
           CourseEntity course = new CourseEntity();
           Iterator <CourseEntity> itr = courses.iterator();
           
           while(itr.hasNext())
           {
               course = itr.next();
               course.getDish().setDishQuantity(oe.getMenu().getNumberOrder()+course.getDish().getDishQuantity());
               System.out.println("InventorySessionBean:issueGoods: course"+i+": inventory has been deducted by "+oe.getMenu().getNumberOrder());
               em.merge(course);
               i++;
           }
           
           Double orderCost = this.assignCost(orderId);
           System.out.println("InventorySessionBean: issueGoods: The order associated cost has been assigned "+orderCost);
           
           
           oe.setStatus("goods issued");
           em.merge(oe);
           return oe; 
        }
        else
        {
            System.out.println("InventorySessionBean:issueGoods: The dish does not exist!"+orderId);
            return null;
        }
    }
    
    public Double assignCost(Long orderId)
    {
        Double currentCost=0.00;
        oe = em.find(OrderEntity.class,orderId);
        if(oe!=null)
        {
            MenuEntity menu = oe.getMenu();
            Set <CourseEntity> courses = menu.getCourses();
            Iterator <CourseEntity> itr = courses.iterator();
            CourseEntity course;
            
            while(itr.hasNext())
            {
                course = itr.next();
                Double unitCost = course.getDish().getDishCost();
                System.out.println("InventorySessionBean:assignCost: The course "+course.getDish().getDishName()+
                        " and its cost is "+unitCost);
                Integer unit = menu.getNumberOrder();
                currentCost = currentCost+ unit*unitCost;
                System.out.println("InventorySessionBean:assignCost: The currentCost is "+currentCost);
            }
            //Assign cost to the order
            oe.setCost(currentCost);
            
            //Assign salePrice to the order
            Integer numberPeople = oe.getMenu().getNumberOrder();
            Double uc = oe.getMenu().getCourses().size()*1.50;
            Double salePrice = numberPeople * uc;
            oe.setSalePrice(salePrice);
            
            em.merge(oe);
            System.out.println("InventorySessionBean:assignCost: The cost assigned is "+oe.getCost());
            return currentCost;
        }
        else
        {
            System.out.println("InventorySessionBean:assignCost: The order does not exist!"+orderId);
            return null;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Set <DishEntity> listDishes()
    {
        Query q = em.createQuery("SELECT d FROM DishEntity d");
        
        Set stateSet = new HashSet <DishEntity> ();
        for (Object o : q.getResultList())
        {
            DishEntity dish = (DishEntity)o;
            stateSet.add(dish);
            System.out.println("InventorySessionBean: listDishes: new dish has been added");
        }
        
        return stateSet;
    }
    

}
