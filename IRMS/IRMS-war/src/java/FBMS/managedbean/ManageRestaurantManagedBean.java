/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import Exception.ExistException;
import FBMS.session.RestaurantSessionBeanRemote;
import FBMS.entity.RestaurantEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

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
    private boolean editMode;
    private String restId;
    
  
    
    

    /**
     * Creates a new instance of ManageRestaurantManagedBean
     */
    public ManageRestaurantManagedBean() {
        selectedRestaurant = new RestaurantEntity();
    }
    
    public List<RestaurantEntity> getAllRestaurants(){
        return restaurantSessionRemote.getAllRestaurants();
        
    }
    
    public boolean isEditMode() {  
        return editMode;  
    }
    
    public RestaurantEntity getEmployees() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String loginId = (String) request.getSession().getAttribute("restId");
       
        return restaurantSessionRemote.getRestaurantById(loginId);
    }

    public void deleteRestaurant(ActionEvent event) throws ExistException {
        setRestaurantId((String) event.getComponent().getAttributes().get("code1"));
        getEm().removeRestaurant(getRestaurantId());
    }
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        restaurantSessionRemote.updateRestaurant(selectedRestaurant);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }

    public RestaurantSessionBeanRemote getEm() {
        return restaurantSessionRemote;
    }

    public void setEm(RestaurantSessionBeanRemote restaurantSessionRemote) {
        this.restaurantSessionRemote = restaurantSessionRemote;
    }

    public RestaurantEntity getSelectedRestaurant() {
        System.out.println("get selectedRestaurant");
        return selectedRestaurant;    
    }

    public void setSelectedRestaurant(RestaurantEntity selectedRestaurant) {
        this.selectedRestaurant = selectedRestaurant;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public String getRestaurantId() {
        return restId;
    }

    public void setRestaurantId(String restId) {
        this.restId = restId;
    }
}
