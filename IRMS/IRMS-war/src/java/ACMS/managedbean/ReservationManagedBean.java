/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomEntity;
import ACMS.session.ReservationSessionBean;
import ACMS.session.RoomSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class ReservationManagedBean {

    @EJB
    private ReservationSessionBean reservationSessionBean;
    private List<ReservationEntity> reservatioinList;
    private ReservationEntity selectReservation;
    private Long searchId;

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    /**
     * Creates a new instance of ReservationManagedBean
     */
    public ReservationManagedBean() {
        selectReservation = new ReservationEntity();

    }

    public void searchById() throws IOException, ExistException {

        selectReservation = reservationSessionBean.getReservationById(searchId);
        System.out.println(searchId);
        if (selectReservation == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No reservation found", ""));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
            FacesContext.getCurrentInstance().getExternalContext().redirect("ReservationSearchResult.xhtml");
        }
    }

    public List<ReservationEntity> getReservatioinList() {
        return reservatioinList;
    }

    public void setReservatioinList(List<ReservationEntity> reservatioinList) {
        this.reservatioinList = reservatioinList;
    }

    public ReservationEntity getSelectReservation() {
        return selectReservation;
    }

    public void setSelectReservation(ReservationEntity selectReservation) {
        this.selectReservation = selectReservation;
    }
}
