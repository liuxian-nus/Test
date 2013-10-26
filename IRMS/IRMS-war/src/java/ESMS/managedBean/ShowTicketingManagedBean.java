/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import ESMS.session.ShowScheduleSessionBean;
import ESMS.session.ShowSessionBean;
import ESMS.session.ShowTicketSessionBean;
import Exception.ExistException;
import java.io.IOException;
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

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ShowTicketingManagedBean {

    @EJB
    ShowSessionBean showSessionBean;
    @EJB
    ShowScheduleSessionBean showScheduleSessionBean;
    @EJB
    ShowTicketSessionBean showTicketSessionBean;
    private ShowEntity selectedShow;
    private ShowScheduleEntity selectedShowSchedule;
    private ShowTicketEntity selectedShowTicket;
    private List<ShowEntity> showList;
    private List<ShowScheduleEntity> showSchedules = new ArrayList<ShowScheduleEntity>();
    private List<ShowTicketEntity> showTickets = new ArrayList<ShowTicketEntity>();
    private String searchName;
    private Long showId;
    private Long showScheduleId;
    private Long showTicketId;
    private boolean mode;
    private int showTicketQuota;

    //Constructor
    public ShowTicketingManagedBean() {
        selectedShow = new ShowEntity();
        selectedShowSchedule = new ShowScheduleEntity();
        selectedShowTicket = new ShowTicketEntity();
    }

    //Methods
    public List<String> complete(String query) throws ExistException {
        List<String> results = new ArrayList<String>();
        System.err.println("auto complete");

        showList = showSessionBean.getAllShows();
        for (Object o : showList) {
            ShowEntity se = (ShowEntity) o;
            if ((se.getShowName()).toString().startsWith(query)) {
                results.add((se.getShowName()).toString());
            }
        }
        return results;
    }

    public void searchByName(ActionEvent event) throws IOException, ExistException {

        System.out.println("NO6 we are in searchByName function " + searchName);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            showList = showSessionBean.getShowByName(searchName);
            if (showList == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Show does not exist!", ""));
                return;
            } else {
                System.out.println("we are after search");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("showList", showList);
                System.out.println("we are after setting parameter");
                request.getSession().setAttribute("showName", searchName);
                System.out.println("we are after setting reservationId session attribute");
                FacesContext.getCurrentInstance().getExternalContext().redirect("listShows.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }
    
    public void initViewList(PhaseEvent event) {
        showList = (List<ShowEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("showList");
    }
    
    public void handleShowChanges() {
        if (showId != null) {
            selectedShow = showSessionBean.getShowById(showId);
            showSchedules = selectedShow.getShowSchedules();
        } else {
            FacesMessage msg = new FacesMessage("Error occours during ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void handleShowScheduleChanges() {
        if (showScheduleId != null) {
            selectedShowSchedule = showScheduleSessionBean.getShowScheduleById(showScheduleId);
            showTickets = selectedShowSchedule.getShowTickets();
        } else {
            FacesMessage msg = new FacesMessage("Error occours during ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void handleShowTicketChanges() {
        if (showTicketId != null) {
            selectedShowTicket = showTicketSessionBean.getShowTicketById(showTicketId);
            showTicketQuota = selectedShowTicket.getShowTicketQuota();
        } else {
            FacesMessage msg = new FacesMessage("Error occours during ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void changeMode(){
        mode = true;
    }

    //Getters and Setters
    public ShowSessionBean getShowSessionBean() {
        return showSessionBean;
    }

    public void setShowSessionBean(ShowSessionBean showSessionBean) {
        this.showSessionBean = showSessionBean;
    }

    public List<ShowEntity> getShowList() {
        return showList;
    }

    public void setShowList(List<ShowEntity> showList) {
        this.showList = showList;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public ShowEntity getSelectedShow() {
        return selectedShow;
    }

    public void setSelectedShow(ShowEntity selectedShow) {
        this.selectedShow = selectedShow;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<ShowScheduleEntity> getShowSchedules() {
        return showSchedules;
    }

    public void setShowSchedules(List<ShowScheduleEntity> showSchedules) {
        this.showSchedules = showSchedules;
    }

    public Long getShowScheduleId() {
        return showScheduleId;
    }

    public void setShowScheduleId(Long showScheduleId) {
        this.showScheduleId = showScheduleId;
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

    public List<ShowTicketEntity> getShowTickets() {
        return showTickets;
    }

    public void setShowTickets(List<ShowTicketEntity> showTickets) {
        this.showTickets = showTickets;
    }

    public Long getShowTicketId() {
        return showTicketId;
    }

    public void setShowTicketId(Long showTicketId) {
        this.showTicketId = showTicketId;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public ShowTicketEntity getSelectedShowTicket() {
        return selectedShowTicket;
    }

    public void setSelectedShowTicket(ShowTicketEntity selectedShowTicket) {
        this.selectedShowTicket = selectedShowTicket;
    }

    public ShowTicketSessionBean getShowTicketSessionBean() {
        return showTicketSessionBean;
    }

    public void setShowTicketSessionBean(ShowTicketSessionBean showTicketSessionBean) {
        this.showTicketSessionBean = showTicketSessionBean;
    }

    public int getShowTicketQuota() {
        return showTicketQuota;
    }

    public void setShowTicketQuota(int showTicketQuota) {
        this.showTicketQuota = showTicketQuota;
    }
}
