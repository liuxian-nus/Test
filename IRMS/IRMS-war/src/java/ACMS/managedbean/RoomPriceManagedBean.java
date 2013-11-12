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
import javax.annotation.PostConstruct;
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
    @PostConstruct
    public void init() {
        roomPriceList = roomPriceSessionBean.getAllRoomPrices();
    }

    public RoomPriceManagedBean() {
        thisRoomPrice = new RoomPriceEntity();
    }

    public List<RoomPriceEntity> getAllRoomPrices() throws ExistException, IOException {
        System.err.println("in getAll rooms");
        return roomPriceSessionBean.getAllRoomPrices();
    }

    public void onCellEdit(CellEditEvent event) {
        System.out.println("in editing roolslslslsls");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        System.out.println("here in editing price" + newValue);
        if (newValue != null && !newValue.equals(oldValue)) {
            System.out.println("here in editing price" + thisRoomPrice.getPriceType());
            thisRoomPrice.setPrice(Double.valueOf((String) newValue));
            roomPriceSessionBean.updatePrice(thisRoomPrice);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Room Price updated", "Original: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<RoomPriceEntity> getRoomPriceList() {
        return roomPriceList;
    }

    public void setRoomPriceList(List<RoomPriceEntity> roomPriceList) {
        this.roomPriceList = roomPriceList;
    }

    public RoomPriceEntity getThisRoomPrice() {
        return thisRoomPrice;
    }

    public void setThisRoomPrice(RoomPriceEntity thisRoomPrice) {
        this.thisRoomPrice = thisRoomPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
    //public doUpdate(ActionEvent event)
}
