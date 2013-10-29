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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
      
     //public doUpdate(ActionEvent event)
}
