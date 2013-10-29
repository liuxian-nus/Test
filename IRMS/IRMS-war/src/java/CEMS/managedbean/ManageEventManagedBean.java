/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.managedbean;

import CEMS.entity.EventEntity;
import CEMS.session.EventSessionBean;
import Exception.ExistException;
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
    private EventEntity selectedEvent;
    private final static String[] eventStatus;    
    private boolean editMode;
    private String status;
    private Long id;
    private List<EventEntity> events;
    
    public ManageEventManagedBean() {
        selectedEvent = new EventEntity();
    }
    
    @PostConstruct
    public void init() {
        events = eventSessionBean.getAllEvents();
    }
    
    static {
        eventStatus = new String[3];
        eventStatus[0] = "Pending";
        eventStatus[1] = "Researved";
        eventStatus[2] = "Confirmed";
    }
    
    public List<EventEntity> getEvents() throws ExistException {
        return events;
    }
    
    public void deleteEvent(ActionEvent event) {
        setId((Long) event.getComponent().getAttributes().get("code1"));
        eventSessionBean.deleteEvent(getId());
    }
    
    public void onCellEdit(CellEditEvent event) {
        System.err.println("onCellEdit Now");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        
        System.err.println(oldValue);
        System.err.println(newValue);
        System.err.println("Selected event: " + selectedEvent);
        
        if (newValue != null && !newValue.equals(oldValue)) {
            selectedEvent.setStatus((String) newValue);
            System.err.println("event: " + selectedEvent.getEventName());
            eventSessionBean.updateEvent(selectedEvent);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Changed", "Previously: " + oldValue + ", Now:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
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
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
