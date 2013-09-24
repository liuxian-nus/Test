/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.managedbean;

import ACMS.entity.ReservationEntity;
import ACMS.session.ReservationSessionBean;
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

/**
 *
 * @author Cookie
 */
@ManagedBean
@ViewScoped
public class ReservationManagedBean implements Serializable {

    @EJB
    private ReservationSessionBean rm;
    @EJB
    ReservationSessionBean reservationSessionBean;
    private List<ReservationEntity> reservationList;
    private ReservationEntity selectReservation;
    private String searchId;

    public String getSearchId() {
        System.out.println("No3: we are in setearchId" + searchId);
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public ReservationSessionBean getRm() {
        return rm;
    }

    public void setRm(ReservationSessionBean rm) {
        this.rm = rm;
    }

    /**
     * Creates a new instance of ReservationManagedBean
     */
    public ReservationManagedBean() {
        this.selectReservation = new ReservationEntity();
    }

    public void searchById(ActionEvent event) throws IOException, ExistException {

        System.out.println("NO6 we are in searchById function " + searchId);
        try {
            setSelectReservation(rm.getReservationById(getSearchId()));
            System.out.println("we are after search");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectReservation", selectReservation);
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("ReservationSearchResult.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public List<ReservationEntity> getReservatioinList() {
        return reservationList;
    }

    public void setReservatioinList(List<ReservationEntity> reservatioinList) {
        this.reservationList = reservatioinList;
    }

    public ReservationEntity getSelectReservation() {
        return selectReservation;
    }

    public void setSelectReservation(ReservationEntity selectReservation) {
        this.selectReservation = selectReservation;
    }

    public List<String> complete(String query) throws ExistException {
        System.out.println("NO4: we are in complete bean BEFORE");
        List<String> results = new ArrayList<String>();

        List<ReservationEntity> reservationList = reservationSessionBean.getAllReservations();
        for (Object o : reservationList) {
            ReservationEntity rve = (ReservationEntity) o;
            if (rve.getReservationId().startsWith(query)) {
                results.add(rve.getReservationId());
            }
        }
        System.out.println("NO5: we are in complete bean AFTER");
        return results;
    }
}
