
package FBMS.managedbean;

import FBMS.entity.RestaurantEntity;
import FBMS.session.RestaurantSessionBeanRemote;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;


/**
 *
 * @author Jieqiong
 */
@ManagedBean
@RequestScoped
public final class AddRestaurantManagedBean implements Serializable{

    @EJB
    private RestaurantSessionBeanRemote restaurantSessionRemote;
    private RestaurantEntity restaurant;
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public RestaurantEntity getRestaurant(){
        return  restaurant;
    }
    
    public void setRestaurant(RestaurantEntity restaurant){
        this.restaurant=restaurant;
    }
    
    public AddRestaurantManagedBean() {
        restaurant=new RestaurantEntity();
    }
    
    public void saveNewRestaurant (ActionEvent event) throws IOException{
        try{
            System.out.println("into AddRestaurantManagedBean");
            restaurantSessionRemote.addRestaurant(restaurant);  
            System.out.println("neighbourhood"+restaurant.getRestNeighbourhood());
            System.out.println("out AddRestaurantManagedBean");
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding a new restaurant", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Restaurant Saved.", ""));
        restaurant=new RestaurantEntity();
 //       FacesContext.getCurrentInstance().getExternalContext().redirect("manageRestaurant.xhtml");
    }
    
    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addRestaurant.xhtml");
    }
    
}



