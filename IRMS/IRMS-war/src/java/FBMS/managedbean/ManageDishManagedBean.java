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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author lionetdd
 */
@ManagedBean
@ViewScoped
public class ManageDishManagedBean implements Serializable {

    @EJB
    private InventorySessionBean inventorySessionBean;
    private List<DishEntity> allDish = new ArrayList<DishEntity>();
    private DishEntity thisDish = new DishEntity();
    
    private boolean editMode;
//    private Long dishId;
//    private String dishName;
//    private Integer dishQuantity;
//    private Double dishCost;
//    private DishEntity dish = new DishEntity();

    public DishEntity getThisDish() {
        System.out.println("This is the dish id"+thisDish.getDishId());
        System.out.println("This is the dish name"+thisDish.getDishName());
        System.out.println("This is the dish quantity"+thisDish.getDishQuantity());
        System.out.println("This is the dish cost"+thisDish.getDishCost());
        return thisDish;
    }

    public void setThisDish(DishEntity thisDish) {
        this.thisDish = thisDish;
    }

    
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

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    
//    public Long getDishId() {
//        return dishId;
//    }
//
//    public void setDishId(Long dishId) {
//        this.dishId = dishId;
//    }
//
//    public String getDishName() {
//        System.out.println("here get dish name"+dishName);
//        return dishName;
//    }
//
//    public void setDishName(String dishName) {
//        this.dishName = dishName;
//    }
//
//    public Integer getDishQuantity() {
//        return dishQuantity;
//    }
//
//    public void setDishQuantity(Integer dishQuantity) {
//        this.dishQuantity = dishQuantity;
//    }
//
//    public Double getDishCost() {
//        return dishCost;
//    }
//
//    public void setDishCost(Double dishCost) {
//        this.dishCost = dishCost;
//    }

    /**
     *
     * @return
     * @throws ExistException
     * @throws IOException
     */
    public List<DishEntity> getAllDishes() throws ExistException, IOException {
        System.err.println("in getAll dishes");
        return inventorySessionBean.listDishes();

    }

    public void addDish(ActionEvent event) throws IOException, ExistException {
       
      //  thisDish = new DishEntity();
        System.out.println("we are in add dish"+thisDish.getDishId());

        try {
//            inventorySessionBean.addDish(thisDish);
            System.out.println("After addDish"+thisDish.getDishId());
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when add dish", ""));
            return;
        }
        thisDish = new DishEntity();
    }
    
       public void updateDish(ActionEvent event) throws IOException, ExistException {
           
           inventorySessionBean.updateDish(thisDish);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));
       
//        //thisDish = new DishEntity();
//        System.out.println("we are in update dish"+thisDish.getDishId());
//
//        try {
// //           inventorySessionBean.updateDish(thisDish);
//            System.out.println("After updateDish"+thisDish.getDishId());
//            
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when add dish", ""));
//            return;
//        }
//        thisDish = new DishEntity();
    }

    public void deleteDish(ActionEvent event) throws IOException, ExistException {

        //thisDish = new DishEntity();
        System.out.println("we are in delete dish");

        try {
            inventorySessionBean.deleteDish(thisDish.getDishId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when delet dish", ""));
            return;
        }
    }

    /**
     * Creates a new instance of ManageDishManagedBean
     */
    public ManageDishManagedBean() {
//        thisDish = new DishEntity();
    }
    
    
}
