/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.RoomServiceEntity;
import ACMS.session.RoomServiceSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class RoomServiceManagedBean {
    @EJB
    private RoomServiceSessionBean roomServiceSessionBean;
    private DualListModel<RoomServiceEntity> roomServices;
    private RoomServiceEntity thisRoomService;
    private String roomServiceName;
    private double roomServicePrice;
    
    public RoomServiceManagedBean() {
    }
    
    public void initViewSelect(PhaseEvent event) {
        System.err.println("in initial view function");
        this.roomServiceName = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("roomServiceName");
        System.err.println("reservaion Id get" + roomServiceName);
    }

    public DualListModel<RoomServiceEntity> getAllRoomServices() throws ExistException {
        List<RoomServiceEntity> source = new ArrayList<RoomServiceEntity>();
        List<RoomServiceEntity> target = new ArrayList<RoomServiceEntity>();
        source = roomServiceSessionBean.getAllRoomServices();
        target = roomServiceSessionBean.getAllRoomServices();
        System.err.println("in get all room services");
        roomServices = new DualListModel<RoomServiceEntity>(source, target);  
        return roomServices;
    }

    public void setRoomServices(DualListModel<RoomServiceEntity> roomServices) {
        this.roomServices = roomServices;
    }

    public RoomServiceEntity getThisRoomService() {
        return thisRoomService;
    }

    public void setThisRoomService(RoomServiceEntity thisRoomService) {
        this.thisRoomService = thisRoomService;
    }

    public String getRoomServiceName() {
        return roomServiceName;
    }

    public void setRoomServiceName(String roomServiceName) {
        this.roomServiceName = roomServiceName;
    }

    public double getRoomServicePrice() {
        return roomServicePrice;
    }

    public void setRoomServicePrice(double roomServicePrice) {
        this.roomServicePrice = roomServicePrice;
    }
    
}
