/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.RoomEntity;
import ACMS.session.RoomSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class RoomSearchResultManagedBean implements Serializable {

    @EJB
    private RoomSessionBean roomSessionBean;
    private RoomEntity thisRoom;
    private List<RoomEntity> rooms;
    private int roomId;

    public RoomSearchResultManagedBean() {
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public RoomEntity getThisRoom() {
        return thisRoom;
    }

    public void setThisRoom(RoomEntity thisRoom) {
        this.thisRoom = thisRoom;
    }

    public void initViewSelect(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        thisRoom = (RoomEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("thisRoom");
        request.getSession().setAttribute("roomId", thisRoom.getRoomId());
    }

    public void intiViewAgain(PhaseEvent event) {
        rooms = (List<RoomEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("Rooms");
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void clearService(ActionEvent event) throws IOException, ExistException {

        System.out.println("we are in clear service charge");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        try {
            roomId = (Integer) request.getSession().getAttribute("roomId");
            roomSessionBean.clearServiceCharge(roomId);
            System.out.println("we are after search roomID: " + roomId);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisRoom", roomSessionBean.getRoomById(roomId));
            System.out.println("we are after setting parameter");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when clearing search", ""));
            return;
        }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "room service charge successfully cleared.", ""));

    }
    
    public void updateHousekeeping(ActionEvent event) throws IOException {
        System.out.println("We are in set housekeeping");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
try {
            roomId = (Integer) request.getSession().getAttribute("roomId");
            roomSessionBean.updateHousekeeping(roomId);
            System.out.println("we are after search roomID: " + roomId);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisRoom", roomSessionBean.getRoomById(roomId));
            System.out.println("we are after setting parameter");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when setting housekeeping status", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "room cleaned-up, ready for next check in.", ""));

    }
   /* public void sendBill() throws IOException {
        System.out.println("we are in clear service charge");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        try {
            roomId = (Integer) request.getSession().getAttribute("roomId");
            roomSessionBean.sendBill(roomId);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisRoom", roomSessionBean.getRoomById(roomId));
            System.out.println("we are after setting parameter");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when clearing search", ""));
            return;
        }
    }*/
}
