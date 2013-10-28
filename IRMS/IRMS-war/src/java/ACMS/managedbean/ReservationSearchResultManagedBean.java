/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.ReservationEntity;
import ACMS.session.ReservationSessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
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

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class ReservationSearchResultManagedBean implements Serializable {
    @EJB
    private ReservationSessionBean reservationSessionBean;

    @EJB
    private ReservationEntity selectReservation;
    private List<ReservationEntity> reservationList;
    private Long reservationId;
    

    public List<ReservationEntity> getReservationList() {
        System.err.println("in get all reservations");
        return reservationSessionBean.getAllReservations();
    }

    public void setReservationList(List<ReservationEntity> reservationList) {
        this.reservationList = reservationList;
    }

    public ReservationEntity getSelectReservation() {
        return selectReservation;
    }

    public void setSelectReservation(ReservationEntity selectReservation) {
        this.selectReservation = selectReservation;
    }

    /**
     * Creates a new instance of ReservationSearchResultManagedBean
     */
    public ReservationSearchResultManagedBean() {
    }

    public void initViewSelect(PhaseEvent event) {
        selectReservation = (ReservationEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectReservation");
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void initViewList(PhaseEvent event) {
        reservationList = (List<ReservationEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("reservationList");
    }

    public void cancelReservation(ActionEvent event) throws IOException {
        System.out.println("We are in cancellation of reservation");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            reservationId = (Long) request.getSession().getAttribute("reservationId");
            reservationSessionBean.cancelReservation(reservationId);
            System.out.println("we are after search roomID: " + reservationId);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("thisReservation", reservationSessionBean.getReservationById(reservationId));
            System.out.println("we are after setting parameter");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when cancelling reservation", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "this reservation is cancelled", ""));

    }
}
