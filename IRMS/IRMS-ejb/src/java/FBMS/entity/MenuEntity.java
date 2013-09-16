/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.entity;

import FBMS.entity.DishEntity;
import FBMS.entity.OrderEntity;
import java.util.Set;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Jieqiong
 */
@Entity
public class MenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long menuId;
    @OneToMany (cascade={CascadeType.ALL},mappedBy = "menu")
    private Set<DishEntity> dishes;
    @OneToOne(cascade={CascadeType.ALL},mappedBy = "menu")
    private OrderEntity order;
    
    public Long getMenuId() {
        return menuId;
    }

    public void setId(Long id) {
        menuId = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    
   public Set<DishEntity> getDishes(){
       return dishes;
   }
   
   public void setDishes(Set<DishEntity> dishes){
       this.dishes=dishes;
   }
    
}
