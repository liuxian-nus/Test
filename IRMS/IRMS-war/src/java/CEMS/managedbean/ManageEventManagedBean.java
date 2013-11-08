/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.EventEntity;
import CEMS.session.EventSessionBean;
import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ManageEventManagedBean {

    @EJB
    EventSessionBean eventSessionBean;
    @EJB
    EmployeeSessionBean employeeSessionBean;
    private EventEntity selectedEvent;
    private List<EventEntity> events;
    private List<EventEntity> pendingEvents;
    private List<EventEntity> reservedEvents;
    private List<EventEntity> confirmedEvents;
    private List<EventEntity> canceledEvents;
    private List<EmployeeEntity> managers;
    private final static String[] eventStatus;
    private final static String[] eventStatus2;
    private final static String[] eventStatus3;
    private boolean editMode;
    private String status;
    private Long id;
    private String selectedManager;

    public ManageEventManagedBean() {
        selectedEvent = new EventEntity();
    }

    @PostConstruct
    public void init() throws ExistException {
        events = eventSessionBean.getAllEvents();
        pendingEvents = eventSessionBean.getPendingEvents();
        reservedEvents = eventSessionBean.getReservedEvents();
        confirmedEvents = eventSessionBean.getConfirmedEvents();
        canceledEvents = eventSessionBean.getCanceledEvents();
        managers = employeeSessionBean.getCEMSEvent();
    }

    static {
        eventStatus = new String[2];
        eventStatus[0] = "Pending";
        eventStatus[1] = "Reserved";
    }

    static {
        eventStatus2 = new String[3];
        eventStatus2[0] = "Reserved";
        eventStatus2[1] = "Confirmed";
        eventStatus2[2] = "Cancel";
    }

    static {
        eventStatus3 = new String[2];
        eventStatus3[0] = "Confirmed";
        eventStatus3[1] = "Cancel";
    }

    public List<EventEntity> getEvents() throws ExistException {
        return events;
    }

    public void deleteEvent(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code1"));
        System.err.println("id: " + getId());
        eventSessionBean.deleteEvent(getId());
    }

    public void onCellEdit(CellEditEvent event) throws IOException {
        System.err.println("onCellEdit Now");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        System.err.println("old: " + oldValue);
        System.err.println("new: " + newValue);
        System.err.println("Selected event: " + selectedEvent);

        if (newValue != null && !newValue.equals(oldValue)) {
            selectedEvent.setStatus((String) newValue);
            System.err.println("event: " + selectedEvent.getEventName());
            eventSessionBean.updateEvent(selectedEvent);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Changed", "Previously: " + oldValue + ", Now:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("manageEvent.xhtml");
    }

    public void saveChanges(ActionEvent event) throws ExistException {
        selectedEvent.setEventManagerId(selectedManager);
        eventSessionBean.updateEvent(selectedEvent);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));
    }

    public EventSessionBean getEventSessionBean() {
        return eventSessionBean;
    }

    public void setEventSessionBean(EventSessionBean eventSessionBean) {
        this.eventSessionBean = eventSessionBean;
    }

    public EventEntity getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(EventEntity selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getEventStatus() {
        return eventStatus;
    }

    public String[] getEventStatus2() {
        return eventStatus2;
    }

    public String[] getEventStatus3() {
        return eventStatus3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<EventEntity> getPendingEvents() {
        return pendingEvents;
    }

    public List<EventEntity> getReservedEvents() {
        return reservedEvents;
    }

    public List<EventEntity> getConfirmedEvents() {
        return confirmedEvents;
    }

    public List<EventEntity> getCanceledEvents() {
        return canceledEvents;
    }

    public List<EmployeeEntity> getManagers() {
        return managers;
    }

    public void setManagers(List<EmployeeEntity> managers) {
        this.managers = managers;
    }

    public EmployeeSessionBean getEmployeeSessionBean() {
        return employeeSessionBean;
    }

    public void setEmployeeSessionBean(EmployeeSessionBean employeeSessionBean) {
        this.employeeSessionBean = employeeSessionBean;
    }

    public String getSelectedManager() {
        return selectedManager;
    }

    public void setSelectedManager(String selectedManager) {
        this.selectedManager = selectedManager;
    }
}
