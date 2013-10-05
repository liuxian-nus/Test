/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.DishEntity;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    
    public InventorySessionBean(){}
    
    public DishEntity addDish(String dishName,Integer dishQuantity,Double dishCost)
    {
        de = new DishEntity();
        de.setDishCost(dishCost);
        de.setDishName(dishName);
        de.setDishQuantity(dishQuantity);
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
            System.out.println("InventorySessionBean: The dish has been updated successfully!"+de.getDishName()+de.getDishCost()+de.getDishQuantity());
            return de;
        }
        else
        {
            System.out.println("InventorySessionBean: The dish does not exist!"+dishId);
            return null;
        }
    }
    

    public void persist(Object object) {
        em.persist(object);
    }
    
    

}
