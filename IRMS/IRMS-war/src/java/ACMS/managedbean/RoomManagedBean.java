/*
 * Model!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Primeface Showcase: Data table -- Expandable Rows
 */
package ACMS.managedbean;

import ACMS.entity.RoomEntity;
import ACMS.session.RoomSessionBean;
import ACMS.entity.ReservationEntity;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class RoomManagedBean {
    
    @EJB
    private RoomSessionBean rm;

    private List<RoomEntity> roomList = new ArrayList<RoomEntity>();
    private List<RoomEntity> selectRoom;
    private List<RoomEntity> roomList2 = new ArrayList<RoomEntity>();

    public List<RoomEntity> getRoomList2() {
        return roomList2;
    }

    public void setRoomList2(List<RoomEntity> roomList2) {
        this.roomList2 = roomList2;
    }
    
    private Long reservationId;
    
    public RoomManagedBean() throws ExistException {
        roomList = rm.getAllRooms();
    }

    public void initViewAll(PhaseEvent event) {
        roomList = (List<RoomEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("AllRooms");
    }
    
    public void initViewAvailable(PhaseEvent event) {
        roomList2 = (List<RoomEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("AllRooms");
    }

    public List<RoomEntity> getRooms() throws ExistException {
        return roomList;
    }

    public void getAvailableRooms(ActionEvent event) throws ExistException, IOException {
        int i = 0;
        RoomEntity oneRoom;
        oneRoom = roomList.get(i);
        if (oneRoom.getRoomStatus().equals("available")) {
            selectRoom.add(oneRoom);
        }
        while (i < (roomList.size() - 1)) {
            i++;
            oneRoom = roomList.get(i);
            if (oneRoom.getRoomStatus().equals("available")) {
                selectRoom.add(oneRoom);
            }//end of if
        }//end of while
        System.err.println("in Getavailable rooms");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("AvailableRooms", selectRoom);
        FacesContext.getCurrentInstance().getExternalContext().redirect("listAvailableRooms.xhtml");
       
    }//end of getAvailableRooms()

    //capture member transaction missing.....
    //check-in, sessionScope reservationId missing..........
    public void checkIn(ActionEvent event) throws IOException {
        RoomEntity thisRoom = new RoomEntity();
        try {
            rm.checkIn(thisRoom.getRoomId(), reservationId);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when checking in", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "room checked-in.", ""));

    }

    //this one copied from PrimeFace showcase
    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((RoomEntity) event.getData()).getRoomId());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
