/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import FBMS.session.RestaurantSessionBeanRemote;
import FBMS.entity.RestaurantEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@ViewScoped
public class ManageRestaurantManagedBean {
    @EJB
    private RestaurantSessionBeanRemote restaurantSessionRemote;
    private RestaurantEntity selectedRestaurant;
    private List<RestaurantEntity> restaurants;
    
  
    
    

    /**
     * Creates a new instance of ManageRestaurantManagedBean
     */
    public ManageRestaurantManagedBean() {
    }
    
    public List<RestaurantEntity> getAllRestaurants(){
        return restaurantSessionRemote.getAllRestaurants();
        
    }
}
