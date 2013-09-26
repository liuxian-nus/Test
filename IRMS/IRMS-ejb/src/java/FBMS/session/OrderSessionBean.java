/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.OrderEntity;
import FBMS.entity.DishEntity;
import CRMS.entity.MemberEntity;
import FBMS.entity.MenuEntity;
import java.util.Date;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Jieqiong
 */
@Stateless
@LocalBean
public class OrderSessionBean implements OrderSessionBeanRemote {
    
    public OrderSessionBean(){
    }
    
    OrderEntity order;
    MenuEntity menu;
    
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    /**
     *
     * @param dishId
     * @return
     */
    @Override
    public DishEntity getDish (Long dishId)
    {
        DishEntity de = em.find(DishEntity.class, dishId);
        return de;
    }
    
    /*E.2.1.2 Configure menu*/
    @Override
    public boolean configureMenu(Set<DishEntity> dishes){
        
        System.out.println("OrderSessionBean: configure menu starts!");
        MenuEntity menu=new MenuEntity();
        menu.setDishes(dishes);
        
        em.persist(menu);
        System.out.println("OrderSessionBean: configure menu is successful!");
        
        return true;
    }
    
    @Override
    public boolean configureMenu(MenuEntity menu){
        
        System.out.println("OrderSessionBean: configure menu starts!");      
        em.persist(menu);
        System.out.println("OrderSessionBean: configure menu is successful!");
        
        return true;
    }
    
    /*E.2.1.3 place order*/
        @Override
        public boolean placeOrder(Date orderDateTime, MenuEntity menu, MemberEntity member){
        System.out.println("OrderSessionBean: place order starts!");
        
        order=new OrderEntity();
        order.setOrderDateTime(orderDateTime);
        order.setMenu(menu);
        order.setMember(member);
       // order.setInvoice(invoice);
        
        em.persist(order);
        System.out.println("OrderSessionBean: place order is successful!");
        
        return true;
    }
    
    @Override
     public boolean placeOrder(OrderEntity order){
        System.out.println("OrderSessionBean: place order starts!");    
        em.persist(order);
        System.out.println("OrderSessionBean: place order is successful!");
        
        return true;
    }
    
    /*E.2.1.4*/
    @Override
    public boolean confirmOrder(OrderEntity order){
        System.out.println("OrderSessionBean: confirm order starts!"); 
        order.setStatus("Confirmed");
        System.out.println("OrderSessionBean: confirm order successfully!");
        return true;
    }
    
    @Override
    public OrderEntity viewOrder(Long orderId){
        OrderEntity this_order = em.find(OrderEntity.class,orderId);
        System.out.println("OrderSessionBean: the order has been found!");
        return this_order;
    }
    
    @Override
    public boolean modifyOrder (Long orderId,Date orderDateTime, MenuEntity menu, MemberEntity member){
        OrderEntity current = em.find(OrderEntity.class, orderId);
        current.setMember(member);
        current.setMenu(menu);
        current.setOrderDateTime(orderDateTime);
        em.merge(current);
        System.out.println("OrderSessionBean: Order has been modified!");
       
        return true;
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    
  


}
