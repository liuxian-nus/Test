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
import java.io.Serializable;
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
public class RoomManagedBean implements Serializable {

    @EJB
    private RoomSessionBean rm;
    
    private List<RoomEntity> selectRoom = new ArrayList<RoomEntity>();
    private Long reservationId;
    private RoomEntity thisRoom = new RoomEntity();

    public RoomEntity getThisRoom() {
        return thisRoom;
    }

    public void setThisRoom(RoomEntity thisRoom) {
        this.thisRoom = thisRoom;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public List<RoomEntity> getSelectRoom() {
        return selectRoom;
    }

    public void setSelectRoom(List<RoomEntity> selectRoom) {
        this.selectRoom = selectRoom;
    }

    public RoomManagedBean() throws ExistException {
    }

    public List<RoomEntity> getAllRooms() throws ExistException, IOException {
        System.err.println("in getAll rooms");
        return rm.getAllRooms();

    }//end of getAllRooms()

    public List<RoomEntity> getAvailableRooms() throws ExistException, IOException {
        System.err.println("in getAvailable rooms");
        return rm.getAvailableRooms();

    }//end of getAllRooms()
    //capture member transaction missing.....
    //check-in, sessionScope reservationId missing..........

    public void checkIn() throws IOException {
        try {
            System.err.println("we are in checkin");
            System.err.println("Reservation ID" + reservationId);
            System.err.println("room ID" + thisRoom.getRoomId());
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
    
   public void initViewSelect(PhaseEvent event) {
       System.err.println("in initial view function"); 
        this.reservationId = (Long) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("ReservationId");
   System.err.println("reservaion Id get" + reservationId); 
   }
}
