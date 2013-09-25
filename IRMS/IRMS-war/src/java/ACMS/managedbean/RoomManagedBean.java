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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private int searchId;

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

    public void searchById(ActionEvent event) throws IOException, ExistException {

        System.out.println("Check-out: searching room by Id " + searchId);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            setThisRoom(rm.getRoomById(getSearchId()));
            System.out.println("we are after search");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisRoom", thisRoom);
            System.out.println("we are after setting parameter");
            request.getSession().setAttribute("roomId", getSearchId());
            System.out.println("we are after setting reservationId session attribute");

            FacesContext.getCurrentInstance().getExternalContext().redirect("RoomSearchResult.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void checkIn() throws IOException {

        System.err.println("we are in checkin");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long getRerservationId = (Long) request.getSession().getAttribute("reservationId");

        try {
            System.err.println("Reservation ID" + getRerservationId);
            System.err.println("room ID" + thisRoom.getRoomId());
            rm.checkIn(thisRoom.getRoomId(), getRerservationId);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when checking in", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "room checked-in.", ""));
    }

    public void checkOut() throws IOException {

        System.err.println("we are in check out");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int roomId = (Integer) request.getSession().getAttribute("roomId");
 
        try {
            System.err.println("room ID" + thisRoom.getRoomId());
            rm.checkOut(thisRoom.getRoomId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when checking out", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "room checked-out successfully.", ""));
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

    public RoomSessionBean getRm() {
        return rm;
    }

    public void setRm(RoomSessionBean rm) {
        this.rm = rm;
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }
}
