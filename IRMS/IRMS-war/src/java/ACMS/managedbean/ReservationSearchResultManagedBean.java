/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.ReservationEntity;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class ReservationSearchResultManagedBean implements Serializable {

    private ReservationEntity selectReservation;
    private List<ReservationEntity> reservations;
    private Long reservationId;

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
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

    public void initViewAll(PhaseEvent event) {
        reservations = (List<ReservationEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("Reservations");
    }

    public void redirect(ActionEvent event) {
    
        try {
            System.out.println("we in redirect function" +selectReservation.getReservationId());
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("ReservationId", selectReservation.getReservationId());
            System.err.println("we are after setting reservation ID" + selectReservation.getReservationId() );
            FacesContext.getCurrentInstance().getExternalContext().redirect("listAvailableRooms.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
        }
    }
}
