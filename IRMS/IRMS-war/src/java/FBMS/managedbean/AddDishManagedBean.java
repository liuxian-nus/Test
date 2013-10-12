/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import FBMS.entity.DishEntity;
import FBMS.session.InventorySessionBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class AddDishManagedBean {
    @EJB
    InventorySessionBean inventorySessionBean;
    
    private DishEntity dish = new DishEntity();
    
    public AddDishManagedBean(){
        dish = new DishEntity();
    }
    
    public void saveNewDish(ActionEvent event) throws IOException {
        System.err.println("Saving New Dish...");       
        inventorySessionBean.addDish(dish);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Dish saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("addDish.xhtml");
    }

    public InventorySessionBean getInventorySessionBean() {
        return inventorySessionBean;
    }

    public void setInventorySessionBean(InventorySessionBean inventorySessionBean) {
        this.inventorySessionBean = inventorySessionBean;
    }

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }
}
