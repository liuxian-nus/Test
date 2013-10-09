/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.ReservationEntity;
import ACMS.session.ReservationSessionBean;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
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
    @EJB
    private ReservationSessionBean re;

    private ReservationEntity selectReservation;
    private List<ReservationEntity> reservations;
    private Long reservationId;

    public List<ReservationEntity> getReservations() {
        System.err.println("in get all reservations");
        return re.getAllReservations();
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

}
