/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.RoomPriceEntity;
import ACMS.session.RoomPriceSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class RoomPriceManagedBean {
    @EJB
    private RoomPriceSessionBean roomPriceSessionBean;
    private List<RoomPriceEntity> roomPriceList;
    private RoomPriceEntity thisRoomPrice;
    private double newPrice;

    /**
     * Creates a new instance of RoomPriceManagedBean
     */
    public RoomPriceManagedBean() {
    } 
      public List<RoomPriceEntity> getAllRoomPrices() throws ExistException, IOException {
        System.err.println("in getAll rooms");
        return roomPriceSessionBean.getAllRoomPrices();
    }
      
      public void onCellEdit(CellEditEvent event) {  
        System.out.println("in editing roolslslslsls");
        Object oldValue = event.getOldValue();  
        Object newValue = event.getNewValue();  
          
        if(newValue != null && !newValue.equals(oldValue)) {  
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Room Price updated", "Original: " + oldValue + ", New:" + newValue);  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        }  
    }  
      
     //public doUpdate(ActionEvent event)
}
