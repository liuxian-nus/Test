/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.managedbean;

import ACMS.entity.RoomEntity;
import Exception.ExistException;
import FBMS.entity.DishEntity;
import FBMS.session.InventorySessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author lionetdd
 */
@Named(value = "manageInventoryManagedBean")
@Dependent
public class ManageInventoryManagedBean implements Serializable {

    @EJB
    private InventorySessionBean inventorySessionBean;
    private List<DishEntity> allDish = new ArrayList<DishEntity>();
    private DishEntity thisDish = new DishEntity();
    private Long dishId;
    private String dishName;
    private Integer dishQuantity;
    private Double dishCost;

    public InventorySessionBean getInventorySessionBean() {
        return inventorySessionBean;
    }

    public void setInventorySessionBean(InventorySessionBean inventorySessionBean) {
        this.inventorySessionBean = inventorySessionBean;
    }

    public List<DishEntity> getAllDish() {
        return allDish;
    }

    public void setAllDish(List<DishEntity> allDish) {
        this.allDish = allDish;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(Integer dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    public Double getDishCost() {
        return dishCost;
    }

    public void setDishCost(Double dishCost) {
        this.dishCost = dishCost;
    }

    public Set<DishEntity> getAllDishes() throws ExistException, IOException {
        System.err.println("in getAll dishes");
        return inventorySessionBean.listDishes();

    }

    public void addDish(ActionEvent event) throws IOException, ExistException {

        System.out.println("we are in add dish");

        try {
            inventorySessionBean.addDish(dishName, dishQuantity, dishCost);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when add dish", ""));
            return;
        }
    }

    public void deleteDish(ActionEvent event) throws IOException, ExistException {

        System.out.println("we are in delete dish");

        try {
            inventorySessionBean.deleteDish(thisDish.getDishId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when delet dish", ""));
            return;
        }
    }

    /**
     * Creates a new instance of ManageInventoryManagedBean
     */
    public ManageInventoryManagedBean() {
    }
}