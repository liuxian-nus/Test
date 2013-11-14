/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import CRMS.entity.MemberEntity;
import CRMS.session.VIPSessionBean;
import ERMS.session.EmailSessionBean;
import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import ESMS.session.ShowScheduleSessionBean;
import ESMS.session.ShowSessionBean;
import ESMS.session.ShowTicketSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ManageShowManagedBean {

    @EJB
    private ShowSessionBean showSessionBean;
    @EJB
    private ShowScheduleSessionBean showScheduleSessionBean;
    @EJB
    private ShowTicketSessionBean showTicketSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    @EJB
    private VIPSessionBean vipSessionBean;
    private ShowEntity selectedShow;
    private ShowScheduleEntity selectedShowSchedule;
    private ShowTicketEntity selectedShowTicket;
    private boolean editMode;
    private boolean editSchedule;
    private boolean editTicket;
    private boolean invitationStatus;
    private Long id;
    private ShowEntity filteredShows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Creates a new instance of ManageShowManagedBean
     */
    public ManageShowManagedBean() {
        selectedShow = new ShowEntity();
    }

    public void sendInvitation() {
        MemberEntity VIP = new MemberEntity();
        List<MemberEntity> VIPs = new ArrayList<MemberEntity>();
        VIPs = vipSessionBean.getSuperVIPs();

//        int number = showTicketSessionBean.getShowTicketById(selectedShow.getShowId()).getShowTicketQuota();
        int number=1000;
        if (number > VIPs.size()) {
            Iterator<MemberEntity> itr = VIPs.iterator();
            while (itr.hasNext()) {
                VIP = itr.next();
                emailSessionBean.sendShowInvitation(selectedShow, VIP);
                System.err.println("selectedShow: " + selectedShow.getShowId());
//                showTicketSessionBean.updateQuantity(selectedShow.getShowId(), 1);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Not enough ticket. ", ""));
        }
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Schedule Edited", null);
        selectedShowSchedule = (ShowScheduleEntity) event.getObject();
        showScheduleSessionBean.updateShowSchedule(selectedShowSchedule);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEditTicket(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Schedule Edited", null);
        selectedShowTicket = (ShowTicketEntity) event.getObject();
        showTicketSessionBean.updateShowTicket(selectedShowTicket);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void setShowStatus(ActionEvent event) throws IOException {
        FacesMessage msg = new FacesMessage("Status Edited", null);
        showSessionBean.updateShow(selectedShow);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().redirect("manageShow.xhtml");
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Schedule Edit Cancelled", null);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isEditMode() {
        return editMode;
    }

    public List<ShowEntity> getShows() throws ExistException {
        return showSessionBean.getAllShows();
    }

    public List<ShowEntity> getAvailableShows() throws ExistException {
        return showSessionBean.getAvailableShows();
    }

    public void deleteShow(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code1"));
        showSessionBean.deleteShow(getId());
    }

    public void deleteShowSchedule(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code2"));
        showScheduleSessionBean.deleteShowSchedule(getId());
    }

    public void deleteShowTicket(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code3"));
        showTicketSessionBean.deleteShowTicket(getId());
    }

    public void saveChanges(ActionEvent event) {
        showSessionBean.updateShow(getSelectedShow());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));
    }

    /**
     * @return the selectedShow
     */
    public ShowEntity getSelectedShow() {
        return selectedShow;
    }

    /**
     * @param selectedShow the selectedShow to set
     */
    public void setSelectedShow(ShowEntity selectedShow) {
        this.selectedShow = selectedShow;
    }

    /**
     * @param editMode the editMode to set
     */
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public ShowSessionBean getShowSessionBean() {
        return showSessionBean;
    }

    public void setShowSessionBean(ShowSessionBean showSessionBean) {
        this.showSessionBean = showSessionBean;
    }

    public ShowScheduleEntity getSelectedShowSchedule() {
        return selectedShowSchedule;
    }

    public void setSelectedShowSchedule(ShowScheduleEntity selectedShowSchedule) {
        this.selectedShowSchedule = selectedShowSchedule;
    }

    public ShowScheduleSessionBean getShowScheduleSessionBean() {
        return showScheduleSessionBean;
    }

    public void setShowScheduleSessionBean(ShowScheduleSessionBean showScheduleSessionBean) {
        this.showScheduleSessionBean = showScheduleSessionBean;
    }

    public ShowTicketSessionBean getShowTicketSessionBean() {
        return showTicketSessionBean;
    }

    public void setShowTicketSessionBean(ShowTicketSessionBean showTicketSessionBean) {
        this.showTicketSessionBean = showTicketSessionBean;
    }

    public ShowTicketEntity getSelectedShowTicket() {
        return selectedShowTicket;
    }

    public void setSelectedShowTicket(ShowTicketEntity selectedShowTicket) {
        this.selectedShowTicket = selectedShowTicket;
    }

    public boolean isEditSchedule() {
        System.err.println(editSchedule);
        return editSchedule;
    }

    public void setEditSchedule(boolean editSchedule) {
        this.editSchedule = editSchedule;
    }

    public boolean isEditTicket() {
        return editTicket;
    }

    public void setEditTicket(boolean editTicket) {
        this.editTicket = editTicket;
    }

    public ShowEntity getFilteredShows() {
        return filteredShows;
    }

    public void setFilteredShows(ShowEntity filteredShows) {
        this.filteredShows = filteredShows;
    }

    public boolean isInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(boolean invitationStatus) {
        this.invitationStatus = invitationStatus;
    }
}
