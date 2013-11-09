/*
 * Model!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Primeface Showcase: Data table -- Expandable Rows
 */
package ACMS.managedbean;

import ACMS.entity.RoomEntity;
import ACMS.session.RoomSessionBean;
import ACMS.entity.RoomServiceEntity;
import ACMS.session.RoomServiceSessionBean;
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
    @EJB
    private RoomServiceSessionBean rs;
    private List<RoomEntity> selectRoom = new ArrayList<RoomEntity>();
    private Long reservationId;
    private String guestName;
    private RoomEntity thisRoom = new RoomEntity();
    private RoomServiceEntity thisRoomService = new RoomServiceEntity();
    private int searchId;
    private int roomId;
    private String serviceName;
    private String creditCardNo;
    private int serviceQuantity = 1;

    public int getServiceQuantity() {
        return serviceQuantity;
    }

    public void setServiceQuantity(int serviceQuantity) {
        this.serviceQuantity = serviceQuantity;
    }

    public RoomServiceSessionBean getRs() {
        return rs;
    }

    public void setRs(RoomServiceSessionBean rs) {
        this.rs = rs;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public RoomServiceEntity getThisRoomService() {
        return thisRoomService;
    }

    public void setThisRoomService(RoomServiceEntity thisRoomService) {
        this.thisRoomService = thisRoomService;
    }

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

    public void saveNewRoomService(ActionEvent event) throws ExistException {
        System.out.println("saving new room services...");
        System.out.println("SaveNewRoomService Name: " + serviceName);
        System.out.println("room ID: " + roomId);
        System.out.println("service quantity is: " + serviceQuantity);
        try {
            rm.addRoomService(roomId, serviceName, serviceQuantity);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new room service", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Room Service saved.", ""));
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

    public List<RoomEntity> getCheckInRooms() throws ExistException {
        System.err.println("we are in checkin managedbean");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long getRerservationId = (Long) request.getSession().getAttribute("reservationId");
        System.out.println(" getting chekin rooms" + getRerservationId);
//        request.getSession().setAttribute("reservationId", getRerservationId);
        return rm.getCheckInRooms(getRerservationId);
    }

    public void searchById(ActionEvent event) throws IOException, ExistException {

        System.out.println("Check-out: searching room by Id " + searchId);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            thisRoom = rm.getRoomById(getSearchId());
            if (thisRoom == null) {
                System.out.println("we are in no room loop");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The room does not exist!", ""));
                return;
            } else {
                System.out.println("we are after search");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisRoom", thisRoom);
                System.out.println("we are after setting parameter");
                request.getSession().setAttribute("roomId", getSearchId());
                System.out.println("we are after setting reservationId session attribute");
                FacesContext.getCurrentInstance().getExternalContext().redirect("RoomSearchResult.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void checkIn() throws IOException {

        System.err.println("we are in checkin managedbean");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long getRerservationId = (Long) request.getSession().getAttribute("reservationId");
        String getGuestName = (String) request.getSession().getAttribute("guestName");

        try {
            System.err.println("Reservation ID" + getRerservationId);
            System.err.println("room ID" + thisRoom.getRoomId());
            rm.checkIn(thisRoom.getRoomId(), getRerservationId, guestName);
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
            System.err.println("room ID" + roomId);
            if (rm.getRoomById(roomId).getRoomServiceCharge() != 0.0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The service charge has not been cleared.", ""));
            }
            if (rm.getRoomById(roomId).getReservation() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The room is not checked in!", ""));
            } else {
                rm.checkOut(roomId);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "room checked-out successfully.Please send housekeepers to clean-up the room", ""));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when checking out", ""));
            return;
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("listAllRooms.xhtml");
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
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

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
}
