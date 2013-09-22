/*
 * Model!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Primeface Showcase: Data table -- Expandable Rows
 */
package ACMS.managedbean;

import ACMS.entity.RoomEntity;
import ACMS.session.RoomSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@RequestScoped
public class RoomManagedBean {

    private List<RoomEntity> roomList;
    private List<RoomEntity> selectRoom;
    private RoomSessionBean rm;

    public RoomManagedBean() throws ExistException {
        roomList = new ArrayList<RoomEntity>();
    }

    public void initView(PhaseEvent event) {
        roomList = (List<RoomEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("AllRooms");
    }

    public List<RoomEntity> getRooms() throws ExistException {
        return roomList;
    }

    public List<RoomEntity> getAvailableRooms() throws ExistException {
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
        return selectRoom;
    }//end of getAvailableRooms()

    //capture member transaction missing.....
    //check-in, sessionScope reservationId missing..........
    public void checkIn(ActionEvent event) throws IOException {
        RoomEntity thisRoom = new RoomEntity();
        Date checkInDate = null;
        Date checkOutDate = null;
        try {
            rm.checkIn(thisRoom.getRoomId(), checkInDate, checkOutDate);
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
