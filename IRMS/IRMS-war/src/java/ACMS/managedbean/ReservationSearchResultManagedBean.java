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
import javax.annotation.PostConstruct;
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
    private ReservationEntity selectReservation;
    private List<ReservationEntity> reservationList;
    private Long reservationId;
//
//    @PostConstruct
//    public void init() {
//        selectReservation = getParameterReservation();
//    }

//    public ReservationEntity getParameterReservation() {
//        return (ReservationEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectReservation");
//    }
    public ReservationEntity getSelectReservation() {
        return selectReservation;
    }

    public void setSelectReservation(ReservationEntity selectReservation) {
        this.selectReservation = selectReservation;
    }

    public List<ReservationEntity> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<ReservationEntity> reservationList) {
        this.reservationList = reservationList;
    }

    /**
     * Creates a new instance of ReservationSearchResultManagedBean
     */
    public ReservationSearchResultManagedBean() {
    }

    public void initViewSelect(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        selectReservation = (ReservationEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectReservation");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
        System.err.println("in getting selectReservation " + selectReservation.getReservationId());
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void initViewList(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        reservationList = (List<ReservationEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("reservationList");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationList", reservationList);
        String searchEmail = (String) request.getSession().getAttribute("rcEmail");
        request.getSession().setAttribute("rcEmail", searchEmail);

    }

    public void cancelReservation(ActionEvent event) throws IOException {
        System.out.println("We are in cancellation of reservation");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            reservationId = (Long) request.getSession().getAttribute("reservationId");
            reservationSessionBean.cancelReservation(reservationId);
            System.out.println("we are after search roomID: " + reservationId);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
            System.out.println("we are after setting parameter");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when cancelling reservation", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "this reservation is cancelled", ""));

    }
}
